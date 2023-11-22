package com.example.cricbuzz.match

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cricbuzz.databinding.FragmentMatchInfoBinding
import com.example.cricbuzz.match.view.PlayingXIAcitivity
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.squareup.picasso.Picasso
import java.io.Serializable





class MatchInfoFragment : Fragment() {

    private var binding: FragmentMatchInfoBinding? = null

    private var matchesViewModel:MatchesViewModel? = null
    var token = "ec471071441bb2ac538a0ff901abd249"
    private val binding1 get() = binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMatchInfoBinding.inflate(inflater, container, false)

        matchesViewModel = MatchesViewModel()

//        getFinishedMatch()




        return binding!!.root
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