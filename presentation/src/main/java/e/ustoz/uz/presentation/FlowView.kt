package e.ustoz.uz.presentation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FlowView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onAttachGlobalNavController()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onInitializeNavController(isLoginHasBeen: Boolean)
}