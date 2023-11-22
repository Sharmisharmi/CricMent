package com.example.cricbuzz


import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.home.HighLightFragment
import com.example.cricbuzz.home.HomeFragment
import com.example.cricbuzz.match.view.MatchFragment
import com.example.cricbuzz.home.SeriesFragment
import com.example.cricbuzz.login.LoginActivity
import com.example.cricbuzz.profile.AboutUs
import com.example.cricbuzz.profile.ConnectWithUs
import com.example.cricbuzz.profile.EditProfile
import com.example.cricbuzz.profile.PrivacyPolicy
import com.example.cricbuzz.profile.TermsAndConditions
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : AppCompatActivity() {


    lateinit var bottom_menu: ChipNavigationBar
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var mailIdTxt: TextView
    lateinit var userName: TextView
    lateinit var profile_image: CircleImageView
    lateinit var profile_image_main: CircleImageView
    lateinit var profile_card: MaterialCardView
    lateinit var navigationView: NavigationView

    var mail = ""
    var personName = "null"
    var personEmail = "null"
    var personPhoto = ""
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        val homeFragment= HomeFragment()
        val seriesFragment= SeriesFragment()
        val matchFragment= MatchFragment()
        val highLightFragment= HighLightFragment()

        setCurrentFragment(homeFragment)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

//        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()


        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        bottom_menu.setItemSelected(R.id.home);

        bottom_menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                val option = when (id) {
                    R.id.home->setCurrentFragment(homeFragment)
                    R.id.series->setCurrentFragment(seriesFragment)
                    R.id.matches->setCurrentFragment(matchFragment)
                    R.id.HighLights->setCurrentFragment(highLightFragment)
                    else -> R.color.white to ""
                }
//                val color = ContextCompat.getColor(this@VerticalModeActivity, option.first)
//                container.colorAnimation(lastColor, color)
//                lastColor = color
//                title.text = option.second
            }
        })


        var pref= getSharedPreferences(CommonConstants.CRICKETBUZZ, AppCompatActivity.MODE_PRIVATE)
        mail = pref?.getString(CommonConstants.EMAIL,"").toString()




//        if (!mail.isNullOrEmpty()) {
//            userName.text = mail
//        }
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
             personName = acct.displayName.toString()
             personEmail = acct.email.toString()
             personPhoto = acct.photoUrl.toString() // this line to get  profile picture

            Log.d("personEmail", "onCreate: "+personEmail.toString())
            Log.d("personName", "onCreate: "+personName.toString())
            Log.d("personPhoto", "onCreate: "+personPhoto.toString())
            mailIdTxt.text = personEmail.toString()
            userName.text = personName.toString()
            Picasso.get()
                .load(personPhoto.toString())
                .into(profile_image);
            Picasso.get()
                .load(personPhoto.toString())
                .into(profile_image_main);
        }

        profile_image_main.setOnClickListener {
            startActivity(Intent(this,EditProfile::class.java))
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }

    override fun onStart() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
        mGoogleApiClient!!.connect()
        super.onStart()
    }

    fun openCloseNavigationDrawer(view: View) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)

        }
    }


    fun profileOnclick(view: View) {
        startActivity(Intent(this, EditProfile::class.java).putExtra("displayName",personName).putExtra("displayMail",personEmail).putExtra("displayPic",personPhoto))
    }


    fun inviteOnclick(view: View) {
        val link = "https://play.google.com/store/apps/details?id=in.cricketexchange.app.cricketexchange"

        val uri = Uri.parse(link)

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.putExtra(Intent.EXTRA_TITLE, "CricBuzz")

        startActivity(Intent.createChooser(intent, "Cricket Updates"))
    }

    fun connectOnclick(view:View){
        startActivity(Intent(this, ConnectWithUs::class.java))
    }

    fun privacyOnclick(view:View){
        startActivity(Intent(this, PrivacyPolicy::class.java))
    }


    fun aboutOnclick(view:View){
        startActivity(Intent(this, AboutUs::class.java))
    }



    fun termsOnclick(view:View){
        startActivity(Intent(this, TermsAndConditions::class.java))
    }


    fun logoutOnclick(view:View){

        showDialog("Logout")
    }



     fun rateOnclick(view: View) {

         // REMAINDER USE THIS CODE FOR LIVE
//         val uri: Uri = Uri.parse("market://details?id=$packageName")

         val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=in.cricketexchange.app.cricketexchange")
         val goToMarket = Intent(Intent.ACTION_VIEW, uri)
         // To count with Play market backstack, After pressing back button,
         // to taken back to our application, we need to add following flags to intent.
         goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                 Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                 Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
         try {
             startActivity(goToMarket)
         } catch (e: ActivityNotFoundException) {
             startActivity(Intent(Intent.ACTION_VIEW,
                 Uri.parse("https://play.google.com/store/apps/details?id=in.cricketexchange.app.cricketexchange")))
         }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alert_layout)



        val yesBtn = dialog.findViewById(R.id.yesBtn) as TextView
        yesBtn.setOnClickListener {
            signOut()
            dialog.dismiss()
        }

        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient!!)
            .setResultCallback(object : ResultCallback<Status?> {
                override fun onResult(status: Status) {

                    val intent= Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
    }

    private fun initView() {
        bottom_menu = findViewById(R.id.bottom_menu)
        drawerLayout = findViewById(R.id.drawerLayout)
        mailIdTxt = findViewById(R.id.mailIdTxt1)
        userName = findViewById(R.id.userName1)
        profile_image = findViewById(R.id.nav_profile)
        profile_image_main = findViewById(R.id.profile_image)
        profile_card = findViewById(R.id.profile_card)
        navigationView = findViewById(R.id.nav_view)
    }


}