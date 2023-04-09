package com.example.cutonapplication.presentation.ui.fragments_home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cutonapplication.databinding.FragmentStandardBinding
import com.example.cutonapplication.domain.entities.Menu
import com.example.cutonapplication.domain.use_cases.AccountFactory.Companion.USER_ACCOUNT
import com.example.cutonapplication.presentation.adapter.MenuItemsAdapter
import com.example.cutonapplication.presentation.ui.ExitActivity
import com.example.cutonapplication.presentation.ui.ExitActivity.Companion.SELECTED_ADDRESS
import com.example.cutonapplication.presentation.ui.ExitActivity.Companion.SELECTED_TOKEN
import com.example.cutonapplication.presentation.ui.HomeActivity
import com.example.cutonapplication.presentation.viewmodel.HomeViewModel
import com.example.cutonapplication.presentation.viewmodel.factory.HomeViewModelFactory


class StandardFragment : Fragment() {

    private var _binding: FragmentStandardBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuItemRecyclerView: RecyclerView
    private lateinit var menuItemsAdapter: MenuItemsAdapter

    private lateinit var address: String
    private lateinit var token: String

    private val sharedViewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(USER_ACCOUNT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        address = requireArguments().getString(HomeActivity.CURRENT_ADDRESS).toString()
        token = requireArguments().getString(HomeActivity.CURRENT_TOKEN).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStandardBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@StandardFragment
        }

        menuItemRecyclerView = binding!!.recyclerViewMenu
        menuItemRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        sharedViewModel.getListMenuItems(address, token).observe(this.viewLifecycleOwner, Observer{ it ->
            val list = ArrayList<Menu.MenuItem>()
            list.add(it!!.menuItem)

            menuItemsAdapter = MenuItemsAdapter(list, this.requireContext(), address, token)

            menuItemRecyclerView.adapter = menuItemsAdapter
        })

    }

    fun closeApplication(){
        val intent = Intent(requireContext(), ExitActivity::class.java)
        intent.putExtra(SELECTED_ADDRESS, address)
        intent.putExtra(SELECTED_TOKEN, token)
        requireContext().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}