package com.eloinavarro.holocron.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.FragmentListBinding
import com.eloinavarro.holocron.domain.SWCharacter
import com.eloinavarro.holocron.ui.detail.CharacterDetailFragment

class CharacterListFragment : Fragment(R.layout.fragment_list), CharacterGridContract.View {

    private lateinit var binding:FragmentListBinding
    private val presenter = CharacterGridPresenter(this)
    private val adapter = SWCharacterAdapter(emptyList()){
        goToDetail(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view).apply {
            toolbar.title = "The Holocron"
            recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = recycler.adapter ?: adapter
        }
        presenter.setup()
    }

    override fun populateCharacters(swCharacters: List<SWCharacter>) {
        val adapter = binding.recycler.adapter as SWCharacterAdapter
        adapter.addItemsToList(swCharacters)
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.recycler.adapter = adapter
    }

    private fun goToDetail(character: SWCharacter) {
        val destination = CharacterDetailFragment.newInstance(character)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_holder, destination)
            .addToBackStack("list")
            .commit()
    }

}