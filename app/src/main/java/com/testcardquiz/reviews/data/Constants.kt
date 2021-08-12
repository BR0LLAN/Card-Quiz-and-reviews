package com.testcardquiz.reviews.data

import com.testcardquiz.reviews.pokerTraining.ComboCards

object Constants {
    val COMBO_CARDS_FIRST: MutableList<ComboCards> = mutableListOf(
        ComboCards(listOf("pk", "p3", "tk", "pa", "chk"), "Set"),
        ComboCards(listOf("p2", "t3", "t5", "b4", "t6"), "Straight"),
        ComboCards(listOf("cha", "ta", "t5", "ch5", "p3"), "Two pairs"),
        ComboCards(listOf("b5", "p3", "ch9", "pa", "ch9"), "Pair"),
        ComboCards(listOf("p10", "ch10", "t5", "p4", "ch5"), "Two pairs"),
        ComboCards(listOf("p7", "ch7", "ta", "t7", "b7"), "Four of a kind"),
    )
    val COMBO_CARDS_SECOND: MutableList<ComboCards> = mutableListOf(
        ComboCards(listOf("ba", "bk", "ta", "chj", "ch5"), "Pair"),
        ComboCards(listOf("pq", "p4", "pk", "p6", "p3"), "Flush"),
        ComboCards(listOf("chk", "t9", "tq", "pq", "bk"), "Two pairs"),
        ComboCards(listOf("ba", "pk", "chj", "pq", "ch10"), "Straight"),
        ComboCards(listOf("pa", "chk", "ta", "b10", "t9"), "Pair"),
        ComboCards(listOf("pj", "ch6", "tj", "chj", "b6"), "Full house"),
    )


}

