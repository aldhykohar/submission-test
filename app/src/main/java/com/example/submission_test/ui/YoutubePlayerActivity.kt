package com.example.submission_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission_test.base.BaseActivity
import com.example.submission_test.data.model.api.videos.VideosItem
import com.example.submission_test.data.network.DataResource
import com.example.submission_test.databinding.ActivityYoutubePlayerBinding
import com.example.submission_test.utils.UtilConstants.EXTRA_MOVIE_VIDEO
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YoutubePlayerActivity : BaseActivity<ActivityYoutubePlayerBinding>() {
    override fun getViewBinding(): ActivityYoutubePlayerBinding =
        ActivityYoutubePlayerBinding.inflate(layoutInflater)

    override fun initView() {
        initDataIntent()
    }

    private fun initDataIntent() {
        val video = intent.getParcelableExtra<VideosItem?>(EXTRA_MOVIE_VIDEO)
        video?.let {
            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(it.key ?: "", 0f)
                }
            })
        }
    }

    override fun initObservers() {

    }

    override fun showFailure(failure: DataResource.Failure) {

    }

}