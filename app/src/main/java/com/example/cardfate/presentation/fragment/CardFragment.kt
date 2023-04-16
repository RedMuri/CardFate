package com.example.cardfate.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cardfate.R


class CardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.cardView).apply {
            clipToOutline = true
        }
    }

    companion object {

        private const val CARD_ID = "card_id"

        fun newInstance(cardId: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(CARD_ID, cardId)
                }
            }
    }
}