package com.example.cricbuzz.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.R
import com.example.cricbuzz.home.adapter.MatchesListAdapter
import com.example.cricbuzz.series.adapter.SeriesListAdapter
import com.example.cricbuzz.series.adapter.SquadsListAdapter
import com.example.cricbuzz.players.adapter.PlayersListAdapter
import com.example.cricbuzz.series.model.SeriesResponse
import com.example.cricbuzz.series.view_model.SeriesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class SeriesFragment : Fragment(), SquadsListAdapter.squadsonClickListener,
    SeriesListAdapter.itemListener {

    lateinit var series_recyclerView:RecyclerView
    lateinit var matches_recyclerView:RecyclerView
    lateinit var squads_recyclerView:RecyclerView
    lateinit var squads_recyclerViewB:RecyclerView

    lateinit var seriesDetailsLL:LinearLayout
    lateinit var matchTxt:TextView
    lateinit var squadTxt:TextView
    lateinit var pointTxt:TextView
    lateinit var squadsLL:LinearLayout
    lateinit var pointsLL:LinearLayout
    lateinit var pointerLL:LinearLayout

    private var bottomSheetDialog: BottomSheetDialog? = null
    lateinit var player_recyclerView:RecyclerView
    lateinit var close:ImageView


    val seriesViewModel:SeriesViewModel = SeriesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_series, container, false)


        initView(view)

//        matchTxt.setTextColor(Color.parseColor("#ffffff"))
//        matchTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));

        bottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView = activity!!.layoutInflater.inflate(R.layout.bottom_squads_layout, null)
        bottomSheetDialog!!.setContentView(sheetView)

        close=sheetView.findViewById(R.id.close)
        player_recyclerView=sheetView.findViewById(R.id.player_recyclerView)


        close.setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }


        getSeriesListAPI()



        getMatches()

        setPlayers()

        pointerView()

        matchTxt.setOnClickListener {
           getMatches()

            matchTxt.setTextColor(Color.parseColor("#ffffff"))
            squadTxt.setTextColor(Color.parseColor("#000000"))
            pointTxt.setTextColor(Color.parseColor("#000000"))
            matchTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            squadTxt.setBackgroundTintList(null);
            pointTxt.setBackgroundTintList(null);
            pointTxt.setBackgroundResource(R.drawable.border_bg);
            squadTxt.setBackgroundResource(R.drawable.border_bg);

        }

        squadTxt.setOnClickListener {
            getSquads()
            getSquadsB()

            matchTxt.setTextColor(Color.parseColor("#000000"))
            squadTxt.setTextColor(Color.parseColor("#ffffff"))
            pointTxt.setTextColor(Color.parseColor("#000000"))
            squadTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            matchTxt.setBackgroundTintList(null);
            pointTxt.setBackgroundTintList(null);
            pointTxt.setBackgroundResource(R.drawable.border_bg);
            matchTxt.setBackgroundResource(R.drawable.border_bg);

        }

        pointTxt.setOnClickListener {
            squadsLL.visibility= View.GONE
            matches_recyclerView.visibility= View.GONE
            squads_recyclerView.visibility= View.GONE
            squads_recyclerViewB.visibility= View.GONE
            pointsLL.visibility= View.VISIBLE

            matchTxt.setTextColor(Color.parseColor("#000000"))
            squadTxt.setTextColor(Color.parseColor("#000000"))
            pointTxt.setTextColor(Color.parseColor("#ffffff"))
            pointTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            matchTxt.setBackgroundTintList(null);
            squadTxt.setBackgroundTintList(null);
            squadTxt.setBackgroundResource(R.drawable.border_bg);
            matchTxt.setBackgroundResource(R.drawable.border_bg);

        }

        return view
    }

    private fun getSeriesListAPI() {

            seriesViewModel!!.getSeriesDataList(CommonConstants.apiKey,0)
                .observe(this) { res: SeriesResponse ->

                    if (res.status.toString().equals("success")) {

                        var data = res.data

                        series_recyclerView.visibility = View.VISIBLE
                        series_recyclerView.setHasFixedSize(true)
                        series_recyclerView.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

                        series_recyclerView.adapter = SeriesListAdapter(requireContext(), data, this)
                    }

        }

    }

    private fun setPlayers() {
        player_recyclerView.visibility= View.VISIBLE
        player_recyclerView.setHasFixedSize(true)
        player_recyclerView.layoutManager = GridLayoutManager(activity,2)
        player_recyclerView.adapter = PlayersListAdapter(requireContext(), 8)
    }

    private fun getSquadsB() {
        squadsLL.visibility= View.VISIBLE
        squads_recyclerViewB.visibility= View.VISIBLE
        matches_recyclerView.visibility= View.GONE
        pointsLL.visibility= View.GONE
        squads_recyclerViewB.setHasFixedSize(true)
        squads_recyclerViewB.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        squads_recyclerViewB.adapter = SquadsListAdapter(requireContext(), 3,this)
    }

    private fun getSquads() {
        squadsLL.visibility= View.VISIBLE
        squads_recyclerView.visibility= View.VISIBLE
        matches_recyclerView.visibility= View.GONE
        pointsLL.visibility= View.GONE
        squads_recyclerView.setHasFixedSize(true)
        squads_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        squads_recyclerView.adapter = SquadsListAdapter(requireContext(), 3,this)
    }

    private fun getMatches() {
        squadsLL.visibility= View.GONE
        matches_recyclerView.visibility= View.VISIBLE
        squads_recyclerView.visibility= View.GONE
        squads_recyclerViewB.visibility= View.GONE
        pointsLL.visibility= View.GONE
        matches_recyclerView.setHasFixedSize(true)
        matches_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

        matches_recyclerView.adapter = MatchesListAdapter(requireContext(), 8)
    }

    private fun initView(view: View) {
        series_recyclerView = view.findViewById(R.id.series_recyclerView)
        matches_recyclerView = view.findViewById(R.id.matches_recyclerView)
        squads_recyclerView = view.findViewById(R.id.squads_recyclerView)
        squads_recyclerViewB = view.findViewById(R.id.squads_recyclerViewB)
        matchTxt = view.findViewById(R.id.matchTxt)
        squadTxt = view.findViewById(R.id.squadTxt)
        pointTxt = view.findViewById(R.id.pointTxt)
        squadsLL = view.findViewById(R.id.squadsLL)
        pointsLL = view.findViewById(R.id.pointsLL)
        pointerLL = view.findViewById(R.id.pointerLL)
        seriesDetailsLL = view.findViewById(R.id.seriesDetailsLL)
    }

    override fun teams(i: Int) {
        bottomSheetDialog!!.show()
    }

    override fun seriesOnclick(pos: Int) {
        seriesDetailsLL.visibility = View.VISIBLE
    }

    private fun pointerView() {


        for (i in 0 until 8) {
            var inflater: LayoutInflater =
                activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var customView: View = inflater.inflate(R.layout.pointer_layout1, null)

//                var nameTxt: TextView = customView.findViewById(R.id.nameTxt)
//                nameTxt.text = nameArray[i]

            pointerLL.addView(customView)
        }
    }
}