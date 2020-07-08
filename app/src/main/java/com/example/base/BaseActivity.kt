package com.example.base

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.example.empoyeelistapp.R
import com.example.empoyeelistapp.databinding.BaseActivityBinding
import com.example.network.ApiInterface
import com.example.util.AppPreferences
import com.example.util.AppUtils
import com.example.util.listener.ConnectivityReceiver
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    @Inject
    lateinit var context: Application

    @Inject
    lateinit var appUtils: AppUtils

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var editor: SharedPreferences.Editor

    @Inject
    lateinit var apiInterface: ApiInterface

    lateinit var baseActivityBinding: BaseActivityBinding

    lateinit var baseViewModel: BaseViewModel

    private var mSnackBar: Snackbar? = null

    lateinit var mainRelativeLayout: RelativeLayout


    private val expression =
        "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication.appComponent.inject(this)

        baseActivityBinding = DataBindingUtil.setContentView(this, R.layout.base_activity)
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)

        baseActivityBinding.data = baseViewModel

        mainRelativeLayout = findViewById(R.id.base_activity_rl_container)

        appUtils.printLog()
        appPreferences.printLog()

    }

    fun initToolbar(isToolbarVisible: Boolean) {

        if (baseActivityBinding.baseActivityToolbar != null) {
            if (isToolbarVisible) {
                baseActivityBinding.baseActivityToolbar.visibility = View.VISIBLE

            } else {
                baseActivityBinding.baseActivityToolbar.visibility = View.GONE
            }
        }

    }

    protected open fun <T : ViewDataBinding?> putContentView(@LayoutRes resId: Int): T {
        val frameLayout = findViewById<ViewGroup?>(R.id.base_activity_fl_content)

        return DataBindingUtil.inflate<T>(
            layoutInflater,
            resId,
            frameLayout,
            true
        )
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        MyApplication.isInternetAvailable.set(isConnected)
    }


    fun showToast(msg: String) {
        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }


    fun getVideoId(videoUrl: String?): String? {
        if (videoUrl == null || videoUrl.trim { it <= ' ' }.length <= 0) {
            return null
        }
        val pattern: Pattern = Pattern.compile(expression)
        val matcher: Matcher = pattern.matcher(videoUrl)
        try {
            if (matcher.find()) return matcher.group()
        } catch (ex: ArrayIndexOutOfBoundsException) {
            ex.printStackTrace()
        }
        return null
    }

}