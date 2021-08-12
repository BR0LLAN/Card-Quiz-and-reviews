package com.testcardquiz.reviews.pokerMain

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.testcardquiz.reviews.R
import com.testcardquiz.reviews.data.Poker
import com.testcardquiz.reviews.pokerTraining.PokerQuiz
import kotlinx.android.synthetic.main.poker_mian.*

class PokerMain : AppCompatActivity() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("Poker")
    private val query: Query = collectionReference
    private lateinit var pokerAdapter: PokerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poker_mian)

        setViews()
        setListeners()
    }

    private fun setListeners() {
        btn_poker_training.setOnClickListener{
            startActivity(Intent(this, PokerQuiz::class.java))
            finish()
        }
    }

    private fun setViews(){
        val options = FirestoreRecyclerOptions.Builder<Poker>()
            .setQuery(query, Poker::class.java).build()

        pokerAdapter = PokerAdapter(options)
            recycler_poker.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recycler_poker.adapter = pokerAdapter
        pokerAdapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        pokerAdapter.stopListening()
    }
}