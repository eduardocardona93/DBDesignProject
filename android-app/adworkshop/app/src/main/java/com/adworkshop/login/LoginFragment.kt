package com.adworkshop.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adworkshop.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

   // private lateinit var loginViewModel: LoginViewModel

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
            loading.visibility = View.VISIBLE
            /*loginViewModel.login(
                username.text.toString(),
                password.text.toString()
            )*/
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_dashboardFragment)
        }
    }


}