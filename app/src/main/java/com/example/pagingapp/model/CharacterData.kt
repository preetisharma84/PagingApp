package com.example.pagingapp.model

data class CharacterData(val results: ArrayList<Characters>)
data class Characters(val id: Int, val name: String, val gender: String, val image: String)
