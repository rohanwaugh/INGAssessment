package com.cts.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cts.sample.R
import com.cts.sample.model.DataModel
import kotlinx.android.synthetic.main.heros_details.*

/* Details Activity Class. */
class HeroDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heros_details)

        val bundle: Bundle? = intent.extras

        bundle.apply {
            val model:DataModel? = this!!.getParcelable("hero")
            heroRealName.text = model?.realname
            heroTeam.text = model?.team
            firstAppreance.text = model?.firstappearance
            createdBy.text = model?.createdby
        }

    }
}