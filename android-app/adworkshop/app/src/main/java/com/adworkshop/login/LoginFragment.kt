package com.adworkshop.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.adworkshop.R
import com.adworkshop.retrofit.API_Constants
import com.adworkshop.retrofit.AdViewModel
import com.adworkshop.retrofit.ServiceMessageResponse
import com.adworkshop.utills.CommonUtils
import kotlinx.android.synthetic.main.fragment_login.*


/*
   Created by Ranjana on Jan 2021
*/

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        login.setOnClickListener {
            hitLoginAPI()

        }
        back.setOnClickListener {
            Navigation.findNavController(requireView())
                .popBackStack(R.id.loginFragment, true)
        }
    }

    private fun hitLoginAPI() {
        if (email.text.toString().isNotBlank() && password.text.toString().isNotBlank()
        ) {
            loading.visibility = View.VISIBLE

            val viewStateObserver = Observer<Any> { viewState ->
                loading.visibility = View.GONE

                if (viewState is ServiceMessageResponse) {
                    CommonUtils.showToast(requireActivity(), viewState.message)
                    API_Constants.USER_DATA=viewState.data
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_loginFragment_to_dashboardFragment)
                } else {
                    CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
                }

            }

            AdViewModel().login(
                LoginRequestModel(
                    email.text.toString(),
                    password.text.toString()

                )
            ).observe(viewLifecycleOwner, viewStateObserver)
        }
    }


}