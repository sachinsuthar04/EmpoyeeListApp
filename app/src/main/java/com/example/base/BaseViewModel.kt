package com.example.base

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.network.ApiInterface
import com.example.util.AppPreferences
import com.example.util.AppUtils
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var context: Application

    @Inject
    lateinit var appUtils: AppUtils

    @Inject
    lateinit var appPreferences: AppPreferences

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    lateinit var body: ResponseBody

    init {
        loadingVisibility.value = View.GONE
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        MyApplication.appComponent.inject(this)
    }


    fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE

        errorMessage.value = null
    }

    fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    fun onRetrievePostListError(error: Throwable) {
        loadingVisibility.value = View.GONE
        errorMessage.value = "error"
        if (error is HttpException) {
            body = error.response().errorBody()!!
            val jsonObject = JSONObject(body.string())
            if (jsonObject.has("status")) {
                jsonObject.get("status")
            }
            if (jsonObject.has("message")) {

                Toast.makeText(
                    context,
                    jsonObject.get("message").toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (jsonObject.has("errors")) {

                Toast.makeText(
                    context,
                    jsonObject.get("errors").toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}