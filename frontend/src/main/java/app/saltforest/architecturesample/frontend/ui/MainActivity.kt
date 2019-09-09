package app.saltforest.architecturesample.frontend.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.saltforest.architecturesample.R
import app.saltforest.architecturesample.frontend.ui.memo.MemoDetailFragment
import app.saltforest.architecturesample.frontend.ui.memo.MemoListFragment
import app.saltforest.architecturesample.frontend.ui.memo.MemoViewModel
import app.saltforest.architecturesample.frontend.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var backdropShown: Boolean = false
    private val animatorSet = AnimatorSet()
    private var height: Int = 0

    private var backStackListener = FragmentManager.OnBackStackChangedListener {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showController()
        }
    }

    private val viewModel: MemoViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MemoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener(this)

        savedInstanceState ?: let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, MemoListFragment())
                .commit()
        }

        addMessageBtn.setOnClickListener {
            if (backdropShown) {
                return@setOnClickListener
            }
            viewModel.new()
            showDetailFragment()
            supportActionBar?.setTitle(R.string.page_title_memo_new)
        }

        viewModel.selectedMemo.observe(this, Observer {
            if (backdropShown) {
                return@Observer
            }
            it?.also {
                showDetailFragment()
                supportActionBar?.setTitle(R.string.page_title_memo_edit)
            }
        })

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            content.background = getDrawable(R.drawable.backdrop_shape)
        }
    }

    private fun showDetailFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, MemoDetailFragment())
            .addToBackStack(null)
            .commit()
        hideController()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            supportFragmentManager.popBackStack()
            return true
        }

        return false
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

    override fun onPause() {
        super.onPause()
        supportFragmentManager.removeOnBackStackChangedListener(backStackListener)
    }

    private fun showController() {
        bottomBar.visibility = View.VISIBLE
        addMessageBtn.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun hideController() {
        bottomBar.visibility = View.GONE
        addMessageBtn.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(v: View?) {
        backdropShown = !backdropShown

        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val translateY = height - resources.getDimensionPixelSize(R.dimen.list_reveral_height)
        val animator = ObjectAnimator.ofFloat(
            content, "translationY", if (backdropShown) {
                translateY
            } else {
                0
            }.toFloat()
        )

        animator.duration = 200
        animatorSet.play(animator)
        animator.start()
    }
}
