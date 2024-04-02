package com.eloinavarro.holocron.ui.list

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.ActivityMainBinding
import com.eloinavarro.holocron.domain.SWCharacter

class CharacterListActivity : AppCompatActivity(R.layout.activity_main),
    CharacterGridContract.View {

    private val presenter = CharacterGridPresenter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        presenter.setup()
    }

    override fun populateCharacters(swCharacters: List<SWCharacter>) {
        val adapter =
            binding.recycler.adapter as CharacterGridAdapter? ?: CharacterGridAdapter(swCharacters)
        binding.recycler.adapter = adapter
    }

}