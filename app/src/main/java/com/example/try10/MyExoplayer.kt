package com.example.try10

import androidx.media3.exoplayer.ExoPlayer
import com.example.try10.models.SongModel
import android.content.Context
import androidx.media3.common.MediaItem

object MyExoplayer {
    private var exoPlayer:ExoPlayer?=null
    private var currentSong:SongModel?=null

    fun getCurrentSong():SongModel?{
        return currentSong
    }

    fun getInstance():ExoPlayer?{
        return exoPlayer
    }
    fun startPlaying(context: Context,song:SongModel){
        if (exoPlayer==null)
        exoPlayer=ExoPlayer.Builder(context).build()

        if (currentSong!=song){
            //Start playing new songs
            currentSong=song
            currentSong?.url?.apply {
                val mediaItem=MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(mediaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()

            }
        }


    }
}