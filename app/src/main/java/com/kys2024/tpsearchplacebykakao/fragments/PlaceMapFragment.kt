package com.kys2024.tpsearchplacebykakao.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kys2024.tpsearchplacebykakao.databinding.FragmentPlaceListBinding
import com.kys2024.tpsearchplacebykakao.databinding.FragmentPlaceMapBinding

class PlaceMapFragment :Fragment() {
    private val binding:FragmentPlaceMapBinding by lazy { FragmentPlaceMapBinding.inflate(layoutInflater) }

    override fun onCreateView( //뷰를 만들기만 하는 곳
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //뷰가 만들어지고 난후의 작업
        super.onViewCreated(view, savedInstanceState)
    }


}