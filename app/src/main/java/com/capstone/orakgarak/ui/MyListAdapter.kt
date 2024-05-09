import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R

class MyListAdapter(private var items: List<ItemData>) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {

    data class ItemData(var title: String, var subtitle: String, var imageResId: Int)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val textViewSubtitle: TextView = view.findViewById(R.id.textViewSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.textViewTitle.text = item.title
        holder.textViewSubtitle.text = item.subtitle
    }

    override fun getItemCount() = items.size
}
