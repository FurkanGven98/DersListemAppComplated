package com.furkanguven.derslistemapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.furkanguven.derslistemapp.R
import com.furkanguven.derslistemapp.util.gorselIndir
import com.furkanguven.derslistemapp.util.placeholderYap
import com.furkanguven.derslistemapp.viewmodel.DersDetayiViewModel
import kotlinx.android.synthetic.main.fragment_ders_detayi.*

class DersDetayiFragment : Fragment() {
private lateinit var viewModel: DersDetayiViewModel
private var dersId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ders_detayi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            dersId=DersDetayiFragmentArgs.fromBundle(it).dersId
        }
        viewModel =ViewModelProviders.of(this).get(DersDetayiViewModel::class.java)
        viewModel.roomVerisiniAl(dersId)
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.dersLiveData.observe(viewLifecycleOwner, Observer { ders->
            ders?.let {
                dersIsim.text =it.dersIsim
                dersHafta1.text=it.Hafta1
                dersHafta2.text=it.Hafta2
                dersHafta3.text=it.Hafta3
                dersHafta4.text=it.Hafta4
                dersHafta5.text=it.Hafta5
                dersHafta6.text=it.Hafta6
                dersHafta7.text=it.Hafta7
                dersHafta8.text=it.Hafta8
                dersHafta9.text=it.Hafta9
                dersHafta10.text=it.Hafta10
                dersHafta11.text=it.Hafta11
                context?.let {
                    dersImage.gorselIndir(ders.dersGorsel, placeholderYap(it))
                }



            }
        })

    }


}