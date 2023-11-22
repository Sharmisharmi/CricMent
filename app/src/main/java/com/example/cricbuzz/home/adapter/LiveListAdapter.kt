package com.example.cricbuzz.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.match.MatchDetails
import com.example.cricbuzz.match.model.MatchesData
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class LiveListAdapter(context: Context, data: ArrayList<MatchesData>) : RecyclerView.Adapter<LiveListAdapter.ViewHolder>() {

    private var context : Context
    var data:ArrayList<MatchesData>


    init {
        this.context=context
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.live_match_list,parent,false))
    }

    override fun onBindViewHolder(holder: LiveListAdapter.ViewHolder, position: Int) {

        holder.seriesName.text=data[position].name.toString().split(",").toTypedArray()[0]
        holder.team1Txt.text= data[position].teamInfo!![0]!!.shortname.toString()
        holder.team2Txt.text= data[position].teamInfo!![1]!!.shortname.toString()
        holder.tossTxt.text= data[position].status.toString()
//
//

        if (!data[position].teamInfo!![0].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data[position].teamInfo!![0].img.toString()).into(holder.team1Img)
        }


        if (!data[position].teamInfo!![1].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data[position].teamInfo!![1].img.toString()).into(holder.team2Img)
        }

//        if (data[position].score!!.size==2){
//
//                if (data[position].score!![1]!!.inning.toString().contains(data[position].teamInfo!![1]!!.name.toString(),ignoreCase = true)){
//                   holder.batImg2.visibility = View.VISIBLE
//                   holder.ballImg2.visibility = View.GONE
//                   holder.batImg1.visibility = View.GONE
//                   holder.ballImg1.visibility = View.VISIBLE
////                    holder.runperwicketTxt2.text = data[position].score!![1]!!.r.toString()+"-"+ data[position].score!![1]!!.w.toString()
////                    holder.runperwicketTxt2.text = data[position].score!![1]!!.o.toString()
//
//                }
////                else if (data[position].score!![1]!!.inning.toString().contains(data[position].teamInfo!![0]!!.name.toString(),ignoreCase = true)){
////                   holder.batImg2.visibility = View.GONE
////                   holder.ballImg2.visibility = View.VISIBLE
////                   holder.batImg1.visibility = View.VISIBLE
////                   holder.ballImg1.visibility = View.GONE
////                    holder.runperwicketTxt1.text = data[position].score!![1]!!.r.toString()+"-"+ data[position].score!![1]!!.w.toString()
////                    holder.overTxt1.text = data[position].score!![1]!!.o.toString()
////
////                }
//
//        }
//        else if (data[position].score!!.size==1){
//
//                if (data[position].score!![0]!!.inning.toString().contains(data[position].teamInfo!![1]!!.name.toString(),ignoreCase = true)){
//                   holder.batImg2.visibility = View.VISIBLE
//                   holder.ballImg2.visibility = View.GONE
//                   holder.batImg1.visibility = View.GONE
//                   holder.ballImg1.visibility = View.VISIBLE
//                   holder.runperwicketTxt1.visibility = View.GONE
//                    holder.runperwicketTxt2.text = data[position].score!![0]!!.r.toString()+"-"+ data[position].score!![0]!!.w.toString()
//                    holder.overTxt2.text = data[position].score!![0]!!.o.toString()
//                    holder.overTxt1.text = "Yet to bat"
//                }else if (data[position].score!![0]!!.inning.toString().contains(data[position].teamInfo!![0]!!.name.toString(),ignoreCase = true)){
//                   holder.batImg2.visibility = View.GONE
//                   holder.ballImg2.visibility = View.VISIBLE
//                   holder.batImg1.visibility = View.VISIBLE
//                   holder.ballImg1.visibility = View.GONE
//                   holder.runperwicketTxt2.visibility = View.GONE
//                    holder.runperwicketTxt1.text = data[position].score!![0]!!.r.toString()+"-"+ data[position].score!![0]!!.w.toString()
//                    holder.overTxt1.text = data[position].score!![0]!!.o.toString()
//                    holder.overTxt2.text = "Yet to bat"
//
//
//                }
//
//        }
//
        holder.liveCard.setOnClickListener {
            context.startActivity(Intent(context, MatchDetails::class.java))
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
        var tossTxt : TextView = itemView.findViewById(R.id.tossTxt)
        var runperwicketTxt1 : TextView = itemView.findViewById(R.id.runperwicketTxt1)
        var runperwicketTxt2 : TextView = itemView.findViewById(R.id.runperwicketTxt2)
        var overTxt1 : TextView = itemView.findViewById(R.id.overTxt1)
        var overTxt2 : TextView = itemView.findViewById(R.id.overTxt2)
        var batImg1 : ImageView = itemView.findViewById(R.id.batImg1)
        var ballImg1 : ImageView = itemView.findViewById(R.id.ballImg1)
        var batImg2 : ImageView = itemView.findViewById(R.id.batImg2)
        var ballImg2 : ImageView = itemView.findViewById(R.id.ballImg2)
        var liveCard : CardView = itemView.findViewById(R.id.liveCard)

    }


}