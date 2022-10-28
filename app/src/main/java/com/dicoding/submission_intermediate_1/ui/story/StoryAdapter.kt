package com.dicoding.submission_intermediate_1.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission_intermediate_1.databinding.ItemStoryBinding
import com.dicoding.submission_intermediate_1.model.Story
import com.dicoding.submission_intermediate_1.util.changeFormatDate

class StoryAdapter: RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private lateinit var listStory: List<Story>

    fun setData(listStory: List<Story>) {
        this.listStory = listStory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bindData(listStory[position])
    }

    override fun getItemCount(): Int = if (listStory != null)  listStory.size else 0

    inner class StoryViewHolder(private val view: ItemStoryBinding): RecyclerView.ViewHolder(view.root){
        fun bindData(story: Story) {
            with(view) {
                txtDate.text = story.createdAt?.let { changeFormatDate(it) }
                txtDescription.text = story.description
                txtCreatedBy.text = story.description

                Glide.with(this.root)
                    .load(story.photoUrl)
                    .into(imgStory)
            }
        }
    }

}