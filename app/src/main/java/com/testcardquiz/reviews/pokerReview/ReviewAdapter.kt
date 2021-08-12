package com.testcardquiz.reviews.pokerReview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.testcardquiz.reviews.R
import kotlinx.android.synthetic.main.single_item_reviews.view.*

class ReviewAdapter(private val keyReview: ArrayList<String>, private val valueReview: ArrayList<String>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsVH {
        return ReviewsVH(LayoutInflater.from(parent.context).inflate(R.layout.single_item_reviews,parent,false))
    }

    override fun onBindViewHolder(holder: ReviewsVH, position: Int) {
        try { keyReview[position].toInt()
            holder.author.text = ""
        }catch (e: NumberFormatException){
            holder.author.text = keyReview[position] }
        holder.reviewU.text = valueReview[position]
    }

    override fun getItemCount(): Int {
        return keyReview.size
    }

    class ReviewsVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val reviewU: TextView = itemView.review
        val author: TextView  = itemView.author
    }
}