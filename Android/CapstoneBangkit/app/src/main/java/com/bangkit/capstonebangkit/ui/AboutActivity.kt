package com.bangkit.capstonebangkit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.adapter.UserAdapter
import com.bangkit.capstonebangkit.model.UserData
import com.bangkit.capstonebangkit.databinding.ActivityAboutBinding
import com.google.firebase.firestore.*

class AboutActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var myAdapter : UserAdapter
    private lateinit var userArrayList : ArrayList<UserData>
    private lateinit var mBinding: ActivityAboutBinding

    companion object {
        const val ABOUT = "About"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        mBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val actionbar = supportActionBar
        actionbar!!.title = ABOUT

        userArrayList = arrayListOf()
        myAdapter = UserAdapter(userArrayList)
        with(mBinding) {
            rvUser.layoutManager = LinearLayoutManager(this@AboutActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = myAdapter
        }
        getData()
    }

    private fun getData() {
        db = FirebaseFirestore.getInstance()
            db.collection("users")
                .addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error !=null){
                            Log.e("Firestore",error.message.toString())
                            return
                        }

                        for (de : DocumentChange in value?.documentChanges!!){
                            if (de.type == DocumentChange.Type.ADDED){
                                userArrayList.add(de.document.toObject(UserData::class.java))
                            }
                        }

                        myAdapter.notifyDataSetChanged()
                    }
                })

    }
}