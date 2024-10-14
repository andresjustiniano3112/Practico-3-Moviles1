package com.example.practicanavegacion.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.practicanavegacion.R
import com.example.practicanavegacion.ui.adapters.GalleryAdapter
import com.example.practicanavegacion.model.Person
import com.example.practicanavegacion.viewmodel.PersonViewModel


class FirstFragment : Fragment() {

    private lateinit var btnGotoSecondFragment : Button
    private lateinit var personViewModel: PersonViewModel
    private lateinit var likeButton: Button
    private lateinit var dislikeButton: Button
    private lateinit var viewPagerGallery: ViewPager2
    private lateinit var nameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        likeButton = view.findViewById(R.id.likeButton)
        dislikeButton = view.findViewById(R.id.dislikeButton)
        viewPagerGallery = view.findViewById(R.id.viewPagerGallery)
        nameTextView = view.findViewById(R.id.nameTextView)
        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)

        btnGotoSecondFragment = view.findViewById(R.id.btnGoToSecondFragment)

        return view
    }

    private fun setupEventListeners(person: Person) {

        likeButton.setOnClickListener {
            personViewModel.likePerson(person)
            updateNextPerson()
        }

        // Acción para el botón "Dislike"
        dislikeButton.setOnClickListener {
            personViewModel.dislikePerson(person)
            updateNextPerson()
        }

        btnGotoSecondFragment.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel.personList.observe(viewLifecycleOwner) { personList ->
            if (personList.isNotEmpty()) {
                val person = personList[0]
                val galleryAdapter = GalleryAdapter(person.photos)
                viewPagerGallery.adapter = galleryAdapter
                nameTextView.text = person.name

                setupEventListeners(person)
            } else {
                nameTextView.text = "Se acabaron las mujeres bro"
                viewPagerGallery.adapter = null
            }
        }
    }


    private fun updateNextPerson() {
        val updatedList = personViewModel.personList.value ?: emptyList()
        if (updatedList.isNotEmpty()) {
            val nextPerson = updatedList[0]
            val galleryAdapter = GalleryAdapter(nextPerson.photos)
            viewPagerGallery.adapter = galleryAdapter
            nameTextView.text = nextPerson.name
        } else {
            nameTextView.text = "Se acabaron las mujeres bro"
            viewPagerGallery.adapter = null
        }
    }

}

