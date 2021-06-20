package ar.com.unlam.marvel_app.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        setListerners()
        session()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menudetail, menu)
        menu?.findItem(R.id.logOut)?.setVisible(false)
        return super.onCreateOptionsMenu(menu)
    }


    private fun session() {
        val pref = getSharedPreferences(getString(R.string.user_login), Context.MODE_PRIVATE)
        val email = pref.getString("email", null)
        if (email != null) {
            loginLayout.visibility = View.INVISIBLE
            goMainActivity(email)
            finish()
        }

    }

    private fun setListerners() {
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
                    getString(R.string.err_mail_pass_incompleted),
                    Toast.LENGTH_SHORT
                ).show()
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
                        Toast.makeText(
                            this,
                            getString(R.string.err_mail_pass),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } else {
                Toast.makeText(
                    this,
                    getString(R.string.err_mail_pass_incompleted),
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

        buttonRegistrarFacebook.setOnClickListener {

            Toast.makeText(
                this,
                getString(R.string.err_face),
                Toast.LENGTH_SHORT
            ).show()
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
        builder.setMessage(getString(R.string.err_authen_alert))
        builder.setPositiveButton("Acept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}