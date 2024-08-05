package com.example.cricbuzz.home

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
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
import com.example.cricbuzz.players.model.PlayerData
import com.example.cricbuzz.players.model.PlayerList
import com.example.cricbuzz.players.model.PlayerResponse
import com.example.cricbuzz.series.model.PointsTableData
import com.example.cricbuzz.series.model.PointsTableResponse
import com.example.cricbuzz.series.model.SeriesData
import com.example.cricbuzz.series.model.SeriesInfoRespnse
import com.example.cricbuzz.series.model.SeriesResponse
import com.example.cricbuzz.series.view_model.SeriesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SeriesFragment : Fragment(), SquadsListAdapter.squadsonClickListener,
    SeriesListAdapter.itemListener {

    lateinit var series_recyclerView:RecyclerView
    lateinit var matches_recyclerView:RecyclerView
    lateinit var squads_recyclerView:RecyclerView
    lateinit var squads_recyclerViewB:RecyclerView

    lateinit var seriesDetailsLL:LinearLayout
    lateinit var seriesLayout:LinearLayout
    lateinit var no_data_availableLL:LinearLayout
    lateinit var NALayout:LinearLayout
    lateinit var matchTxt:TextView
    lateinit var squadTxt:TextView
    lateinit var pointTxt:TextView
    lateinit var seriesNameTXt:TextView
    lateinit var durationTXT:TextView
    lateinit var totalTeamsTXT:TextView
    lateinit var matchesTXT:TextView
    lateinit var squadsLL:LinearLayout
    lateinit var pointsLL:LinearLayout
    lateinit var pointerLL:LinearLayout

    private var bottomSheetDialog: BottomSheetDialog? = null
    lateinit var player_recyclerView:RecyclerView
    lateinit var close:ImageView
    lateinit var teamName_bottomSheet:TextView
    lateinit var allTxt:TextView
    lateinit var battingTxt:TextView
    lateinit var bowlerTxt:TextView

    var seriesId = ""
    var matchId = ""

    var onClickStatus = ""

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
        teamName_bottomSheet=sheetView.findViewById(R.id.teamName_bottomSheet)
        allTxt=sheetView.findViewById(R.id.allTxt)
        battingTxt=sheetView.findViewById(R.id.battingTxt)
        bowlerTxt=sheetView.findViewById(R.id.bowlerTxt)
        player_recyclerView=sheetView.findViewById(R.id.player_recyclerView)


        close.setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }


        getSeriesListAPI()






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
            onClickStatus = "squads"
