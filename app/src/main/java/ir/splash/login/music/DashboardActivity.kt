package ir.splash.login.music

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.splash.login.music.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    companion object {
        fun start(activity: LoginActivity) {
            activity.startActivity(Intent(activity, DashboardActivity::class.java))
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToList(view: View) {
        MusicListActivity.start(this)
    }
    fun goToMusicPlayer(view: View) {}

}
