package com.furkanguven.derslistemapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanguven.derslistemapp.R
import com.furkanguven.derslistemapp.model.Ders
import com.furkanguven.derslistemapp.util.gorselIndir
import com.furkanguven.derslistemapp.util.placeholderYap
import com.furkanguven.derslistemapp.view.DersListesiFragmentDirections
import kotlinx.android.synthetic.main.ders_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_ders_detayi.view.*

class DersRecyclerAdapter(val dersListesi : ArrayList<Ders>) : RecyclerView.Adapter<DersRecyclerAdapter.DersViewHolder>() {
    class DersViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.ders_recycler_row,parent,false)
        return DersViewHolder(view)
    }

    override fun onBindViewHolder(holder: DersViewHolder, position: Int) {
        holder.itemView.ders.text = dersListesi.get(position).dersIsim
        holder.itemView.devre.text =dersListesi.get(position).dersDevre



        holder.itemView.setOnClickListener{
            val action =DersListesiFragmentDirections.actionDersListesiFragmentToDersDetayiFragment(dersListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.gorselIndir(dersListesi.get(position).dersGorsel, placeholderYap(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return dersListesi.size
    }
    fun dersListesiniGuncelle(yeniDerslListesi:List<Ders>){
        dersListesi.clear()
        dersListesi.addAll(yeniDerslListesi)
        notifyDataSetChanged()
    }
}