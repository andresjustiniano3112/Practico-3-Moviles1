import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.practicanavegacion.ui.adapters.LikedPersonAdapter
import com.example.practicanavegacion.viewmodel.PersonViewModel
import com.example.practicanavegacion.R


class SecondFragment : Fragment() {

    private lateinit var personViewModel: PersonViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LikedPersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewLikes)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = LikedPersonAdapter()
        recyclerView.adapter = adapter

        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)
        personViewModel.likesList.observe(viewLifecycleOwner) { likedPersons ->
            adapter.submitList(likedPersons)
        }

        return view
    }
}
