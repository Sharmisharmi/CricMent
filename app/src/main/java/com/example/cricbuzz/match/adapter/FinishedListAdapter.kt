package com.example.cricbuzz.match.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.match.MatchDetails
import com.example.cricbuzz.match.model.MatchesData
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FinishedListAdapter(context: Context, data: ArrayList<MatchesData>) : RecyclerView.Adapter<FinishedListAdapter.ViewHolder>() {

    private var context : Context
    var data:ArrayList<MatchesData>


    init {
        this.context=context
        this.data = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishedListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.finished_match_list,parent,false))
    }

    override fun onBindViewHolder(holder: FinishedListAdapter.ViewHolder, position: Int) {



        holder.team1.text = data[position].teamInfo!![0].shortname.toString()
        holder.team2.text = data[position].teamInfo!![1].shortname.toString()


        var team1Innings = data[position].score!![0].inning.toString().replace(" Inning 1","")
        var team2Innings = data[position].score!![1].inning.toString().replace(" Inning 1","")

        var condition1 = team1Innings.trim().equals(data[position].teamInfo!![0].name.toString())
        var condition2 = team1Innings.trim().equals(data[position].teamInfo!![1].name.toString())
        var condition3 = team2Innings.trim().equals(data[position].teamInfo!![0].name.toString())
        var condition4 = team2Innings.trim().equals(data[position].teamInfo!![1].name.toString())

        if (condition1){
            holder.team1score.text = data[position].score!![0].r.toString() +" - "+data[position].score!![0].w.toString()
            holder.team1over.text = data[position].score!![0].o.toString()
            holder.team2score.text = data[position].score!![1].r.toString() +" - "+data[position].score!![1].w.toString()
            holder.team2over.text = data[position].score!![1].o.toString()

        }else if (condition3){
            holder.team1score.text = data[position].score!![1].r.toString() +" - "+data[position].score!![1].w.toString()
            holder.team1over.text = data[position].score!![1].o.toString()
            holder.team2score.text = data[position].score!![0].r.toString() +" - "+data[position].score!![0].w.toString()
            holder.team2over.text = data[position].score!![0].o.toString()
        }



        if (!data[position].teamInfo!![0].img.toString().isNullOrEmpty()) {
                Picasso.get().load(data[position].teamInfo!![0].img.toString()).into(holder.team1Img)
            }


            if (!data[position].teamInfo!![1].img.toString().isNullOrEmpty()) {
                Picasso.get().load(data[position].teamInfo!![1].img.toString()).into(holder.team2mg)
            }

//                    if (data[position].status.toString().contains("won",ignoreCase = true)){
//                holder.countryTxt.text = data.statusNote.toString().split(" by").toTypedArray()[0]
//                holder.byRunsTxt.text = data.statusNote.toString().split("won ").toTypedArray()[1]
//            }


//        if (!data[position].status.toString().equals("Match not started",ignoreCase = true)) {
//            if ((data[position].t1.toString().contains("[")||data[position].t1.toString().contains("]")) && (data[position].t2.toString().contains("[")||data[position].t2.toString().contains("]") )){
//                holder.team1.text =
//                    data[position].t1.toString().split(" [").toTypedArray()[0]
//                holder.team2.text =
//                    data[position].t2.toString().split(" [").toTypedArray()[0]
//            }else{
//                holder.team1.text =
//                    data[position].t1.toString()
//                holder.team2.text =
//                    data[position].t2.toString()
//            }
//
//
//            if(!data[position].t1s.toString().isNullOrEmpty() && !data[position].t2s.toString().isNullOrEmpty()){
//                holder.team1score.text = data[position].t1s.toString().split(" ").toTypedArray()[0]
//                holder.team2score.text = data[position].t2s.toString().split(" ").toTypedArray()[0]
//                holder.team1over.text = data[position].t1s.toString().split(" ").toTypedArray()[1]
//                holder.team2over.text = data[position].t2s.toString().split(" ").toTypedArray()[1]
//            }else if(!data[position].t1s.toString().isNullOrEmpty()) {
//                holder.team1score.text = data[position].t1s.toString().split(" ").toTypedArray()[0]
//                holder.team1over.text = data[position].t1s.toString().split(" ").toTypedArray()[1]
//
//                holder.team2over.text = "0"
//                holder.team2score.text = "0"
//            }else{
//                holder.team2score.text = data[position].t2s.toString().split(" ").toTypedArray()[0]
//                holder.team2over.text = data[position].t2s.toString().split(" ").toTypedArray()[1]
//
//                holder.team1over.text = "0"
//                holder.team1score.text = "0"
//            }
//
//
            if (data[position].status.toString().contains("won",ignoreCase = true)){
                holder.countryTxt.text = data[position].status.toString().split(" by").toTypedArray()[0]
                holder.byRunsTxt.text = data[position].status.toString().split("won ").toTypedArray()[1]
            }
//
//            if (!data[position].t1img.toString().isNullOrEmpty()) {
//                Picasso.get().load(data[position].t1img).into(holder.team1Img)
//            }
//
//
//            if (!data[position].t2img.toString().isNullOrEmpty()) {
//                Picasso.get().load(data[position].t2img).into(holder.team2mg)
//            }
//
//        }

//
//

//
        holder.finishedCard.setOnClickListener {
            context.startActivity(Intent(context, MatchDetails::class.java))
        }

//        holder.news_desc.setSelected(true);
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

                var team1 : TextView = itemView.findViewById(R.id.team1finished)
                var team2 : TextView = itemView.findViewById(R.id.team2finished)
                var team1score : TextView = itemView.findViewById(R.id.team1score)
                var team2score : TextView = itemView.findViewById(R.id.team2score)
                var team1over : TextView = itemView.findViewById(R.id.team1over)
                var team2over : TextView = itemView.findViewById(R.id.team2over)
                var countryTxt : TextView = itemView.findViewById(R.id.countryTxt)
                var byRunsTxt : TextView = itemView.findViewById(R.id.byRunsTxt)
        var team2mg : CircleImageView = itemView.findViewById(R.id.team2mg)
        var team1Img : CircleImageView = itemView.findViewById(R.id.team1Img)
        var finishedCard : CardView = itemView.findViewById(R.id.finishedCard)

    }


}