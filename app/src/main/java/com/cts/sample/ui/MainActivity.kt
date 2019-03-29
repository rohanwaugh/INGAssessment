package com.cts.sample.ui

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cts.sample.R
import com.cts.sample.adapter.HeroesAdapter
import com.cts.sample.databinding.ActivityMainBinding
import com.cts.sample.di.AppModule
import com.cts.sample.di.DaggerAppComponent
import com.cts.sample.model.DataModel
import com.cts.sample.network.State
import com.cts.sample.viewmodel.HeroViewModel
import javax.inject.Inject

/* Main Activity class */
class MainActivity : AppCompatActivity() {

    lateinit var heroAdapter: HeroesAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var heroViewModel : HeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder()
            .appModule( AppModule(this))
            .build()
            .inject(this)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
            heroAdapter =  HeroesAdapter(this@MainActivity) { hero : DataModel -> recyclerviewItemClicked(hero) }
            it.adapter = heroAdapter
        }
        initBinding()
        callHeroApi()

    }

    /* This function initialize the binding. */
    private fun initBinding(){
        binding.viewModel = heroViewModel
        observeViewModel()
    }

    /* This function calls ViewModel fetchHeros method to get the data for RecyclerView. */
    fun callHeroApi() {
        heroViewModel.fetchHeros()
    }

    /* This function observe LiveData object and update the RecyclerView Adapter with the data. */
    private fun observeViewModel() {

            heroViewModel.heroList.observe(this, Observer {
                it?.let {
                    when (it.state) {
                        State.SUCCESS -> {
                            heroAdapter.heroList = it.data
                            heroAdapter.notifyDataSetChanged()
                            heroViewModel.isLoading.set(false)
                            heroViewModel.isError.set(false)
                        }

                        State.ERROR -> {
                            heroViewModel.isLoading.set(false)
                            heroViewModel.isError.set(true)
                        }
                    }
                }
            })


    }

    /* This function handles RecyclerView Item click and launch new activity with details. */
    private fun recyclerviewItemClicked(hero : DataModel) {
        val intent = Intent(this, HeroDetailsActivity::class.java)
        intent.putExtra(getString(R.string.key_intent_data), hero)
        this.startActivity(intent)
    }
}