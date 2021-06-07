package ar.com.unlam.marvel_app.view.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(suportFragmentManager: FragmentManager) :FragmentPagerAdapter(suportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        private val mFragmentsList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()


    override fun getItem(position: Int): Fragment {
       return mFragmentsList[position]
    }
    override fun getCount(): Int {
        return mFragmentsList.size
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment,title:String){
        mFragmentsList.add(fragment)
        mFragmentTitleList.add(title)

    }

}