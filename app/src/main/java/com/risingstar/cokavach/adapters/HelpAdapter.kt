package com.risingstar.cokavach.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.cokavach.R
import com.risingstar.cokavach.models.ModelHelp


class HelpAdapter(var context: Context, val itemList: ArrayList<ModelHelp>) : RecyclerView.Adapter<HelpAdapter.HelpViewHolder>() {

    class HelpViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvSno : TextView = view.findViewById(R.id.tvSno)
        var tvStatename : TextView = view.findViewById(R.id.tvStatename)
        var tvPhno : TextView = view.findViewById(R.id.tvPhno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.help_recycler_single_row, parent, false)
        return HelpViewHolder(view)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        val helplineNumber = itemList[position]

        holder.tvSno.text = helplineNumber.sno.toString()
        holder.tvStatename.text = helplineNumber.stateName
        holder.tvPhno.text = helplineNumber.phoneNumber

        holder.tvPhno.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${helplineNumber.phoneNumber}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}