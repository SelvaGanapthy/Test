package com.example.test.view.activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArrayMap
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.models.ErrorHandler
import com.example.test.data.models.ProgressHandler
import com.example.test.data.models.SearchResultsModel
import com.example.test.databinding.ActivityMainBinding
import com.example.test.utils.Constants
import com.example.test.view.adapters.DataAdapter
import com.example.test.view.interfaces.OnItemClickListener
import com.example.test.viewmodel.MainViewModel
import java.lang.Exception
import java.util.*
import android.util.Log
import com.example.test.data.models.PROFILE
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), Observer, LifecycleOwner, OnItemClickListener {

    lateinit var activityMainBinding: ActivityMainBinding
    var viewmodel: MainViewModel = MainViewModel()
    var params: ArrayMap<String, String> = ArrayMap()
    var allDataList: ArrayList<PROFILE> = ArrayList()
    lateinit var progressDialog: ProgressDialog
    lateinit var adapter: DataAdapter
    private var mListScrollListener: ListScrollListener? = null

    companion object {
        var LoadmoreProgress: Boolean = false
        var page: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        activityMainBinding.viewmodel = viewmodel
        viewmodel.addObserver(this)
        lifecycle.addObserver(viewmodel)


        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setCancelable(false)
    }


    override fun onStart() {
        super.onStart()
        mListScrollListener = ListScrollListener()
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun update(p0: Observable?, p1: Any?) {
        try {
            if (p1 is View) {
                when (p1.id) {
                    R.id.btnFetchData -> {
                        viewmodel.matchProfileApiCall(
                            "Matches",
                            Constants.getParams(
                                params,
                                "DashBoard",
                                1,
                                ""
                            )
                        )
                    }
                }
            } else if (p1 is SearchResultsModel) {


                if (viewmodel.ReqType.equals("Matches")) {

                    allDataList.addAll(p1.SEARCHRES.PROFILE)
                    activityMainBinding.rv.setHasFixedSize(true)
                    adapter = DataAdapter(this@MainActivity, allDataList, this)
                    activityMainBinding.rv.adapter = this.adapter
                    activityMainBinding.rv.addOnScrollListener(mListScrollListener!!)
                    activityMainBinding.rv.setOnTouchListener(View.OnTouchListener { v, event ->
                        v.parent.requestDisallowInterceptTouchEvent(true)
                        false
                    })
                } else {
                    LoadmoreProgress =
                        allDataList.addAll(p1.SEARCHRES.PROFILE)
                    adapter.notifyDataSetChanged()

                }


            } else if (p1 is ProgressHandler) {
                if (p1.isProgress) {
                    progressDialog.setMessage(p1.message)
                    progressDialog.show()
                } else
                    progressDialog.dismiss()
            } else if (p1 is ErrorHandler) {
                Toast.makeText(this, "" + p1.errorMsg, Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun itemClick(pos: Int, item: View) {

        when (item.id) {

            R.id.shorlist_or_deleteButton -> {
                Toast.makeText(this, "shorlist_or_deleteButton", Toast.LENGTH_SHORT).show()
            }


            R.id.chatButton -> {
                Toast.makeText(this, "chatButton", Toast.LENGTH_SHORT).show()
            }

            R.id.send_interestButton -> {
                Toast.makeText(this, "send_interestButton", Toast.LENGTH_SHORT).show()
            }

        }

    }


    inner class ListScrollListener : RecyclerView.OnScrollListener() {

        /*It is responsiable for indicating start scrolling or (End/Stop) scrolling*/
        override fun onScrollStateChanged(recyclerView: RecyclerView, scrollState: Int) {
            try {
                /*three state main to start scroll or stop scroll*/
                when (scrollState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {

                    }

                    RecyclerView.SCROLL_STATE_DRAGGING -> {

                    }
                    RecyclerView.SCROLL_STATE_SETTLING -> {

                    }


                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            val lm: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            val profileVisibleItemCount: Int = recyclerView.childCount
            val profileTotalItemCount: Int = lm.itemCount
            val profileFirstVisiblepassedItem: Int = lm.findFirstVisibleItemPosition()
            val profileLastVisibleItem: Int = lm.findLastVisibleItemPosition()
            val initialFirstVisibleItem: Int = profileFirstVisiblepassedItem
            listScroll(
                profileFirstVisiblepassedItem,
                profileVisibleItemCount,
                profileTotalItemCount
            )
            recyclerView.parent.requestDisallowInterceptTouchEvent(true)
        }


    }


    fun listScroll(pasedViableItemCount: Int, curVisibleItemsCount: Int, totalItemCount: Int) {
        if (pasedViableItemCount + curVisibleItemsCount == (totalItemCount) && totalItemCount != 0) {

            if (!progressDialog.isShowing) {
//                && isAdded()
                progressDialog.show()

                activityMainBinding.rv.post(object : Runnable {

                    override fun run() {
                        LoadmoreProgress = true
                        adapter.setHideLoadMoreProgress()
                        page += 1

                        viewmodel.matchProfileApiCall(
                            "LatestMatches",
                            Constants.getParams(
                                params, "LatestMatches",
                                page, "1587220052"
                            )
                        )

                    }
                })

            }


        }
//        progressDialog.dismiss()

    }
}