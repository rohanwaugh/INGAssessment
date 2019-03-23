package com.cts.sample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.util.Log
import com.cts.sample.network.DataRepository
import com.cts.sample.viewmodel.HeroViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertTrue

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
        heroViewModel.fetchHeros()
        assertTrue(heroViewModel.heroList?.value?.isEmpty()?:false)
    }
}