package com.adworkshop.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.adworkshop.R
import com.adworkshop.retrofit.AdViewModel
import com.adworkshop.retrofit.ServiceMessageResponse
import com.adworkshop.utills.CommonUtils
import com.adworkshop.utills.Validator

import kotlinx.android.synthetic.main.fragment_signup.*

/*
  Created by Ranjana on Jan 2021
*/

class SignUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup.setOnClickListener {
            hitSignUp()
        }
        back.setOnClickListener {
            Navigation.findNavController(requireView())
                .popBackStack(R.id.signUpFragment, true)
        }

    }

        /* Method  to hit SignUp API
        */
    private fun hitSignUp() {

        if (validateFields()) {
            loading.visibility = View.VISIBLE

            val viewStateObserver = Observer<Any> { viewState ->
                loading.visibility = View.GONE
                if (viewState is ServiceMessageResponse) {
                    CommonUtils.showToast(requireActivity(), viewState.message)
                    CommonUtils.showToast(requireActivity(), viewState.message)
                            Navigation.findNavController(requireView())
                                .popBackStack(R.id.signUpFragment, true)
                        }

                 else {
                    CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
                }
            }

            AdViewModel().signUp(
                SignUpRequestModel(
                    full_name.text.toString(),
                    phone_number.text.toString().toLong(),
                    email.text.toString(),
                    password.text.toString()

                )
            ).observe(viewLifecycleOwner, viewStateObserver)
        }

    }
    /* Method  to validate fields
   */
    private fun validateFields(): Boolean {
        if (Validator.isBlank(full_name.text.toString())) {
            CommonUtils.showSnackbar(requireView(), getString(R.string.err_blank_full_name))
            return false
        } else if (Validator.isBlank(email.text.toString())) {
            CommonUtils.showSnackbar(requireView(), getString(R.string.err_email_blank))
            return false
        } else if (!Validator.isEmailValid(email.text.toString())) {
            CommonUtils.showSnackbar(requireView(), getString(R.string.err_valid_email))
            return false
        } else if (Validator.isBlank(phone_number.text.toString())) {
            CommonUtils.showSnackbar(requireView(), getString(R.string.err_mobile_blank))
            return false
        } else if (Validator.isBlank(password.text.toString())) {
            CommonUtils.showSnackbar(requireView(), getString(R.string.err_password))
            return false
        }
        return true
    }

}