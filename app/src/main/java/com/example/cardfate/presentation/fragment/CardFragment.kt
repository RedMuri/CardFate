package com.example.cardfate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentCardBinding
import com.example.cardfate.presentation.viewmodel.CardViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class CardFragment : Fragment() {

    private var cardId: String = "null"

    private var _binding: FragmentCardBinding? = null
    private val binding: FragmentCardBinding
        get() = _binding ?: throw RuntimeException("FragmentCardBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val cardViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CardViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardId = it.getString(CARD_ID)?:"null"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCardById()
        observeViewModel()
    }

    private fun observeViewModel() {
        cardViewModel.card.observe(viewLifecycleOwner){
            with(binding){
                Picasso.get().load(it.imageUrl).into(ivCardImage)
                tvFullName.text = it.fullName
                tvProfession.text = it.skills
                tvBio.text = it.bio
            }
        }
    }

    private fun getCardById() {
        cardViewModel.getCardById(cardId)
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