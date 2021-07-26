package com.project.leverxtask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.project.leverxtask.databinding.DetailNewsFragmentBinding
import com.project.leverxtask.repository.model.Link

class DetailNewsViewPagerFragment : Fragment() {

    private var _binding: DetailNewsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var links: Link

    companion object {
        fun newInstance(link: Link): DetailNewsViewPagerFragment {
            val fragment = DetailNewsViewPagerFragment()
            if (link != null) {
                val bundle = Bundle()
                bundle.putParcelable("links", link)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailNewsFragmentBinding.inflate(inflater, container, false)

        showResults()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            links = arguments?.getParcelable("links")!!
        }
    }

    private fun showResults() {
        if (links != null) {
            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(links.url!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}