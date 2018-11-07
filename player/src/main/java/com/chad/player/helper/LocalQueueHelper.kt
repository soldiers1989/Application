package com.chad.player.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadata
import android.media.MediaMetadataRetriever
import android.media.session.MediaSession
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.TextUtils
import com.chad.player.util.LogUtil
import java.io.ByteArrayInputStream
import java.io.File

/**
 * 单例模式在Kotlin中叫做对象声明
 * 跟在object关键字后面的是对象名
 * 和变量名一样，对象声明并不是表达式
 * 而且不能作为右值用在赋值语句
 * 直接通过名字来访问这个类
 * 该类也可以有父类
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object LocalQueueHelper {

    private val TAG = LocalQueueHelper::class.simpleName.toString()
    private val mLogUtil = LogUtil()
    /**
     * Kotlin标准库提供了arrayOf（）创建数组
     */
    private val mAudiolFileTypes = arrayOf(".mp3", ".mp2", ".mp1", ".flac", ".aac", ".m4a", ".wav", ".ogg")
    /**
     * MutableList<E>接口继承于List<E>，MutableCollection&ltE>
     * 是对只读集合的扩展，增加了对集合的添加及删除元素的操作。
     */
    private val mAudioFiles: MutableList<File> = ArrayList()

    fun getAudioQueue(path: String): MutableList<MediaSession.QueueItem>? {
        mLogUtil.d(TAG, "getAudioQueue : path = $path")
        val isScanFinished = scanAudioFiles(path)
        mLogUtil.d(TAG, "getAudioQueue : isScanFinished = $isScanFinished")
        var audioQueue: MutableList<MediaSession.QueueItem>? = null
        if (isScanFinished && mAudioFiles.isNotEmpty())
            audioQueue = createAudioQueue()
        return audioQueue
    }

    private fun scanAudioFiles(path: String): Boolean {
        mLogUtil.d(TAG, "scanAudioFiles : path = $path")
        val file = File(path)
        if (TextUtils.isEmpty(path) || !file.exists())
            return false
        val listFiles = file.listFiles()
        if (listFiles.isNotEmpty()) {
            val audioFiles: MutableList<File> = ArrayList()
            /**
             * in是一个运算符，中文的意思就是“在...之内”
             */
            for (file in listFiles) {
                if (file.isHidden) {
                    continue
                } else if (!file.isDirectory) {
                    val fileName = file.name.toLowerCase()
                    /**
                     * 通过Array的索引进行迭代
                     */
                    for (i in mAudiolFileTypes.indices) {
                        if (fileName.endsWith(mAudiolFileTypes[i])) {
                            audioFiles.add(file)
                            break
                        }
                    }
                } else if (file.isDirectory) {
                    scanAudioFiles(file.path)
                }
            }
            audioFiles.sort()
            mAudioFiles.addAll(audioFiles)
        }
        return true
    }

    private fun createAudioQueue(): MutableList<MediaSession.QueueItem> {
        mLogUtil.d(TAG, "createAudioQueue : mAudioFiles.isNotEmpty = $mAudioFiles.isNotEmpty()")
        val audioQueue: MutableList<MediaSession.QueueItem> = ArrayList()
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        for (file in mAudioFiles) {
            val mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(file.path)
            val title = mediaMetadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = mediaMetadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val album = mediaMetadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            val duration = mediaMetadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            val art = mediaMetadataRetriever.embeddedPicture
            var albumArt: Bitmap? = null
            if (art.isNotEmpty()) {
                albumArt = BitmapFactory.decodeStream(ByteArrayInputStream(art), null, options)
            }
            val mediaMetadata = MediaMetadata.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, title)
                    .putString(MediaMetadata.METADATA_KEY_ARTIST, artist)
                    .putString(MediaMetadata.METADATA_KEY_ALBUM, album)
                    .putLong(MediaMetadata.METADATA_KEY_DURATION, duration.toLong())
                    .putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, albumArt)
                    .build()
            val queueItem = MediaSession.QueueItem(mediaMetadata.description,
                    MediaSession.QueueItem.UNKNOWN_ID.toLong())
            audioQueue.add(queueItem)
        }
        return audioQueue
    }
}