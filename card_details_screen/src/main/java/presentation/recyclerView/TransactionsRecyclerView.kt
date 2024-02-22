package presentation.recyclerView
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.card_details.databinding.TransactionsMockLayoutBinding
import com.bumptech.glide.Glide
import domain.models.TransactionsMockDTO
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionsRecyclerView(
    private val view:View
):PagingDataAdapter<TransactionsMockDTO,TransactionsRecyclerView.PagedTransactionsHolderMock>(TransactionsDiffCallbackMock()){
    override fun onBindViewHolder(
        holder: TransactionsRecyclerView.PagedTransactionsHolderMock,
        position: Int
    ) {
        val transaction = getItem(position)
        transaction?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionsRecyclerView.PagedTransactionsHolderMock {
        val binding = TransactionsMockLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagedTransactionsHolderMock(binding)
    }

    inner class PagedTransactionsHolderMock(
        private val binding:TransactionsMockLayoutBinding
    ):RecyclerView.ViewHolder(binding.root){
       @SuppressLint("SetTextI18n")
       fun bind(transaction: TransactionsMockDTO){
           val card = transaction.card
           val amount = transaction.amount
           val date = dateConverter(transaction.createdAt)
           binding.amountId.text = amount.toString()
           binding.transactionDate.text = date
           when(card != null){
               true -> {
                   binding.last4CardNumbers.text = "•• ${transaction.card.last4Numbers}"
                   binding.cardHolderName.text = transaction.card.cardHolder.name
                   Glide.with(view).load(transaction.card.cardHolder.logoUrl).into(
                       binding.holderImage
                   )
               }
               false -> {
                   binding.last4CardNumbers.text = "unknown last 4 card numbers"
                   binding.cardHolderName.text = "unknown card holder name"
                   binding.holderImage.setImageResource(
                       com.app.core.R.drawable.iconquestion_name
                   )
               }
           }
           when(transaction.type){
               "Load" -> {
                   val color = ContextCompat.getColor(view.context,com.app.core.R.color.green)
                   binding.amountId.setTextColor(color)
               }
               "Refund" -> {
                   val color = ContextCompat.getColor(view.context,com.app.core.R.color.green)
                   binding.amountId.setTextColor(color)
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

class TransactionsDiffCallbackMock : DiffUtil.ItemCallback<TransactionsMockDTO>() {
    override fun areItemsTheSame(oldItem: TransactionsMockDTO, newItem: TransactionsMockDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransactionsMockDTO, newItem: TransactionsMockDTO): Boolean {
        return oldItem == newItem
    }
}

private fun dateConverter(
    date: String
):String{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val parsedDate: Date? = inputFormat.parse(date)
    return parsedDate?.let { outputFormat.format(it) } ?: "Invalid date"
}