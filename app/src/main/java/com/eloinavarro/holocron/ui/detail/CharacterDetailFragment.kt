package com.eloinavarro.holocron.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eloinavarro.holocron.R
import com.eloinavarro.holocron.databinding.FragmentDetailBinding
import com.eloinavarro.holocron.domain.SWCharacter
import com.squareup.picasso.Picasso

class CharacterDetailFragment: Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    companion object {
        private val NAME = "name"
        private val DESC = "description"
        private val IMG = "image"
        fun newInstance(character: SWCharacter):CharacterDetailFragment {
            return CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, character.name)
                    putString(DESC, character.description)
                    putString(IMG, character.image)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(arguments){
            binding.toolbar.title = this?.getString(NAME, "")
            binding.txtCharacterDescription.text = this?.getString(DESC, "")
            Picasso.get().load(this?.getString(IMG, "")).placeholder(R.color.gray500).into(binding.imgCharacter)
        }
    }
}