package com.chad.player.ui.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.session.MediaController
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import com.chad.player.service.PlayerService
import com.chad.player.util.LogUtil

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AudioPlayerFragment : Fragment(), ServiceConnection {

    private val TAG = AudioPlayerFragment::class.simpleName.toString()
    private val mLogUtil = LogUtil()

    private var mSessionToken: MediaSession.Token? = null
    private var mMediaController: MediaController? = null
    private var mMediaCallback: MediaCallback? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mLogUtil.d(TAG,"onActivityCreated")
        initData()
        super.onActivityCreated(savedInstanceState)
    }

    private fun initData() {
        mLogUtil.d(TAG, "initData")
        bindPlayerService()
    }

    private fun bindPlayerService() {
        /**
         * ::得到类的class对象
         * ?表示这个对象可能为空
         */
        mLogUtil.d(TAG, "bindPlayerService")
        var intent = Intent(context, PlayerService::class.java)
        context?.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        /**
         * $可以包含模板表达式，会求值并把结果包含到字符串中
         * is操作符检查一个对象是否是某个特定类
         */
        mLogUtil.d(TAG, "onServiceConnected : service = $service")
        if (service is PlayerService.MediaBinder) {
            mSessionToken = service.getToken()
            mMediaController = MediaController(context, mSessionToken)
            mMediaCallback = MediaCallback()
            mMediaController?.registerCallback(mMediaCallback)
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        mLogUtil.d(TAG, "onServiceDisconnected")
        mSessionToken = null
        mMediaController?.unregisterCallback(mMediaCallback)
        mMediaCallback = null
        mMediaController = null
    }

    /**
     * inner标记一个类是内部类
     * 可以引用外部类的成员
     */
    private inner class MediaCallback : MediaController.Callback() {

        override fun onAudioInfoChanged(info: MediaController.PlaybackInfo?) {
            super.onAudioInfoChanged(info)
        }

        override fun onPlaybackStateChanged(state: PlaybackState?) {
            super.onPlaybackStateChanged(state)
        }
    }
}