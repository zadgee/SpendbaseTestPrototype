package presentation.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.cards_list.databinding.TranscationsListBinding
import com.bumptech.glide.Glide
import domain.models.TransactionInfo



class TransactionsAdapter(
    private val view: View
):PagingDataAdapter<TransactionInfo,TransactionsAdapter.PagedTransactionsHolder>(TransactionDiffCallback()){
    override fun onBindViewHolder(
        holder: TransactionsAdapter.PagedTransactionsHolder,
        position: Int
    ) {
        val transaction = getItem(position)
        transaction?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionsAdapter.PagedTransactionsHolder {
        val binding = TranscationsListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagedTransactionsHolder(binding)
    }

    inner class PagedTransactionsHolder(
        private val binding:TranscationsListBinding
    ): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(transaction: TransactionInfo) {
            val card = transaction.card
            binding.transactionPrice.text = transaction.amount.toString()
            when(
                card != null
            ){
                true -> {
                    val cardHolderImage = card.cardHolder.logoUrl
                    val cardName = card.name
                    val last4Numbers = card.last4Numbers
                    Glide.with(view).load(cardHolderImage).into(binding.brandImage)
                    binding.cardNameId.text = cardName
                    binding.last4CardNumbers.text = last4Numbers
                }
                false -> {
                    binding.brandImage.setImageResource(
                        com.app.core.R.drawable.iconquestion_name
                    )
                    binding.cardNameId.text = "Card name was not provided"
                    binding.last4CardNumbers.text = "4 last card numbers was not provided"
                }
            }

            when(transaction.type){
                "Load" -> {
                    val color = ContextCompat.getColor(view.context,com.app.core.R.color.green)
                    binding.transactionPrice.setTextColor(color)
                }
                "Refund" -> {
                    val color = ContextCompat.getColor(view.context,com.app.core.R.color.green)
                    binding.transactionPrice.setTextColor(color)
                }
            }

            when(transaction.status){
                "Success" ->{
                    binding.transactionStatus.setImageResource(
                        com.app.core.R.drawable.successful_transaction_status
                    )
                }
            }
        }
    }
}


class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionInfo>() {
    override fun areItemsTheSame(oldItem: TransactionInfo, newItem: TransactionInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransactionInfo, newItem: TransactionInfo): Boolean {
        return oldItem == newItem
    }

}