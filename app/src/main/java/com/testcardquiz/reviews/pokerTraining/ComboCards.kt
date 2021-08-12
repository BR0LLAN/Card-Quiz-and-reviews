package com.testcardquiz.reviews.pokerTraining

class ComboCards(
    private val cards: List<String>,
    private val nameCombo: String
) {

    fun getCards(): List<String> {
        return cards
    }

    fun getName(): String {
        return nameCombo
    }

}