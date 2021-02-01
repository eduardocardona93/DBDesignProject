package com.adworkshop.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.adworkshop.R
import com.adworkshop.callbacks.RecyAdapCallBack
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A fragment representing a list of Items.
 */
class DashboardFragment : Fragment(), RecyAdapCallBack<DashboardAdapter.SideMenuViewHolder> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMenu=ArrayList<DashboardMenuModel>()
        listMenu.add(DashboardMenuModel(R.drawable.ic_baseline_group_add_client_24,getString(R.string.add_client)))
        listMenu.add(DashboardMenuModel(R.drawable.ic_baseline_view_list_24,getString(R.string.client_list)))
        rcDashBoard.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        rcDashBoard.adapter = DashboardAdapter(requireActivity(),listMenu,this)

    }

    override fun onItemClick(view: View, position: Int, type: Int, selectedRow: Any?) {
        when(selectedRow as Int){
            R.drawable.ic_baseline_group_add_client_24 ->{
                Navigation.findNavController(view)
                    .navigate(R.id.action_dashboardFragment_to_addClientFragment)
            }
            R.drawable.ic_baseline_view_list_24->{
                Navigation.findNavController(view)
                    .navigate(R.id.action_dashboardFragment_to_clientsListingFragment)
            }
        }

    }

}