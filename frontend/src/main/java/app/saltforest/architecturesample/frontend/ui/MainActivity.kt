package app.saltforest.architecturesample.frontend.ui

import android.os.Bundle
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.frontend.ui.memo.MemoListFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        savedInstanceState ?: let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, MemoListFragment())
                .commit()
        }

        addMessageBtn.setOnClickListener {
            // TODO:　作成画面の表示
        }
    }

}
