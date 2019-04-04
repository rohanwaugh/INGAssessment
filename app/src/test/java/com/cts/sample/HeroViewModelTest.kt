package com.cts.sample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.cts.sample.model.DataModel
import com.cts.sample.network.DataRepository
import com.cts.sample.network.State
import com.cts.sample.util.Constants
import com.cts.sample.viewmodel.HeroViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/* This is Unit Test class for ViewModel. */
class HeroViewModelTest {

    @Mock
    lateinit var dataRepository: DataRepository

    lateinit var heroViewModel: HeroViewModel


    @Rule
    fun rule() = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        heroViewModel = HeroViewModel(dataRepository)
    }

    @Test
    fun testFetchHerosSuccess(){

        val dummyData = listOf(DataModel("Captain America","Steve Rogers", "Avengers", "1941",
            "Joe Simon", "DummyURL"),DataModel("Wolvorine","James Howlett", "X-Men", "1974",
            "Len Wein", "DummyURL1"))

        whenever(dataRepository.getHeros(any(),any()))
            .thenAnswer {
                (it.arguments[0] as ((List<DataModel?>?) -> Unit)?)
                    ?.invoke(dummyData)

            }

        heroViewModel.fetchHeros()

        assertEquals(true,heroViewModel.getHeroList().value?.data?.isNotEmpty())
        assertEquals(State.SUCCESS, heroViewModel.getHeroList().value?.state)
        assertEquals(Constants.SUCCESS_MSG, heroViewModel.getHeroList().value?.message)
        assertEquals(2,heroViewModel.getHeroList().value?.data?.size)

    }

    @Test
    fun testFetchHerosError()
    {

        whenever(dataRepository
            .getHeros(any(), any()))
            .thenAnswer {
                (it.arguments[1] as (((String) -> Unit)?))
                    ?.invoke(Constants.ERROR_MSG)
            }

        heroViewModel.fetchHeros()

        assertEquals(null, heroViewModel.getHeroList().value?.data)
        assertEquals(State.ERROR , heroViewModel.getHeroList().value?.state)
        assertEquals(Constants.ERROR_MSG , heroViewModel.getHeroList().value?.message)

    }


}