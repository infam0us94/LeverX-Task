package com.project.leverxtask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.leverxtask.R
import com.project.leverxtask.databinding.DetailNewsFragmentBinding
import com.project.leverxtask.repository.model.DetailNews

class DetailNewsFragment : Fragment() {

    private var _binding: DetailNewsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailsNewsViewModel

    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailNewsFragmentBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("link").toString()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailsNewsViewModel::class.java)
        viewModel.getDetail(url).observe(viewLifecycleOwner, detailNewsObserver)
    }

    private val detailNewsObserver = Observer<DetailNews> {
        it.let { result ->
            if (result != null) {
                binding.mainTitle.text = result.main_title
                binding.title.text = result.title
                binding.desc.text = result.desc
                    Glide.with(this)
                        .load(result.image)
                        .placeholder(R.drawable.dev_by)
                        .into(binding.image)
                    binding.progressBar.visibility = View.GONE

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}