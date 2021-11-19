package com.isc.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.isc.proyecto.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN = 150
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var gAuth: FirebaseAuth
    private lateinit var GAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        gAuth = FirebaseAuth.getInstance()
        val usuario = gAuth.currentUser

       Handler().postDelayed({
            if (usuario != null){ //si el usuario está logeado, lo mandamos al dashboard.
                    val enviaDashboard = Intent(this,activity_Drawer::class.java)
                    startActivity(enviaDashboard)
                    finish()
            }else{ //no ha iniciado sesión aún, le indicamos que lo haga.
                Toast.makeText(this, "Inicie Sesión con Google", Toast.LENGTH_LONG).show()
            }
       }, 4000)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) //"error" generado por un bug del Android Studio, el proyecto SÍ funciona
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        GAuth = FirebaseAuth.getInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btGoogle.setOnClickListener{
                signIn()
        }


    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("MainActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("MainActivity", "Inicio con Google falló", e)
                }
            }else{
                Log.w("MainActivity", exception.toString())
            }

        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        GAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("MainActivity", "signInWithCredential:success")
                    val accede = Intent(this, activity_Drawer::class.java)
                    startActivity(accede)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("MainActivity", "signInWithCredential:failure", task.exception)

                }
            }
    }
}