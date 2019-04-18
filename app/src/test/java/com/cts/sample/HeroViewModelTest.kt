package com.cts.sample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.cts.sample.model.MarvelHero
import com.cts.sample.network.HeroRepository
import com.cts.sample.network.Result
import com.cts.sample.util.Constants
import com.cts.sample.viewmodel.HeroViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/* This is Unit Test class for ViewModel. */
class HeroViewModelTest {

    @Mock
    lateinit var heroRepository: HeroRepository

    private lateinit var heroViewModel: HeroViewModel

    @Mock
    lateinit var observer: Observer<Result>


    @Rule
    fun rule() = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        heroViewModel = HeroViewModel(heroRepository)
    }

    /* This function will test if HeroViewModel's liveData object is getting updated/ changed or not. */
    @Test
    fun testIfFetchHerosTriggersLiveData(){

        val dummyData = listOf(MarvelHero("Captain America","Steve Rogers", "Avengers", "1941",
            "Joe Simon", "DummyURL"),MarvelHero("Wolvorine","James Howlett", "X-Men", "1974",
            "Len Wein", "DummyURL1"))

        whenever(heroRepository.getHeros(any(),any()))
            .thenAnswer {
                (it.arguments[0] as ((List<MarvelHero?>?) -> Unit)?)
                    ?.invoke(dummyData)

            }


        heroViewModel.getHeroList().observeForever(observer)
        heroViewModel.fetchHeros()

       //verify(observer).onChanged(Result.ERROR(Constants.ERROR_MSG))
       verify(observer).onChanged(Result.SUCCESS(dummyData,Constants.SUCCESS_MSG))
       // assertEquals(Result.ERROR(Constants.ERROR_MSG), heroViewModel.getHeroList().value)
        assertEquals(Result.SUCCESS(dummyData,Constants.SUCCESS_MSG), heroViewModel.getHeroList().value)
    }
}