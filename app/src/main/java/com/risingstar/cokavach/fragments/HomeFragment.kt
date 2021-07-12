package com.risingstar.cokavach.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.risingstar.cokavach.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import org.json.JSONException


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tvTotalCases : TextView  = view.findViewById(R.id.tvTotalCases)
        val tvNewCases : TextView  = view.findViewById(R.id.tvNewCases)
        val tvTotalDeaths : TextView  = view.findViewById(R.id.tvTotalDeaths)
        val tvNewDeaths : TextView  = view.findViewById(R.id.tvNewDeaths)
        val tvTotalRecovered : TextView  = view.findViewById(R.id.tvTotalRecovered)
        val tvNewRecovered : TextView  = view.findViewById(R.id.tvNewRecovered)
        //val imgRefresh : ImageView = view.findViewById(R.id.imgRefresh)
        //val checkStatus : Button = view.findViewById(R.id.checkStatus)
        val mPieChart = view.findViewById(R.id.piechart) as PieChart

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "https://api.covid19api.com/summary"

        val jsonObject =
                object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                    try {
                        val globalData = it.getJSONObject("Global")
                        tvNewCases.text = globalData.getString("NewConfirmed")
                        tvTotalCases.text = globalData.getString("TotalConfirmed")
                        tvNewDeaths.text = globalData.getString("NewDeaths")
                        tvTotalDeaths.text = globalData.getString("TotalDeaths")
                        tvNewRecovered.text = globalData.getString("NewRecovered")
                        tvTotalRecovered.text = globalData.getString("TotalRecovered")


                        mPieChart.addPieSlice(PieModel("Cases",
                            globalData.getString("TotalConfirmed").toInt().toFloat(), Color.parseColor("#56B7F1")))
                        mPieChart.addPieSlice(PieModel("Deaths", globalData.getString("TotalDeaths").toInt().toFloat(), Color.parseColor("#FE6DA8")))
                        mPieChart.addPieSlice(PieModel("Recovery", globalData.getString("TotalRecovered").toInt().toFloat(), Color.parseColor("#FED70E")))


                    } catch (e: JSONException) {
                        Toast.makeText(activity as Context,"Error in json catch",Toast.LENGTH_SHORT).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(activity as Context,"Error in json error listener",Toast.LENGTH_SHORT).show()
                }) {}
        queue.add(jsonObject)

//        imgRefresh.setOnClickListener {
//            this@HomeFragment.onResume()
//        }
//
//        checkStatus.setOnClickListener {
//            Toast.makeText(activity as Context,"Turn on location",Toast.LENGTH_SHORT).show()
//        }


        mPieChart.startAnimation()

        return view
    }

    override fun onResume() {

        super.onResume()
    }

}