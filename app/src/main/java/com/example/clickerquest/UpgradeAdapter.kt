package com.example.clickerquest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clickerquest.fragments.HomeFragment
import com.example.clickerquest.fragments.UpgradeFragment
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class UpgradeAdapter (val context: Context, val upgrades: List<Upgrades>): RecyclerView.Adapter<UpgradeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpgradeAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.upgrade_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val screen = upgrades[position]
        holder.bind(screen)
    }

    override fun getItemCount(): Int {
        return upgrades.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description: TextView = itemView.findViewById(R.id.upgrade_description)
        val APamount: TextView = itemView.findViewById(R.id.upgrade_cost_description)
        val upgradeButton: Button = itemView.findViewById(R.id.upgrade_button)

        fun bind(up: Upgrades) {
            val amount = up.getAmount()
            val cost = up.getCost()
            description.text = up.getDesc() + "\n COST: $cost"
            APamount.text = "+$amount AP"
            upgradeButton.text = "BUY"

            upgradeButton.setOnClickListener {
                HomeFragment.player_gold -= cost
                UpgradeFragment.gold_text = HomeFragment.player_gold.toString()
                HomeFragment.attackpower += amount
                updatePlayer(HomeFragment.player_gold, HomeFragment.attackpower)
            }
        }

        private fun updatePlayer(playerGold: Int, attackpower: Int) {
            val user = ParseUser.getCurrentUser()
            user.put("gold", playerGold)
            user.put("attack_power", attackpower)
            user.saveInBackground()

            UpgradeFragment.gold_count.text = playerGold.toString()
        }
    }
}
