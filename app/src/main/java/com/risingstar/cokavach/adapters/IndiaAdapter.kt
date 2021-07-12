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
import com.risingstar.cokavach.models.ModelIndia
import com.risingstar.cokavach.models.ModelStat
import java.util.*
import kotlin.collections.ArrayList

class IndiaAdapter(context: Context , val itemList : ArrayList<ModelIndia>) : RecyclerView.Adapter<IndiaAdapter.IndiaViewHolder>(),Filterable {

    var itemFilterList = ArrayList<ModelIndia>()

    init {
        itemFilterList = itemList
    }

    class IndiaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtStateName : TextView = view.findViewById(R.id.txtStateName)
        val txtStateConfirmed : TextView = view.findViewById(R.id.txtStateConfirmed)
        val txtStateRecovered :TextView = view.findViewById(R.id.txtStateRecovered)
        val txtStateDeaths : TextView = view.findViewById(R.id.txtStateDeaths)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.india_recycler_single_row,parent,false)
        return IndiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndiaViewHolder, position: Int) {
        val indiaStatList = itemFilterList[position]

        holder.txtStateName.text = indiaStatList.stateName
        holder.txtStateConfirmed.text = indiaStatList.confirmed
        holder.txtStateRecovered.text = indiaStatList.recovered
        holder.txtStateDeaths.text = indiaStatList.deaths
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
                    val resultList = ArrayList<ModelIndia>()
                    for (row in itemList) {
                        if (row.stateName.toUpperCase(Locale.ROOT).contains(charSearch.toUpperCase(Locale.ROOT))) {
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
                itemFilterList = results?.values as ArrayList<ModelIndia>
                notifyDataSetChanged()
            }

        }
    }

}