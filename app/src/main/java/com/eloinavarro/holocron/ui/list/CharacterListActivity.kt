package com.eloinavarro.holocron.ui.list

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.ActivityMainBinding
import com.eloinavarro.holocron.domain.SWCharacter

class CharacterListActivity : AppCompatActivity(R.layout.activity_main),
    CharacterGridContract.View {

    private val presenter = CharacterGridPresenter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        presenter.setup()
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycler.layoutManager = layoutManager
        val adapter = CharacterGridAdapter(emptyList())
        binding.recycler.adapter = adapter
    }

    override fun populateCharacters(swCharacters: List<SWCharacter>) {
        val adapter = binding.recycler.adapter as CharacterGridAdapter
        adapter.addItemsToList(swCharacters)
        binding.recycler.adapter = adapter
        binding.recycler.scheduleLayoutAnimation()
    }

}