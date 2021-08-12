package com.testcardquiz.reviews.pokerReview

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.testcardquiz.reviews.R
import com.testcardquiz.reviews.pokerMain.PokerMain
import kotlinx.android.synthetic.main.article.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class Article : AppCompatActivity() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("Poker")
    private var dataMap: HashMap<String, String> = hashMapOf()
    private var ratingMap: HashMap<String, Int> = hashMapOf()
    private var keyReview: ArrayList<String> = arrayListOf()
    private var valueReview: ArrayList<String> = arrayListOf()
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article)

        preferences = getSharedPreferences("REVIEW", Context.MODE_PRIVATE)
        println(preferences.getString(intent.getStringExtra("ID").toString(),""))
        if(preferences.getString(intent.getStringExtra("ID").toString(),"")!!.isNotEmpty()){
            form_review.visibility = View.GONE
        }
        setDataArticle()
        setListener()
    }

    private fun setListener() {
        back_to_main.setOnClickListener {
            startActivity(Intent(this, PokerMain::class.java))
        }
        ratingBarLarge.numStars = 5
        ratingBarLarge.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingBarLarge.rating
            setRating(rating)
        }

        submit.setOnClickListener {
            if (message_review.text.isNotEmpty()) {
                checkMessageReview(message_review.text.toString())
                message_review.text = null
                message_review.hint = "Please enter your name"
            }
        }
    }

    private fun setDataArticle() {
        art_name_poker.text = intent.getStringExtra("NAME")
        description.text = intent.getStringExtra("DESCRIPTION")?.replace("\n", "\n")
        val uri = Uri.parse(intent.getStringExtra("ICON"))
        Picasso.with(this).load(uri).into(icon_review)
        art_rating_poker.text = intent.getStringExtra("RATING")
        val ratingPoker = intent.getStringExtra("RATING")?.replace(",", ".")?.toFloat()
        if (ratingPoker != null) {
            ratingBar.rating = ratingPoker
        }

        collectionReference.document(intent.getStringExtra("ID").toString()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data?.get("reviews") as Map<String, String>
                    data.forEach { (key, value) ->
                        keyReview.add(key)
                        valueReview.add(value)
                    }
                }

                reviewAdapter = ReviewAdapter(keyReview, valueReview)
                rv_reviews.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                rv_reviews.adapter = reviewAdapter
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, PokerMain::class.java))
    }

    private fun checkMessageReview(message: String) {
        preferences.edit().putString(intent.getStringExtra("ID").toString(), message).apply()

        if (intent.getStringExtra("REVIEW") != null) {
            form_review.visibility = View.GONE
        } else {
            submit.setOnClickListener {
                if (message_review.text.isNotEmpty()) {
                    val name = message_review.text.toString()
                    queryReviews(name, message)
                    message_review.text = null
                    form_review.visibility = View.GONE
                    setListener()
                } else {
                    queryReviews(message, Random.nextInt())
                    message_review.text = null
                    form_review.visibility = View.GONE
                    setListener()
                }
            }
        }

    }

    private fun queryReviews(userN: String, m: String) {
        collectionReference.document(intent.getStringExtra("ID").toString()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data?.get("reviews") as Map<String, String>
                    data.forEach { (key, value) ->
                        dataMap[key] = value
                    }
                    dataMap[userN] = m
                    collectionReference.document(intent.getStringExtra("ID").toString())
                        .update("reviews", dataMap)
                } else {
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("get failed with $exception")
            }

    }

    private fun queryReviews(m: String, intId: Int) {
        collectionReference.document(intent.getStringExtra("ID").toString()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data?.get("reviews") as Map<String, String>
                    data.forEach { (key, value) ->
                        dataMap[key] = value
                    }
                    message_review.text = null
                    dataMap[intId.toString()] = m
                    collectionReference.document(intent.getStringExtra("ID").toString())
                        .update("reviews", dataMap)
                } else {
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("get failed with $exception")
            }


    }

    private fun setRating(rating: Float) {
        collectionReference.document(intent.getStringExtra("ID").toString()).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data?.get("rating") as Map<String, Int>
                    data.forEach { (key, value) ->
                        ratingMap[key] = value
                    }
                    message_review.text = null
                    ratingMap[rating.toInt().toString()] =
                        ratingMap.getValue(rating.toInt().toString()) + 1

                    collectionReference.document(intent.getStringExtra("ID").toString())
                        .update("rating", ratingMap)
                } else {
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("get failed with $exception")
            }
    }
}

