package ru.eshtykin.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eshtykin.notesapp.model.Note
import ru.eshtykin.notesapp.utils.TYPE_FIREBASE
import ru.eshtykin.notesapp.utils.TYPE_ROOM

class MainViewModel(application: Application): AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                        Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                        Note(title = "Note 3", subtitle = "Subtitle for note 3"),
                        Note(title = "Note 4", subtitle = "Subtitle for note 4"),
                        Note(title = "Note 5", subtitle = "Subtitle for note 5")
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }

    fun initDataBase(type: String){
        dbType.value = type
        Log.d("MyLog", "MainViewModel initDataBase with type: $type")
    }
}

class MainViewModelFactory (private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw java.lang.IllegalArgumentException("Unknow ViewModel Class")
    }
}