package com.example.cricbuzz.series.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R

class SquadsListAdapter(context: Context, i:Int, onClickListener: squadsonClickListener) : RecyclerView.Adapter<SquadsListAdapter.ViewHolder>() {

    private var context : Context
    private var i:Int
    var onClickListener: squadsonClickListener


    init {
        this.context=context
        this.i = i
        this.onClickListener = onClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.squads_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.title.text=data[position].cat_name.toString()
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//


        holder.poolLL.setOnClickListener {
            onClickListener.teams(i)
        }

    }


    override fun getItemCount(): Int {
        return i
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
//        var title : TextView = itemView.findViewById(R.id.title)
//        var image : ImageView = itemView.findViewById(R.id.images)
        var poolLL : LinearLayout = itemView.findViewById(R.id.poolLL)

    }

    interface squadsonClickListener{
        fun teams(i: Int)
    }

}