package com.risingstar.cokavach.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.risingstar.cokavach.*
import com.risingstar.cokavach.fragments.*

class MainActivity : AppCompatActivity() {

    lateinit var bottom_nav : BottomNavigationView
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout

    //lateinit var usernumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav = findViewById(R.id.bottom_nav)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        frameLayout = findViewById(R.id.frame)

        //usernumber = intent.getStringExtra("User number").toString()
        openHomeFragment()

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home ->{
                    openHomeFragment()
                }
                R.id.nav_countries ->{
                    val countriesFragment = CountriesFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(
                                    R.id.frame,
                                    countriesFragment
                            )
                            .commit()
                    supportActionBar?.title = "Countries Stat"
                }
                R.id.nav_india ->{
                    val indiaFragment = IndiaFragment()
                    supportFragmentManager.beginTransaction()
                            .replace(
                                    R.id.frame,
                                    indiaFragment
                            )
                            .commit()
                    supportActionBar?.title = "India Stat"
                }
                R.id.nav_vaccination ->{
                    val vaccinationFragment = VaccinationFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            vaccinationFragment
                        )
                        .commit()
                    supportActionBar?.title = "Vaccination Stat"
                }
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    fun openHomeFragment(){
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.frame,
                        homeFragment
                )
                .commit()
        supportActionBar?.title = "World Stat"
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is HomeFragment -> openHomeFragment()

            else -> super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_help){
            startActivity(Intent(this@MainActivity, HelpActivity :: class.java))
            return true
        }
        return false
    }
}

/*
<com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="@style/ThemeOverlay.AppCompat.Dark"
            android:elevation="0dp">

            <androidx.appcompat.widget.Toolbar
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:minHeight="?attr/actionBarSize"
                android:background="@color/colorPrimary"
                android:id="@+id/toolbar"
                android:theme="@style/ThemeOverlay.AppCompat.Dark"
                app:layout_scrollFlags ="scroll|enterAlways">

                <ImageView
                    android:id="@+id/imgHelp"
                    android:layout_width="50dp"
                    android:layout_height="?actionBarSize"
                    android:layout_gravity="end"
                    android:scaleType="centerInside"
                    android:src="@drawable/ic_help"/>
            </androidx.appcompat.widget.Toolbar>




        </com.google.android.material.appbar.AppBarLayout>
*/