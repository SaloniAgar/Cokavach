package com.risingstar.cokavach.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter

import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.cokavach.R
import com.risingstar.cokavach.models.ModelStat
import java.util.*
import kotlin.collections.ArrayList

class CountriesAdapter(context: Context, val itemList : ArrayList<ModelStat>) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(),Filterable {

    var itemFilterList = ArrayList<ModelStat>()

    init {
        itemFilterList = itemList
    }

    class CountriesViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvCountryName : TextView = view.findViewById(R.id.tvCountryName)
        val tvCountryCode : TextView = view.findViewById(R.id.tvCountryCode)
        val tvCountryTotalCases : TextView = view.findViewById(R.id.tvCountryTotalCases)
        val tvCountryNewCases : TextView = view.findViewById(R.id.tvCountryNewCases)
        val tvCountryTotalDeaths : TextView = view.findViewById(R.id.tvCountryTotalDeaths)
        val tvCountryNewDeaths : TextView = view.findViewById(R.id.tvCountryNewDeaths)
        val tvCountryTotalRecovered :TextView = view.findViewById(R.id.tvCountryTotalRecovered)
        val tvCountryNewRecovered : TextView = view.findViewById(R.id.tvCountryNewRecovered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.countries_recycler_single_row,parent,false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val statList = itemFilterList[position]

        holder.tvCountryName.text = statList.country
        holder.tvCountryCode.text = statList.countryCode
        holder.tvCountryTotalCases.text = statList.totalConfirmed
        holder.tvCountryNewCases.text = statList.newConfirmed
        holder.tvCountryTotalDeaths.text = statList.totalDeaths
        holder.tvCountryNewDeaths.text = statList.newDeaths
        holder.tvCountryTotalRecovered.text = statList.totalRecovered
        holder.tvCountryNewRecovered.text = statList.newRecovered
    }

    override fun getItemCount(): Int {
       return itemFilterList.size
    }

override fun getFilter(): Filter {
    return object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint.toString()
            if (charSearch.isEmpty()) {
                itemFilterList = itemList
            } else {
                val resultList = ArrayList<ModelStat>()
                for (row in itemList) {
                    if (row.country.toUpperCase(Locale.ROOT).contains(charSearch.toUpperCase(Locale.ROOT))) {
                        resultList.add(row)
                    }
                }
                itemFilterList = resultList
            }
            val filterResults = FilterResults()
            filterResults.values = itemFilterList
            return filterResults
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            itemFilterList = results?.values as ArrayList<ModelStat>
            notifyDataSetChanged()
        }

    }
}

}