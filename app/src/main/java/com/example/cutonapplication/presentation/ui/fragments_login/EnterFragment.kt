package com.example.cutonapplication.presentation.ui.fragments_login

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cutonapplication.R
import com.example.cutonapplication.databinding.FragmentEnterBinding
import com.example.cutonapplication.presentation.ui.HomeActivity
import com.example.cutonapplication.presentation.ui.HomeActivity.Companion.CURRENT_ADDRESS
import com.example.cutonapplication.presentation.ui.HomeActivity.Companion.CURRENT_TOKEN
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel


class EnterFragment() : Fragment() {
    private var _binding: FragmentEnterBinding? = null

    private val binding get() =_binding

    private val sharedViewModel: LoginViewModel by activityViewModels()

    private lateinit var textPhone:EditText
    private lateinit var textPassword:EditText
    private lateinit var textStatus: TextView

    private lateinit var buttonLogin:Button

    override fun onStart() {
        super.onStart()
        textPhone.text.clear()
        textPassword.text.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)

        val root:View = binding!!.root

        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fragment = this@EnterFragment
        }
        init()

        buttonLogin.setOnClickListener {
            exitToAccount()
        }

       checkApplicationVersion()

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })
    }

    private fun checkApplicationVersion(){
        if(isConnected()){
            sharedViewModel.getApiAddress()?.observe(viewLifecycleOwner, Observer {
                sharedViewModel.getVersion(VERSION_NUMBER, it!!.link)?.observe(viewLifecycleOwner, Observer { it ->
                    if(it!!.number == OLD_VERSION){
                        showAlert(R.string.old_version)
                    }
                })
            })
        }else{
            Toast.makeText(requireContext(), R.string.internet, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAlert(oldVersion: Int) {
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setMessage(oldVersion)
        val dialog = builder.create()
        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ShowToast")
    private fun exitToAccount() {
        if(isConnected()){
            if(textPhone.text.toString().isNotEmpty() && textPassword.text.toString().isNotEmpty()){
                getJsonFromAccount()
            }else{
                textStatus.setText(R.string.empty_fields)
            }
        }else{
            Toast.makeText(requireContext(), R.string.internet, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getJsonFromAccount() {
        val fullPhoneText = if (textPhone.text.toString()[0] == '+')
            textPhone.text.toString().substring(1)
            else textPhone.text.toString()

        println("getJsonFromAccount")
        sharedViewModel.getApiAddress()?.observe(viewLifecycleOwner, Observer { link ->
            sharedViewModel.getVersion(VERSION_NUMBER, link!!.link)?.observe(viewLifecycleOwner, Observer { it ->
                if(it!!.number == RESULT_VERSION){
                    sharedViewModel
                        .getUserToken(link = link.link, fullPhoneText,
                            textPassword.text.toString())
                        ?.observe(viewLifecycleOwner, Observer { t ->

                            if(t != null){
                                //println("token=" + t.token)
                                textStatus.text = ""

                                lifecycleScope.coroutineContext.let {
                                    sharedViewModel.getUserAccount(link.link, t.token)
                                    val intent = Intent(requireContext(), HomeActivity::class.java)
                                    intent.putExtra(CURRENT_ADDRESS, link.link)
                                    intent.putExtra(CURRENT_TOKEN, t.token)
                                    requireActivity().startActivity(intent)
                                }

                            }else{
                                textStatus.setText(R.string.failure_login_password)
                            }
                        })
                }
            })
        })
    }

    private fun init() {
        textPhone = binding!!.editTextPhone
        textPassword = binding!!.editTextTextPassword
        buttonLogin = binding!!.buttonLogin

        textStatus = binding!!.textStatus
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isConnected(): Boolean {
        var connected = false
        try {
            val cm = context
                ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: Exception) {
            e!!.message?.let { Log.e("Connectivity Exception", it) }
        }
        return connected
    }

    companion object {
        const val VERSION_NUMBER:Int = 36
        const val RESULT_VERSION:Int = 1
        const val OLD_VERSION:Int = 2
    }
}