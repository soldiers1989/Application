package com.chad.player.service

import android.app.Service
import android.content.Intent
import android.media.session.MediaSession
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.RequiresApi
import com.chad.player.util.LogUtil

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class PlayerService : Service() {

    private val TAG = PlayerService::class.simpleName.toString()
    private val mLogUtil = LogUtil()

    private var mMediaSession = MediaSession(applicationContext, TAG)
    private var mMediaSessionCallBack = MediaSessionCallBack()

    override fun onCreate() {
        mLogUtil.d(TAG, "onCreate")
        initData()
        super.onCreate()
    }

    private fun initData() {
        mLogUtil.d(TAG, "initData")
        mMediaSession.setCallback(mMediaSessionCallBack)
    }

    override fun onBind(intent: Intent?): IBinder {
        mLogUtil.d(TAG, "onBind")
        return MediaBinder()
    }

    inner class MediaBinder : Binder() {

        fun getToken(): MediaSession.Token? {
            mLogUtil.d(TAG, "getToken")
            return mMediaSession?.sessionToken
        }
    }

    private inner class MediaSessionCallBack : MediaSession.Callback() {

        override fun onCustomAction(action: String?, extras: Bundle?) {
            mLogUtil.d(TAG, "onCustomAction")
            super.onCustomAction(action, extras)
        }

        override fun onSkipToPrevious() {
            mLogUtil.d(TAG, "onSkipToPrevious")
            super.onSkipToPrevious()
        }

        override fun onPlay() {
            mLogUtil.d(TAG, "onPlay")
            super.onPlay()
        }

        override fun onPause() {
            mLogUtil.d(TAG, "onPause")
            super.onPause()
        }

        override fun onStop() {
            mLogUtil.d(TAG, "onPause")
            super.onStop()
        }

        override fun onSkipToNext() {
            mLogUtil.d(TAG, "onSkipToNext")
            super.onSkipToNext()
        }
    }
}