package com.example.practicanavegacion.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicanavegacion.R
import com.example.practicanavegacion.databinding.FragmentFirstBinding
import com.example.practicanavegacion.model.Person


class PersonAdapter(private val onClick: (Person, Boolean) -> Unit) :
    ListAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = FragmentFirstBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }

    inner class PersonViewHolder(private val binding: FragmentFirstBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.nameTextView.text = person.name

            val galleryAdapter = GalleryAdapter(person.photos)
            binding.viewPagerGallery.adapter = galleryAdapter

            binding.likeButton.setOnClickListener {
                onClick(person, true)
            }

            binding.dislikeButton.setOnClickListener {
                onClick(person, false)
            }
        }
    }
}



class GalleryAdapter(private val photoList: List<Int>) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photoResId = photoList[position]
        Glide.with(holder.itemView.context)
            .load(photoResId)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = photoList.size

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}





class LikedPersonAdapter : RecyclerView.Adapter<LikedPersonAdapter.LikedPersonViewHolder>() {

    private var likedPersons: List<Person> = emptyList()

    fun submitList(persons: List<Person>) {
        likedPersons = persons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedPersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_likes, parent, false)
        return LikedPersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikedPersonViewHolder, position: Int) {
        val person = likedPersons[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = likedPersons.size

    inner class LikedPersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewProfile)
        private val textView: TextView = itemView.findViewById(R.id.textViewName)

        fun bind(person: Person) {
            textView.text = person.name
            Glide.with(itemView.context)
                .load(person.photos.first())
                .circleCrop()
                .into(imageView)
        }
    }
}



class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        // Define cómo saber si dos objetos son el mismo
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        // Define cómo saber si el contenido es el mismo
        return oldItem == newItem
    }
}




