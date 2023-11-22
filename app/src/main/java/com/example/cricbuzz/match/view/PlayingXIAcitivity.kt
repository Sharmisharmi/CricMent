package com.example.cricbuzz.match.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cricbuzz.databinding.ActivityPlayingXiacitivityBinding
import com.example.cricbuzz.match.MatchDetails
import com.example.cricbuzz.match.adapter.PlayingXIAdapter


class PlayingXIAcitivity : AppCompatActivity() {
    private lateinit var binding:ActivityPlayingXiacitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingXiacitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImage.setOnClickListener {
            startActivity(Intent(this,MatchDetails::class.java))
        }


        val i = intent
        val bundle = i.extras
//        val players: List<Player>? = bundle!!.getSerializable("players") as  List<Player>?
//        val teams: List<Team>? = bundle!!.getSerializable("teams") as  List<Team>?

//        binding.team1.text = teams!![0].abbr.toString()
//        binding.team2.text = teams!![1].abbr.toString()

        binding.playingXIRecyclerView.visibility= View.VISIBLE
        binding.playingXIRecyclerView.setHasFixedSize(true)
        binding.playingXIRecyclerView.layoutManager = GridLayoutManager(this,2)
//        binding.playingXIRecyclerView.adapter = PlayingXIAdapter(this, players)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MatchDetails::class.java))


    }
}