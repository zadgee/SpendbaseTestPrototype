package presentation.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.repository.HomeScreenRepository
import domain.router.HomeRouter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val router:HomeRouter,
    homeScreenRepository: HomeScreenRepository,
):ViewModel() {

    val cardsPagingSource = homeScreenRepository.cardPagerProvider().cachedIn(viewModelScope)
    val transactions = homeScreenRepository.transactionPagerProvider().cachedIn(viewModelScope)

    fun homeToTransactionsActionId():Int{
        return router.homeToTransactionsActionId()
    }

}