package presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.repo.CardDetailsRepository
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    cardDetailsRepository: CardDetailsRepository
): ViewModel(){

    val pagingSource = cardDetailsRepository.getTransactionsList().cachedIn(viewModelScope)

}