package com.example.cricbuzz.home.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class LiveListAdapter(context: Context, data: ArrayList<CFData>) : RecyclerView.Adapter<LiveListAdapter.ViewHolder>() {

    private var context : Context
    var data:ArrayList<CFData>

    var editor: SharedPreferences.Editor? = null

    init {
        this.context=context
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.live_match_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: LiveListAdapter.ViewHolder, position: Int) {

        editor = context.getSharedPreferences(CommonConstants.CRICKETBUZZ, AppCompatActivity.MODE_PRIVATE).edit()

        holder.seriesName.text=data[position].name.toString().split(",").toTypedArray()[0]

        if (data[position].teamInfo != null) {

            holder.team1Txt.text =
                data[position].teamInfo?.get(0)?.shortname ?: data[position].teamInfo?.get(0)?.name
            Log.d("teamInfo_size", "onBindViewHolder: "+ data[position].teamInfo!!.size)
            if (data[position].teamInfo!!.size > 1) {
//                if(data[position].teamInfo!![1].shortname.toString() != null)
                holder.team2Txt.text = data[position].teamInfo!![1]?.shortname
                    ?: data[position].teamInfo!![1].name.toString()

                if (!data[position].teamInfo!![1].img.toString().isNullOrEmpty()) {
                    Picasso.get().load(data[position].teamInfo!![1].img.toString()).into(holder.team2Img)
                }
            }
        }
        holder.tossTxt.text= data[position].status.toString()

        val ldt: LocalDateTime = LocalDateTime.parse(data[position].dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)



        holder.time.text =date_time.split("- ").toTypedArray()[1]

        if (!data[position].teamInfo!![0].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data[position].teamInfo!![0].img.toString()).into(holder.team1Img)
        }




        if (data[position].score != null) {
            if (data[position].score!!.size == 2) {
                if (data[position].score!![0].inning.toString()
                        .contains(data[position].teamInfo!![0].name.toString())
                ) {
                    holder.runperwicketTxt1.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.overTxt1.text = "("+data[position].score!![0].o.toString()+")"
                    holder.runperwicketTxt2.text =
                        data[position].score!![1].r.toString() + "-" + data[position].score!![1].w.toString()
                    holder.overTxt2.text = "("+data[position].score!![1].o.toString()+")"


                } else {
                    holder.runperwicketTxt1.text =
                        data[position].score!![1].r.toString() + "-" + data[position].score!![1].w.toString()
                    holder.overTxt1.text = "("+data[position].score!![1].o.toString()+")"
                    holder.runperwicketTxt2.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.overTxt2.text = "("+data[position].score!![0].o.toString()+")"

                }
            } else if (data[position].score!!.size == 1) {
                if (data[position].score!![0].inning.toString()
                        .contains(data[position].teamInfo!![0].name.toString())
                ) {
                    holder.runperwicketTxt1.text =
                        data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.overTxt1.text = "("+data[position].score!![0].o.toString()+")"
                    holder.runperwicketTxt2.visibility = View.GONE
                    holder.overTxt2.visibility = View.GONE
                    holder.batImg1.visibility = View.VISIBLE
                    holder.ballImg2.visibility = View.VISIBLE
                    holder.toBatTXT2.visibility = View.VISIBLE
                    holder.toBatTXT1.visibility = View.GONE
                    holder.batImg2.visibility = View.GONE
                    holder.ballImg1.visibility = View.GONE


                } else {
                    holder.runperwicketTxt1.visibility = View.GONE
                    holder.overTxt1.visibility = View.GONE
                    holder.runperwicketTxt2.text = data[position].score!![0].r.toString() + "-" + data[position].score!![0].w.toString()
                    holder.overTxt2.text = "("+data[position].score!![0].o.toString()+")"
                    holder.batImg1.visibility = View.GONE
                    holder.ballImg2.visibility = View.GONE
                    holder.batImg2.visibility = View.VISIBLE
                    holder.ballImg1.visibility = View.VISIBLE
                    holder.toBatTXT2.visibility = View.GONE
                    holder.toBatTXT1.visibility = View.VISIBLE

                }
            } else if (data[position].score!!.size == 0) {

                holder.runperwicketTxt1.visibility = View.GONE
                holder.overTxt1.visibility = View.GONE
                holder.runperwicketTxt2.visibility = View.GONE
                holder.overTxt2.visibility = View.GONE
                holder.runperwicketTxt1.visibility = View.GONE
                holder.overTxt1.visibility = View.GONE
                holder.runperwicketTxt2.visibility = View.GONE
                holder.overTxt2.visibility = View.GONE
                holder.liveLL.visibility = View.GONE
                holder.time.visibility = View.VISIBLE
                holder.timeLL.visibility = View.VISIBLE

            }
        }

        holder.liveCard.setOnClickListener {
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

        var seriesName : TextView = itemView.findViewById(R.id.seriesName)
        var team1Txt : TextView = itemView.findViewById(R.id.team1Txt)
        var team2Txt : TextView = itemView.findViewById(R.id.team2Txt)
        var team1Img : CircleImageView = itemView.findViewById(R.id.team1Img)
        var team2Img : CircleImageView = itemView.findViewById(R.id.team2Img)
        var time : TextView = itemView.findViewById(R.id.time)
        var tossTxt : TextView = itemView.findViewById(R.id.tossTxt)
        var runperwicketTxt1 : TextView = itemView.findViewById(R.id.runperwicketTxt1)
        var runperwicketTxt2 : TextView = itemView.findViewById(R.id.runperwicketTxt2)
        var overTxt1 : TextView = itemView.findViewById(R.id.overTxt1)
        var overTxt2 : TextView = itemView.findViewById(R.id.overTxt2)
        var batImg1 : ImageView = itemView.findViewById(R.id.batImg1)
        var ballImg1 : ImageView = itemView.findViewById(R.id.ballImg1)
        var batImg2 : ImageView = itemView.findViewById(R.id.batImg2)
        var ballImg2 : ImageView = itemView.findViewById(R.id.ballImg2)
        var toBatTXT2 : TextView = itemView.findViewById(R.id.toBatTXT2)
        var toBatTXT1 : TextView = itemView.findViewById(R.id.toBatTXT1)
        var liveCard : CardView = itemView.findViewById(R.id.liveCard)
        var liveLL : LinearLayout = itemView.findViewById(R.id.liveLL)
        var timeLL : LinearLayout = itemView.findViewById(R.id.timeLL)

    }


}