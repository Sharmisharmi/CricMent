package com.example.cricbuzz.home.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.R
import com.example.cricbuzz.home.model.CFData
import com.example.cricbuzz.home.model.UFFData
import com.example.cricbuzz.match.MatchDetails
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeAdapter(context: Context, data: List<CFData>?) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var context : Context
    var data:List<CFData>
    var editor: SharedPreferences.Editor? = null


    init {
        this.context=context
        this.data = data!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {

        editor = context.getSharedPreferences(CommonConstants.CRICKETBUZZ, AppCompatActivity.MODE_PRIVATE).edit()

        holder.matchName.text=data[position].name.toString()
        holder.statusTXT.text=data[position].status.toString()

        val ldt: LocalDateTime = LocalDateTime.parse(data[position].dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)

        holder.time.text =date_time.split("- ").toTypedArray()[1]

        when{
            data[position].matchStarted ==true && data[position].matchEnded == false -> {
                holder.liveLL.visibility = View.VISIBLE
            }
        }


        if (data[position].teamInfo != null){
            if (data[position].teamInfo!![0].img != null){
                Picasso.get().load(data[position].teamInfo!![0].img.toString()).into(holder.team1NameImage)
            }
            if (data[position].teamInfo!![1].img != null){
                Picasso.get().load(data[position].teamInfo!![1].img.toString()).into(holder.team2Image)
            }
            holder.teamName1.text= data[position].teamInfo!![0].shortname.toString()
            holder.teamName2.text= data[position].teamInfo!![1].shortname.toString()

        }else{
            holder.teamName1.text= data[position].teams!![0].toString()
            holder.teamName2.text= data[position].teams!![1].toString()

        }

        if (data[position].score != null) {
            if (data[position].score!!.size == 2) {
                if (data[position].score!![0].inning.toString()
                        .contains(data[position].teamInfo!![0].name.toString())
                ) {
                    holder.team1Score.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.team1Wicket.text = "("+data[position].score!![0].o.toString()+")"
                    holder.team2Score.text =
                        data[position].score!![1].r.toString() + "-" + data[position].score!![1].w.toString()
                    holder.team2Wicket.text = "("+data[position].score!![1].o.toString()+")"
                    holder.toBatTXT1.visibility = View.GONE
                    holder.toBatTXT2.visibility = View.GONE
                    holder.team1BBImage.visibility = View.GONE
                    holder.team2BBImage.visibility = View.GONE

                } else {
                    holder.team1Score.text =
                        data[position].score!![1].r.toString() + "-" + data[position].score!![1].w.toString()
                    holder.team1Wicket.text = "("+data[position].score!![1].o.toString()+")"
                    holder.team2Score.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.team2Wicket.text = "("+data[position].score!![0].o.toString()+")"
                    holder.toBatTXT1.visibility = View.GONE
                    holder.toBatTXT2.visibility = View.GONE
                    holder.team1BBImage.visibility = View.GONE
                    holder.team2BBImage.visibility = View.GONE
                }
            } else if (data[position].score!!.size == 1) {
                if (data[position].score!![0].inning.toString()
                        .contains(data[position].teamInfo!![0].name.toString())
                ) {
                    holder.team1Score.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.team1Wicket.text = "("+data[position].score!![0].o.toString()+")"
                    holder.team2Score.visibility = View.GONE
                    holder.team2Wicket.visibility = View.GONE
                    holder.toBatTXT1.visibility = View.GONE
                    holder.team1BBImage.visibility = View.VISIBLE
                    holder.team2BBImage.visibility = View.VISIBLE
                    holder.toBatTXT2.visibility = View.VISIBLE

                    holder.team1BBImage.setImageResource(R.drawable.cricket_bat)
                    holder.team2BBImage.setImageResource(R.drawable.cricket_ball)

                } else {
                    holder.team1Score.visibility = View.GONE
                    holder.team1Wicket.visibility = View.GONE
                    holder.team2Score.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.team2Wicket.text = "("+data[position].score!![0].o.toString()+")"
                    holder.toBatTXT1.visibility = View.VISIBLE
                    holder.team1BBImage.visibility = View.VISIBLE
                    holder.team2BBImage.visibility = View.VISIBLE
                    holder.toBatTXT2.visibility = View.GONE

                    holder.team1BBImage.setImageResource(R.drawable.cricket_ball)
                    holder.team2BBImage.setImageResource(R.drawable.cricket_bat)
                }
            } else if (data[position].score!!.size == 0) {

                holder.team1Score.visibility = View.GONE
                holder.team1Wicket.visibility = View.GONE
                holder.team2Score.visibility = View.GONE
                holder.team2Wicket.visibility = View.GONE
                holder.toBatTXT1.visibility = View.GONE
                holder.toBatTXT2.visibility = View.GONE
                holder.team1Score.visibility = View.GONE
                holder.team1Wicket.visibility = View.GONE
                holder.team2Score.visibility = View.GONE
                holder.team2Wicket.visibility = View.GONE
                holder.toBatTXT1.visibility = View.GONE
                holder.toBatTXT2.visibility = View.GONE
                holder.team1BBImage.visibility = View.GONE
                holder.team2BBImage.visibility = View.GONE
                holder.timeLL.visibility = View.VISIBLE


            }
        }

        holder.linearLayout.setOnClickListener {
            editor!!.putString(CommonConstants.ID, data[position].id.toString())
            editor!!.commit()
            editor!!.apply()
            context.startActivity(Intent(context,MatchDetails::class.java))
        }



    }


    override fun getItemCount(): Int {
        if (data.size<=6){
        return data.size
        }else{
            return 6
        }
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        var matchName : TextView = itemView.findViewById(R.id.matchName)
        var teamName1 : TextView = itemView.findViewById(R.id.teamName1)
        var teamName2 : TextView = itemView.findViewById(R.id.teamName2)
        var team1Score : TextView = itemView.findViewById(R.id.team1Score)
        var team2Score : TextView = itemView.findViewById(R.id.team2Score)
        var team1Wicket : TextView = itemView.findViewById(R.id.team1Wicket)
        var team2Wicket : TextView = itemView.findViewById(R.id.team2Wicket)
        var toBatTXT2 : TextView = itemView.findViewById(R.id.toBatTXT2)
        var toBatTXT1 : TextView = itemView.findViewById(R.id.toBatTXT1)
        var statusTXT : TextView = itemView.findViewById(R.id.statusTXT)
        var time : TextView = itemView.findViewById(R.id.time)
        var liveLL : LinearLayout = itemView.findViewById(R.id.liveLL)
        var team1NameImage : CircleImageView = itemView.findViewById(R.id.team1NameImage)
        var team2Image : CircleImageView = itemView.findViewById(R.id.team2Image)
        var team1BBImage : ImageView = itemView.findViewById(R.id.team1BBImage)
        var team2BBImage : ImageView = itemView.findViewById(R.id.team2BBImage)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)
        var timeLL : LinearLayout = itemView.findViewById(R.id.timeLL)

    }


}