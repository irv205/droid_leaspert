package com.iteneum.office.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteneum.network.DataState
import com.iteneum.office.domain.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * This class will handle the UI Logic and expose data to the composable views.
 *
 * @author Andres Ivan Medina
 */
class OfficeViewModel() : ViewModel() {

    private val _state: MutableStateFlow<OfficeStateResponse> =
        MutableStateFlow(OfficeStateResponse.Loading)
    val state: StateFlow<OfficeStateResponse> = _state

    private val _officeInfo: MutableState<Office?> = mutableStateOf(null)
    val officeInfo: Office?
        get() = _officeInfo.value

    init {
        getOfficeInformation()
    }

    internal fun getOfficeInformation() = viewModelScope.launch {
        when (officeResponse) {
            is DataState.Success -> {
                _officeInfo.value = officeResponse.data
                _state.emit(OfficeStateResponse.Success)
            }

            is DataState.Error -> {
                _state.emit(OfficeStateResponse.Error)
            }

            is DataState.Loading -> {
                _state.emit(OfficeStateResponse.Loading)
            }

            else -> Unit
        }
    }

    fun makeCall() {
        /*TODO implement intent actions to make call*/
    }

    fun sendEmail() {
        /*TODO implement intent actions to send email*/
    }
}


/**
 * This variable represents the data Mock brought by the API call.
 * @param officeInfoResponse this is the mock data exposed as list*
 *
 * @author Andres Ivan Medina
 */
val officeResponse: DataState<Office> = DataState.Success(
    Office(
        address = "4950 Gaidrew, Alpharetta, GA, 30022",
        schedule = "9AM-6PM",
        phone = "+1 452 123 4567",
        email = "contact@leaspert.com"
    )
)

/**
 * class:OfficeStateResponse  represents the states of the data
 * exposed through the list.
 *
 * @author Andres Ivan Medina
 */
sealed class OfficeStateResponse {
    object Loading : OfficeStateResponse()
    object Error : OfficeStateResponse()
    object Success : OfficeStateResponse()
}