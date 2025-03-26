import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookfinder.databinding.AutherListItemsBinding

class AutherListAdapter(private val autherList: List<String>): RecyclerView.Adapter<AutherListAdapter.AutherViewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutherViewholder {
        val binding = AutherListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AutherViewholder(binding)
    }

    override fun getItemCount(): Int {
        return autherList.size
    }

    override fun onBindViewHolder(holder: AutherViewholder, position: Int) {
        val autherName = autherList[position]
        holder.binding.autherName.text = autherName
    }
    inner class AutherViewholder(var binding: AutherListItemsBinding) : RecyclerView.ViewHolder(binding.root)

}