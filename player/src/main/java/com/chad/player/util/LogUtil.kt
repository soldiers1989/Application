package com.chad.player.util

import android.util.Log

class LogUtil {

    private val isShowLog = true

    fun v(tag: String, msg: String) {
        if (!isShowLog) {
            return
        }
        Log.v(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (!isShowLog) {
            return
        }
        Log.d(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (!isShowLog) {
            return
        }
        Log.i(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (!isShowLog) {
            return
        }
        Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (!isShowLog) {
            return
        }
        Log.e(tag, msg)
    }
}