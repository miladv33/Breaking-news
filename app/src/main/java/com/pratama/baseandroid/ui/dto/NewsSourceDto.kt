package com.pratama.baseandroid.ui.dto

import android.os.Parcelable
import com.pratama.baseandroid.domain.entity.News
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NewsSourceDto(
    val news:@RawValue List<News>,
    val status: String
) : Parcelable
