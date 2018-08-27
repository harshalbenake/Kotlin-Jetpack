package com.harshalbenake.kotlinjetpack.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.harshalbenake.kotlinjetpack.BR
import com.harshalbenake.kotlinjetpack.R
import com.harshalbenake.kotlinjetpack.data.Entity.PersonProfile
import java.util.*

/**
 * This class is used for listview adapter of person profiles
 */
class PersonProfileAdapter(personProfile: ArrayList<PersonProfile>, listener: OnItemClickListener) : RecyclerView.Adapter<PersonProfileAdapter.RecyclerViewHolder>() {

    private var listPersonProfile: List<PersonProfile> = personProfile
    private var listenerPersonProfile: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(personProfile: PersonProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.rowitem_personprofile, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPersonProfile.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listPersonProfile[position], listenerPersonProfile)
    }

    /**
     * adds Contacts
     */
    fun addContacts(listPersonProfile: List<PersonProfile>) {
        this.listPersonProfile = listPersonProfile
        notifyDataSetChanged()
    }

    /**
     * This class is a viewholder class for listview adapter of person profiles
     */
    class RecyclerViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(personprofile: PersonProfile, listenerPersonProfile: OnItemClickListener) {
            binding.setVariable(BR.personprofile, personprofile)
            binding.executePendingBindings()
            binding.root.setOnClickListener() {
                listenerPersonProfile.onItemClick(personprofile)
            }
        }
    }
}