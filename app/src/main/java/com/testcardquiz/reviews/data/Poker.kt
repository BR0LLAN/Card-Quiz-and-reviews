package com.testcardquiz.reviews.data

data class Poker(var id: String? = null,
                 var name: String? = null,
                 var description: String? = null,
                 val iconReview: String? = null,
                 val rating: MutableMap<String, Int>? = null,
                 val reviews: MutableMap<String, String>? = null)


