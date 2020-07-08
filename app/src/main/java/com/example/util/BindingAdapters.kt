package com.example.util

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.empoyeelistapp.R
import com.facebook.drawee.view.SimpleDraweeView


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(
            parentActivity,
            Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("imageFresco")
fun setImageViewWithGlide(imageView: SimpleDraweeView, resource: String?) {
    val parentActivity: AppCompatActivity? = imageView.getParentActivity()
    if (parentActivity != null && resource != null && !resource.equals("")) {
//        Picasso.get().load(resource)
//            .placeholder(R.drawable.splash_bg)
//            .into(imageView)
        imageView.getHierarchy().setPlaceholderImage(R.drawable.progress);
        imageView.setImageURI(resource);
    }
}
