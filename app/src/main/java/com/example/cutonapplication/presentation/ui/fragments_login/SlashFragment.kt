package com.example.cutonapplication.presentation.ui.fragments_login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.cutonapplication.R

class SlashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Handler(Looper.myLooper()!!).postDelayed({
            Navigation.findNavController(requireView()).navigate(R.id.action_slashFragment_to_exitFragment)
        }, 5000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slash, container, false)
    }

    companion object {
    }
}