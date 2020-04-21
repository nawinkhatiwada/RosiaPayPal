package com.example.navigationcomponent

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Money(
    var senderName: String?,
    var receiverName: String,
    var amount: BigDecimal
) : Parcelable