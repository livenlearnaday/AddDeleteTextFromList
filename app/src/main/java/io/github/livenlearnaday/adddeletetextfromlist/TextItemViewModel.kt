package io.github.livenlearnaday.adddeletetextfromlist

import androidx.lifecycle.ViewModel
import io.github.livenlearnaday.adddeletetextfromlist.model.TextItem

class TextItemViewModel : ViewModel() {


    fun getAllSelectedItems(allTextItems: MutableList<TextItem>):MutableList<TextItem>{
        val list = ArrayList<TextItem>()
        for (item in allTextItems) {
            if (item.isSelected) {
                list.add(item)
            }
        }
        return list.toMutableList()
    }

}