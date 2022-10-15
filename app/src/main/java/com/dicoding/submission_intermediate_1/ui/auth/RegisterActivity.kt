package com.dicoding.submission_intermediate_1.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.submission_intermediate_1.databinding.ActivityRegisterBinding
import com.dicoding.submission_intermediate_1.model.UserModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}