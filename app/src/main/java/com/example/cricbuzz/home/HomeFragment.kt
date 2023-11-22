package com.example.cricbuzz.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.arindicatorview.ARIndicatorView
import com.example.cricbuzz.R
import com.example.cricbuzz.home.adapter.HomeAdapter
import com.example.cricbuzz.home.adapter.LatestNewsAdapter


class HomeFragment : Fragment() {


    lateinit var latest_news_recyclerView:RecyclerView
    lateinit var recycler:RecyclerView
    lateinit var arIndicatorView:ARIndicatorView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_home, container, false)

        initView(view)


        recycler.setHasFixedSize(true)

        recycler.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL, false
            )
        )

        recycler.setAdapter(HomeAdapter(requireContext(),3))


        // add pager behavior
        // add pager behavior
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(recycler)

        arIndicatorView.attachTo(recycler, true)
        arIndicatorView.numberOfIndicators = 3





        latest_news_recyclerView.visibility= View.VISIBLE
        latest_news_recyclerView.setHasFixedSize(true)
        latest_news_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        latest_news_recyclerView.adapter = LatestNewsAdapter(requireContext(), 8)

        return view
    }

    private fun initView(view: View) {
        latest_news_recyclerView =view.findViewById(R.id.latest_news_recyclerView)
        recycler =view.findViewById(R.id.recycler)
        arIndicatorView =view.findViewById(R.id.ar_indicator)
    }
}