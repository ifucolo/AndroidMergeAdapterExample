package com.ifucolo.mergeadapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ifucolo.mergeadapterexample.adapter.LoadStateAdapter
import com.ifucolo.mergeadapterexample.adapter.SimpleTextAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var simpleTextAdapter: SimpleTextAdapter
    private lateinit var loadStateAdapter: LoadStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        addItems()
    }

    private fun addItems() {
        val list = ArrayList<String>()
        (0..7).forEach {
            list.add("Adapter $it")
        }
        simpleTextAdapter.list = list
    }

    private fun setupRecyclerView() = with(recyclerView) {
        simpleTextAdapter = SimpleTextAdapter()
        loadStateAdapter = LoadStateAdapter()

        layoutManager = LinearLayoutManager(this@MainActivity)

        adapter = MergeAdapter(
            simpleTextAdapter,
            loadStateAdapter
        )

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                    postDelayed(
                        {
                            addItems()
                        },
                        1000
                    )
                }
            }
        })
    }
}