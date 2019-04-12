package com.cts.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cts.sample.R
import com.cts.sample.model.MarvelHero
import kotlinx.android.synthetic.main.heros_details.*

/* This class will display selected Hero's details . */
class HeroDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heros_details)

        val bundle: Bundle? = intent.extras

        bundle?.getParcelable<MarvelHero>("hero")?.apply {
            heroRealName.text = this.realname
            heroTeam.text = this.team
            firstAppreance.text = this.firstappearance
            createdBy.text = this.createdby
        }

    }
}