//            getSquadsB()
            getSeriesListAPI()


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

            getPointersTableAPI(seriesId)

        }

        return view
    }



    private fun getSeriesListAPI() {

            seriesViewModel!!.getSeriesDataList(CommonConstants.apiKey,0)
                .observe(this) { res: SeriesResponse ->

                    if (res.status.toString().equals("success")) {

                        var data = res.data
                        seriesId = data!![0].id.toString()





                        series_recyclerView.visibility = View.VISIBLE
                        series_recyclerView.setHasFixedSize(true)
                        series_recyclerView.layoutManager =
                            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

                        series_recyclerView.adapter = SeriesListAdapter(requireContext(), data, this)


                        getSeriesInfoAPI(seriesId)


                    }else{
                        seriesLayout.visibility = View.GONE
                        no_data_availableLL.visibility = View.VISIBLE

                    }

        }

    }
    private fun getPointersTableAPI(seriesId:String) {
        seriesViewModel!!.getPointsTableList(CommonConstants.apiKey,seriesId)
            .observe(this) { res: PointsTableResponse ->
                if (res.status.equals("success",ignoreCase = true)){
                    if (res.data!!.size>0){
                        pointerView(res.data)
                    }else {
                        NALayout.visibility = View.VISIBLE
                        pointsLL.visibility = View.GONE
                    }
                }else{
                    NALayout.visibility  = View.VISIBLE
                    pointsLL.visibility= View.GONE

                }
            }
    }

    private fun setPlayers(players: MutableList<PlayerList>, status: String) {
        var  playerList: MutableList<PlayerList>? = mutableListOf<PlayerList>()
        if (players.size>0){
            for (i in 0 until players.size){
                if (players[i].role.toString().contains(status,ignoreCase = true)){
                    playerList!!.add(players[i])
                }

            }

        }
        player_recyclerView.visibility= View.VISIBLE
        player_recyclerView.setHasFixedSize(true)
        player_recyclerView.layoutManager = GridLayoutManager(activity,2)
        player_recyclerView.adapter = PlayersListAdapter(requireContext(), playerList,status)
    }

    private fun getSquadsB() {
        squadsLL.visibility= View.VISIBLE
        squads_recyclerViewB.visibility= View.VISIBLE
        matches_recyclerView.visibility= View.GONE
        pointsLL.visibility= View.GONE
//        squads_recyclerViewB.setHasFixedSize(true)
//        squads_recyclerViewB.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
//
//        squads_recyclerViewB.adapter = SquadsListAdapter(requireContext(), 3,this)
    }

    private fun getSquads() {


        
        squadsLL.visibility= View.VISIBLE
        squads_recyclerView.visibility= View.VISIBLE
        matches_recyclerView.visibility= View.GONE
        pointsLL.visibility= View.GONE

    }

    private fun getMatches() {
        squadsLL.visibility= View.GONE
        matches_recyclerView.visibility= View.VISIBLE
        squads_recyclerView.visibility= View.GONE
        squads_recyclerViewB.visibility= View.GONE
        pointsLL.visibility= View.GONE

        getSeriesInfoAPI(seriesId)


    }

    private fun initView(view: View) {
        series_recyclerView = view.findViewById(R.id.series_recyclerView)
        matches_recyclerView = view.findViewById(R.id.matches_recyclerView)
        squads_recyclerView = view.findViewById(R.id.squads_recyclerView)
        squads_recyclerViewB = view.findViewById(R.id.squads_recyclerViewB)
        matchTxt = view.findViewById(R.id.matchTxt)
        squadTxt = view.findViewById(R.id.squadTxt)
        pointTxt = view.findViewById(R.id.pointTxt)
        seriesNameTXt = view.findViewById(R.id.seriesNameTXt)
        durationTXT = view.findViewById(R.id.durationTXT)
        totalTeamsTXT = view.findViewById(R.id.totalTeamsTXT)
        matchesTXT = view.findViewById(R.id.matchesTXT)

        squadsLL = view.findViewById(R.id.squadsLL)
        pointsLL = view.findViewById(R.id.pointsLL)
        pointerLL = view.findViewById(R.id.pointerLL)
        seriesDetailsLL = view.findViewById(R.id.seriesDetailsLL)
        seriesLayout = view.findViewById(R.id.seriesLayout)
        no_data_availableLL = view.findViewById(R.id.no_data_availableLL)
        NALayout = view.findViewById(R.id.NALayout)
    }

    override fun teams(data: PlayerData) {
        bottomSheetDialog!!.show()
        teamName_bottomSheet.text = data.teamName.toString()
        allTxt.text = "All "+"("+data.players!!.size.toString()+")"

        setPlayers(data.players!!, "All")

        battingTxt.setOnClickListener {

            setPlayers(data.players!!,"Batsman")
            allTxt.setTextColor(Color.parseColor("#000000"))
            bowlerTxt.setTextColor(Color.parseColor("#000000"))
            battingTxt.setTextColor(Color.parseColor("#ffffff"))
            battingTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            bowlerTxt.setBackgroundTintList(null);
            allTxt.setBackgroundTintList(null);
            bowlerTxt.setBackgroundResource(R.drawable.border_bg);
            allTxt.setBackgroundResource(R.drawable.border_bg);
        }
        bowlerTxt.setOnClickListener {
            setPlayers(data.players!!,"Bowler")
            allTxt.setTextColor(Color.parseColor("#000000"))
            battingTxt.setTextColor(Color.parseColor("#000000"))
            bowlerTxt.setTextColor(Color.parseColor("#ffffff"))
            bowlerTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            battingTxt.setBackgroundTintList(null);
            allTxt.setBackgroundTintList(null);
            battingTxt.setBackgroundResource(R.drawable.border_bg);
            allTxt.setBackgroundResource(R.drawable.border_bg);
        }

        allTxt.setOnClickListener {
            setPlayers(data.players!!, "All")
            battingTxt.setTextColor(Color.parseColor("#000000"))
            bowlerTxt.setTextColor(Color.parseColor("#000000"))
            allTxt.setTextColor(Color.parseColor("#ffffff"))
            allTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            bowlerTxt.setBackgroundTintList(null);
            battingTxt.setBackgroundTintList(null);
            bowlerTxt.setBackgroundResource(R.drawable.border_bg);
            battingTxt.setBackgroundResource(R.drawable.border_bg);
        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun seriesOnclick(pos: Int, data: SeriesData) {
        seriesDetailsLL.visibility = View.VISIBLE

        seriesId = data.id.toString()
        setSeriesInfoData(data)
        getSeriesInfoAPI(seriesId)


    }

    private fun getSeriesInfoAPI(id: String?) {

        seriesViewModel!!.getSeriesInfoData(CommonConstants.apiKey, id.toString())
            .observe(this) { res: SeriesInfoRespnse ->


                var data = res.data

                NALayout.visibility = View.GONE
                matches_recyclerView.visibility = View.VISIBLE
                matches_recyclerView.setHasFixedSize(true)
                matches_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)

                matches_recyclerView.adapter = MatchesListAdapter(requireContext(), data!!.matchList)

                matchId = data.info!!.id.toString()


                totalTeamsTXT.text = "Total teams : "+data.info!!.squads.toString()
                matchesTXT.text = "Matches : "+data.info!!.matches.toString()

                getPlayerListAPI(matchId)

            }

            }
    private fun getPlayerListAPI(id: String?) {

        seriesViewModel!!.getPlayersList(CommonConstants.apiKey, id.toString())
            .observe(this) { res: PlayerResponse ->

                var data = res.data
                if (data!!.size>0) {

                    NALayout.visibility = View.GONE
                    squads_recyclerView.setHasFixedSize(true)
                    squads_recyclerView.layoutManager = GridLayoutManager(activity,2)

                    squads_recyclerView.adapter = SquadsListAdapter(requireContext(), data,this)

                }else{
                    if (onClickStatus.equals("squads",ignoreCase = true)) {
                        squadsLL.visibility = View.GONE
                        NALayout.visibility = View.VISIBLE
                        matches_recyclerView.visibility = View.GONE

                    }
                }
            }

            }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun setSeriesInfoData(data: SeriesData) {

        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val formatter = SimpleDateFormat("dd MMM", Locale.ENGLISH)

        val start_dt: Date? = originalFormat.parse(data.startDate.toString())
        val star_date: String = formatter.format(start_dt)

        seriesNameTXt.text = data.name
        durationTXT.text = star_date +" - "+ data.endDate.toString()

    }

    private fun pointerView(data: List<PointsTableData>?) {


        for (i in 0 until data!!.size) {
            var inflater: LayoutInflater =
                activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var customView: View = inflater.inflate(R.layout.pointer_layout1, null)

                var countryNameTXT: TextView = customView.findViewById(R.id.countryNameTXT)
                var pointsTXT: TextView = customView.findViewById(R.id.pointsTXT)
                var winTXT: TextView = customView.findViewById(R.id.winTXT)
                var lossTXT: TextView = customView.findViewById(R.id.lossTXT)
                var tieTXT: TextView = customView.findViewById(R.id.tieTXT)
                var nrTXT: TextView = customView.findViewById(R.id.nrTXT)

            countryNameTXT.text = data[i].shortname.toString()
            pointsTXT.text = data[i].matches.toString()
            winTXT.text = data[i].wins.toString()
            lossTXT.text = data[i].loss.toString()
            tieTXT.text = data[i].ties.toString()
            nrTXT.text = data[i].nr.toString()

            pointerLL.addView(customView)
        }
    }
}