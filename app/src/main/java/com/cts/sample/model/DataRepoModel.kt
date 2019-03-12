package com.cts.sample.model

class DataRepoModel {

    var heroList: List<DataModel>? = null
    lateinit var throwable: Throwable

    constructor(heroList: List<DataModel>) {
        this.heroList = heroList
    }

    constructor(throwable: Throwable) {
        this.throwable = throwable
    }

}