package glue.home_screen.router

import domain.router.HomeRouter
import glue.home_screen.di.annotations.HomeToTransactions
import javax.inject.Inject

class HomeRouterImpl @Inject constructor(
   @HomeToTransactions private val actionId:Int
):HomeRouter {

    override fun homeToTransactionsActionId(): Int {
        return actionId
    }
}