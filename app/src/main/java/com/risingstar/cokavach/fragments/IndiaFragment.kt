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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.risingstar.cokavach.R
import com.risingstar.cokavach.adapters.IndiaAdapter
import com.risingstar.cokavach.models.ModelIndia
import org.json.JSONException
import java.util.*
import kotlin.Comparator

class IndiaFragment : Fragment() {
    lateinit var recyclerIndia : RecyclerView
    lateinit var recyclerAdapter : IndiaAdapter
    lateinit var layoutManager : LinearLayoutManager
    lateinit var searchState : SearchView
    lateinit var sortIndia : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_india, container, false)

        recyclerIndia= view.findViewById(R.id.recyclerIndia)
        layoutManager = LinearLayoutManager(activity as Context)
        searchState = view.findViewById(R.id.searchState)
        sortIndia = view.findViewById(R.id.sortIndia)

        val indiaStatList  = arrayListOf<ModelIndia>()


        val comparator = Comparator<ModelIndia>{ state1, state2 ->
            state1.stateName.compareTo(state2.stateName)
        }

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "https://api.rootnet.in/covid19-in/stats/latest"

        val jsonObject =
            object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                try {
                    val countriesData = it.getJSONObject("data").getJSONArray("regional")
                    for (i in 0 until countriesData.length()) {
                        val indiaStat= ModelIndia(stateName = countriesData.getJSONObject(i).getString("loc"),
                            confirmed = countriesData.getJSONObject(i).getString("totalConfirmed"),
                            recovered = countriesData.getJSONObject(i).getString("discharged"),
                            deaths = countriesData.getJSONObject(i).getString("deaths"))

                        indiaStatList.add(indiaStat)
                        recyclerAdapter = IndiaAdapter(activity as Context , indiaStatList)
                        recyclerIndia.adapter = recyclerAdapter
                        recyclerIndia.layoutManager = layoutManager
                    }

                } catch (e: JSONException) {
                    Toast.makeText(activity as Context,"Error in json catch", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(activity as Context,"Error in json error listener", Toast.LENGTH_SHORT).show()
            }) {}
        queue.add(jsonObject)

        searchState.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerAdapter.filter.filter(newText)
                return false
            }

        })

        sortIndia.setOnClickListener {

            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Sort by")
            val option = arrayOf("A to Z","Z to A")
            dialog.setSingleChoiceItems(option,-1){dialog,which ->
                when(which){
                    0->{
                        Collections.sort(indiaStatList,comparator)
                    }
                    1->{
                        Collections.sort(indiaStatList,comparator)
                        indiaStatList.reverse()
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