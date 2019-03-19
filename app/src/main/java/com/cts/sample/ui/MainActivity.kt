package com.cts.sample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cts.sample.R
import com.cts.sample.adapter.HeroesAdapter
import com.cts.sample.databinding.ActivityMainBinding
import com.cts.sample.model.DataModel
import com.cts.sample.viewmodel.HeroViewModel
import kotlinx.android.synthetic.main.activity_main.*

/* Main Activity class */
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroesAdapter
    private lateinit var binding: ActivityMainBinding

    private val model by lazy {
        ViewModelProviders.of(this).get<HeroViewModel>(HeroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
        }
        initBinding()
    }

    /* This function initialize the binding. */
    private fun initBinding(){
        binding.viewModel = model
        callApi()
        observeViewModel()
    }

    /* This function calls ViewModel fetchHeros method to get the data for RecyclerView. */
    fun callApi() {
        model.fetchHeros()
    }

    /* This function observe LiveData object and update the RecyclerView Adapter with the data. */
    private fun observeViewModel() {
        model.getListObservable()?.observe(this, Observer {
            if(it!!.heroList != null){
                adapter = HeroesAdapter(this@MainActivity, it) { hero : DataModel -> recyclerviewItemClicked(hero) }
                recyclerview!!.adapter = adapter
                model.isLoading.set(false)
                model.isError.set(false)

            }else{
                model.isLoading.set(false)
                model.isError.set(true)
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