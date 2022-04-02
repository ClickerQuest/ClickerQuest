package com.example.clickerquest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.clickerquest.Monster
import com.example.clickerquest.R
import com.parse.*

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

        var monster = Monster()
        var stage = 1

        getMonsters(stage)

        imageView2.setOnClickListener {

            onImageClicked()
        }

    }

    private fun onImageClicked() {
        Log.i("img", "is clicked")
    }

    open fun getMonsters(stage: Int){
        val query: ParseQuery<Monster> = ParseQuery.getQuery(Monster::class.java)
        query.whereEqualTo(Monster.MONSTER_STAGE, stage)
        query.findInBackground(object: FindCallback<Monster> {
            override fun done(objects: MutableList<Monster>?, e: ParseException?) {
                if(e != null) {
                    Log.e("Monsters", "Error getting monsters $e")
                }
                else {
                    //    monster_health.text = Monster.MONSTER_HEALTH
                    //    stage_number.text = Monster.MONSTER_STAGE
                }
            }
        })
    }
}
