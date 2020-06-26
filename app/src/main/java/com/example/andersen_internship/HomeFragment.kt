package com.example.andersen_internship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var objectItemsList = ArrayList<DataModel>()
    val myAdapter: MyAdapter = MyAdapter(objectItemsList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addData()
        myAdapter.objectListItems = objectItemsList
        recyclerView.layoutManager = LinearLayoutManager (this.context)
        recyclerView.adapter = myAdapter
    }

    private fun addData() {
        for (i in 0..10) {
            objectItemsList.add(DataModel("text $i", R.drawable.ic_home,1))
            objectItemsList.add(DataModel("text $i", R.drawable.ic_home,0))
        }
    }

}