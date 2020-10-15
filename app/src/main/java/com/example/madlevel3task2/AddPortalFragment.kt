package com.example.madlevel3task2

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*
import java.net.URL

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener{
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val URLTitle = txtInputTitle.text.toString()
        val validURL = isValidURL(txtInputURL.text.toString())

        if (URLTitle.isNotBlank() && validURL) {
            //Add to recycleview
            findNavController().popBackStack()
        } else {
            Toast.makeText(activity, "Fill in a title and valid URL", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidURL(urlToCheck: String): Boolean {
        return URLUtil.isValidUrl(urlToCheck) && Patterns.WEB_URL.matcher(urlToCheck).matches()
    }
}