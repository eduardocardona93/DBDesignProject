package com.adworkshop.clients

import ClientData
import ClientListingResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import com.adworkshop.retrofit.API_Constants
import com.adworkshop.retrofit.AdViewModel
import com.adworkshop.retrofit.ServiceMessageResponse
import com.adworkshop.utills.CommonUtils
import kotlinx.android.synthetic.main.fragment_clients.*


class ClientsListingFragment : Fragment(), RecyAdapCallBack<ClientsAdapter.DashboardViewHolder> {

    // list to store clients
    var clientListing = ArrayList<ClientData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener {
            Navigation.findNavController(view)
                .popBackStack(R.id.clientsListingFragment, true)
        }

        //Init recyclerview adapter
        rcClients.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rcClients.adapter = ClientsAdapter(requireActivity(), clientListing, this)

    }

    override fun onResume() {
        super.onResume()
        clientListing.clear()
        hitGetClientListing()
    }
    private fun hitGetClientListing() {
        loading.visibility = View.VISIBLE

        val viewStateObserver = Observer<Any> { viewState ->
            loading.visibility = View.GONE

            if (viewState is ClientListingResponse) {
                //CommonUtils.showToast(requireActivity(), viewState.message)
                clientListing.addAll(viewState.data)
                rcClients.adapter!!.notifyDataSetChanged()
            } else {
                CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
            }

        }

        AdViewModel().clientListing(
            ClientListingRequestModel(
                API_Constants.USER_DATA.sP_UID
            )
        ).observe(viewLifecycleOwner, viewStateObserver)
    }

    override fun onItemClick(view: View, position: Int, type: Int, selectedRow: Any?) {
        when (type) {
            0 -> {
                // Delete Client
                hitDeleteClient(selectedRow as ClientData)
            }
            1 -> {
                //Edit Client
                API_Constants.EDIT_CLIENT=true
                API_Constants.CLIENT_DATA=selectedRow as ClientData
                Navigation.findNavController(view)
                    .navigate(R.id.action_clientsListingFragment_to_addClientFragment)
            }
        }


    }

    private fun hitDeleteClient(clientData: ClientData) {
        loading.visibility = View.VISIBLE

        val viewStateObserver = Observer<Any> { viewState ->
            loading.visibility = View.GONE

            if (viewState is ServiceMessageResponse) {
                CommonUtils.showToast(requireActivity(), viewState.message)
                clientListing.remove(clientData)
                rcClients.adapter!!.notifyDataSetChanged()
            } else {
                CommonUtils.showSnackbar(requireView(), getString(R.string.went_wrong))
            }

        }

        AdViewModel().deleteClient(
            ClientListingRequestModel(
                clientData.cLIENT_UID
            )
        ).observe(viewLifecycleOwner, viewStateObserver)
    }

}