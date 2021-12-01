package io.github.livenlearnaday.adddeletetextfromlist

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.livenlearnaday.adddeletetextfromlist.databinding.ActivityAddDeleteTextBinding
import io.github.livenlearnaday.adddeletetextfromlist.model.TextItem
import timber.log.Timber


class TextItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDeleteTextBinding

    private val mTextWatcher = TextItemTextWatcher()

    private var mTextItemList = mutableListOf<TextItem>()

    private lateinit var mTextItemAdapter: TextItemAdapter

    private val viewModel: TextItemViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDeleteTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        setupListener()
        setupTextWatchers()

    }


    private fun setupTextWatchers() {
        binding.editText.addTextChangedListener(mTextWatcher)


    }

    private fun setupListener() {
        binding.addButton.setOnClickListener {
            addNewTextItem()
        }

        binding.deleteButton.setOnClickListener {
            deleteTextItems()
        }
    }


    private fun deleteTextItems() {
        val selectedTextItemList = viewModel.getAllSelectedItems(mTextItemList)
        if(selectedTextItemList.isEmpty())
            return
        mTextItemList.removeAll(selectedTextItemList)
        mTextItemAdapter.updateItems(mTextItemList)

    }


    private inner class TextItemTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            binding.addButton.setBackgroundColor(Color.parseColor("#50e23425"))
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.editText.text.toString().isNotBlank()) {
                binding.addButton.setBackgroundColor(Color.RED)
            }
        }
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        mTextItemAdapter = TextItemAdapter(mutableListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = mTextItemAdapter
    }


    private fun addNewTextItem() {
        mTextItemList.add(TextItem(binding.editText.text.toString(),false))
        Timber.v("mTextItemList.size ::: %d", mTextItemList.size)

        mTextItemList.forEachIndexed { index, s ->
            Timber.v("mTextItemList string ::: %s", s.text)
        }

        mTextItemAdapter.updateItems(mTextItemList)
        binding.editText.text.clear()

    }

}