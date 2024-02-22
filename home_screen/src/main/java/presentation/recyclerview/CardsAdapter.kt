package presentation.recyclerview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.cards_list.databinding.PagedListLayoutBinding
import com.bumptech.glide.Glide
import domain.models.CreditCardInfo

class CardsAdapter(
    private val view:View,
    private val homeToTransactionsActionId:Int
):PagingDataAdapter<CreditCardInfo,CardsAdapter.PagedCardsHolder>(CardDiffCallback()) {


    override fun onBindViewHolder(holder: PagedCardsHolder, position: Int) {
        val card = getItem(position)
        card?.let { holder.bind(it) }
    }

    inner class PagedCardsHolder(
        private val binding:PagedListLayoutBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(card: CreditCardInfo) {
            binding.last4CardNumbers.text = card.last4Numbers
            binding.cardName.text = card.name
            Glide.with(view).load(card.cardHolder.logoUrl).into(binding.cardImage)
            binding.nextButton.setOnClickListener {
                view.findNavController().navigate(homeToTransactionsActionId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedCardsHolder {
        val binding = PagedListLayoutBinding.inflate(LayoutInflater.from(
            parent.context
        ), parent, false)
        return PagedCardsHolder(binding)
    }

}

class CardDiffCallback : DiffUtil.ItemCallback<CreditCardInfo>() {
    override fun areItemsTheSame(oldItem: CreditCardInfo, newItem: CreditCardInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CreditCardInfo, newItem: CreditCardInfo): Boolean {
        return oldItem == newItem
    }
}