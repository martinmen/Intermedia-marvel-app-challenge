package ar.com.unlam.marvel_app.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.view.ui.adapters.ViewPagerAdapter
import ar.com.unlam.marvel_app.databinding.ActivityMainBinding
import ar.com.unlam.marvel_app.view.ui.fragments.EventsFragment
import ar.com.unlam.marvel_app.view.ui.fragments.HeroListFragmentRV

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabs()
        //setupFragment()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HeroListFragmentRV(), "Heroes")
        adapter.addFragment(EventsFragment(), "Events")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_superhero)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_calendar)

    }

    /*private fun setupFragment() {

        val fragment  = HeroesFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()
    }*/
}