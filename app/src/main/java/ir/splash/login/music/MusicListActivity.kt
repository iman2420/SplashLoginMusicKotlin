package ir.splash.login.music

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewbestpractice.Music
import ir.splash.login.music.databinding.ActivityMusicListBinding

class MusicListActivity : AppCompatActivity() {

    private lateinit var mainRecyclerViewAdapter: MainRecyclerViewAdapter
    private lateinit var musicList:ArrayList<Music>

    private lateinit var binding: ActivityMusicListBinding

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, MusicListActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        musicList = ArrayList()

        for (i in 0..50) {
            musicList.add(Music("Iman $i", "Album 2022", R.drawable.im_music, R.raw.music))
        }

        val mainRv = binding.recyclearViewMain

        mainRecyclerViewAdapter = MainRecyclerViewAdapter(musicList, MainRecyclerViewAdapter.ItemClickListener {
            startPlayerActivity(it)
        })


        mainRv.adapter = mainRecyclerViewAdapter
            mainRv.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            //layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)



    }
    private fun startPlayerActivity(music: Music) {
        val intent = Intent(this@MusicListActivity, PlayerActivity::class.java)
        intent.putExtra("music", music)
        startActivity(intent)
    }


}