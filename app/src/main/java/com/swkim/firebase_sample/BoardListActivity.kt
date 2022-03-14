package com.swkim.firebase_sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.swkim.firebase_sample.databinding.ActivityBoardListBinding

class BoardListActivity : AppCompatActivity() {

    lateinit var LVAdapter: ListViewAdapter
    var backtime : Long = 0
    lateinit var binding : ActivityBoardListBinding
    val list = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBoardListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.writeBtn.setOnClickListener {
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }



        LVAdapter = ListViewAdapter(list)
        val lv : ListView = binding.listview
        lv.adapter = LVAdapter

        getData()
    }


    private fun getData(){

        val database = Firebase.database
        val myRef = database.getReference("board") // 데이터를 사용할 경로 지정

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(Model::class.java)
                    Log.d("BoardListActivity", item?.title.toString())
                    list.add(item!!)
                }
                LVAdapter.notifyDataSetChanged() // 어뎁터 뷰 업데이트
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }

    // 뒤로가기로 종료
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backtime >= 2000) {
            backtime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else {
            finish()
        }
    }

}