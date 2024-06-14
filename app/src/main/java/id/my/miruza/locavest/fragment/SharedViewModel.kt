package id.my.miruza.locavest.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _phoneNumber = MutableLiveData<String>()
    private val _address = MutableLiveData<String>()
    private val _imageUri = MutableLiveData<String>()

    val username: LiveData<String> get() = _username
    val email: LiveData<String> get() = _email
    val phoneNumber: LiveData<String> get() = _phoneNumber
    val address: LiveData<String> get() = _address
    val imageUri: LiveData<String> get() = _imageUri

    fun setUsername(newUsername: String) {
        _username.value = newUsername
    }

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setPhoneNumber(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }

    fun setAddress(newAddress: String) {
        _address.value = newAddress
    }

    fun setImageUri(newImageUri: String) {
        _imageUri.value = newImageUri
    }


}