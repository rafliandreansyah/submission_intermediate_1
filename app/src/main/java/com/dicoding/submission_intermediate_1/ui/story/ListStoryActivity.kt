package com.dicoding.submission_intermediate_1.ui.story

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission_intermediate_1.databinding.ActivityListStoryBinding

class ListStoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListStoryBinding
    private val storyViewModel: StoryViewModel by viewModels()
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        hideActionBar()
        initRecycleview()

        isLoading(true)
        storyViewModel.getAllStory()
        setData()

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            isLoading(true)
            storyViewModel.getAllStory()
        }
    }

    private fun hideActionBar() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setData(){
        storyViewModel.listStoryData.observe(this) { responseListStory ->
            isLoading(false)
            if (responseListStory != null) {
                if (!responseListStory.error) {
                    storyAdapter.setData(responseListStory.listStory)
                    storyAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initRecycleview() {
        storyAdapter = StoryAdapter()
        with(binding) {
            rvListStory.layoutManager = LinearLayoutManager(this@ListStoryActivity)
            rvListStory.setHasFixedSize(true)
            rvListStory.adapter = storyAdapter
        }


    }

    private fun isLoading(isL: Boolean) {
        if (isL) {
            binding.rlLoading.visibility = View.VISIBLE
        } else {
            binding.rlLoading.visibility = View.GONE
        }
    }

}