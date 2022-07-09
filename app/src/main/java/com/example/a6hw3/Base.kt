package com.example.a6hw3

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class Base<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected abstract fun inflateVB(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateVB(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
    }

    abstract fun initListener()

    abstract fun initView()
}