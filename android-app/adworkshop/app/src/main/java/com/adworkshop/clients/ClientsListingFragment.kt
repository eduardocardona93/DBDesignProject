package com.adworkshop.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import kotlinx.android.synthetic.main.fragment_clients.*


class ClientsListingFragment : Fragment(), RecyAdapCallBack<ClientsAdapter.DashboardViewHolder> {

    // private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcClients.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rcClients.adapter = ClientsAdapter(requireActivity(), this)

        back.setOnClickListener {
            Navigation.findNavController(view)
                .popBackStack(R.id.clientsListingFragment, true)
        }

    }

    override fun onItemClick(view: View, position: Int, type: Int, selectedRow: Any?) {

    }


}