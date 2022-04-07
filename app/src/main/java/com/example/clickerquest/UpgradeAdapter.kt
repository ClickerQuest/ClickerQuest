package com.example.clickerquest

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clickerquest.fragments.HomeFragment
import com.example.clickerquest.fragments.UpgradeFragment
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
        val LvLamount: TextView = itemView.findViewById(R.id.upgrade_amount)
        val upgradeButton: Button = itemView.findViewById(R.id.upgrade_button)

        fun bind(up: Upgrades) {
            var amount = up.getAmount()
            var cost = up.getCost() * HomeFragment.playerlvl
            description.text = up.getDesc() + "\n COST: $cost"
            LvLamount.text = "+$amount lvl"
            upgradeButton.text = "BUY"

            upgradeButton.setOnClickListener {

                val currentGold = HomeFragment.player_gold

                if((currentGold - cost)  < 0){
                    val alert = AlertDialog.Builder(itemView.context)
                    alert.setTitle("Out of Gold!")
                    alert.setMessage("cannot afford purchase")
                    alert.setPositiveButton("ok"){dialogInterface, which ->
                        Toast.makeText(itemView.context,"You need more gold!",Toast.LENGTH_LONG).show()
                    }

                    val alertDialog: AlertDialog = alert.create()
                    // Set other dialog properties
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
                else {
                    if(up.getUpgradeTag() == "level"){
                        var player_ap = amount * 2 + (HomeFragment.attackpower)

                        HomeFragment.player_gold -= cost
                        UpgradeFragment.gold_text = HomeFragment.player_gold.toString()
                        HomeFragment.playerlvl += amount
                        updatePlayer(HomeFragment.player_gold, HomeFragment.playerlvl, player_ap)

                        cost = up.getCost() * HomeFragment.playerlvl

                        description.text = up.getDesc() + "\n COST: $cost"
                    }
                }
            }
        }

        private fun updatePlayer(playerGold: Int, playerlvl: Int, player_ap: Int) {
            val user = ParseUser.getCurrentUser()
            user.put("gold", playerGold)
            user.put("level", playerlvl)
            user.put("attack_power", player_ap)
            user.saveInBackground()

            UpgradeFragment.gold_count.text = playerGold.toString()
        }
    }
}
