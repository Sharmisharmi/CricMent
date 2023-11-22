package com.example.cricbuzz.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R

class MatchesListAdapter  (context: Context, i:Int ) : RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    private var context : Context
    private var i:Int


    init {
        this.context=context
        this.i = i

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.matches_list,parent,false))
    }

    override fun onBindViewHolder(holder: MatchesListAdapter.ViewHolder, position: Int) {

//        holder.title.text=data[position].cat_name.toString()
//
//
//        Picasso.get().load(data[position].image_url).into(holder.image)
//
//        holder.sportsCard.setOnClickListener {
//            context.startActivity(Intent(context,CategoryDetailsActivity::class.java).putExtra("category_id",data[position].cat_id))
//        }

    }


    override fun getItemCount(): Int {
        return i
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
//        var title : TextView = itemView.findViewById(R.id.title)
//        var image : ImageView = itemView.findViewById(R.id.images)
//        var sportsCard : CardView = itemView.findViewById(R.id.sportsCard)

    }


}