package com.swkim.firebase_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.swkim.firebase_sample.databinding.ActivityBoardWriteBinding

class BoardWriteActivity : AppCompatActivity() {
    lateinit var binding : ActivityBoardWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBoardWriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.writeUploadBtn.setOnClickListener {

            val database = Firebase.database
            val myRef = database.getReference("board")
            myRef.push().setValue(
                Model(binding.wrtieTextArea.text.toString())
            )
        }


    }
}