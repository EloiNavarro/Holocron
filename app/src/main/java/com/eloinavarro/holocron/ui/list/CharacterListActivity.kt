package com.eloinavarro.holocron.ui.list

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.ActivityMainBinding
import com.eloinavarro.holocron.domain.SWCharacter

class CharacterListActivity : AppCompatActivity(R.layout.activity_main),
    CharacterGridContract.View {

    private val presenter = CharacterGridPresenter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter.setup()
    }

    override fun populateCharacters(swCharacters: List<SWCharacter>) {
        val layoutManager = GridLayoutManager(this, 2)
        binding.recycler.layoutManager = layoutManager
        val adapter =
            binding.recycler.adapter as CharacterGridAdapter? ?: CharacterGridAdapter(swCharacters)
        binding.recycler.adapter = adapter
        binding.recycler.scheduleLayoutAnimation()
    }

}