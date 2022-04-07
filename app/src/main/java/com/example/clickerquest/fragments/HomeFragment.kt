package com.example.clickerquest.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.clickerquest.Monster
import com.example.clickerquest.R
import com.example.clickerquest.Settings
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class HomeFragment : Fragment() {

    lateinit var activesetting2: ImageButton
    lateinit var imageView2:ImageView
    lateinit var stage_number: TextView
    lateinit var monster_health:TextView
    lateinit var gold_count:TextView
    lateinit var monster_name:TextView
    lateinit var attack_power_count:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init objects
        activesetting2 = view.findViewById(R.id.activesetting2)
        imageView2 = view.findViewById(R.id.imageView2)
        stage_number = view.findViewById(R.id.stage_number)
        monster_health = view.findViewById(R.id.monster_health)
        gold_count = view.findViewById(R.id.gold_Count)
        monster_name = view.findViewById(R.id.monster_name)
        attack_power_count = view.findViewById(R.id.attack_power_Count)

        getUsers()
        getMonsters(stage)

        imageView2.setOnClickListener {
            currenthp -= attackpower
            monster_health.text = currenthp.toString()
            Log.i("Monster", "is clicked")

            if(currenthp <= 0){
                Log.i("Monster", "is killed")
                stage++
                stageprogress++
                player_gold += award_gold
                gold_count.text = player_gold.toString()
                Log.i("User", "+ $player_gold")
                awardGold(player_gold)
                if(stage > 5){
                    stage = 1
                }
                getMonsters(stage)
            }
        }

        activesetting2.setOnClickListener {
            val intent = Intent(context, Settings::class.java)
            context?.startActivity(intent)
        }

    }

    private fun awardGold(playerGold: Int) {
        val user = ParseUser.getCurrentUser()
        user.put("gold", playerGold)
        user.put("stage_progress", stageprogress)
        user.put("stage_cycle", stage)
        user.saveInBackground()
    }

    private fun getUsers(){
        val queryPlayer = ParseUser.getQuery()
        queryPlayer.whereEqualTo("username", (ParseUser.getCurrentUser()).username)

        queryPlayer.findInBackground { objects, e ->
            if (e != null) {
                Log.e("User", "Error getting user $e")
            } else {
                val results = queryPlayer.find()
                if (results.isNotEmpty()) {
                    val objectId = results[0].objectId
                    Log.i("User", "$objectId")

                    //player
                    playerlvl = results[0].getInt("level")
                    player_gold = results[0].getInt("gold")
                    gold_count.text = (results[0].getInt("gold")).toString()
                    attackpower = results[0].getInt("attack_power")
                    attack_power_count.text = (results[0].getInt("attack_power")).toString()

                    stageprogress = results[0].getInt("stage_progress")
                    stage = results[0].getInt("stage_cycle")
                }
                else{
                    Log.e("User", "cannot find user")
                }
            }
        }
    }


    private fun getMonsters(stage: Int){

        stage_number.text = "Stage " + stageprogress.toString()

        val query: ParseQuery<Monster> = ParseQuery.getQuery(Monster::class.java)
        query.whereEqualTo(Monster.MONSTER_STAGE, stage)
        query.findInBackground(object: FindCallback<Monster> {
            override fun done(objects: MutableList<Monster>?, e: ParseException?) {
                if (e != null) {
                    Log.e("Monsters", "Error getting monsters $e")
                } else {
                    val results = query.find()
                    if (results.isNotEmpty()) {
                        val objectId = results[0].objectId
                        Log.i("Monsters", "$objectId")

                        //monster
                        monster_name.text = results[0].getName()
                        currenthp = results[0].getHealth()
                        currenthp += 10* playerlvl
                        monster_health.text = currenthp.toString()
                        award_gold = results[0].getGold()
                        view?.let {
                            Glide.with(it.context).load(results[0].getImage()?.url).into(imageView2)
                        }

                    }
                }
            }
        })
    }

    companion object{
        //monster + stage info
        var stage = 1
        var currenthp = 1
        var attackpower = 1
        var playerlvl = 1
        var player_gold = 0
        var award_gold = 0
        var stageprogress = 1
    }
}
