package com.example.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseActivity
import com.example.empoyeelistapp.R
import com.example.empoyeelistapp.databinding.ActivityHomeBinding
import com.example.ui.ListAdapter
import com.example.ui.viewmodels.EmployeeViewModel
import com.example.util.ConnectionDetector


class HomeActivity : BaseActivity() {

    lateinit var homeBinding: ActivityHomeBinding
    lateinit var employeeModel: EmployeeViewModel
    lateinit var cd: ConnectionDetector


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding =
            putContentView(R.layout.activity_home) as ActivityHomeBinding

        employeeModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        homeBinding.data = employeeModel

        initToolbar(true)
        initdata()
        setlistner()
    }

    private fun initdata() {
        baseActivityBinding.ivBack.visibility = View.GONE
        baseActivityBinding.tvToolbarTitle.setText("Employee List")
        cd = ConnectionDetector()
        cd.isConnectingToInternet(this@HomeActivity)

        if (cd.isConnectingToInternet(this@HomeActivity)) {
            employeeModel.getEmployee()
        } else {
            Toast.makeText(context, "No internet connection!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun setlistner() {


        employeeModel.employeeResponse.observe(this, Observer {
            if (it.status.equals("success")) {
                if (it.data.isNotEmpty()) {
                    homeBinding.listRecyclerView.visibility = View.VISIBLE
                    homeBinding.tvnorecord.visibility = View.GONE
                    homeBinding.listRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@HomeActivity)
                        adapter = ListAdapter(it.data)
                    }
                    Toast.makeText(context, "Employee list get successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    homeBinding.listRecyclerView.visibility = View.GONE
                    homeBinding.tvnorecord.visibility = View.VISIBLE
                }
            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }

        })

    }
}