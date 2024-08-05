package com.example.cricbuzz.match.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.R
import com.example.cricbuzz.home.model.CFData
import com.example.cricbuzz.match.MatchDetails
import com.example.cricbuzz.match.model.MatchesData
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class UpcomingListAdapter(context: Context, data: ArrayList<CFData>) : RecyclerView.Adapter<UpcomingListAdapter.ViewHolder>() {

    private var context : Context
    var data:ArrayList<CFData>
    var editor: SharedPreferences.Editor? = null



    init {
        this.context=context
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.upcoming_match_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingListAdapter.ViewHolder, position: Int) {

        editor = context.getSharedPreferences(CommonConstants.CRICKETBUZZ, AppCompatActivity.MODE_PRIVATE).edit()

        try {
                  holder.team1.text =
                      data[position].teamInfo!![0].shortname.toString()
                  holder.team2.text =
                      data[position].teamInfo!![1].shortname.toString()
              }catch (e:Exception){

              }

        holder.location.text = data[position].venue.toString()


        val ldt: LocalDateTime = LocalDateTime.parse(data[position].dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)


        holder.date.text =date_time.split("- ").toTypedArray()[0]
        holder.time.text =date_time.split("- ").toTypedArray()[1]

        if (data[position].teamInfo != null) {

            if (data[position].teamInfo!![0].img != null) {
                Picasso.get().load(data[position].teamInfo!![0].img.toString())
                    .into(holder.team1Img)
            }


            if (data[position].teamInfo!![1].img != null) {
                Picasso.get().load(data[position].teamInfo!![1].img.toString())
                    .into(holder.team2Img)
            }
        }


        holder.matchesCard.setOnClickListener {
            if (data[position].id.toString() != null) {
                editor!!.putString(CommonConstants.ID, data[position].id.toString())
                editor!!.commit()
                editor!!.apply()
                context.startActivity(Intent(context, MatchDetails::class.java))
            }
        }


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
        var matchesCard : CardView = itemView.findViewById(R.id.matchesCard)

    }


}