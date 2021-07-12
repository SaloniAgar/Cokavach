package com.risingstar.cokavach.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.risingstar.cokavach.models.ModelStat
import com.risingstar.cokavach.R
import com.risingstar.cokavach.adapters.CountriesAdapter
import org.json.JSONException
import java.util.*
import kotlin.Comparator


class CountriesFragment : androidx.fragment.app.Fragment() {

    lateinit var recyclerStat : RecyclerView
    lateinit var recyclerAdapter : CountriesAdapter
    lateinit var layoutManager : LinearLayoutManager
    lateinit var searchCountry : SearchView
    lateinit var sortStat :ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_countries, container, false)

        recyclerStat= view.findViewById(R.id.recyclerStat)
        layoutManager = LinearLayoutManager(activity as Context)
        searchCountry = view.findViewById(R.id.searchCountry)
        sortStat = view.findViewById(R.id.sortStat)

        val statList  = arrayListOf<ModelStat>()


        val comparator = Comparator<ModelStat>{ country1, country2 ->
            country1.country.compareTo(country2.country)
        }

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "https://api.covid19api.com/summary"

        val jsonObject =
                object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                    try {
                        val countriesData = it.getJSONArray("Countries")
                        for (i in 0 until countriesData.length()) {
                            val stat= ModelStat(countriesData.getJSONObject(i).getString("Country"),
                            countriesData.getJSONObject(i).getString("CountryCode"),
                            countriesData.getJSONObject(i).getString("NewConfirmed"),
                            countriesData.getJSONObject(i).getString("TotalConfirmed"),
                            countriesData.getJSONObject(i).getString("NewDeaths"),
                            countriesData.getJSONObject(i).getString("TotalDeaths"),
                            countriesData.getJSONObject(i).getString("NewRecovered"),
                            countriesData.getJSONObject(i).getString("TotalRecovered"))

                            statList.add(stat)
                            recyclerAdapter = CountriesAdapter(activity as Context , statList)
                            recyclerStat.adapter = recyclerAdapter
                            recyclerStat.layoutManager = layoutManager
                        }

                    } catch (e: JSONException) {
                        Toast.makeText(activity as Context,"Error in json catch", Toast.LENGTH_SHORT).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(activity as Context,"Error in json error listener", Toast.LENGTH_SHORT).show()
                }) {}
        queue.add(jsonObject)

        searchCountry.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerAdapter.filter.filter(newText)
                return false
            }

        })

        sortStat.setOnClickListener {

            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Sort by")
            val option = arrayOf("A to Z","Z to A")
            dialog.setSingleChoiceItems(option,-1){dialog,which ->
                when(which){
                    0->{
                        Collections.sort(statList,comparator)
                    }
                    1->{
                        Collections.sort(statList,comparator)
                        statList.reverse()
                    }
                }

            }
            dialog.setPositiveButton("OK"){text,listener->
                recyclerAdapter.notifyDataSetChanged()
            }
            dialog.create()
            dialog.show()

        }

        return view
    }


}
//class SortAsc : Comparator<ModelSat>{
//    override fun compare(left: ModelSat?, right: ModelSat?): Int {
//        if (left != null) {
//            if (right != null) {
//                return left.country.compareTo(right.country)
//            }
//        }
//        return 0
//    }

//}

