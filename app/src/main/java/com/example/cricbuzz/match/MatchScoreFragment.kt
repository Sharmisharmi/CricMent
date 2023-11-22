package com.example.cricbuzz.match

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cricbuzz.R
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class MatchScoreFragment : Fragment() {


    lateinit var batter_ll: LinearLayout
    lateinit var bowler_ll:LinearLayout
    lateinit var team1Txt:TextView
    lateinit var team2Txt:TextView
    lateinit var team1score:TextView
    lateinit var team2score:TextView
    lateinit var team1over:TextView
    lateinit var team2over:TextView
    lateinit var team1Img:CircleImageView
    lateinit var team2Img:CircleImageView


    var matchesViewModel:MatchesViewModel? = null

    var token = "ec471071441bb2ac538a0ff901abd249"

    var nameArray:ArrayList<String> = ArrayList()
    var bowlernameArray:ArrayList<String> = ArrayList()
    var playerIdArray:ArrayList<Int> = ArrayList()
    var playerRunArray:ArrayList<Int> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_match_score, container, false)
        matchesViewModel = MatchesViewModel()

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


//        getFinishedMatch()

        return view
    }
//    private fun getFinishedMatch() {
//        matchesViewModel!!.getFinishedMatchesData(token)
//            .observe(this) { res: CompletedMatchesResponse ->
//
//                if (res.status.toString().equals("ok",ignoreCase = true)) {
//                    var data = res.response
//                    team1Txt.text = data!!.teams!![0].abbr.toString()
//                    team2Txt.text = data.teams!![1].abbr.toString()
//                    team1score.text = data.teams!![0].scores.toString()
//                    team1over.text = data.teams!![0].overs.toString()
//                    team2score.text = data.teams!![1].scores.toString()
//                    team2over.text = data.teams!![1].overs.toString()
//
//
//
//
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
//
//                    Log.d("commentry_size", "getFinishedMatch: "+ data.commentaries!!.size)
//                    if (data.commentaries!!.size>0) {
//                        for (com in 0 until data.commentaries!!.size) {
//                            if (data.commentaries!![com].bats != null ) {
//                                for (i in 0 until data.commentaries!![com].bats!!.size) {
//                                    getBatterScore(i, data.commentaries!![com].bats, data.commentaries!![com].bowls,data.players)
//                                }
//                            }
//                        }
//                    }
//
//                    batterView()
//
//                    bowlerView()
//
//                }else{
//
//                }
//
//
//            }
//    }

    private fun bowlerView() {
        if (bowlernameArray.size>0) {
            for (j in 0 until bowlernameArray.size) {

                var inflater: LayoutInflater =
                    activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var customView1: View = inflater.inflate(
                    R.layout.bowler_layout, null
                )

                var bowlerName:TextView = customView1.findViewById(R.id.bowlerName)

                bowlerName.text = bowlernameArray[j]
//
//            runs.text = "1"

                bowler_ll.addView(customView1)


            }
        }
    }

    private fun batterView() {

        if (nameArray.size>0) {
            for (i in 0 until nameArray.size) {
                var inflater: LayoutInflater =
                    activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var customView: View = inflater.inflate(
                    R.layout.batter_runs_layout1, null
                )

                var nameTxt: TextView = customView.findViewById(R.id.nameTxt)
                nameTxt.text = nameArray[i]

                batter_ll.addView(customView)
            }
        }


    }

//    private fun getBatterScore(
//        i: Int,
//        batsmen: List<Bat>?,
//        bbb: List<Bowl>?,
//        players: List<Player>?
//    ) {
////        for (i in 0 until 11) {
//
//
//        Log.d("bbb", "getBatterScore: "+ bbb!![i].bowlerId.toString())
//
//
//        if (players != null) {
//
//
//            for (p in 0 until players.size){
//                if (players[p].pid.toString().equals(batsmen!![i].batsmanId.toString())){
//                    nameArray.add(players[p].shortName.toString())
//                    playerIdArray.add(players[p].pid!!.toInt())
//
//
////                    val hm = HashMap<Int, ArrayList<Int>>()
////
////                    // Change Value using put method
////
////                    // Change Value using put method
////                    hm[players[p].pid!!.toInt()] = playerRunArray
////
////
////                    println("Initial Map $hm")
////                    Log.d("hashmap", "getBatterScore: "+hm)
////                    Log.d("runs", "getBatterScore: "+playerRun)
//
//
//
//                }else{
//                    bowlernameArray.add(players[p].shortName.toString())
//                }
//
//            }
//        }
//
//
//        if (players != null) {
//
//
//            for (p in 0 until players.size){
//                if(players[p].pid!!.toInt() == (bbb!![i].bowlerId!!.toInt())){
//
//                    bowlernameArray.add(players[p].shortName.toString())
//
//
//                }
//
//            }
//        }
//
//
//        val hashSet = HashSet<String>()
//        hashSet.addAll(nameArray)
//        nameArray.clear()
//        nameArray.addAll(hashSet)
//
//        val bowlerhashSet = HashSet<String>()
//        hashSet.addAll(bowlernameArray)
//        bowlernameArray.clear()
//        bowlernameArray.addAll(bowlerhashSet)
//
//        val playerIdHash = HashSet<Int>()
//        playerIdHash.addAll(playerIdArray)
//        playerIdArray.clear()
//        playerIdArray.addAll(playerIdHash)
//
////        for (runs in 0 until playerIdArray.size){
////            playerRunArray.add(0)
////        }
////
////        var playerRun =0
//////        if (playerIdArray.contains(batsmen!![i].batsmanId!!.toInt())){
//////            playerRun += batsmen[i].runs!!.toInt()
//////
//////        }
////
//////        var plar:ArrayList<Int>[playerIdArray.size] = ArrayList()
//////        for (r in 0 until  playerIdArray.size){
//////            playerRunArray.set(r,playerRun)
//////        }
////        for (runs in 0 until playerIdArray.size){
////
////            if (playerIdArray[runs]  == batsmen!![i].batsmanId!!.toInt()){
////                playerRun += batsmen[i].runs!!.toInt()
////                playerRunArray.set(runs,playerRun)
////            }else{
////
////                break
////            }
////
////        }
//
//        Log.d("playerRun1", "getBatterScore: "+playerRunArray)
//
//        Log.d("playerArray", "getBatterScore: "+bowlernameArray)
//
//
//
//
////
////            runs.text = "1"
//
//
//
//
////        }
//    }






}