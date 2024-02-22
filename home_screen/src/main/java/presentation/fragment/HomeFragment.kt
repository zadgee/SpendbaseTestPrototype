package presentation.fragment

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.cards_list.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import presentation.recyclerview.CardsAdapter
import presentation.recyclerview.TransactionsAdapter
import presentation.viewModel.HomeViewModel
import utils.toastToBeDeveloped

@AndroidEntryPoint
class HomeFragment : Fragment() {
private var binding: FragmentHomeBinding?=null
private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardsAdapter = CardsAdapter(
             view = view,
             homeToTransactionsActionId = viewModel.homeToTransactionsActionId()
        )
        val transactionAdapter = TransactionsAdapter(
            view = view,
        )
        binding?.myCardsRecView?.apply{
            adapter = cardsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        binding?.recentTransactionsRecView?.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        binding?.seeAllCards?.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding?.seeAllTransactions?.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding?.seeAllTransactions?.setOnClickListener{
            toastToBeDeveloped(view.context)
        }
        lifecycleScope.launch {
          viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
              viewModel.cardsPagingSource.collect{ pagingData->
                  cardsAdapter.submitData(pagingData)
                  viewModel.transactions.collect{ pagedListData->
                      transactionAdapter.submitData(pagedListData)
                  }
              }
          }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}