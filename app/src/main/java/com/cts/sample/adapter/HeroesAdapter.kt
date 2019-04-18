package com.cts.sample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.cts.sample.R
import com.cts.sample.model.MarvelHero
import kotlinx.android.synthetic.main.recyclerview_layout.view.*

/* This is Adapter class for RecyclerView. */
class HeroesAdapter(private val mCtx: Context, private val clickListener: (MarvelHero) -> Unit) : RecyclerView.Adapter<HeroesAdapter.HeroViewHolder>() {

    var heroList: List<MarvelHero>? = null


    /* This functions returns the new instance of HeroViewHolder class. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false)
        return HeroViewHolder(view)
    }


    /* This function binds data to child views of HeroViewHolder class. */
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroList?.get(position)
        Glide.with(mCtx).load(hero?.imageurl).into(holder.itemView.imageView)
        (holder).bind(heroList?.get(position), clickListener)

    }

    /* Returns the number of heros. */
    override fun getItemCount(): Int {
         this.heroList?.let {
            return it.size
        }?:return 0
    }

    /* RecyclerView ViewHolder class implementation. */
    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hero: MarvelHero?, clickListener: (MarvelHero) -> Unit) {
            itemView.textView.text = hero?.name
            itemView.setOnClickListener { hero?.let { hero->
                clickListener(hero)
            }}

        }
    }
}