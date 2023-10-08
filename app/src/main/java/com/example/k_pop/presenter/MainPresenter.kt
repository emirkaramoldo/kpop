package com.example.k_pop.presenter

import com.example.k_pop.SongRepository
import com.example.k_pop.model.KPopModel
import com.example.k_pop.view.MainContract


class MainPresenter(private val view: MainContract.View, private val repository: SongRepository) :
    MainContract.Presenter {

    override fun searchSong(songName: String) {
        repository.getSong(songName, object : SongRepository.RepositoryCallback {
            override fun onSuccess(model: KPopModel) {
                view.showSongInfo(model)
            }

            override fun onFailure(t: Throwable) {
                view.showError(t.message.toString())
            }
        })
    }
}