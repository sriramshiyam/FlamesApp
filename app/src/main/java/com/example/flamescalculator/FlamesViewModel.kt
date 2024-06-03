package com.example.flamescalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlamesViewModel : ViewModel() {
    var result = MutableLiveData<String>()

    init {
        result.value = ""
    }

    fun calculate(name1: String, name2: String) {
        val nam1 = name1.lowercase().trim().toList().filter { it != ' ' }.toMutableList()
        val nam2 = name2.lowercase().trim().toList().filter { it != ' ' }.toMutableList()

        var flames =
            mutableListOf("Friendship", "Love", "Affection", "Marriage", "Enemy", "Siblings")

        var value = nam1.size + nam2.size
        println(value)

        for (letter in nam1) {
            if (letter in nam2) {
                nam1[nam1.indexOf(letter)] = '0'
                nam2.remove(letter)
            }
        }

        value = nam1.filter { it != '0' }.size + nam2.size
        println(value)

        if (value < 5) {
            result.value = "invalid"
        }

        val count = value

        var canCheck = true

        while (canCheck) {
            for (letter in flames) {
                if (letter == "0") {
                    continue
                }
                value--

                if (value == 0) {
                    flames[flames.indexOf(letter)] = "0"
                    value = count
                }

                val one = flames.count { it == "0" }

                if (one == 5) {
                    flames = flames.filter { it != "0" }.toMutableList()
                    canCheck = false
                }
            }
        }

        result.value = flames[0]
    }
}