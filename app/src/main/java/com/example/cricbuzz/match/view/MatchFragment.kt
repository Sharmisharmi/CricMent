package com.example.cricbuzz.match.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.R
import com.example.cricbuzz.home.adapter.LiveListAdapter
import com.example.cricbuzz.home.model.CFData
import com.example.cricbuzz.home.model.CFResponse
import com.example.cricbuzz.home.viewmodel.HomeViewModel
import com.example.cricbuzz.match.adapter.FinishedListAdapter
import com.example.cricbuzz.match.adapter.UpcomingListAdapter
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MatchFragment : Fragment() {


    lateinit var live_recyclerView:RecyclerView
    lateinit var finished_recyclerView:RecyclerView
    lateinit var upcoming_recyclerView:RecyclerView
    lateinit var no_data_availableLL:LinearLayout
    lateinit var no_data:ImageView

    lateinit var finishedTxt:TextView
    lateinit var liveTxt:TextView
    lateinit var upcomingTxt:TextView



    // for static
    lateinit var upcomingLL:LinearLayout
    lateinit var liveCard:CardView


    var upcoming_data = ArrayList<CFData>()
    var finished_data = ArrayList<CFData>()
    var live_data = ArrayList<CFData>()



    var token = "ec471071441bb2ac538a0ff901abd249"
//    var apiKey = "3a207f19-8a19-424e-87eb-c503383a23d4"  // kaviya
//    var apiKey = "ae7b0420-bacd-430d-ba08-c4398d66f56b" // mine
//    var apiKey = "0b9a94d0-740c-4145-b7e0-935b515c9584" // for morning


    // RAPID API
//    var apiKey = "d8686e86cdmshee0bb392831c2ffp169d40jsn635e8060975c"
//    var apiHost = "cricbuzz-cricket.p.rapidapi.com"

    private var matchesViewModel: MatchesViewModel? = null
    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_match, container, false)

        matchesViewModel = MatchesViewModel()
        homeViewModel = HomeViewModel()
        initView(view)

        Glide.with(this).load(R.drawable.no_data_found).into(no_data);

        getUpcomigMatch("Live")

        liveTxt.setOnClickListener {

//            startUpdates()

            upcomingLL.visibility =View.GONE
            finished_recyclerView.visibility = View.GONE
            liveCard.visibility = View.VISIBLE
            liveTxt.setTextColor(Color.parseColor("#1877F2"))
            finishedTxt.setTextColor(Color.parseColor("#ffffff"))
            upcomingTxt.setTextColor(Color.parseColor("#ffffff"))
            liveTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.white));
            finishedTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            upcomingTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            getUpcomigMatch("Live")
        }

        upcomingTxt.setOnClickListener {
            upcomingLL.visibility =View.VISIBLE
            finished_recyclerView.visibility = View.GONE
            liveCard.visibility = View.GONE
            upcomingTxt.setTextColor(Color.parseColor("#1877F2"))
            liveTxt.setTextColor(Color.parseColor("#ffffff"))
            finishedTxt.setTextColor(Color.parseColor("#ffffff"))
            upcomingTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.white));
            liveTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            finishedTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            getUpcomigMatch("Upcoming")

        }


        finishedTxt.setOnClickListener {
            upcomingLL.visibility =View.GONE
            finished_recyclerView.visibility = View.VISIBLE
            liveCard.visibility = View.GONE
            finishedTxt.setTextColor(Color.parseColor("#1877F2"))
            liveTxt.setTextColor(Color.parseColor("#ffffff"))
            upcomingTxt.setTextColor(Color.parseColor("#ffffff"))
            finishedTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.white));
            liveTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            upcomingTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.transparent));
            getUpcomigMatch("Finished")
        }
        return view
    }

    fun startUpdates() {
//        val lifecycle = this // in Activity
        val lifecycle = viewLifecycleOwner // in Fragment

        lifecycle.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // this block is automatically executed when moving into
                // the started state, and cancelled when stopping.
                while (true) {
                   // getLiveMatch() // the function to repeat
                    delay(60000)
                }
            }
        }
    }

    private fun getUpcomigMatch(onClick: String) {
        homeViewModel!!.getMatchesList(CommonConstants.apiKey)
            .observe(this) { res: CFResponse ->

                upcoming_data.clear()
                finished_data.clear()
                live_data.clear()

                if (res.status.toString().equals("success",ignoreCase = true)) {
                    no_data_availableLL.visibility = View.GONE


                    for (i in 0 until res.data!!.size){
                        if (res.data[i].matchStarted == false && res.data[i].matchEnded == false){
                            upcoming_data.add(res.data[i])

                        }else if (res.data[i].matchStarted == true && res.data[i].matchEnded == true){
                            finished_data.add(res.data[i])
                        }else if (res.data[i].matchStarted == true && res.data[i].matchEnded == false){
                            live_data.add(res.data[i])
                        }
                    }

                    Log.d("Upcoming_data", "getUpcomigMatch: "+upcoming_data.size)
                    if (live_data.size>0) {

                        live_recyclerView.visibility = View.VISIBLE
                        finished_recyclerView.visibility = View.GONE
                        upcoming_recyclerView.visibility = View.GONE

                        live_recyclerView.setHasFixedSize(true)
                        live_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        live_recyclerView.adapter = LiveListAdapter(requireContext(), live_data)
                    }else{
                        live_recyclerView.visibility = View.GONE
                        finished_recyclerView.visibility = View.GONE
                        upcoming_recyclerView.visibility = View.GONE
                        no_data_availableLL.visibility = View.VISIBLE
                    }

                    if (onClick.equals("Upcoming",ignoreCase = true)){
                        upcoming_recyclerView.visibility = View.VISIBLE
                        finished_recyclerView.visibility = View.GONE
                        live_recyclerView.visibility = View.GONE
                        upcoming_recyclerView.setHasFixedSize(true)
                        upcoming_recyclerView.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        upcoming_recyclerView.adapter = UpcomingListAdapter(
                            requireContext(),
                            upcoming_data as ArrayList<CFData>
                        )
                    }else if (onClick.equals("Finished",ignoreCase = true)){
                        finished_recyclerView.visibility = View.VISIBLE
                        live_recyclerView.visibility = View.GONE
                        upcoming_recyclerView.visibility = View.GONE
                        finished_recyclerView.setHasFixedSize(true)
                        finished_recyclerView.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        finished_recyclerView.adapter = FinishedListAdapter(
                            requireContext(),
                            finished_data as ArrayList<CFData>
                        )
                    }else if (onClick.equals("Live",ignoreCase = true)){

                        live_recyclerView.visibility = View.VISIBLE
                        finished_recyclerView.visibility = View.GONE
                        upcoming_recyclerView.visibility = View.GONE

                        live_recyclerView.setHasFixedSize(true)
                        live_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                        live_recyclerView.adapter = LiveListAdapter(requireContext(), live_data)

                    }


                }else{
                    live_recyclerView.visibility = View.GONE
                    finished_recyclerView.visibility = View.GONE
                    upcoming_recyclerView.visibility = View.GONE
                    no_data_availableLL.visibility = View.VISIBLE
                }


            }
    }


    private fun initView(view: View) {

        live_recyclerView = view.findViewById(R.id.live_recyclerView)
        finished_recyclerView = view.findViewById(R.id.finished_recyclerView)
        upcoming_recyclerView = view.findViewById(R.id.upcoming_recyclerView)
        no_data_availableLL = view.findViewById(R.id.no_data_availableLL)
        no_data = view.findViewById(R.id.no_data)
        finishedTxt = view.findViewById(R.id.finishedTxt)
        upcomingTxt = view.findViewById(R.id.upcomingTxt)
        liveTxt = view.findViewById(R.id.liveTxt)


        //static
        upcomingLL = view.findViewById(R.id.upcomingLL)
        liveCard = view.findViewById(R.id.liveCard)

    }


}