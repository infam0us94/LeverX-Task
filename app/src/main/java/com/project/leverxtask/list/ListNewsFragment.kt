package com.project.leverxtask.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.leverxtask.adapter.DataAdapter
import com.project.leverxtask.databinding.ListNewsFragmentBinding
import com.project.leverxtask.repository.model.News

class ListNewsFragment : Fragment() {

    private var _binding: ListNewsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DataAdapter
    private lateinit var recView: RecyclerView
    private lateinit var viewModel: ListNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListNewsFragmentBinding.inflate(inflater, container, false)

        initRecView()

        initViewModel()

        return binding.root
    }

    private fun initRecView() {
        recView = binding.recView
        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = DataAdapter()
        recView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ListNewsViewModel::class.java)
        viewModel.getListNews().observe(viewLifecycleOwner, listNewsObserver)
    }

    private val listNewsObserver = Observer<List<News>> {
        it.let { result ->
            if (result != null) {
                adapter.setNews(result)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}