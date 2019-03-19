package com.cts.sample.model

/* This is wrapper class around Live Data object to handle error message. */
class DataRepoModel {

    var heroList: List<DataModel>? = null
    lateinit var throwable: Throwable

    constructor(heroList: List<DataModel>?) {
        this.heroList = heroList
    }

    constructor(throwable: Throwable) {
        this.throwable = throwable
    }

}