package com.bangkit.capstonebangkit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.adapter.HelpAdapter
import com.bangkit.capstonebangkit.databinding.ActivityHelpBinding
import com.bangkit.capstonebangkit.model.HelpData
import com.google.firebase.firestore.*

class HelpActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore
    private lateinit var myAdapter : HelpAdapter
    private lateinit var helpArrayList : ArrayList<HelpData>
    private lateinit var mBinding: ActivityHelpBinding

    companion object {
        const val HELP = "Help"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        mBinding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val actionbar = supportActionBar
        actionbar!!.title = HELP

        helpArrayList = arrayListOf()
        myAdapter = HelpAdapter(helpArrayList)
        with(mBinding) {
            rvHelp.layoutManager = LinearLayoutManager(this@HelpActivity)
            rvHelp.setHasFixedSize(true)
            rvHelp.adapter = myAdapter
        }
        getData()
    }

    private fun getData() {
        db = FirebaseFirestore.getInstance()
        db.collection("helps")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
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
                            helpArrayList.add(de.document.toObject(HelpData::class.java))
                        }
                    }

                    myAdapter.notifyDataSetChanged()
                }
            })

    }
}