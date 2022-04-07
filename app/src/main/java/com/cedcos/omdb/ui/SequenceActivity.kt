package com.cedcos.omdb.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cedcos.omdb.R
import com.cedcos.omdb.data.model.ImageModel
import com.cedcos.omdb.databinding.ActivityMovieBinding
import com.cedcos.omdb.util.Status
import com.cedcos.omdb.viewModels.SequenceViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *@collectLast to
 * withLoadStateFooter() returns us a ConcatAdapter, use it to show loading status of the newly added paginatedData
 */
@AndroidEntryPoint
class SequenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: SequenceViewModel

    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()

        Handler(Looper.getMainLooper()).postDelayed({
            setListener()
            setViewModel()
            setObserver()
        }, 3000)

        //setAdapter()
      //  collectLast(viewModel.userItemsUiStates, ::setMovies)
    }

    private fun setViewModel() {

        viewModel = ViewModelProvider(this).get(SequenceViewModel::class.java)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }

    private fun setListener() {
       // binding.btnRetry.setOnClickListener { imageAdapter.retry() }
        binding.topAppbar.setTitle(getString(R.string.series))
        binding.btnRetry.setOnClickListener{startActivity(Intent(this,ParallelActivity::class.java))
        finish()}
        binding.rvImages.layoutManager = GridLayoutManager(this,10)
        imageAdapter = ImageAdapter(arrayListOf())
        binding.rvImages.addItemDecoration(
            DividerItemDecoration(
                binding.rvImages.context,
                (binding.rvImages.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvImages.adapter = imageAdapter

        //Since every item here has fixed.
        binding.rvImages.setHasFixedSize(true)
    }

    private fun setObserver() {
        viewModel.getMovieImages().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.rvImages.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvImages.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(users: List<ImageModel>) {
        imageAdapter.addData(users)
        imageAdapter.notifyDataSetChanged()
    }


}