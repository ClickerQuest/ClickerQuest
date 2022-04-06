package com.example.clickerquest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickerquest.R
import com.example.clickerquest.UpgradeAdapter
import com.example.clickerquest.Upgrades
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class UpgradeFragment : Fragment() {

    lateinit var upgradesRV: RecyclerView

    lateinit var  adapter: UpgradeAdapter

    var allUpgrades: MutableList<Upgrades> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upgrade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upgradesRV = view.findViewById(R.id.upgrade_rows)

        adapter = UpgradeAdapter(requireContext(), allUpgrades)
        upgradesRV.adapter = adapter

        upgradesRV.layoutManager = LinearLayoutManager(requireContext())

        gold_count = view.findViewById(R.id.Gold_Count)

        queryUpgrades()
    }

    open fun queryUpgrades() {
        val query: ParseQuery<Upgrades> = ParseQuery.getQuery(Upgrades::class.java)
        query.addDescendingOrder("cost")
        query.findInBackground(object: FindCallback<Upgrades> {
            override fun done(objects: MutableList<Upgrades>?, e: ParseException?) {
                if(e != null) {
                    Log.e("Upgrade", "Error fetching upgrades")
                }
                else {
                    if(objects != null) {
                        allUpgrades.clear()

                        allUpgrades.addAll(objects)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })

        gold_text = HomeFragment.player_gold.toString()
        gold_count.text = gold_text.toString()
    }

    companion object{
        lateinit var gold_count: TextView
        var gold_text = ""
    }

}
