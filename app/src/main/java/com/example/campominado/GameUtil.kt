package com.example.campominado

class GameUtil {
    companion object {
        fun createRandomItemList(): List<Int> {
            val list = mutableListOf<Int>()
            for (i in 0 until 55) {
                list.add(R.drawable.facing_down)
            }
            for (i in 0 until 15) {
                list.add(R.drawable.bomb)
            }

            return list.shuffled()
        }
    }
}