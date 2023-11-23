package com.example.cricbuzz.home.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.series.model.MatchList
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MatchesListAdapter(context: Context, data: List<MatchList>?) : RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    private var context : Context
    private var data:List<MatchList>


    init {
        this.context=context
        this.data = data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.matches_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MatchesListAdapter.ViewHolder, position: Int) {

        holder.location.text=data[position].venue.toString()
        holder.team1.text= data[position].teamInfo!![0].shortname.toString()
        holder.team2.text= data[position].teamInfo!![1].shortname.toString()

        val ldt: LocalDateTime = LocalDateTime.parse(data[position].dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)

        holder.dateTXT.text= date_time.split("-").toTypedArray()[0]
        holder.timeTXT.text= date_time.split("- ").toTypedArray()[1]

        if (!data[position].teamInfo!![0].img.toString().isNullOrEmpty())
            Picasso.get().load(data[position].teamInfo!![0].img).into(holder.team1Image)

        if (!data[position].teamInfo!![1].img.toString().isNullOrEmpty())
            Picasso.get().load(data[position].teamInfo!![1].img).into(holder.team2Image)
//
//        holder.sportsCard.setOnClickListener {
//            context.startActivity(Intent(context,CategoryDetailsActivity::class.java).putExtra("category_id",data[position].cat_id))
//        }

    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
        var location : TextView = itemView.findViewById(R.id.location)
        var team1 : TextView = itemView.findViewById(R.id.team1)
        var team2 : TextView = itemView.findViewById(R.id.team2)
        var dateTXT : TextView = itemView.findViewById(R.id.dateTXT)
        var timeTXT : TextView = itemView.findViewById(R.id.timeTXT)
        var team1Image : ImageView = itemView.findViewById(R.id.team1Image)
        var team2Image : ImageView = itemView.findViewById(R.id.team2Image)
//        var sportsCard : CardView = itemView.findViewById(R.id.sportsCard)

    }


}