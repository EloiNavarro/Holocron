package com.eloinavarro.holocron.ui.test

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.ActivityTestBinding
import com.eloinavarro.holocron.ui.list.CharacterListActivity


class ActivityTest: AppCompatActivity(R.layout.activity_test) {

    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        binding.btnTest.setOnClickListener {
            startNextActivity()
        }
    }

    private fun startNextActivity() {
        val myIntent = Intent(this@ActivityTest, CharacterListActivity::class.java)
        this@ActivityTest.startActivity(myIntent)
    }
}