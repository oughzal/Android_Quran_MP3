package com.omarcomputer.quran.ui

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.omarcomputer.quran.MP3_BASE_URL
import com.omarcomputer.quran.R
import com.omarcomputer.quran.databinding.ActivityMainBinding
import com.omarcomputer.quran.model.Ayat
import com.omarcomputer.quran.model.Sorat
import com.omarcomputer.quran.recyclerview.AyatAdaper
import com.omarcomputer.quran.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AyatAdaper.Listener {
    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var ayatAdaper: AyatAdaper;
    var ayatList = ArrayList<Ayat>()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var playPauseButton: Button
    private var isPaused = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ayatAdaper = AyatAdaper(ayatList,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = ayatAdaper
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.sowar.observe(this) {
            for (s in it) {
                binding.navigation.menu.add(s.name).setIcon(R.drawable.ic_mosque)
                    .setOnMenuItemClickListener {
                        showSorat(s)
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
            }
        }
        mainViewModel.ayat.observe(this) {
            mainViewModel.currentAyat.value = it[0]
            ayatList.clear()
            ayatAdaper.viewList.clear()
            ayatList.addAll(it)
            ayatAdaper.notifyDataSetChanged()
            binding.recyclerView.scrollToPosition(0)

        }
        mainViewModel.currentAyat.observe(this) {
            val index = mainViewModel.index.value?:0
            binding.recyclerView.scrollToPosition(index)

            mediaPlayer?.release()

            mediaPlayer = MediaPlayer().apply {

                binding.btnPlay.isEnabled = false
                binding.btnPlay.setImageResource(R.drawable.pause)
                val url = MP3_BASE_URL + it.id + ".mp3"
                setDataSource(url)
                prepareAsync()
                setOnPreparedListener {
                    binding.btnPlay.isEnabled = true
                    isPaused = false
                    binding.btnPlay.setImageResource(R.drawable.pause)
                    mediaPlayer?.start()
                    ayatAdaper.selectItem(index)

                }
                setOnCompletionListener {
                    mainViewModel.nextAya()

                }
            }
            binding.btnPlay.setOnClickListener {
                if(isPaused){
                    mediaPlayer?.start()
                    binding.btnPlay.setImageResource(R.drawable.pause)
                    isPaused =false

                }else{
                    mediaPlayer?.pause()
                    binding.btnPlay.setImageResource(R.drawable.play)
                    isPaused =true
                }
            }



        }
        mainViewModel.getSowar()
    }
        private fun showSorat(sorat: Sorat){
            mainViewModel.getAyat(sorat)
        }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onItemClick(position: Int, view: View) {
        ayatAdaper.selectItem(position)
        mainViewModel.index.value = position

        mainViewModel.currentAyat.value = mainViewModel.ayat.value!![position]
    }


}