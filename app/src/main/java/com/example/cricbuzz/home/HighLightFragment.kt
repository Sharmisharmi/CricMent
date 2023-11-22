package com.example.cricbuzz.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.adapter.BISAdapter
import com.example.cricbuzz.home.adapter.LiveListAdapter


class HighLightFragment : Fragment() {

    lateinit var best_sport_recyclerView:RecyclerView
    lateinit var newsLayout:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_high_light, container, false)

        initView(view)

        getBestInSports()

        newsLayout.setOnClickListener {
            startActivity(Intent(activity,NewsLayout::class.java))
        }
        return view
    }

    private fun getBestInSports() {
        best_sport_recyclerView.visibility= View.VISIBLE
        best_sport_recyclerView.setHasFixedSize(true)
        best_sport_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        best_sport_recyclerView.adapter = BISAdapter(requireContext(), 8)
    }

    private fun initView(view: View) {
        best_sport_recyclerView = view.findViewById(R.id.best_sport_recyclerView)
        newsLayout = view.findViewById(R.id.newsLayout)
    }


}