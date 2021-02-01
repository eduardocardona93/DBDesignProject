package com.adworkshop.dashboard

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import com.adworkshop.databinding.FragmentItemDashboardBinding


class DashboardAdapter(
    val activity: Activity,
    val listMenu: ArrayList<DashboardMenuModel>,
val recyAdapCallBack: RecyAdapCallBack<SideMenuViewHolder>
    ) :
    RecyclerView.Adapter<DashboardAdapter.SideMenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideMenuViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item_dashboard, parent, false)
        return SideMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: SideMenuViewHolder, position: Int) {
        holder.binding.menuName.text = listMenu[position].name
        holder.binding.icon.setImageResource(listMenu[position].icon)
         holder.binding.listRow.setOnClickListener {
             recyAdapCallBack.onItemClick(
                 holder.binding.listRow,
                 position,
                 0,
                 listMenu[position].icon
             )
         }

    }

    override fun getItemCount() = listMenu.size

    class SideMenuViewHolder(
        itemView: View?
    ) : RecyclerView.ViewHolder(itemView!!) {

        val binding: FragmentItemDashboardBinding
            get() = FragmentItemDashboardBinding.bind(itemView)
    }

}