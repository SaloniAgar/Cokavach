package com.risingstar.cokavach.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.risingstar.cokavach.R


class VaccinationFragment : Fragment() {


    lateinit var registerVaccination : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vaccination, container, false)

        registerVaccination = view.findViewById(R.id.vaccinationData)

        registerVaccination.setOnClickListener {
            Toast.makeText(activity as Context,"Data will be added soon.", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}