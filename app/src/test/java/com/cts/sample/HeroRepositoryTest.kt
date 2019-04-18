package com.cts.sample

import com.cts.sample.model.MarvelHero
import com.cts.sample.network.HeroApi
import com.cts.sample.network.HeroRepository
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

/* This is Unit Test class for HeroRepository.*/
class HeroRepositoryTest {

    @Mock
    lateinit var heroApi: HeroApi

    @Mock
    lateinit var heroList: Call<List<MarvelHero>>

    private lateinit var heroRepository: HeroRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        heroRepository = HeroRepository(heroApi)
    }

    @Test
    fun testGetHerosSuccess() {

        whenever(heroApi.getHeros())
            .thenReturn(heroList)

        whenever(heroList
            .enqueue(any()))
            .thenAnswer {
                (it.arguments[0] as? Callback<List<MarvelHero>>)
                    ?.onResponse(heroList, Response.success(getDummyData()))
            }

        heroRepository.getHeros({list->
            assertTrue(list?.size==2)
            assertEquals(list?.get(0)?.name,"Captain America")
        },{})
    }



    private fun getDummyData():List<MarvelHero>{
        return listOf(MarvelHero("Captain America","Steve Rogers", "Avengers", "1941",
            "Joe Simon", "DummyURL"),MarvelHero("Wolvorine","James Howlett", "X-Men", "1974",
            "Len Wein", "DummyURL1"))
    }


}