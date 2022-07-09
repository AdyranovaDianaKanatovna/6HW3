package com.example.a6hw3

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a6hw3.adapter.Adapter
import com.example.a6hw3.databinding.ActivityMainBinding

class MainActivity : Base<ActivityMainBinding>(), Adapter.Listener {
    private val adapter = Adapter(this)
    private val imageList = arrayListOf<Uri>()

    private var activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val image = result.data?.data
                if (image != null) {
                    adapter.addImage(image)
                }
            }
        }

    override fun inflateVB(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initListener() {
        binding.floatingActionFirstSelect.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            intent.putExtra(Intent.ACTION_PICK, true)
            activityResultLauncher.launch(intent)
        }
        binding.floatingActionChoose.setOnClickListener {
            openActivity(imageList)
        }
    }

    private fun openActivity(imageList: ArrayList<Uri>) {
        Intent(this@MainActivity, SecondActivity::class.java).apply {
            putExtra(KEY_IMG,imageList)
            startActivity(this)
        }
    }

    override fun initView() {
        binding.recyclerViewPhoto.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.recyclerViewPhoto.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(mainImage: Uri) {
        imageList.addAll(listOf(mainImage))
        if (imageList.size >= 0) with(binding) {
            tvOneTitle.text =
                getString(R.string.Selected) + " " + imageList.size + getString(R.string.Photo)
            tvOneTitle.setOnClickListener {
                openActivity(imageList)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun deleteClick(mainImage: Uri) {
        imageList.remove(mainImage)
        if (imageList.size >= 0) with(binding) {
            tvOneTitle.text =
                getString(R.string.Selected) + " " + imageList.size + getString(R.string.Photo)
        }
    }

    companion object {
        const val KEY_IMG = "img"
    }
}


