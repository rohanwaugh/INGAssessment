package com.cts.sample.network

import com.cts.sample.model.DataModel

sealed class State {

    object SUCCESS : State()
    object ERROR   : State()
}

data class Data(
    var state :State,
    var data:List<DataModel>? = null,
    var message:String? = null

)