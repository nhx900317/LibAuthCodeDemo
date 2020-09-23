package com.thcb.libauthcodedemo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.thcb.libauthcode.authcode.AuthCode
import com.thcb.libauthcode.authcode.AuthCodeAb
import com.thcb.libauthcode.utils.LogDebug

/**
 * @author 天河归来
 * @date 2020/09/22
 * 描述：MainActivity
 */

class MainActivity : Activity() {

    private val TAG = "MainActivity"
    private var mAuthCodeAb: AuthCodeAb? = null
    private var tvMsg: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMsg = findViewById(R.id.tv_msg)

        mAuthCodeAb = AuthCode(this)
        mAuthCodeAb?.checkAuthCode(
                "88e8a57c58d7ea088e1b32881338aaab",
                object : AuthCodeAb.AuthCodeCallback {
                    override fun onError(code: Int, msg: String) {
                        LogDebug.d(TAG, "checkAuthCode onError,code:$code,msg:$msg")
                        tvMsg!!.text = msg
                    }

                    override fun onSuccess(msg: String) {
                        LogDebug.d(TAG, "checkAuthCode onSuccess,msg:$msg")
                        tvMsg!!.text = msg
                    }
                })
    }
}