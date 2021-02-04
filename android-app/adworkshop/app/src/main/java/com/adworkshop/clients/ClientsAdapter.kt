package com.adworkshop.clients

import ClientData
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import com.adworkshop.databinding.FragmentItemClientListBinding


class ClientsAdapter(
    val activity: Activity,
    val clientListing: ArrayList<ClientData>,
    val recyAdapCallBack: RecyAdapCallBack<DashboardViewHolder>
) :
    RecyclerView.Adapter<ClientsAdapter.DashboardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_client_list, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.binding.clientName.text = clientListing[position].cLIENT_NAME
        holder.binding.clientPhoneNo.text = clientListing[position].cONTACT_NUMBER.toString()
        holder.binding.clientEmail.text = clientListing[position].eMAIL
        holder.binding.clientDelete.setOnClickListener {
            recyAdapCallBack.onItemClick(
                holder.binding.listRow,
                position,
                0,
                clientListing[position]
            )
        }
    holder.binding.clientEdit.setOnClickListener {
            recyAdapCallBack.onItemClick(
                holder.binding.listRow,
                position,
                1,
                clientListing[position]
            )
        }

    }

    override fun getItemCount() = clientListing.size

    class DashboardViewHolder(
        itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val binding: FragmentItemClientListBinding
            get() = FragmentItemClientListBinding.bind(itemView)
    }

}