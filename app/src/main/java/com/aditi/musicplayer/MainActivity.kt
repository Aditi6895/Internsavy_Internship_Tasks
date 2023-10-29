package com.aditi.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer : MediaPlayer
    private var totalTime : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val btnplay = findViewById<ImageView>(R.id.play)
        val btnpause = findViewById<ImageView>(R.id.pause)
        val btnstop = findViewById<ImageView>(R.id.stop)
        val seekBarMusic= findViewById<SeekBar>(R.id.seekBar)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime = mediaPlayer.duration

        btnplay.setOnClickListener {
            mediaPlayer.start()
        }

        btnpause.setOnClickListener {
            mediaPlayer.pause()
        }

        btnstop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
        }
        seekBarMusic.max = totalTime
        seekBarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progess:Int, fromUser:Boolean) {
                if(fromUser) {
                    mediaPlayer.seekTo(progess)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        //change the seekbar based on the music
        val handler = android.os.Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekBarMusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exception: java.lang.Exception) {
                    seekBarMusic.progress = 0
                }
            }
        }, 0)
    }
}

