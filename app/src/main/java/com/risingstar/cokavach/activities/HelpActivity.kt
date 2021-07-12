package com.risingstar.cokavach.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.cokavach.models.ModelHelp
import com.risingstar.cokavach.R
import com.risingstar.cokavach.adapters.HelpAdapter

class HelpActivity : AppCompatActivity() {
    //lateinit var txtHelpline : TextView
    lateinit var recyclerHelp : RecyclerView
    lateinit var recyclerHelpAdapter: HelpAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val helplineNumbers = arrayListOf<ModelHelp>(
                ModelHelp(1,"Andhra Pradesh","0866-2410978"),
                ModelHelp(2,"Arunachal Pradesh", "9436055743"),
                ModelHelp(3,"Assam" ,"6913347770"),
                ModelHelp(4,"Bihar", "104"),
                ModelHelp(5,"Chhattisgarh" ,"104"),
                ModelHelp(6,"Goa" ,"104"),
                ModelHelp(7,"Gujarat" ,"104"),
                ModelHelp(8,"Haryana" ,"8558893911"),
                ModelHelp(9,"Himachal Pradesh", "104"),
                ModelHelp(10,"Jharkhand", "104"),
                ModelHelp(11,"Karnataka" ,"104"),
                ModelHelp(12,"Kerala" ,"0471-2552056"),
                ModelHelp(13,"Madhya Pradesh" ,"104"),
                ModelHelp(14,"Maharashtra" ,"020-26127394"),
                ModelHelp(15,"Manipur", "3852411668"),
                ModelHelp(16,"Meghalaya" ,"108"),
                ModelHelp(17,"Mizoram", "102"),
                ModelHelp(18,"Nagaland" ,"7005539653"),
                ModelHelp(19,"Odisha", "9439994859"),
                ModelHelp(20,"Punjab", "104"),
                ModelHelp(21,"Rajasthan", "0141-2225624"),
                ModelHelp(22,"Sikkim", "104"),
                ModelHelp(23,"Tamil Nadu", "044-29510500"),
                ModelHelp(24,"Telangana", "104"),
                ModelHelp(25,"Tripura" ,"0381-2315879"),
                ModelHelp(26,"Uttarakhand" ,"104"),
                ModelHelp(27,"Uttar Pradesh" ,"18001805145"),
                ModelHelp(28,"West Bengal", "03323412600"),
                ModelHelp(29,"Andaman and Nicobar Islands" ,"03192-232102"),
                ModelHelp(30,"Chandigarh" ,"9779558282"),
                ModelHelp(31,"Dadra and Nagar Haveli and Daman & Diu" ,"104"),
                ModelHelp(32,"Delhi", "011-22307145"),
                ModelHelp(33,"Jammu & Kashmir" ,"01912520982"),
                ModelHelp(34,"Ladakh" ,"01982256462"),
                ModelHelp(35,"Lakshadweep" ,"104"),
                ModelHelp(36,"Puducherry", "104")
        )

        recyclerHelp = findViewById(R.id.recyclerHelp)
        recyclerHelpAdapter = HelpAdapter(this@HelpActivity,helplineNumbers)
        layoutManager = LinearLayoutManager(this@HelpActivity)

        recyclerHelp.adapter = recyclerHelpAdapter
        recyclerHelp.layoutManager = layoutManager
//        txtHelpline = findViewById(R.id.txtHelpline)
//        txtHelpline.text = "1 Andhra Pradesh 0866-2410978\n" +
//                "2 Arunachal Pradesh 9436055743\n" +
//                "3 Assam 6913347770\n" +
//                "4 Bihar 104\n" +
//                "5 Chhattisgarh 104\n" +
//                "6 Goa 104\n" +
//                "7 Gujarat 104\n" +
//                "8 Haryana 8558893911\n" +
//                "9 Himachal Pradesh 104\n" +
//                "10 Jharkhand 104\n" +
//                "11 Karnataka 104\n" +
//                "12 Kerala 0471-2552056\n" +
//                "13 Madhya Pradesh 104\n" +
//                "14 Maharashtra 020-26127394\n" +
//                "15 Manipur 3852411668\n" +
//                "16 Meghalaya 108\n" +
//                "17 Mizoram 102\n" +
//                "18 Nagaland 7005539653\n" +
//                "19 Odisha 9439994859\n" +
//                "20 Punjab 104\n" +
//                "21 Rajasthan 0141-2225624\n" +
//                "22 Sikkim 104\n" +
//                "23 Tamil Nadu 044-29510500\n" +
//                "24 Telangana 104\n" +
//                "25 Tripura 0381-2315879\n" +
//                "26 Uttarakhand 104\n" +
//                "27 Uttar Pradesh 18001805145\n" +
//                "28 West Bengal 1800313444222, 03323412600\n"+
//                "29 Andaman and Nicobar Islands 03192-232102\n" +
//                "30 Chandigarh 9779558282\n" +
//                "31 Dadra and Nagar Haveli and Daman & Diu 104\n" +
//                "32 Delhi 011-22307145\n" +
//                "33 Jammu & Kashmir 01912520982, 0194-2440283\n" +
//                "34 Ladakh 01982256462\n" +
//                "35 Lakshadweep 104\n" +
//                "36 Puducherry 104"


    }
}
