package ar.com.unlam.marvel_app.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.view.ui.adapters.ViewPagerAdapter
import ar.com.unlam.marvel_app.databinding.ActivityMainBinding
import ar.com.unlam.marvel_app.view.ui.fragments.EventsFragment
import ar.com.unlam.marvel_app.view.ui.fragments.CharactersFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val pref = getSharedPreferences(getString(R.string.user_login), Context.MODE_PRIVATE).edit()
        pref.putString("email", email)
        pref.apply()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menudetail,menu)
        menu?.findItem(R.id.back)?.setVisible(false)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut  -> {
                logOut()
            }
        }
        return super.onOptionsItemSelected(item)

    }
    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(CharactersFragment(), getString(R.string.characterNavBar))
        adapter.addFragment(EventsFragment(), getString(R.string.eventNavBar))
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_superhero)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_calendar)

    }

    private fun logOut() {
        val pref = getSharedPreferences(getString(R.string.user_login), Context.MODE_PRIVATE).edit()
        pref.clear()
        pref.apply()

        FirebaseAuth.getInstance().signOut()
        val mainIntent = Intent(this, LoginActivity::class.java)
        startActivity(mainIntent)
    }

}