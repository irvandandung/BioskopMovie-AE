package project.irvandandung.bioskopmovie.setting_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingPageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This feature is under development"
    }
    val text: LiveData<String> = _text
}