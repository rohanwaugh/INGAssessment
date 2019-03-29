package com.cts.sample

import com.cts.sample.model.DataModel
import com.cts.sample.network.HeroApi
import com.cts.sample.network.DataRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepositoryTest {

    @Mock
    lateinit var heroApi: HeroApi

    @Mock
    lateinit var heroList: Call<List<DataModel>>

    lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataRepository = DataRepository(heroApi)
    }

    @Test
    fun testGetHerosSuccess() {

        whenever(heroApi.getHeros())
            .thenReturn(heroList)

        whenever(heroList
            .enqueue(any()))
            .thenAnswer {
                (it.arguments[0] as? Callback<List<DataModel>>)
                    ?.onResponse(heroList, Response.success(getFakePreviewList()))
            }

        dataRepository.getHeros({
            assertTrue(it?.size==2)
            assertEquals(it?.get(0)?.name,"Captain America")
        },{})
    }



    fun getFakePreviewList():List<DataModel>{
        return listOf(DataModel("Captain America","Steve Rogers", "Avengers", "1941",
            "Joe Simon", "DummyURL"),DataModel("Wolvorine","James Howlett", "X-Men", "1974",
            "Len Wein", "DummyURL1"))
    }


}