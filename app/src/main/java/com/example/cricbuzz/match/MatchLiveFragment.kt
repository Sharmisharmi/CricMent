package com.example.cricbuzz.match


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.cricbuzz.R
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.roundToInt


class MatchLiveFragment : Fragment() {


    lateinit var overLL:LinearLayout
//    lateinit var run_ll:LinearLayout
    lateinit var batter_ll:LinearLayout
    lateinit var bowler_ll:LinearLayout
    lateinit var team1Txt:TextView
    lateinit var team2Txt:TextView
    lateinit var team1score:TextView
    lateinit var team2score:TextView
    lateinit var team1over:TextView
    lateinit var team2over:TextView
    lateinit var countryTxt:TextView
    lateinit var needRunTxt:TextView
    lateinit var runsTxt:TextView
    lateinit var ballsTxt:TextView
    lateinit var ballOrRun:TextView
    lateinit var crr:TextView
    lateinit var rrr:TextView
    lateinit var team1Img:CircleImageView
    lateinit var team2Img:CircleImageView

    var oversList:ArrayList<String> = ArrayList()
    private var matchesViewModel: MatchesViewModel? = null


    var token = "ec471071441bb2ac538a0ff901abd249"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_match_live, container, false)

        overLL = view.findViewById(R.id.overLL)

        batter_ll = view.findViewById(R.id.batter_ll)
        bowler_ll = view.findViewById(R.id.bowler_ll)
        team1Txt = view.findViewById(R.id.team1Txt)
        team2Txt = view.findViewById(R.id.team2Txt)
        team1score = view.findViewById(R.id.team1score)
        team2score = view.findViewById(R.id.team2score)
        team1over = view.findViewById(R.id.team1over)
        team2over = view.findViewById(R.id.team2over)
        team1Img = view.findViewById(R.id.team1Img)
        team2Img = view.findViewById(R.id.team2Img)
        countryTxt = view.findViewById(R.id.countryTxt)
        needRunTxt = view.findViewById(R.id.needRunTxt)
        runsTxt = view.findViewById(R.id.runsTxt)
        ballsTxt = view.findViewById(R.id.ballsTxt)
        ballOrRun = view.findViewById(R.id.ballOrRun)
        crr = view.findViewById(R.id.crr)
        rrr = view.findViewById(R.id.rrr)

        matchesViewModel = MatchesViewModel()

//        getFinishedMatch()


        return  view
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

//    private fun getBatterScore(i: Int, batsmen: List<Batsman>) {
//
//
//            var inflater: LayoutInflater =
//                activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            var customView: View = inflater.inflate(
//                R.layout.batter_runs_layout, null
//            )
//
//            var runsperBall:TextView = customView.findViewById(R.id.runsperBall)
//            var batsmanName:TextView = customView.findViewById(R.id.batsmanName)
//            var foursTxt:TextView = customView.findViewById(R.id.foursTxt)
//            var sixTxt:TextView = customView.findViewById(R.id.sixTxt)
//            var strikeRate:TextView = customView.findViewById(R.id.strikeRate)
//
//
//        batsmanName.text = batsmen[i].name.toString()
//        runsperBall.text = batsmen[i].runs.toString()+"("+batsmen[i].ballsFaced.toString()+")"
//        foursTxt.text = batsmen[i].fours.toString()
//        sixTxt.text = batsmen[i].sixes.toString()
//        strikeRate.text = batsmen[i].strikeRate.toString()
////
////            runs.text = "1"
//
//            batter_ll.addView(customView)
//
//
//
//    }
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