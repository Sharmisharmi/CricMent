package com.example.cricbuzz.match


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentMatchLiveBinding
import com.example.cricbuzz.match.model.BallbyBallResponse
import com.example.cricbuzz.match.model.Bbb
import com.example.cricbuzz.match.model.MatchInfoData
import com.example.cricbuzz.match.model.MatchInfoReponse
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class MatchLiveFragment : Fragment() {




    private  lateinit var binding:FragmentMatchLiveBinding

    var oversList:ArrayList<String> = ArrayList()
    private var matchesViewModel: MatchesViewModel? = null


    var token = "ec471071441bb2ac538a0ff901abd249"

    var pref: SharedPreferences? = null
    var match_id = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMatchLiveBinding.inflate(inflater, container, false)



        matchesViewModel = MatchesViewModel()

//        getFinishedMatch()

        Glide.with(this).load(R.drawable.no_data_found).into(binding.noDataImg);

        pref =activity!!.getSharedPreferences(CommonConstants.CRICKETBUZZ, AppCompatActivity.MODE_PRIVATE)

        match_id = pref?.getString(CommonConstants.ID,"").toString()

        getMatchInfoAPI(match_id)




        return  binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMatchInfoAPI(matchId: String) {

        matchesViewModel!!.getMatchInfoData(CommonConstants.apiKey,matchId)
            .observe(this) { res: MatchInfoReponse ->

                if (res.status.toString().equals("success",ignoreCase = true)){

                    var data =res.data



                val ldt: LocalDateTime = LocalDateTime.parse(data!!.dateTimeGMT.toString())
                val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH)
                val date_time: String = ldt.format(formatter)
                println(date_time)


                if (data.matchStarted == false && data.matchEnded == false){
                    binding.liveMatchLayout.visibility = View.GONE
                    binding.finishedLayout.visibility = View.GONE
                    binding.battingStatsInnings1.visibility = View.GONE
                    binding.bowlingStatsInnings1.visibility = View.GONE
                    binding.upcomingMatchesLayout.visibility = View.VISIBLE
                   binding.NALayout.visibility = View.VISIBLE
                }else if (data.matchStarted == true && data.matchEnded == false){
                    binding.liveMatchLayout.visibility = View.VISIBLE
                    binding.battingStatsInnings1.visibility = View.VISIBLE
                    binding.bowlingStatsInnings1.visibility = View.VISIBLE
                    binding.upcomingMatchesLayout.visibility = View.GONE
                    binding.finishedLayout.visibility = View.GONE
                    binding.NALayout.visibility = View.GONE
                }else{
                    binding.upcomingMatchesLayout.visibility = View.GONE
                    binding.NALayout.visibility = View.GONE
                    binding.liveMatchLayout.visibility = View.GONE
                    binding.finishedLayout.visibility = View.VISIBLE
                    binding.battingStatsInnings1.visibility = View.VISIBLE
                    binding.bowlingStatsInnings1.visibility = View.VISIBLE
                    binding.NALayout.visibility = View.VISIBLE
//                    if (data.bbbEnabled == true){
//                        getBBBData(data.id)
//                    }
                }


                if(data.teamInfo != null){

                    binding.team1Txt.text = data.teamInfo!![0].shortname.toString()
                    binding.team2Txt.text = data.teamInfo!![1].shortname.toString()
                    binding.team1TxtUC.text = data.teamInfo!![0].shortname.toString()
                    binding.team2TxtUC.text = data.teamInfo!![1].shortname.toString()
                    binding.team1TxtFC.text = data.teamInfo!![0].shortname.toString()
                    binding.team2TxtFC.text = data.teamInfo!![1].shortname.toString()



                    if (data.teamInfo!![0].img != null) {
                        Picasso.get().load(data.teamInfo!![0].img.toString()).into(binding.team1Img)
                        Picasso.get().load(data.teamInfo!![0].img.toString()).into(binding.team1ImgUC)
                        Picasso.get().load(data.teamInfo!![0].img.toString()).into(binding.team1ImgFC)
                    }

                    if (data.teamInfo!![1].img != null) {
                        Picasso.get().load(data.teamInfo!![1].img.toString()).into(binding.team2Img)
                        Picasso.get().load(data.teamInfo!![1].img.toString()).into(binding.team2ImgUC)
                        Picasso.get().load(data.teamInfo!![1].img.toString()).into(binding.team2ImgFC)
                    }
                }else{
                    binding.team1Txt.text = data!!.teams!![0]
                    binding.team2Txt.text = data.teams!![1]
                    binding.team1TxtUC.text = data.teams!![0].toString()
                    binding.team2TxtUC.text = data.teams!![1].toString()
                    binding.team1TxtFC.text = data.teams!![0].toString()
                    binding.team2TxtFC.text = data.teams!![1].toString()
                    binding.team1over.visibility = View.GONE
                    binding.team1score.visibility = View.GONE
                    binding.team2over.visibility = View.GONE
                    binding.team2score.visibility = View.GONE
                    binding.targetTXT.visibility = View.GONE
                    binding.runsDetailsRL.visibility = View.GONE
                    binding.battingStatsInnings1.visibility = View.GONE
                    binding.bowlingStatsInnings1.visibility = View.GONE
                }
                    
                  setScoreDetails(data)

                binding.matchNameTXTUC.text = data.name.toString()
                binding.dateUC.text = date_time.toString()
                binding.dateForNA.text = date_time.toString()
                binding.locationUC.text = data.venue.toString()

            }else{
                binding.noDataAvailableLL.visibility =View.VISIBLE
                    Glide.with(activity!!).load(R.drawable.no_data_found).into(binding.noDataImg)
                    binding.finishedLayout.visibility = View.GONE
                    binding.upcomingMatchesLayout.visibility = View.GONE
                    binding.liveMatchLayout.visibility = View.GONE
                }
            }

    }

    private fun getBBBData(id: String?) {
        matchesViewModel!!.getBBBData(CommonConstants.apiKey, id.toString())
            .observe(this) { res: BallbyBallResponse ->

                var batsmen = ArrayList<String>()
                if (res.status.toString().equals("success",ignoreCase = true)){
                    if (res.data!!.bbb!!.size>0){
                        val mapOfList = res.data.bbb!!.groupBy (
                                keySelector = { it.batsman!!.name },
                                valueTransform = { res.data.bbb },)

                        Log.d("mopOfList", "getBBBData: "+mapOfList.toString())
                        for (i in 0 until res.data.bbb!!.size) {
                            if (res.data.bbb[i].inning!!.toInt()==0) {
                                var data = res.data.bbb


                                batsmen.add(res.data.bbb[i].batsman!!.name.toString())


                                var inflater: LayoutInflater =
                                    activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                var customView: View = inflater.inflate(
                                    R.layout.batter_runs_layout, null
                                )

                                var inflater2: LayoutInflater =
                                    activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                                var customView2: View = inflater2.inflate(
                                    R.layout.batter_runs_layout, null
                                )

                                var runsperBall:TextView = customView.findViewById(R.id.runsperBall)
                                var batsmanName:TextView = customView.findViewById(R.id.batsmanName)



                                batsmanName.text = data[i].batsman!!.name.toString()
                                runsperBall.text = data[i].runs.toString() +"-"+data[i].over





                                var runsperBall2:TextView = customView2.findViewById(R.id.runsperBall)
                                var batsmanName2:TextView = customView2.findViewById(R.id.batsmanName)



                                batsmanName2.text = data[i].bowler!!.name.toString()
                                runsperBall2.text = data[i].runs.toString() +"-"+data[i].over


//            runs.text = "1"

                                binding.batterLl.addView(customView)
                                binding.bowlingLlInning1.addView(customView2)
                            }
                        }

                    }
                }

            }
    }

    private fun setScoreDetails(data: MatchInfoData) {
        if (data.score != null) {
            if (data.score!!.size == 2) {
                if (data.score!![0].inning.toString()
                        .contains(data.teamInfo!![0].name.toString())
                ) {
                    binding.team1score.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team1over.text = "("+data.score!![0].o.toString()+")"
                    binding.team2score.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.team2over.text = "("+data.score!![1].o.toString()+")"
                    binding.team1scoreFC.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team1overFC.text = "("+data.score!![0].o.toString()+")"
                    binding.team2scoreFC.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.team2overFC.text = "("+data.score!![1].o.toString()+")"


                } else {
                    binding.team1score.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.team1over.text = "("+data.score!![1].o.toString()+")"
                    binding.team2score.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team2over.text = "("+data.score!![0].o.toString()+")"
                    binding.team1scoreFC.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.team1overFC.text = "("+data.score!![1].o.toString()+")"
                    binding.team2scoreFC.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team2overFC.text = "("+data.score!![0].o.toString()+")"

                }
            } else if (data.score!!.size == 1) {
                if (data.score!![0].inning.toString()
                        .contains(data.teamInfo!![0].name.toString())
                ) {
                    binding.team1score.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team1over.text = "("+data.score!![0].o.toString()+")"
                    binding.team2score.visibility = View.GONE
                    binding.team2over.visibility = View.GONE
                    binding.team1scoreFC.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team1overFC.text = "("+data.score!![0].o.toString()+")"
                    binding.team2scoreFC.visibility = View.GONE
                    binding.team2overFC.visibility = View.GONE

                } else {
                    binding.team1score.visibility = View.GONE
                    binding.team1over.visibility = View.GONE
                    binding.team2score.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.team2over.text = "("+data.score!![0].o.toString()+")"



                }
            } else if (data.score!!.size == 0) {

                binding.team1score.visibility = View.GONE
                binding.team1over.visibility = View.GONE
                binding.team2score.visibility = View.GONE
                binding.team2over.visibility = View.GONE
                binding.team1scoreFC.visibility = View.GONE
                binding.team1overFC.visibility = View.GONE
                binding.team2scoreFC.visibility = View.GONE
                binding.team2overFC.visibility = View.GONE

            }
        }
        if (data.status.toString().contains("won", ignoreCase = true)) {
            binding.countryTxtFC.text =
                data.status.toString().split(" by", ignoreCase = true).toTypedArray()[0]
            binding.byRunsTxt.text =
                data.status.toString().split("won ", ignoreCase = true).toTypedArray()[1]
        } else if (data.status.toString().contains("win", ignoreCase = true)) {
            binding.countryTxtFC.text =
                data.status.toString().split(" by", ignoreCase = true).toTypedArray()[0]
            binding.byRunsTxt.text =
                data.status.toString().split("win ", ignoreCase = true).toTypedArray()[1]
        } else {
            binding.countryTxtFC.visibility = View.GONE
            binding.byRunsTxt.text = data.status.toString()
        }

    }


