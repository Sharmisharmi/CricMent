package com.example.cricbuzz.match.adapter

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
import com.example.cricbuzz.match.model.MatchesData
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UpcomingListAdapter(context: Context, data: ArrayList<MatchesData>) : RecyclerView.Adapter<UpcomingListAdapter.ViewHolder>() {

    private var context : Context
    var data:ArrayList<MatchesData>


    init {
        this.context=context
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upcoming_match_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingListAdapter.ViewHolder, position: Int) {

            try {
                  holder.team1.text =
                      data[position].teamInfo!![0].shortname.toString()
                  holder.team2.text =
                      data[position].teamInfo!![1].shortname.toString()
              }catch (e:Exception){

              }

        holder.location.text =
                    data[position].venue.toString()


        val ldt: LocalDateTime = LocalDateTime.parse(data[position].dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)


        holder.date.text =date_time.split("- ").toTypedArray()[0]
        holder.time.text =date_time.split("- ").toTypedArray()[1]


        if (!data[position].teamInfo!![0].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data[position].teamInfo!![0].img.toString()).into(holder.team1Img)
        }


        if (!data[position].teamInfo!![1].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data[position].teamInfo!![1].img.toString()).into(holder.team2Img)
        }


//        holder.liveCard.setOnClickListener {
//            context.startActivity(Intent(context, MatchDetails::class.java))
//        }


    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var team1 : TextView = itemView.findViewById(R.id.team1)
        var team2 : TextView = itemView.findViewById(R.id.team2)
        var date : TextView = itemView.findViewById(R.id.date)
        var time : TextView = itemView.findViewById(R.id.time)
        var location : TextView = itemView.findViewById(R.id.location)
        var team1Img : ImageView = itemView.findViewById(R.id.team1Img)
        var team2Img : ImageView = itemView.findViewById(R.id.team2Img)
//        var liveCard : CardView = itemView.findViewById(R.id.liveCard)

    }


}