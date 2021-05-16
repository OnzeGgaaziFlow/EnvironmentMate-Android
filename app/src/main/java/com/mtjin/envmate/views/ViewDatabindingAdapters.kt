package com.mtjin.envmate.views

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mtjin.envmate.R

@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String) {
    Glide.with(this).load(url)
        .thumbnail(0.1f)
        .into(this)
}

@BindingAdapter("urlImageRadius16")
fun ImageView.setUrlImageRadius16(url: String) {
    Glide.with(this).load(url)
        .thumbnail(0.1f)
        .transform(RoundedCorners(16))
        .into(this)
}

@BindingAdapter("htmlText")
fun TextView.setHtmlText(html: String?) {
    text = html?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT) }
}

// 약관 동의 여부에 따른 배경색
@BindingAdapter("onAgreementBackground")
fun TextView.setOnNextBackground(isCompleted: Boolean) {
    if (isCompleted) setBackgroundColor(resources.getColor(R.color.dark_gray_333333))
    else setBackgroundColor(resources.getColor(R.color.light_gray_EBEBEB))
}