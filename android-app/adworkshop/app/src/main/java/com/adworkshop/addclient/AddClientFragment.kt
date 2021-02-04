package com.adworkshop.addclient

import android.os.Bundle
import android.util.Log
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
import com.adworkshop.utills.Validator
import kotlinx.android.synthetic.main.fragment_add_client.*
import kotlinx.android.synthetic.main.fragment_add_client.back
import kotlinx.android.synthetic.main.fragment_add_client.client_name
import kotlinx.android.synthetic.main.fragment_add_client.email
import kotlinx.android.synthetic.main.fragment_add_client.loading
import kotlinx.android.synthetic.main.fragment_add_client.phone_number
import kotlinx.android.synthetic.main.fragment_item_client_list.*
import kotlinx.android.synthetic.main.fragment_signup.*


class AddClientFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*Init field if redirected from Edit client
        * */
        if(API_Constants.EDIT_CLIENT){
            Log.e("client data :",API_Constants.CLIENT_DATA.toString())
            client_name.setText(API_Constants.CLIENT_DATA.cLIENT_NAME)
            client_type.setText(API_Constants.CLIENT_DATA.cLIENT_TYPE)
            company_code.setText(API_Constants.CLIENT_DATA.cOMPANY_CODE.toString())
            phone_number.setText(API_Constants.CLIENT_DATA.cONTACT_NUMBER.toString())
            email.setText(API_Constants.CLIENT_DATA.eMAIL)
            screen_title.setText(getString(R.string.edit_client_info))
            add.text=getString(R.string.edit)
        }


        /* Click Listeners*/
        add.setOnClickListener {
            if(API_Constants.EDIT_CLIENT){
                hitUpdateClientAPI()
            }else {
                hitAddClientAPI()
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(requireView())
                .popBackStack(R.id.addClientFragment, true)
        }


    }

    private fun hitAddClientAPI() {
        if (validateFields()) {
            loading.visibility = View.VISIBLE


            val viewStateObserver = Observer<Any> { viewState ->
                loading.visibility = View.GONE

                if (viewState is ServiceMessageResponse) {
                    CommonUtils.showToast(requireActivity(), viewState.message)

                    Navigation.findNavController(requireView())
                        .popBackStack(R.id.addClientFragment, true)
                } else {
                    CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
                }

            }

            AdViewModel().createClient(
                CreateClientRequestModel(
                    client_name.text.toString(),
                    client_type.text.toString(),
                    company_code.text.toString().toLong(),
                    phone_number.text.toString().toLong(),
                    email.text.toString(),
                    API_Constants.USER_DATA.sP_UID

                )
            ).observe(viewLifecycleOwner, viewStateObserver)
        }
    }
    private fun hitUpdateClientAPI() {
        if (validateFields()) {
            loading.visibility = View.VISIBLE


            val viewStateObserver = Observer<Any> { viewState ->
                loading.visibility = View.GONE

                if (viewState is ServiceMessageResponse) {
                    CommonUtils.showToast(requireActivity(), viewState.message)

                    Navigation.findNavController(requireView())
                        .popBackStack(R.id.addClientFragment, true)
                } else {
                    CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
                }

            }

            AdViewModel().updateClient(
                CreateClientRequestModel(
                    client_name.text.toString(),
                    client_type.text.toString(),
                    company_code.text.toString().toLong(),
                    phone_number.text.toString().toLong(),
                    email.text.toString(),
                    API_Constants.USER_DATA.sP_UID

                )
            ).observe(viewLifecycleOwner, viewStateObserver)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        API_Constants.EDIT_CLIENT=false
    }


    /*Method to check fields validations
    * */
    private fun validateFields(): Boolean {
        when {
            Validator.isBlank(client_name.text.toString()) -> {
                CommonUtils.showSnackbar(requireView(), getString(R.string.err_client_name_blank))
                return false
            }
            Validator.isBlank(client_type.text.toString()) -> {
                CommonUtils.showSnackbar(requireView(), getString(R.string.err_client_type_blank))
                return false
            }
            Validator.isBlank(company_code.text.toString()) -> {
                CommonUtils.showSnackbar(requireView(), getString(R.string.err_company_code))
                return false
            }
            Validator.isBlank(phone_number.text.toString()) -> {
                CommonUtils.showSnackbar(requireView(), getString(R.string.err_mobile_blank))
                return false
            }
            Validator.isBlank(email.text.toString()) -> {
                CommonUtils.showSnackbar(requireView(), getString(R.string.err_email_blank))
                return false
            }
            else -> return true
        }
    }
}