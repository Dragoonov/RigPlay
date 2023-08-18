package com.moonlightbutterfly.rigplay.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.moonlightbutterfly.rigplay.gamedetails.component.GameDetailsComponent
import com.moonlightbutterfly.rigplay.gamedetails.view.GameDetailsView
import com.moonlightbutterfly.rigplay.gamelist.component.GameListComponent
import com.moonlightbutterfly.rigplay.gamelist.view.GameListView
import com.moonlightbutterfly.rigplay.usecase.GetGameDetailsUseCase
import com.moonlightbutterfly.rigplay.usecase.GetGamesUseCase
import com.moonlightbutterfly.rigplay.utils.Consumer
import kotlin.coroutines.CoroutineContext

class RootComponent internal constructor(
    componentContext: ComponentContext,
    private val gameListView: (ComponentContext, Consumer<GameListView.Output>) -> GameListView,
    private val gameDetailsView: (ComponentContext, itemId: Int) -> GameDetailsView
) : Root, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        getGamesUseCase: GetGamesUseCase,
        getGameDetailsUseCase: GetGameDetailsUseCase,
        mainContext: CoroutineContext,
        ioContext: CoroutineContext,
    ) : this(
        componentContext = componentContext,
        gameListView = { childContext, output ->
            GameListComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output,
                getGamesUseCase = getGamesUseCase,
                mainContext = mainContext,
                ioContext = ioContext
            )
        },
        gameDetailsView = { childContext, itemId ->
            GameDetailsComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                itemId = itemId,
                getGameDetailsUseCase = getGameDetailsUseCase,
                mainContext = mainContext,
                ioContext = ioContext,
            )
        }
    )

    private val navigation = StackNavigation<Configuration>()

    private val stack =
        childStack(
            source = navigation,
            initialConfiguration = Configuration.GameList,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val childStack: Value<ChildStack<*, Root.Child>> = stack

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Root.Child =
        when (configuration) {
            is Configuration.GameList -> Root.Child.GameList(gameListView(componentContext, Consumer(::onMainOutput)))
            is Configuration.GameDetails -> Root.Child.GameDetails(gameDetailsView(componentContext, configuration.itemId))
        }

    private fun onMainOutput(output: GameListView.Output): Unit =
        when (output) {
            is GameListView.Output.GameSelected -> navigation.push(Configuration.GameDetails(itemId = output.id))
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object GameList : Configuration()

        @Parcelize
        data class GameDetails(val itemId: Int) : Configuration()
    }
}
