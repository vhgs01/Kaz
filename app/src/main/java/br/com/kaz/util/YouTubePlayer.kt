package br.com.kaz.util

import android.content.Context
import android.widget.Toast
import br.com.kaz.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

object YouTubePlayer {

    fun initializeYouTubePlayer(view: YouTubePlayerView, context: Context, videoId: String) {
        view.initialize(
            context.getString(R.string.youtube_api_key),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1!!.loadVideo(videoId)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(context, "Vídeo indisponível", Toast.LENGTH_LONG).show()
                }
            })
    }

}