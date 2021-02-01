package com.adworkshop.clients

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import com.adworkshop.databinding.FragmentItemClientBinding
import com.adworkshop.databinding.FragmentItemDashboardBinding


class ClientsAdapter(
    val activity: Activity,

    val recyAdapCallBack: RecyAdapCallBack<DashboardViewHolder>
) :
    RecyclerView.Adapter<ClientsAdapter.DashboardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_client, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        /* holder.binding.menuName.text = listMenu[position].name
         holder.binding.icon.setImageResource(listMenu[position].icon)
          holder.binding.listRow.setOnClickListener {
              recyAdapCallBack.onItemClick(
                  holder.binding.listRow,
                  position,
                  0,
                  listMenu[position].icon
              )
          }*/

    }

    override fun getItemCount() = 20

    class DashboardViewHolder(
        itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val binding: FragmentItemClientBinding
            get() = FragmentItemClientBinding.bind(itemView)
    }

}