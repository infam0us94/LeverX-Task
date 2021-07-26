package com.project.leverxtask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.leverxtask.databinding.ContainerFragmentBinding
import com.project.leverxtask.repository.model.Link
import java.util.*

class ContainerFragment : Fragment() {

    private var _binding: ContainerFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailsNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContainerFragmentBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun init(
        links: ArrayList<Link>
    ) {
        val fragments: ArrayList<Fragment> = ArrayList<Fragment>()
        for (link in links) {
            val fragment: DetailNewsViewPagerFragment =
                DetailNewsViewPagerFragment.newInstance(link)
            fragments.add(fragment)
        }
        val pagerAdapter = DetailNewsPagerAdapter(parentFragmentManager, fragments)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailsNewsViewModel::class.java)
        viewModel.getDetail().observe(viewLifecycleOwner, detailNewsObserver)
    }

    private val detailNewsObserver = Observer<ArrayList<Link>> {
        it.let { result ->
            if (result != null) {
                init(result)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}