package com.example.k_pop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.k_pop.databinding.ActivityMainBinding
import com.example.k_pop.model.KPopModel

import com.example.k_pop.model.SongApi
import com.example.k_pop.presenter.MainPresenter
import com.example.k_pop.view.MainContract
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMVP()
        initClickers()
        lifecycle.addObserver(binding.youtubePlayerView)
    }

    private fun setupMVP() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://k-pop.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val songApi = retrofit.create(SongApi::class.java)
        val repository = SongRepository(songApi)
        presenter = MainPresenter(this, repository)
    }

    private fun initClickers() {
        with(binding) {
            songBtn.setOnClickListener {
                val songName = songEt.text.toString().trim()
                presenter.searchSong(songName)
            }
        }
    }

    override fun showSongInfo(model: KPopModel) {
        val songId = model.data.firstOrNull()?.video?.replace("https://youtu.be/", "")
        binding.apply {
            val artistName = model.data.firstOrNull()?.artist ?: ""
            val songName = model.data.firstOrNull()?.songName ?: ""

            artistNameTv.text = artistName
            songNameTv.text = songName

            youtubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    songId?.let { youTubePlayer.loadVideo(it, 0f) }
                }
            })
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}