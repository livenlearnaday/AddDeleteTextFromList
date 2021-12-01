package io.github.livenlearnaday.adddeletetextfromlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.livenlearnaday.adddeletetextfromlist.databinding.TextItemBinding
import io.github.livenlearnaday.adddeletetextfromlist.model.TextItem


class TextItemAdapter(
    private var mAllTextList: MutableList<TextItem>
) : RecyclerView.Adapter<TextItemAdapter.TextItemViewHolder>() {


    override fun getItemCount(): Int = mAllTextList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val binding =
            TextItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TextItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.bind(mAllTextList[position])
    }


    class TextItemViewHolder(
        private val itemBinding: TextItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var textItem: TextItem

        fun bind(item: TextItem) {
            this.textItem = item
            itemBinding.textview.text = item.text
            itemBinding.checkbox.isChecked = textItem.isSelected
            itemBinding.checkbox.setOnCheckedChangeListener { _, b ->
                textItem.isSelected = b
            }
            itemBinding.checkbox.isChecked = textItem.isSelected
        }
    }


    fun updateItems(items: List<TextItem>) {
        this.mAllTextList.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

}