//    private fun getFinishedMatch() {
//        matchesViewModel!!.getFinishedMatchesData(token)
//            .observe(this) { res: CompletedMatchesResponse ->
//
//                if (res.status.toString().equals("ok",ignoreCase = true)) {
//                    var data = res.response
//                  team1Txt.text = data!!.teams!![0].abbr.toString()
//                    team2Txt.text = data.teams!![1].abbr.toString()
//                    team1score.text = data.teams!![0].scores.toString()
//                    team1over.text = data.teams!![0].overs.toString()
//                    team2score.text = data.teams!![1].scores.toString()
//                    team2over.text = data.teams!![1].overs.toString()
//                    countryTxt.text = data.statusNote.toString().split(" by").toTypedArray()[0]
//                    needRunTxt.visibility = View.GONE
//                    runsTxt.text = data.statusNote.toString().split("won").toTypedArray()[1]
//                    runsTxt.setTextColor(Color.parseColor("#5DCF61"))
//                    countryTxt.setTextColor(Color.parseColor("#5DCF61"))
//                    countryTxt.setTextSize(12f)
//                    runsTxt.setTextSize(12f)
//
//                    countryTxt!!.typeface = ResourcesCompat.getFont(context!!, com.example.cricbuzz.R.font.poppins_medium)
//                    runsTxt!!.typeface = ResourcesCompat.getFont(context!!, com.example.cricbuzz.R.font.poppins_medium)
//
//                    ballsTxt.visibility=View.GONE
//                    ballOrRun.visibility=View.GONE
//                    crr.visibility=View.GONE
//                    rrr.visibility=View.GONE
//
//                    Log.d("overs_count", "getFinishedMatch: "+data.liveScore!!.overs!!.toFloat().roundToInt().toString())
//
//                    for (i in 0 until data.liveScore!!.overs!!.toFloat().roundToInt().toString().toInt()+1){
//
//                        var inflater: LayoutInflater =
//                            activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//                        var customView: View = inflater.inflate(
//                            R.layout.overs_layout_layout, null
//                        )
//
//                        var overNumber:TextView = customView.findViewById(R.id.overNumber)
//                        var run_ll:LinearLayout = customView.findViewById(R.id.run_ll)
//
//                        overNumber.text = "Over "+i.toString()
//                        for (runs in 0 until data.commentaries!!.size) {
//
//                            if (i == data.commentaries!![runs].over!!.toInt() && !data.commentaries!![runs].score!!.toString().contains("/") ) {
//
//                                addrun(data.commentaries!![runs].score!!.toString(), run_ll)
//                            }
//                        }
//
//                            overLL.addView(customView)
//
//                    }
//
//                    if (!data.teams!![0].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![0].logoUrl.toString()).into(team1Img)
//                    }
//
//
//                    if (!data.teams!![1].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![1].logoUrl.toString()).into(team2Img)
//                    }
//
//                    for (i in 0 until data.batsmen!!.size){
//                        getBatterScore(i,data.batsmen!!)
//                    }
//
//                    for (i in 0 until data.bowlers!!.size){
//                        getBowlerScore(i,data.bowlers!!)
//                    }
//
//                }else{
//
//                }
//
//
//            }
//    }

    private fun addrun(toInt: String, run_ll: LinearLayout) {
        var inflater: LayoutInflater =
            activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var customView: View = inflater.inflate(
            R.layout.runs_layout, null
        )

        var runs:TextView = customView.findViewById(R.id.runs)

        runs.text=toInt.toString()

        if (toInt.toString().equals("4")){
            runs.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary1));
        }
        when{
            toInt.equals("w",ignoreCase = true) ->{
                runs.text = "W"
                runs.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.red));
                runs.setTextColor(Color.parseColor("#ffffff"))
            }
        }

            run_ll.addView(customView)
    }

    private fun getBatterScore(data: List<Bbb>) {






    }
//    private fun getBowlerScore(i: Int, bowlers: List<Bowler>) {
//        for (i in 0 until 1) {
//
//            var inflater: LayoutInflater =
//                activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            var customView: View = inflater.inflate(
//                R.layout.bowler_layout, null
//            )
//
//            var bowlerName:TextView = customView.findViewById(R.id.bowlerName)
//            var runsOver:TextView = customView.findViewById(R.id.runsOver)
//            var over:TextView = customView.findViewById(R.id.over)
//            var economy:TextView = customView.findViewById(R.id.economy)
//
//            bowlerName.text = bowlers[i].name.toString()
//            runsOver.text = bowlers[i].runsConceded.toString()+"-"+ bowlers[i].wickets.toString()
//            over.text = bowlers[i].overs.toString()
//            economy.text = bowlers[i].econ.toString()
////
////            runs.text = "1"
//
//            bowler_ll.addView(customView)
//
//
//        }
//    }

}