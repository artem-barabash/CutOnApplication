package com.example.cutonapplication.presentation.ui.fragments_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cutonapplication.R
import com.example.cutonapplication.data.retrofit.ItemsRequest
import com.example.cutonapplication.databinding.FragmentCatalogBinding
import com.example.cutonapplication.domain.entities.CatalogBrands
import com.example.cutonapplication.domain.use_cases.AccountFactory
import com.example.cutonapplication.domain.use_cases.AccountFactory.Companion.USER_ACCOUNT
import com.example.cutonapplication.presentation.adapter.BrandItemsAdapter
import com.example.cutonapplication.presentation.ui.HomeActivity
import com.example.cutonapplication.presentation.viewmodel.HomeViewModel
import com.example.cutonapplication.presentation.viewmodel.factory.HomeViewModelFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogFragment : Fragment() {
    private val sharedViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(USER_ACCOUNT)
    }

    private var _binding: FragmentCatalogBinding? = null

    private val binding get() = _binding!!

    private lateinit var address: String
    private lateinit var token: String

    private lateinit var brandRecyclerView: RecyclerView
    private lateinit var brandItemsAdapter: BrandItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        address = requireArguments().getString(HomeActivity.CURRENT_ADDRESS).toString()
        token = requireArguments().getString(HomeActivity.CURRENT_TOKEN).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@CatalogFragment
        }

        brandRecyclerView = binding!!.recyclerViewBrands
        brandRecyclerView.layoutManager = GridLayoutManager(requireContext(),2,
            GridLayoutManager.VERTICAL,  false)

        val itemsRequest = ItemsRequest(address)

        itemsRequest.apiItems.getCatalogBrands(token).enqueue(object : Callback<CatalogBrands>{
            override fun onResponse(call: Call<CatalogBrands>, response: Response<CatalogBrands>) {
                if(response.isSuccessful){
                    val map = response.body()!!.map.toList()

                    brandItemsAdapter = BrandItemsAdapter(map, requireContext())

                    brandRecyclerView.adapter = brandItemsAdapter
                }
            }

            override fun onFailure(call: Call<CatalogBrands>, t: Throwable) {
                println("No, it is not list here.")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}