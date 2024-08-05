package com.example.cricbuzz.match

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.databinding.FragmentMatchInfoBinding
import com.example.cricbuzz.match.model.MatchInfoData
import com.example.cricbuzz.match.model.MatchInfoReponse
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class MatchInfoFragment : Fragment() {

    private lateinit var binding: FragmentMatchInfoBinding

    private var matchesViewModel: MatchesViewModel? = null
    var token = "ec471071441bb2ac538a0ff901abd249"

    var match_id = ""

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMatchInfoBinding.inflate(inflater, container, false)

        matchesViewModel = MatchesViewModel()

        editor = activity!!.getSharedPreferences(
            CommonConstants.CRICKETBUZZ,
            AppCompatActivity.MODE_PRIVATE
        ).edit()
        pref = activity!!.getSharedPreferences(
            CommonConstants.CRICKETBUZZ,
            AppCompatActivity.MODE_PRIVATE
        )

        match_id = pref?.getString(CommonConstants.ID, "").toString()

//        getFinishedMatch()
        getMatchInfo(match_id)

        return binding!!.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMatchInfo(matchId: String) {

        matchesViewModel!!.getMatchInfoData(CommonConstants.apiKey, matchId)
            .observe(this) { res: MatchInfoReponse ->

                var data = res.data


                if (data!!.matchStarted == true && data.matchEnded == false) {
                    binding.liveCard.visibility = View.VISIBLE
                    binding.finishedLayout.visibility = View.GONE
                    binding.upcomingCard.visibility = View.GONE
                    liveMatchDataSet(data)
                } else if (data!!.matchStarted == true && data.matchEnded == true) {
                    binding.liveCard.visibility = View.GONE
                    binding.upcomingCard.visibility = View.GONE
                    binding.finishedLayout.visibility = View.VISIBLE
                } else {
                    binding.liveCard.visibility = View.GONE
                    binding.upcomingCard.visibility = View.VISIBLE
                    binding.finishedLayout.visibility = View.GONE
                    upComingMatchDataSet(data)
                }
                binding!!.matchNameTXT.text = data!!.name.toString()
                binding!!.seriesNameTXT.text = data!!.name.toString()
                binding!!.matchStatusTXT.text = data!!.status.toString()
                binding!!.matchTypeTXT.text = data!!.matchType.toString()
                binding!!.tossTXT.text = data!!.tossWinner.toString()
                binding!!.tossChoiceTXT.text = data!!.tossChoice.toString()
//                binding.tossTxt.text= data.status.toString()


                val ldt: LocalDateTime = LocalDateTime.parse(data.dateTimeGMT.toString())
                val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH)
                val date_time: String = ldt.format(formatter)
                println(date_time)

                binding!!.date.text = date_time
                binding!!.location.text = data.venue


                binding!!.team1Txt.text = data.teams!![0]
                binding!!.team2Txt.text = data.teams!![1]

                if (data.teamInfo != null) {

                    binding!!.team1Txt.text = data.teamInfo!![0].shortname.toString()
                    if (data.teamInfo!!.size > 1) {
                        binding!!.team2Txt.text = data.teamInfo!![1].shortname ?: data.teamInfo!![1].name

                        if (data.teamInfo!![1].img != null)
                            Picasso.get().load(data.teamInfo!![1].img.toString())
                                .into(binding!!.team2Img)
                    }

                    if (data.matchStarted == false && data.matchEnded == false) {
                        binding!!.team1Over.visibility = View.GONE
                        binding!!.team1Score.visibility = View.GONE
                        binding!!.team2Over.visibility = View.GONE
                        binding!!.team2Score.visibility = View.GONE
                    }

                    if (data.teamInfo!![0].img != null)
                        Picasso.get().load(data.teamInfo!![0].img.toString())
                            .into(binding!!.team1Img)

                } else {
                    binding!!.team1Over.visibility = View.GONE
                    binding!!.team1Score.visibility = View.GONE
                    binding!!.team2Over.visibility = View.GONE
                    binding!!.team2Score.visibility = View.GONE
                }

            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun upComingMatchDataSet(data: MatchInfoData) {
        binding.locationUC.text = data.venue.toString()
        binding.team1UC.text = data.teamInfo!![0]!!.shortname.toString()
        if (data.teamInfo!!.size > 1)
            binding.team2UC.text = data.teamInfo!![1]!!.shortname ?: data.teamInfo!![1].name
        val ldt: LocalDateTime = LocalDateTime.parse(data.dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)



        binding.timeUC.text = "start at " + date_time.split("- ").toTypedArray()[1]
        if (!data.teamInfo!![0].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data.teamInfo!![0].img.toString()).into(binding.team1ImgUC)
        }
        if (data.teamInfo!!.size > 1) {

            if (!data.teamInfo!![1].img.toString().isNullOrEmpty()) {
                Picasso.get().load(data.teamInfo!![1].img.toString()).into(binding.team2ImgUC)
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun liveMatchDataSet(data: MatchInfoData) {
        binding.seriesNameC.text=data.name.toString().split(",").toTypedArray()[0]
        binding.team1Txt.text= data.teamInfo!![0]!!.shortname.toString()
        if (data.teamInfo!!.size >1) {
            binding.team2Txt.text = data.teamInfo!![1]!!.shortname ?: data.teamInfo!![1].name
            if (!data.teamInfo!![1].img.toString().isNullOrEmpty()) {
                Picasso.get().load(data.teamInfo!![1].img.toString()).into(binding.team2ImgC)
            }
        }

        val ldt: LocalDateTime = LocalDateTime.parse(data.dateTimeGMT.toString())
        val formatter = DateTimeFormatter.ofPattern("dd MMM, EEEE- hh:mm a", Locale.ENGLISH)
        val date_time: String = ldt.format(formatter)
        println(date_time)



        binding.time.text ="start at "+date_time.split("- ").toTypedArray()[1]

        if (!data.teamInfo!![0].img.toString().isNullOrEmpty()) {
            Picasso.get().load(data.teamInfo!![0].img.toString()).into(binding.team1ImgC)
        }




        if (data.score != null) {
            if (data.score!!.size == 2) {
                if (data.score!![0].inning.toString()
                        .contains(data.teamInfo!![0].name.toString())
                ) {
                    binding.runperwicketTxt1.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.overTxt1.text = "("+data.score!![0].o.toString()+")"
                    binding.runperwicketTxt2.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.overTxt2.text = "("+data.score!![1].o.toString()+")"


                } else {
                    binding.runperwicketTxt1.text =
                        data.score!![1].r.toString() + "-" + data.score!![1].w.toString()
                    binding.overTxt1.text = "("+data.score!![1].o.toString()+")"
                    binding.runperwicketTxt2.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.overTxt2.text = "("+data.score!![0].o.toString()+")"

                }
            } else if (data.score!!.size == 1) {
                if (data.score!![0].inning.toString()
                        .contains(data.teamInfo!![0].name.toString())
                ) {
                    binding.runperwicketTxt1.text =
                        data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.overTxt1.text = "("+data.score!![0].o.toString()+")"
                    binding.runperwicketTxt2.visibility = View.GONE
                    binding.overTxt2.visibility = View.GONE
                    binding.batImg1.visibility = View.VISIBLE
                    binding.ballImg2.visibility = View.VISIBLE
                    binding.toBatTXT2.visibility = View.VISIBLE
                    binding.toBatTXT1.visibility = View.GONE
                    binding.batImg2.visibility = View.GONE
                    binding.ballImg1.visibility = View.GONE


                } else {
                    binding.runperwicketTxt1.visibility = View.GONE
                    binding.overTxt1.visibility = View.GONE
                    binding.runperwicketTxt2.text = data.score!![0].r.toString() + "-" + data.score!![0].w.toString()
                    binding.overTxt2.text = "("+data.score!![0].o.toString()+")"
                    binding.batImg1.visibility = View.GONE
                    binding.ballImg2.visibility = View.GONE
                    binding.batImg2.visibility = View.VISIBLE
                    binding.ballImg1.visibility = View.VISIBLE
                    binding.toBatTXT2.visibility = View.GONE
                    binding.toBatTXT1.visibility = View.VISIBLE

                }
            } else if (data.score!!.size == 0) {

                binding.runperwicketTxt1.visibility = View.GONE
                binding.overTxt1.visibility = View.GONE
                binding.runperwicketTxt2.visibility = View.GONE
                binding.overTxt2.visibility = View.GONE
                binding.runperwicketTxt1.visibility = View.GONE
                binding.overTxt1.visibility = View.GONE
                binding.runperwicketTxt2.visibility = View.GONE
                binding.overTxt2.visibility = View.GONE
                binding.liveLL.visibility = View.GONE
                binding.time.visibility = View.VISIBLE
                binding.timeLL.visibility = View.VISIBLE

            }
        }
    }
//    private fun getFinishedMatch() {
//        matchesViewModel!!.getFinishedMatchesData(token)
//            .observe(this) { res: CompletedMatchesResponse ->
//
//                if (res.status.toString().equals("ok",ignoreCase = true)) {
//                    var data = res.response
//                    binding!!.team1Txt.text =data!!.teams!![0].abbr.toString()
//
//                    binding!!.team2.text = data.teams!![1].abbr.toString()
//                    binding!!.team1Score.text = data.teams!![0].scores.toString()
//                    binding!!.team1Over.text = data.teams!![0].overs.toString()
//                    binding!!.team2Score.text = data.teams!![1].scores.toString()
//                    binding!!.team2Over.text = data.teams!![1].overs.toString()
//                    binding!!.date.text = res.datetime.toString()
//
//                    if (!data.teams!![0].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![0].logoUrl.toString()).into(binding!!.team1Img)
//                    }
//
//                    if (!data.teams!![1].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![1].logoUrl.toString()).into(binding!!.team2Img)
//                    }
//
//                    binding!!.playing1RL.setOnClickListener {
//                        val userItem: List<Player>? = data.players
//                        val teamInfo: List<Team>? = data.teams
//
//                        val yourIntent = Intent(activity, PlayingXIAcitivity::class.java)
//                        val b = Bundle()
////                        yourIntent.putExtra("LIST", userItem as Serializable?)
//                        b.putSerializable("players", userItem as Serializable)
//                        b.putSerializable("teams", teamInfo as Serializable)
//                        yourIntent.putExtras(b) //pass bundle to your intent
//
//                        startActivity(yourIntent)
//                    }
//
//
//                }else{
//
//                }
//
//
//            }
//    }
}