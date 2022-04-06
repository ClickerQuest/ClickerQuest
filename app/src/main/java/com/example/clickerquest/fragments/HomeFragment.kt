package com.example.clickerquest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.clickerquest.Monster
import com.example.clickerquest.R
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

        getMonsters(stage)

        imageView2.setOnClickListener {
            onImageClicked()
        }

    }

    private fun onImageClicked() {
        if(stage > 5){
            stage = 1
        }

        currenthp--
        monster_health.text = currenthp.toString()
        Log.i("img", "is clicked")

        if(currenthp == 0){
            Log.i("img", "monster killed")
            stage++
            getMonsters(stage)
        }
    }

    open fun getMonsters(stage: Int){
        val query: ParseQuery<Monster> = ParseQuery.getQuery(Monster::class.java)
        val query_player = ParseUser.getQuery()
        query.whereEqualTo(Monster.MONSTER_STAGE, stage)
        query_player.whereEqualTo("username", ParseUser.getCurrentUser())
        query.findInBackground(object: FindCallback<Monster> {
            override fun done(objects: MutableList<Monster>?, e: ParseException?) {
                if (e != null) {
                    Log.e("Monsters", "Error getting monsters $e")
                } else {
                    val results = query.find()
                    if (!results.isEmpty()) {
                        val objectId = results[0].objectId
                        Log.i("Monsters", "$objectId")

                        //monster
                        monster_name.text = results[0].getName()
                        monster_health.text = results[0].getHealth().toString()
                        currenthp = results[0].getHealth()
                        view?.let {
                            Glide.with(it.context).load(results[0].getImage()?.url).into(imageView2)
                        }

                    }
                }
            }
        })
        query_player.findInBackground { objects, e ->
            if (e != null) {
                Log.e("User", "Error getting user $e")
            } else {
                val results = query.find()
                if (!results.isEmpty()) {
                    val objectId = results[0].objectId
                    Log.i("User", "$objectId")

                    //player
                    player_gold = results[0].getInt("coins")
                    gold_count.text = (results[0].getInt("coins")).toString()
                    attack_power_count.text = (results[0].getInt("attack_power")).toString()
                }
            }
        }
    }

    companion object{
        //monster + stage info
        var stage = 1
        var currenthp = 1
        var attackpower = 1
        var playerlvl = 1
        var player_gold = 0
    }
}
