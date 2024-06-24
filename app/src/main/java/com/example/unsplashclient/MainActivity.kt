package com.example.unsplashclient

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.unsplashclient.databinding.ActivityMainBinding
import com.example.unsplashclient.ui.main_fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var navController: NavController? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { }

    override fun onStart() {
        super.onStart()

        processDataFromIntent()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        processDataFromIntent()
    }

    private fun processDataFromIntent() {
        if (intent != null) {
            if (intent.data != null) {
                val data: Uri = intent.data!!

                when (data.host) {
                    "images.unsplash.com" -> openImageViewFragment(
                        authorName = "Image view",
                        imageUrl = intent.data.toString(),
                        color = "#000000",
                        postUrl = null,
                    )

                    "unsplash.com" -> openImageViewFragment(
                        authorName = "Image view",
                        imageUrl = "",
                        color = "#000000",
                        postUrl = intent.data.toString(),
                    )
                }

                return
            }

            val authorName = intent.extras?.getString("authorName")
            val imageUrl = intent.extras?.getString("imageUrl")
            val color = intent.extras?.getString("color")

            if (
                authorName != null &&
                imageUrl != null &&
                color != null
            ) {
                openImageViewFragment(
                    authorName = authorName,
                    imageUrl = imageUrl,
                    color = color,
                    postUrl = null,
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        this.navController = navController

        binding.toolbarSearchButton.setOnClickListener {
            binding.toolbarSearchButton.visibility = View.GONE
            binding.toolbarTitle.visibility = View.GONE
            binding.toolbarBackButton.visibility = View.VISIBLE
            binding.toolbarSearchView.visibility = View.VISIBLE

            binding.toolbarSearchView.isIconified = false
        }
        binding.toolbarBackButton.setOnClickListener {
            binding.toolbarBackButton.visibility = View.GONE
            binding.toolbarSearchView.visibility = View.GONE
            binding.toolbarTitle.visibility = View.VISIBLE
            binding.toolbarSearchButton.visibility = View.VISIBLE
        }

        binding.toolbarSearchView.setOnQueryTextListener(MySearchListener())
    }

    inner class MySearchListener() : androidx.appcompat.widget.SearchView.OnQueryTextListener {

        private var timer: SearchDelayTimer? = null

        inner class SearchDelayTimer(val callback: () -> Unit) : CountDownTimer(600, 100) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                callback()
            }
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
                ?.let { fragment ->
                    (fragment.childFragmentManager.fragments[0] as MainFragment)
                        .passNewSearchQueryToViewModel(
                            query
                        )
                }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {

            timer?.cancel()
            timer = SearchDelayTimer(
                callback = {
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
                        ?.let { fragment ->
                            (fragment.childFragmentManager.fragments[0] as MainFragment)
                                .passNewSearchQueryToViewModel(
                                    newText
                                )
                        }
                }
            )
            timer?.start()


            return true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun toggleSearchBar(value: Boolean) {
        binding.toolbarSearchButton.visibility = if (value) View.VISIBLE else View.GONE
        binding.toolbarTitle.visibility = View.VISIBLE
        binding.toolbarSearchView.visibility = View.GONE
        binding.toolbarBackButton.visibility = View.GONE
    }

    fun setTitle(title: String) {
        binding.toolbarTitle.text = title
    }

    fun openImageViewFragment(
        authorName: String,
        imageUrl: String,
        color: String,
        postUrl: String?,
    ) {
        if (navController?.currentDestination?.id == R.id.imageViewFragment) {
            navController?.popBackStack()
        }

        toggleSearchBar(false)
        setTitle(authorName)

        navController?.navigate(
            R.id.action_mainFragment_to_imageViewFragment,
            bundleOf(
                "IMAGE_URL" to imageUrl,
                "COLOR" to color,
                "AUTHOR_NAME" to authorName,
                "POST_URL" to postUrl,
            )
        )
    }
}