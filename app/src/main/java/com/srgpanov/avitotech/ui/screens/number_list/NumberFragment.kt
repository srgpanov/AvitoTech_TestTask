package com.srgpanov.avitotech.ui.screens.number_list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.srgpanov.avitotech.App
import com.srgpanov.avitotech.data.NumberItem
import com.srgpanov.avitotech.databinding.FragmentNumbersBinding
import com.srgpanov.avitotech.databinding.ItemNumberBinding
import com.srgpanov.avitotech.other.extension.addSystemWindowInsetToPadding
import com.srgpanov.avitotech.other.inflateBinding
import javax.inject.Inject

class NumberFragment : Fragment() {
    private var _binding: FragmentNumbersBinding? = null
    private val binding: FragmentNumbersBinding
        get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: NumberViewModel by viewModels(factoryProducer = { factory })

    private val adapter = NumberAdapter()

    companion object {
        const val TAG = "NumberFragment"
        private const val ROW_SIZE_PORTRAIT = 2
        private const val ROW_SIZE_LANDSCAPE = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.injectNumberFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = container?.inflateBinding(FragmentNumbersBinding::inflate)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsets()
        setupRv()
        observeViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        adapter.listener = { binding: ItemNumberBinding, item: NumberItem ->
            binding.btn.setOnClickListener {
                viewModel.removeItem(item)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.numbers.observe(viewLifecycleOwner) { list ->
            adapter.submitList(ArrayList(list))
        }

    }

    private fun setupInsets() {
        if (isPortraitOrientation()) {
            binding.rv.addSystemWindowInsetToPadding(top = true, bottom = true)
        } else {
            val rotation: Int = requireActivity().windowManager.defaultDisplay.rotation
            when (rotation) {
                Surface.ROTATION_90 -> {
                    binding.rv.addSystemWindowInsetToPadding(top = true, right = true)
                }
                Surface.ROTATION_270 -> {
                    binding.rv.addSystemWindowInsetToPadding(top = true, left = true)
                }
            }
        }
    }

    private fun setupRv() {
        val span = getSpanCount()
        val layoutManager = GridLayoutManager(requireContext(), span)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = layoutManager
        val decorator = Decorator(span)
        binding.rv.addItemDecoration(decorator)
    }

    private fun getSpanCount(): Int = if (isPortraitOrientation()) {
        ROW_SIZE_PORTRAIT
    } else {
        ROW_SIZE_LANDSCAPE
    }

    private fun isPortraitOrientation(): Boolean {
        return requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}