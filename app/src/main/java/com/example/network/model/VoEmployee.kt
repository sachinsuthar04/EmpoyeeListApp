package com.example.network.model

class MineUserEntity {
    data class VoEmployeedata(
        val status: String,
        val data: List<Data>
    )

    data class Data(

        val id: Int,
        val employee_name: String,
        val employee_salary: Int,
        val employee_age: Int,
        val profile_image: String
    )

}