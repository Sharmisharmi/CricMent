package com.example.cricbuzz.login

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.MainActivity
import com.example.cricbuzz.R
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.ApiClient
import com.example.cricbuzz.api.LoginApiClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


class LoginActivity : AppCompatActivity() {

    lateinit var google_LL: LinearLayout
    lateinit var faceBookLL: LinearLayout
    lateinit var imageView: ImageView

    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code:Int=123
    var firebaseAuth= FirebaseAuth.getInstance()

    var editor: SharedPreferences.Editor? = null

    private var loginViewModel:LoginViewModel? = null

    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        editor = getSharedPreferences(CommonConstants.CRICKETBUZZ, MODE_PRIVATE).edit()
        loginViewModel = LoginViewModel()

        faceBookLL.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("132303044784-cc8dq1qf2j7qf0l54vhb7u8bhoi8ksnk.apps.googleusercontent.com")
            .requestEmail()
            .build()
// getting the value of gso inside the GoogleSigninClient
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)
// initialize the firebaseAuth variable
        firebaseAuth= FirebaseAuth.getInstance()

        google_LL.setOnClickListener {
//            startActivity(Intent(this,MainActivity::class.java))

            val signInIntent: Intent =mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent,Req_Code)


        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Req_Code){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    // handleResult() function -  this is where we update the UI after Google signin takes place
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException){
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    // UpdateUI() function - this is where we specify what UI updation are needed after google signin has taken place.
    private fun UpdateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
//                SavedPreference.setEmail(this,account.email.toString())
//                SavedPreference.setUsername(this,account.displayName.toString())

                var email = account.email.toString()
                var userName = account.displayName.toString()
                var profile = account.photoUrl.toString()

                val fileUri =
                    URL(profile)
                val startIndex = fileUri.toString().lastIndexOf('/')
                val fileName = fileUri.toString().substring(startIndex + 1)
                println(fileName)
                Log.d("fileName", "UpdateUI: "+fileName)

//                val file = File(URL(profile).toURI())

//                downloadAndSaveImage(profile, imageView)
//                Toast.makeText(this,res.message.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                     login(email,userName,file)
//
//                editor = getSharedPreferences(CommonConstants.CRICKETBUZZ, MODE_PRIVATE).edit()
//                editor!!.putString(CommonConstants.EMAIL,email)
//                editor!!.commit()
//                editor!!.apply()

            }
        }
    }






    override fun onStart() {
        super.onStart()
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun login(email: String, userName: String, profile: File?) {



            val json = JSONObject()
            try {
                json.put("image", profile)
                json.put("name", userName)
                json.put("email", email)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            Log.d("json_post", json.toString())
            loginViewModel!!.generalLogin(json.toString(),this).observe(this) { res: LoginResponse ->
                if (res.message.toString().equals("success")){

                    var mail = res.data!!.email.toString()
                    Log.d("mail", "login: "+mail)
                    editor!!.putString(CommonConstants.EMAIL, mail.toString())
                    editor!!.putString(CommonConstants.USERNAME, res.data!!.name.toString())
                    editor!!.putString(CommonConstants.PROFILEPIC, res.data!!.image.toString())
                    editor!!.putString(CommonConstants.USERID, res.data!!.id.toString())
                    editor!!.commit()
                    editor!!.apply()

                    Toast.makeText(this,res.message.toString(),Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
//                    getLogin()
                    //startActivity(Intent(this, UserRegistration::class.java))
                }

        }

    }
    private fun initView() {
        google_LL = findViewById(R.id.google_LL)
        faceBookLL = findViewById(R.id.faceBookLL)
        imageView = findViewById(R.id.imageView)
    }
}