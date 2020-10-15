package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        rviewPortals.layoutManager = GridLayoutManager(context, 2)
        rviewPortals.adapter = portalAdapter
        //Add item deco?
    }

    private fun observeAddPortalResult() {
        var title = ""
        var url = ""

        setFragmentResultListener(REQ_PORTAL_KEY) {key, bundle ->
            bundle.getString(BUNDLE_PORTAL_TITLE)?.let {
                title = it
            } ?: Log.e("PortalFragment", "Request triggered, but empty text!")

            bundle.getString(BUNDLE_PORTAL_URL)?.let {
                url = it
            } ?: Log.e("PortalFragment", "Request triggered, but empty text!")

            val portal = Portal(title, url)
            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }
    }
}