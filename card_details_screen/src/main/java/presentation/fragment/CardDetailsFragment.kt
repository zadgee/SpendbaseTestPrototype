package presentation.fragment

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
import com.app.card_details.databinding.FragmentCardDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import presentation.recyclerView.TransactionsRecyclerView
import presentation.viewModel.CardDetailsViewModel

@AndroidEntryPoint
class CardDetailsFragment : Fragment() {
private var binding: FragmentCardDetailsBinding?=null
private val viewModel by viewModels<CardDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardDetailsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transactionsAdapter = TransactionsRecyclerView(
            view = view
        )
        binding?.transactionsRecyclerView?.apply {
            adapter = transactionsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.pagingSource.collect{ pagingData->
                    transactionsAdapter.submitData(pagingData)
            }
        }
    }
}
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}