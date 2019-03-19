package com.cts.sample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.cts.sample.R
import com.cts.sample.model.DataRepoModel
import com.cts.sample.model.DataModel
import kotlinx.android.synthetic.main.recyclerview_layout.view.*

/* This is Adapter class for RecyclerView. */
class HeroesAdapter(val mCtx: Context, var model: DataRepoModel?, val clickListener: (DataModel) -> Unit) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    private var heroList: List<DataModel>? = null

    init {
        this.model = model
        this.heroList = this.model!!.heroList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroList!![position]
        Glide.with(mCtx).load(hero.imageurl).into(holder.itemView.imageView)
        (holder).bind(heroList!![position], clickListener)

    }

    override fun getItemCount(): Int {
        return this.heroList!!.size
    }

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hero: DataModel, clickListener: (DataModel) -> Unit) {
            itemView.textView.text = hero.name
            itemView.setOnClickListener { clickListener(hero)}

        }
    }
}