package com.furkanguven.derslistemapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanguven.derslistemapp.R
import com.furkanguven.derslistemapp.adapter.DersRecyclerAdapter
import com.furkanguven.derslistemapp.viewmodel.DersListesiViewModel
import kotlinx.android.synthetic.main.fragment_ders_listesi.*

class DersListesiFragment : Fragment() {

        private lateinit var viewModel : DersListesiViewModel
        private val recyclerDersAdapter=DersRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ders_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProviders.of(this).get(DersListesiViewModel::class.java)
        viewModel.refreshData()

        dersListRecycler.layoutManager = LinearLayoutManager(context)
        dersListRecycler.adapter=recyclerDersAdapter

        swipeRefreshLayout.setOnRefreshListener {
            dersYukleniyor.visibility=View.VISIBLE
            dersHataMesaji.visibility=View.GONE
            dersListRecycler.visibility=View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.dersler.observe(viewLifecycleOwner, Observer { dersler ->
            dersler?.let {
                dersListRecycler.visibility=View.VISIBLE
                recyclerDersAdapter.dersListesiniGuncelle(dersler)

            }
        })
        viewModel.dersHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if(it){
                    dersHataMesaji.visibility=View.VISIBLE
                    dersListRecycler.visibility=View.GONE
                }else{
                    dersHataMesaji.visibility=View.GONE
                }
            }
        })
        viewModel.dersYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let {
                if(it){
                    dersListRecycler.visibility=View.GONE
                    dersHataMesaji.visibility=View.GONE
                    dersYukleniyor.visibility=View.VISIBLE
                }else{
                    dersYukleniyor.visibility=View.GONE
                }
            }
        })
    }

}