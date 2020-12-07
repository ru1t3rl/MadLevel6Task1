package tech.ru1t3rl.madlevel6task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import tech.ru1t3rl.madlevel6task1.databinding.FragmentColorBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ColorFragment : Fragment() {

    private val viewModel: ColorViewModel by viewModels()

    private val colors = arrayListOf<ColorItem>()
    private lateinit var colorAdapter: ColorAdapter


    private var _binding: FragmentColorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentColorBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorAdapter = ColorAdapter(colors, ::onColorClick)
        binding.rvColors.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvColors.adapter = colorAdapter

        observeColors()

    }

    private fun observeColors() {
        viewModel.colorItems.observe(viewLifecycleOwner, Observer {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }



    private fun onColorClick(colorItem: ColorItem) {
        Snackbar.make(binding.rvColors, "This color is: ${colorItem.name}", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}