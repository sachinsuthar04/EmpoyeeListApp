package com.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.empoyeelistapp.R
import com.example.network.model.MineUserEntity
import com.facebook.drawee.view.SimpleDraweeView

class ListAdapter(private val list: List<MineUserEntity.Data>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MineUserEntity.Data = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mEmpid: TextView? = null
    private var mempsalary: TextView? = null
    private var mempage: TextView? = null
    private var mempprofile: SimpleDraweeView? = null


    init {
        mEmpid = itemView.findViewById(R.id.empid)
        mTitleView = itemView.findViewById(R.id.empname)
        mempsalary = itemView.findViewById(R.id.empsalary)
        mempage = itemView.findViewById(R.id.empage)
        mempprofile = itemView.findViewById(R.id.ivthumb)
    }

    fun bind(data: MineUserEntity.Data) {
        mEmpid?.text = "EMP ID : " + data.id
        mTitleView?.text = "EMP NAME :" + data.employee_name
        mempsalary?.text = "SALARY :" + data.employee_salary.toString()
        mempage?.text = "AGE : " + data.employee_age.toString()
        if (data.profile_image.isNotEmpty()) {
            mempprofile?.setImageURI(data.profile_image.toString())
        }
    }

}