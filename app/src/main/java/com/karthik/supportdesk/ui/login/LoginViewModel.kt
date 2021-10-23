package com.karthik.supportdesk.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karthik.supportdesk.utility.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    val alert = MutableLiveData<Event<Events.Alert>>()
    val navigate = MutableLiveData<Event<Events.Navigation>>()

    sealed class Events {

        sealed class Navigation {
            object OpenTicketScreen: Navigation()
        }

        sealed class Alert: Events() {
            class EmailInvalid(val message: String): Alert()
        }
    }

    private fun isValidUrl(url: String) = url.isNotEmpty()

    fun validateUrl(url: String) {
        if (!isValidUrl(url)) {
            alert.postValue(Event(Events.Alert.EmailInvalid("Please enter a Support Desk URL")))
        }
    }

    fun nextTapped(url: String) {
        if (!isValidUrl(url)) {
            alert.postValue(Event(Events.Alert.EmailInvalid("Please enter a Support Desk URL")))
            return
        }

        navigate.postValue(Event(Events.Navigation.OpenTicketScreen))
    }

}