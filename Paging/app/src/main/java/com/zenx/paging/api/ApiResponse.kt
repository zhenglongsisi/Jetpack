package com.zenx.paging.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Long
 */
data class ApiResponse<T>(

    var errorCode: Int,

    @SerializedName("errorMsg")
    var errorMessage: String,

    var data: T

)

data class ListWrapper<T>(
    @SerializedName("datas")
    var list: List<T>
)
