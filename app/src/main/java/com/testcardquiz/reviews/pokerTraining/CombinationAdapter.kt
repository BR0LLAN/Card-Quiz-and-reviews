package com.testcardquiz.reviews.pokerTraining

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.testcardquiz.reviews.R
import kotlinx.android.synthetic.main.single_item_combo.view.*

class CombinationAdapter(private val firstCombo: List<String>) :
    RecyclerView.Adapter<CombinationVH>() {

    private lateinit var idCombo: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinationVH {
        return CombinationVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_item_combo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CombinationVH, position: Int) {
        idCombo = firstCombo[position]
        val resourceID = holder.context.resources.getIdentifier(idCombo,
            "drawable", holder.context.packageName)

        holder.logo.setImageResource(resourceID)
    }

    override fun getItemCount(): Int {
        return firstCombo.size
    }

}

class CombinationVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context = itemView.context
    val logo: ImageView = itemView.item_list
}
