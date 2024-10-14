package com.example.practicanavegacion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicanavegacion.R
import com.example.practicanavegacion.model.Person


class PersonViewModel : ViewModel() {

    private val _personList = MutableLiveData<List<Person>>(emptyList())
    val personList: LiveData<List<Person>> get() = _personList

    private val _likesList = MutableLiveData<List<Person>>(emptyList())
    val likesList: LiveData<List<Person>> get() = _likesList

    init {
        _personList.value = listOf(
            Person("Dove Cameron", listOf(R.drawable.dove1, R.drawable.dove2, R.drawable.dove3)),
            Person("Nicki Nicole", listOf(R.drawable.cole2, R.drawable.cole3, R.drawable.cole1)),
            Person("Dua Lipa", listOf(R.drawable.dua1,R.drawable.dua2,R.drawable.dua3)),
            Person("Megan Fox", listOf(R.drawable.fox1,R.drawable.fox2,R.drawable.fox3)),
            Person("Gigi Hadid", listOf(R.drawable.gigi1,R.drawable.gigi3)),
            Person("Jennifer", listOf(R.drawable.jennifer3,R.drawable.jennifer1,R.drawable.jennifer2)),
            Person("Abby", listOf(R.drawable.lol1,R.drawable.lol2)),
            Person("Mictia", listOf(R.drawable.mictia1,R.drawable.mictia3)),
            Person("Young Miko", listOf(R.drawable.miko1,R.drawable.miko2,R.drawable.miko3)),
            Person("Milica", listOf(R.drawable.milica1,R.drawable.milica2,R.drawable.milica3)),
            Person("Mimi", listOf(R.drawable.mimi1,R.drawable.mimi2,R.drawable.mimi3)),
            Person("Scarlett", listOf(R.drawable.scarlett1,R.drawable.scarlett2,R.drawable.scarlett3)),
            Person("Kyedae", listOf(R.drawable.valo1,R.drawable.valo2,R.drawable.valo3)),
            Person("Emma Watson", listOf(R.drawable.watson1,R.drawable.watson3)),
            Person("Zendaya", listOf(R.drawable.zendaya1,R.drawable.zendaya2,R.drawable.zendaya3)),
        )
    }

    fun likePerson(person: Person) {
        _personList.value = _personList.value?.minus(person)
        _likesList.value = _likesList.value?.plus(person)
    }

    fun dislikePerson(person: Person) {
        _personList.value = _personList.value?.minus(person)
    }
}

