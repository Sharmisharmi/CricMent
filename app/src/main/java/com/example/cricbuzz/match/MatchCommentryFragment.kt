package com.example.cricbuzz.match

import android.content.Context
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
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentMatchCommentryBinding
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.match.viewmodel.MatchesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt


class MatchCommentryFragment : Fragment() {

    private var binding: FragmentMatchCommentryBinding? = null

    private val binding1 get() = binding!!

    private var bottomSheetDialog: BottomSheetDialog? = null

    var matchesViewModel : MatchesViewModel? = null
    var token = "ec471071441bb2ac538a0ff901abd249"

    lateinit var teamsName:TextView
    lateinit var close:ImageView

    lateinit var commentryLL:LinearLayout

    var overposition=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMatchCommentryBinding.inflate(inflater, container, false)

        matchesViewModel = MatchesViewModel()


        bottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView = activity!!.layoutInflater.inflate(R.layout.commentry_bottom_sheet, null)
        bottomSheetDialog!!.setContentView(sheetView)

        teamsName = sheetView.findViewById(R.id.teamsName)
        close = sheetView.findViewById(R.id.close)


        binding!!.wicketsTxt.setOnClickListener {
            binding!!.wicketCommentary.visibility=View.VISIBLE
            binding!!.overLL.visibility=View.GONE

            binding!!.wicketsTxt.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.primary));
            binding!!.wicketsTxt.setTextColor(Color.parseColor("#FFFFFF"))
            binding!!.oversTxt.setTextColor(Color.parseColor("#000000"))
            binding!!.oversTxt.setBackgroundTintList(null);

        }


        close.setOnClickListener {
            bottomSheetDialog!!.dismiss()
        }

        commentryLL = sheetView.findViewById(R.id.commentryLL)


//        getFinishedMatch()

        return binding!!.root
    }

//    private fun getFinishedMatch() {
//        matchesViewModel!!.getFinishedMatchesData(token)
//            .observe(this) { res: MatchesResponse ->
//
//                if (res.status.toString().equals("ok",ignoreCase = true)) {
//                    var data = res.data
//                    binding!!.team1.text = data!!.teams!![0].abbr.toString()
//                    binding!!.team2.text = data.teams!![1].abbr.toString()
//                    binding!!.team1Score.text = data.teams!![0].scores.toString()
//                    binding!!.team1Over.text = data.teams!![0].overs.toString()
//                    binding!!.team2Score.text = data.teams!![1].scores.toString()
//                    binding!!.team2Over.text = data.teams!![1].overs.toString()
//
//
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
//                        var overNumber: TextView = customView.findViewById(R.id.overNumber)
//                        var run_ll: LinearLayout = customView.findViewById(R.id.run_ll)
//
//                        overNumber.text = "Over "+i.toString()
//                        for (runs in 0 until data.commentaries!!.size) {
//
//                            if (i == data.commentaries!![runs].over!!.toInt() && !data.commentaries!![runs].score!!.toString().contains("/") ) {
//                                addrun(data.commentaries!![runs].score!!.toString(), run_ll)
//
//                                teamsName.text=data.teamBatting.toString()+" VS "+data.teamBowling.toString()
////                                addCommentaries(data.commentaries!![runs].commentary!!.toString(),data.commentaries!![runs].over!!.toString(),data.commentaries!![runs].run!!.toString(),i)
//
//
//
//                                binding!!.overLL.setOnClickListener {
//                                    bottomSheetDialog!!.show()
//                                    Toast.makeText(activity,runs.toString(),Toast.LENGTH_SHORT).show()
//                                    overposition = i
//
//                                }
//
//                                addCommentaries1(data,overposition)
//                            }else{
//
//                            }
//
//
//                            // wicket layout
//
//
//                        }
//
//
//                        binding!!.overLL.addView(customView)
//
//
//                    }
//
//
//
//                    // WicketLayout
//                    for (i in 0 until data.liveScore!!.overs!!.toFloat().roundToInt().toString().toInt()+1){
//
//
//                        var inflater: LayoutInflater =
//                            activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//                        var customView1: View = inflater.inflate(
//                            R.layout.commentry_layout, null
//                        )
//
//
//                        var commentryTxt:TextView = customView1.findViewById(R.id.commentryTxt)
//                        var overCount:TextView = customView1.findViewById(R.id.overCount)
//                        var totalRuns:TextView = customView1.findViewById(R.id.totalRuns)
//
//
//                        for (runs in 0 until data.commentaries!!.size) {
//
//                            if (!data.commentaries!![runs].score!!.toString().contains("/") && data.commentaries!![runs].score!!.toString().equals("w",ignoreCase = true) ) {
//
//                              commentryTxt.text = data.commentaries!![runs].commentary.toString()
//                              overCount.text = data.commentaries!![runs].over.toString()+"."+data.commentaries!![runs].ball.toString()
//                              totalRuns.text = data.commentaries!![runs].score.toString()
//                                totalRuns.setTextColor(Color.parseColor("#ffffff"))
//                                totalRuns.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(),R.color.red));
//
//
//                            }
//
//
//
//
//                        }
//
//
//                        binding!!.wicketCommentary.addView(customView1)
//
//
//                    }
//
//                    if (!data.teams!![0].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![0].logoUrl.toString()).into(binding!!.team1Img)
//                    }
//
//
//                    if (!data.teams!![1].logoUrl.toString().isNullOrEmpty()) {
//                        Picasso.get().load(data.teams!![1].logoUrl.toString()).into(binding!!.team2Img)
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



//    private fun addCommentaries1(data: CompletedMatchesData?, i: Int) {
//        var inflater: LayoutInflater =
//            activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var customView: View = inflater.inflate(
//            R.layout.commentry_layout, null
//        )
//
//
//        var commentryTxt:TextView = customView.findViewById(R.id.commentryTxt)
//        var overCount:TextView = customView.findViewById(R.id.overCount)
//        var totalRuns:TextView = customView.findViewById(R.id.totalRuns)
//
//        overCount.text = "Over : "+ data!!.commentaries!![i].over.toString()
//        totalRuns.text = "Score : "+ data!!.commentaries!![i].score.toString()
//
////        if (commentaries!![index].score!!.toString().contains("/") ) {
//
//        commentryTxt.text = data!!.commentaries!![i].commentary.toString()
////        }
//
//
//        commentryLL.addView(customView)
//    }


    private fun addCommentaries(commentaries: String, over: String, score: String, i: Int) {

        var inflater: LayoutInflater =
            activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var customView: View = inflater.inflate(
            R.layout.commentry_layout, null
        )


        var commentryTxt:TextView = customView.findViewById(R.id.commentryTxt)
        var overCount:TextView = customView.findViewById(R.id.overCount)
        var totalRuns:TextView = customView.findViewById(R.id.totalRuns)

        overCount.text = "Over : "+ over.toString()
        totalRuns.text = "Score : "+ score.toString()

//        if (commentaries!![index].score!!.toString().contains("/") ) {

        commentryTxt.text = commentaries
//        }


        commentryLL.addView(customView)

    }

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



}