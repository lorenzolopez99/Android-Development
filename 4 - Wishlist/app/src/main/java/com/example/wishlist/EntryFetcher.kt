package com.example.wishlist

class EntryFetcher {
    companion object{
        var names = mutableListOf<String>()
        var prices = mutableListOf<Double>()
        var urls = mutableListOf<String>()
        fun getEntries(): MutableList<Entry>{
            var entries: MutableList<Entry> = ArrayList()
            if (entries.size == 0) return entries
            for (i in 0..names.size){
                val entry = Entry(names[i],prices[i],urls[i])
                entries.add(entry)
            }
            return entries
        }


    }
}