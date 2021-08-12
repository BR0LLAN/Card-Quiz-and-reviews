package com.testcardquiz.reviews.pokerMain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.testcardquiz.reviews.R
import com.testcardquiz.reviews.data.Poker
import com.testcardquiz.reviews.pokerReview.Article
import kotlinx.android.synthetic.main.single_item_poker.view.*

class PokerAdapter(options: FirestoreRecyclerOptions<Poker>) : FirestoreRecyclerAdapter<Poker, PokerAdapter.PokerViewHolder>(options){

    private var storageReference:StorageReference = FirebaseStorage.getInstance().reference
    private var value:Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokerViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_poker,parent,false)
        return PokerViewHolder(v)
    }

    override fun onBindViewHolder(holder: PokerViewHolder, position: Int, model: Poker) {
        holder.namePoker.text = model.name
        storageReference.child("IconPoker/${model.id}.png")
            .downloadUrl.addOnSuccessListener { uri -> Glide.with(holder.iconPoker.context)
                .load(uri.toString())  .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
               .into(holder.iconPoker) }

        if (model.rating!!.isNotEmpty()){
            value = model.rating.getValue("1")+
                    model.rating.getValue("2")+
                    model.rating.getValue("3")+
                    model.rating.getValue("4")+
                    model.rating.getValue("5")

            var ratingPoker = String.format("%.1f", (
                    5*model.rating.getValue("5")+
                    4*model.rating.getValue("4")+
                    3*model.rating.getValue("3")+
                    2*model.rating.getValue("2")+
                    1*model.rating.getValue("1"))/value.toDouble())
            .replace(".",",")

            try {
                if (value == 0) {
                    ratingPoker = "0,0"
                }
            }
            finally{

            }
            holder.ratingMain.text = ratingPoker

            holder.progress1.progress = (model.rating.getValue("1")/value.toFloat()*100).toInt()
            holder.progress2.progress = (model.rating.getValue("2")/value.toFloat()*100).toInt()
            holder.progress3.progress = (model.rating.getValue("3")/value.toFloat()*100).toInt()
            holder.progress4.progress = (model.rating.getValue("4")/value.toFloat()*100).toInt()
            holder.progress5.progress = (model.rating.getValue("5")/value.toFloat()*100).toInt()
            holder.showMoreBtn.setOnClickListener {
                val intent = Intent(holder.activity, Article::class.java)
                intent.putExtra("ID", model.id)
                intent.putExtra("NAME", model.name)
                intent.putExtra("DESCRIPTION", model.description)
                intent.putExtra("RATING", ratingPoker)
                intent.putExtra("ICON", model.iconReview)
                holder.activity.startActivity(intent)
                holder.activity.finish() }
        } else {
            holder.ratingMain.text = ""
            holder.progress1.progress = 0
            holder.progress2.progress = 0
            holder.progress3.progress = 0
            holder.progress4.progress = 0
            holder.progress5.progress = 0
        }
    }

    class PokerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val activity: AppCompatActivity = itemView.context as AppCompatActivity
        val namePoker: TextView = itemView.poker_name
        var ratingMain: TextView = itemView.rating_main
        val progress1: ProgressBar = itemView.pb1
        val progress2: ProgressBar = itemView.pb2
        val progress3: ProgressBar = itemView.pb3
        val progress4: ProgressBar = itemView.pb4
        val progress5: ProgressBar = itemView.pb5
        val iconPoker: ImageView = itemView.icon_poker
        val showMoreBtn:Button = itemView.show_more_btn
    }
}
