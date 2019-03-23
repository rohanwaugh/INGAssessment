package com.cts.sample

import com.cts.sample.model.DataModel
import com.cts.sample.network.API
import com.cts.sample.network.DataRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call

class DataRepositoryTest {

    @Mock
    lateinit var webService: API

    @Mock
    lateinit var heroList: Call<List<DataModel>>

    lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataRepository = DataRepository(webService)
    }

    fun testGetHerosSuccess(){
        dataRepository.getHeros(success = {},failure = {})
    }
}