package com.example.cardfate.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.cardfate.R

class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.itemCard).apply {
            setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, CardFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}