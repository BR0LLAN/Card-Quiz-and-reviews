package com.testcardquiz.reviews.pokerTraining

class QuestionQuiz(
    private val firstComboCards: ComboCards,
    private val secondComboCards: ComboCards,
    private val answer: String
) {
    fun getAnswer(): String {
        return answer
    }

    fun getFirstCombo(): ComboCards {
        return firstComboCards
    }

    fun getSecondCombo(): ComboCards {
        return secondComboCards
    }


}