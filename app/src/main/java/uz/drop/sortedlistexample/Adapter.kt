package uz.drop.sortedlistexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class Adapter() : RecyclerView.Adapter<Adapter.VH>() {

    var editListener: SingleBlock? = null

    private val adapterCallback = object : SortedListAdapterCallback<Person>(this) {
        override fun areItemsTheSame(item1: Person, item2: Person): Boolean {
            return item1.name == item2.name
        }

        override fun compare(o1: Person, o2: Person): Int = o1.age.compareTo(o2.age)

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    private val callback = object : SortedList.Callback<Person>() {
        override fun areItemsTheSame(item1: Person, item2: Person): Boolean {
            return item1.name == item2.name
        }


        override fun compare(o1: Person, o2: Person): Int = o1.age.compareTo(o2.age)

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

    }
    private val sortedList = SortedList(Person::class.java, adapterCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount(): Int = sortedList.size()

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    fun setOnEditClickListener(block: SingleBlock) {
        editListener = block
    }

    inner class VH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        init {
            itemView.setOnLongClickListener {
                val popupMenu = PopupMenu(it.context, it)
                popupMenu.inflate(R.menu.menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit -> {
                            editListener?.invoke(sortedList[adapterPosition])
                        }
                        else -> {
                            sortedList.removeItemAt(adapterPosition)
                        }
                    }
                    true
                }
                true
            }
        }

        fun bind() {
            val data = sortedList[adapterPosition]
            name.text = data.name
            age.text = data.age.toString()
        }
    }

    fun insert(person: Person) {
        sortedList.add(person)
    }

    fun remove(person: Person) {
        sortedList.remove(person)
    }

    fun update(person: Person) {
        val i = sortedList.indexOf(person)
        sortedList.updateItemAt(i, person)

    }

    fun submitList(list: List<Person>) {
        sortedList.beginBatchedUpdates()
        sortedList.addAll(list)
        sortedList.endBatchedUpdates()
    }


}