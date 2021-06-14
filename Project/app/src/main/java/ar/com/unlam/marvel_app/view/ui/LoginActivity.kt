package ar.com.unlam.marvel_app.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ar.com.unlam.marvel_app.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()
        loginLayout.visibility = View.VISIBLE
    }

    private fun session() {
        val pref = getSharedPreferences(getString(R.string.user_login), Context.MODE_PRIVATE)
        val email = pref.getString("email",null)

        if (email!=null){
            loginLayout.visibility = View.INVISIBLE
        goMainActivity(email)
            finish()
        }

    }

    private fun setup() {
        title = "Authenticacion"
        buttonRegistrar.setOnClickListener {

            if (et_TextEmailAddress.text.isNotEmpty() && et_TextPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    et_TextEmailAddress.text.toString(),
                    et_TextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goMainActivity(it.result?.user?.email.toString() ?: "")
                    } else {
                        showAlert()
                    }
                }

            } else {
                Toast.makeText(
                    this,
                    "Los campos email y contraseña deben ser completados",
                    Toast.LENGTH_SHORT
                )
            }

        }

        buttonLogin.setOnClickListener {

            if (et_TextEmailAddress.text.isNotEmpty() && et_TextPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    et_TextEmailAddress.text.toString(),
                    et_TextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goMainActivity(it.result?.user?.email.toString() ?: "")
                    } else {
                        showAlert()
                    }
                }

            } else {
                Toast.makeText(
                    this,
                    "Los campos email y contraseña deben ser completados",
                    Toast.LENGTH_SHORT
                )

            }

        }
    }

    private fun goMainActivity(email: String) {
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(mainIntent)
        finish()

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la authenticacion de usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}