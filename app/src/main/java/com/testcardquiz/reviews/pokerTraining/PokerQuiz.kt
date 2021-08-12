package com.testcardquiz.reviews.pokerTraining

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.testcardquiz.reviews.R
import com.testcardquiz.reviews.data.Constants
import com.testcardquiz.reviews.pokerMain.PokerMain
import kotlinx.android.synthetic.main.poker_traning.*


class PokerQuiz : AppCompatActivity() {
    private val iterator: MutableListIterator<ComboCards> =
        Constants.COMBO_CARDS_FIRST.listIterator()
    private val iterator2: MutableListIterator<ComboCards> =
        Constants.COMBO_CARDS_SECOND.listIterator()
    private val iteratorAnswers: MutableListIterator<String> = mutableListOf("A", "B","A","B","A","A").listIterator()
    private lateinit var combinationAdapter: CombinationAdapter
    private lateinit var combinationAdapter2: CombinationAdapter
    private var lastAction: String = "NONE"
    private lateinit var combo: ComboCards
    private lateinit var combo2: ComboCards
    private var answer: String = iteratorAnswers.next()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poker_traning)
        setListeners()
        combo = iterator.next()
        combo2 = iterator2.next()
        combinationAdapter = CombinationAdapter(combo.getCards())
        combinationAdapter2 = CombinationAdapter(combo2.getCards())
        first_name_combination.text = "A"
        second_name_combination.text = "B"
        setViews()
    }

    private fun setViews() {

        firstCombination.layoutManager = GridLayoutManager(this, 5)
        secondCombination.layoutManager = GridLayoutManager(this, 5)
        firstCombination.adapter = combinationAdapter
        secondCombination.adapter = combinationAdapter2
    }

    private fun setListeners() {

        mainButton.setOnClickListener {
            startActivity(Intent(this, PokerMain::class.java))
            finish()
        }
        btnA.setOnClickListener {
            if(answer == "A")  Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
        btnB.setOnClickListener {
            if(answer == "B") Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
        btnBoth.setOnClickListener {
            if(answer == "Both")  Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
        previous_combination.setOnClickListener {
            if (!iterator.hasNext()) {
                iterator.previous()
                iterator2.previous()
                iteratorAnswers.previous()
                combo = iterator.previous()
                combo2 = iterator2.previous()
                answer = iteratorAnswers.previous()
                combinationAdapter = CombinationAdapter(combo.getCards())
                combinationAdapter2 = CombinationAdapter(combo2.getCards())
                first_name_combination.text = "A"
                second_name_combination.text = "B"
                lastAction = "PREV"
                setViews()
            } else if (iterator.hasPrevious()) {
                if (lastAction == "NEXT" && iterator.hasPrevious()) {
                    iterator.previous()
                    iterator2.previous()
                    iteratorAnswers.previous()
                }
                combo = iterator.previous()
                combo2 = iterator2.previous()
                answer = iteratorAnswers.previous()
                previous_combination.visibility = View.VISIBLE
                combinationAdapter = CombinationAdapter(combo.getCards())
                combinationAdapter2 = CombinationAdapter(combo2.getCards())
                first_name_combination.text = "A"
                second_name_combination.text = "B"
                lastAction = "PREV"
                setViews()
            }
            if (!iterator.hasPrevious()) {
                previous_combination.visibility = View.GONE
            }
        }


        next_combination.setOnClickListener {
            previous_combination.visibility = View.VISIBLE
            if (!iterator.hasPrevious()) {

                iterator.next()
                iterator2.next()
                iteratorAnswers.next()
                combo = iterator.next()
                combo2 = iterator2.next()
                answer = iteratorAnswers.next()
                combinationAdapter = CombinationAdapter(combo.getCards())
                combinationAdapter2 = CombinationAdapter(combo2.getCards())
                first_name_combination.text = "A"
                second_name_combination.text = "B"
                lastAction = "NEXT"
                setViews()
            } else if (iterator.hasNext()) {
                if (lastAction == "PREV" && iterator.hasNext()) {
                    iterator.next()
                    iterator2.next()
                    iteratorAnswers.next()
                }
                combo = iterator.next()
                combo2 = iterator2.next()
                answer = iteratorAnswers.next()
                combinationAdapter = CombinationAdapter(combo.getCards())
                combinationAdapter2 = CombinationAdapter(combo2.getCards())
                first_name_combination.text = "A"
                second_name_combination.text = "B"
                lastAction = "NEXT"
                setViews()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, PokerMain::class.java))
        finish()
    }
}

