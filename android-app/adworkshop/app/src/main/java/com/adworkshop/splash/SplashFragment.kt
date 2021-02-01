package com.adworkshop.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.adworkshop.R
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {

   // private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imgClose.setOnClickListener {
         requireActivity().finish()
        }
        login.setOnClickListener {

            Navigation.findNavController(view)
                .navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }


}