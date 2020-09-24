package com.thcb.libauthcodedemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.thcb.libauthcode.InitLibAuthCode
import com.thcb.libauthcode.utils.LogDebug

/**
 * @author 天河归来
 * @date 2020/09/22
 * 描述：RApplication
 */

class RApplication : Application() {
    private val TAG = "RApplication"

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        LogDebug.d(TAG, "onCreate")

        val initLibAuthCode = InitLibAuthCode()
        initLibAuthCode.init(context)
    }
}