package com.example.cricbuzz.players

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.databinding.ActivityPlayerInfoBinding
import com.example.cricbuzz.players.model.PlayerInfoResponse
import com.example.cricbuzz.players.model.PlayerList
import com.example.cricbuzz.series.view_model.SeriesViewModel
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class PlayerInfo : AppCompatActivity() {

    lateinit var binding : ActivityPlayerInfoBinding

    var seriesViewModel:SeriesViewModel = SeriesViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getIntent = intent.getSerializableExtra("PlayerInfo") as? PlayerList

        binding.playerNameTXT.text = getIntent!!.name.toString()



    getPlayersInfoAPI(getIntent.id)


    }

    private fun getPlayersInfoAPI(id: String?) {


        if (id != null) {
            seriesViewModel!!.getPlayersInfoData(CommonConstants.apiKey,id)
                .observe(this) { res: PlayerInfoResponse ->

                    if (res.status.toString().equals("success")) {

                        var data = res.data

                        if (data != null) {
                            binding.countryTXT.text = data.country.toString()
                            binding.roleTXT.text = data.role.toString()
                            binding.styleTXT.text = data.battingStyle.toString()
                            binding.nationalityTXT.text = data.country.toString()
                            binding.dobTXT.text = data.dateOfBirth.toString().split("T").toTypedArray()[0]
                            binding.matchesTXT.text = data.stats!!.size.toString()
                            Picasso.get().load(data.playerImg.toString()).into(binding.playerImage)
                            setAge(data.dateOfBirth.toString().split("T").toTypedArray()[0])
                        }



                    }
                }
        }
    }

    private fun setAge(toString: String) {

        val currentDate: Calendar = Calendar.getInstance()

        val myFormat = SimpleDateFormat("yyyy-MM-dd")

        var birthdate: Date? = null

        try {
            birthdate = myFormat.parse(toString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val time: Long = currentDate.getTime().getTime() / 1000 - birthdate!!.getTime() / 1000

        val years = Math.round(time.toFloat()) / 31536000
        val months = Math.round((time - years * 31536000).toFloat()) / 2628000

        Log.d("Age_difference", "setAge: "+years+"--"+months)
        binding.ageTXT.text = years.toString()

    }
}