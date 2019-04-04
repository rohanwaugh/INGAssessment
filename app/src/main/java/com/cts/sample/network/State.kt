package com.cts.sample.network

import com.cts.sample.model.DataModel

/* This is Sealed class for handling Success and Error scenarios. */
sealed class State {

    object SUCCESS : State()
    object ERROR   : State()
}

data class Data(
    var state :State,
    var data:List<DataModel>? = null,
    var message:String? = null

)