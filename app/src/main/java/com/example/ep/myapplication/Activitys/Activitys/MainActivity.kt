package com.example.ep.myapplication.Activitys.Activitys

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ep.myapplication.Activitys.Fragments.SecondFragment
import com.example.ep.myapplication.Activitys.Fragments.SecondFragment.OnSecondFragmentInteractionListener
import com.example.ep.myapplication.Activitys.Fragments.mainFirstFragment
import com.example.ep.myapplication.Activitys.Fragments.mainFirstFragment.OnFirstFragmentInteractionListener
import com.example.ep.myapplication.Activitys.Model.State
import com.example.ep.myapplication.R

class MainActivity : AppCompatActivity(), OnFirstFragmentInteractionListener,
    OnSecondFragmentInteractionListener {
    private var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fregment_container = all the fragments will be on him
        val manager = supportFragmentManager
        var fragment = manager.findFragmentById(R.id.fregment_container)
        if (fragment == null) // if it the first time to call the first fragment
        {
            fragment = mainFirstFragment()
            val transaction = manager.beginTransaction()
            transaction.add(R.id.fregment_container, fragment, "0").commit()
        }
    }

    fun LoadSecFragment(s: State?) // replace the first fragment with the second fragment
    {
        val secondFregment = SecondFragment()
        intent.putExtra("StateOb", s)
        val transaction = supportFragmentManager.beginTransaction()

//        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(
            R.id.fregment_container,
            secondFregment,
            (supportFragmentManager.backStackEntryCount - 1).toString() + ""
        ).addToBackStack(null).commit()
        flag = 1
    }

    fun GoBack() { // return to first fragment
        val f: Fragment?
        supportFragmentManager.popBackStack()
        supportFragmentManager.backStackEntryCount
        f = supportFragmentManager.findFragmentByTag("0")
        supportFragmentManager.beginTransaction().replace(
            R.id.fregment_container,
            f!!,
            (supportFragmentManager.backStackEntryCount - 1).toString() + ""
        ).commit()
        flag = 0
    }

    override fun onBackPressed() { // override the back button on android phones
        if (flag == 0) {
            AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onSecondFragmentInteraction(uri: Uri?) {}
    override fun onFirstFragmentInteraction(uri: Uri?) {}
}