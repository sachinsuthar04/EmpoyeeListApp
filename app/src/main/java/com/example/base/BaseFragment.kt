package com.example.base

import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import javax.inject.Inject

class BaseFragment : Fragment() {
    @Inject
    lateinit var preferences: SharedPreferences
    @Inject
    lateinit var editor: SharedPreferences.Editor
}