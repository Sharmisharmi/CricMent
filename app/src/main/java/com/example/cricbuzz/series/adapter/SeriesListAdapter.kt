package com.example.cricbuzz.series.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.series.model.SeriesData

class SeriesListAdapter(context: Context, data: List<SeriesData>?, listener: itemListener) : RecyclerView.Adapter<SeriesListAdapter.ViewHolder>() {

    private var context : Context
    private var data:List<SeriesData>
    var listener: itemListener


    init {
        this.context=context
        this.data = data!!
        this.listener = listener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.series_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.seriesNameTXT.text=data[position].name.toString()
        holder.seriesNameTXT.isSelected = true
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//
        holder.seriesLL.setOnClickListener {
            listener.seriesOnclick(position,data[position])
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
        var seriesNameTXT : TextView = itemView.findViewById(R.id.seriesNameTXT)
//        var image : ImageView = itemView.findViewById(R.id.images)
        var seriesLL : LinearLayout = itemView.findViewById(R.id.seriesLL)

    }

    interface itemListener{
        fun seriesOnclick(pos:Int, data: SeriesData)

    }


}