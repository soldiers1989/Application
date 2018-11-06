package com.chad.player.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.session.MediaSession
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.text.TextUtils
import com.chad.player.util.LogUtil
import java.io.File

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class PlayerService : Service() {

    private val tag = PlayerService::class.simpleName.toString()
    private val mLogUtil = LogUtil()

    private val mMediaSession = MediaSession(applicationContext, tag)
    private val mMediaSessionCallBack = MediaSessionCallBack()
    private val mMediaPlayer = MediaPlayer()
    private val mMediaCompletionListener = MediaCompletionListener()
    private val mMediaErrorListener = MediaErrorListener()

    private var isPlayed = false

    override fun onCreate() {
        mLogUtil.d(tag, "onCreate")
        initData()
        super.onCreate()
    }

    private fun initData() {
        mLogUtil.d(tag, "initData")
        mMediaSession.setCallback(mMediaSessionCallBack)
        mMediaPlayer.setOnCompletionListener(mMediaCompletionListener)
        mMediaPlayer.setOnErrorListener(mMediaErrorListener)
    }

    override fun onBind(intent: Intent?): IBinder {
        mLogUtil.d(tag, "onBind")
        return MediaBinder()
    }

    private fun playTrack(path: String) {
        mLogUtil.d(tag, "playTrack : path = $path")
        val file = File(path)
        if (TextUtils.isEmpty(path) || !file.exists())
            return
        isPlayed = false
        mMediaPlayer.reset()
        mMediaPlayer.setDataSource(path)
        mMediaPlayer.prepare()
        mMediaPlayer.start()
        isPlayed = true
    }

    private fun previous() {
        mLogUtil.d(tag, "previous")
    }

    private fun next() {
        mLogUtil.d(tag, "next")
    }

    private fun play() {
        mLogUtil.d(tag, "play")
        if (!isPlayed)
            return
        if (!mMediaPlayer.isPlaying)
            mMediaPlayer.pause()
    }

    private fun pause() {
        mLogUtil.d(tag, "pause")
        if (!isPlayed)
            return
        if (mMediaPlayer.isPlaying)
            mMediaPlayer.start()
    }

    private fun stop() {
        mLogUtil.d(tag, "stop")
        if (!isPlayed)
            return
        mMediaPlayer.stop()
        mMediaPlayer.release()
        isPlayed = false
    }

    private fun seekTo(pos: Long) {
        mLogUtil.d(tag, "seekTo : pos = $pos")
        if (!isPlayed)
            return
        mMediaPlayer.seekTo(pos.toInt())
    }

    private inner class MediaSessionCallBack : MediaSession.Callback() {

        override fun onCustomAction(action: String?, extras: Bundle?) {
            mLogUtil.d(tag, "onCustomAction")
            super.onCustomAction(action, extras)
        }

        override fun onSkipToPrevious() {
            mLogUtil.d(tag, "onSkipToPrevious")
            previous()
            super.onSkipToPrevious()
        }

        override fun onSkipToNext() {
            mLogUtil.d(tag, "onSkipToNext")
            next()
            super.onSkipToNext()
        }

        override fun onPlay() {
            mLogUtil.d(tag, "onPlay")
            play()
            super.onPlay()
        }

        override fun onPause() {
            mLogUtil.d(tag, "onPause")
            pause()
            super.onPause()
        }

        override fun onStop() {
            mLogUtil.d(tag, "onPause")
            stop()
            super.onStop()
        }

        override fun onSeekTo(pos: Long) {
            mLogUtil.d(tag, "onSeekTo : pos = $pos")
            seekTo(pos)
            super.onSeekTo(pos)
        }
    }

    private inner class MediaCompletionListener : MediaPlayer.OnCompletionListener {

        override fun onCompletion(p0: MediaPlayer?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private inner class MediaErrorListener : MediaPlayer.OnErrorListener {

        override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    inner class MediaBinder : Binder() {

        fun getToken(): MediaSession.Token? {
            mLogUtil.d(tag, "getToken")
            return mMediaSession?.sessionToken
        }
    }
}