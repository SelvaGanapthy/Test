package com.example.test.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.test.R
import com.example.test.data.models.PROFILE
import com.example.test.databinding.LoadmoreProgresadpterBinding
import com.example.test.databinding.ProfilelistItemAdpterBinding
import com.example.test.view.activities.MainActivity
import com.example.test.view.interfaces.OnItemClickListener
import java.lang.Exception

class DataAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var dataList: ArrayList<PROFILE>
    lateinit var recyclerView: RecyclerView
    lateinit var clkListener: OnItemClickListener

    constructor(
        context: Context, dataList: ArrayList<PROFILE>, clkListener: OnItemClickListener) : this(context) {
        this.dataList = dataList
        this.context = context
        this.clkListener = clkListener
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemId(position: Int): Long = super.getItemId(position)

    override fun getItemViewType(position: Int): Int {
//  return super.getItemViewType(position)

        if (position != 0 && position == itemCount - 1)
            return 0
        else
            return 1
    }


    fun setHideLoadMoreProgress() {
        notifyAdapterChange()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            0 -> LoadingViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.loadmore_progresadpter, parent, false
                )
            )


            1 -> ViewHolder1(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.profilelist_item_adpter, parent, false
                )
            )

            else -> null!!
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder.itemViewType) {
            0 -> {
            }

            1 -> {
                val model: PROFILE = dataList[position]
            }
        }

        /*Assigning Adpater to view model of adapter*/
        if (holder is ViewHolder1) {
            holder.view.model = dataList[position]
            holder.view.clklistener = this.clkListener

            /*Glide*/
            Glide.with(context)
                .load(dataList[position].THUMBNAME)
                .listener(object :
                    RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        try {
//                            ExceptionTrack.getInstance().TrackImageFailure(
//                                e,
//                                "Listings-$from",
//                                Constants.alllistdata.get(dvmProfileListViewHolder.position).THUMBNAME
//                            )

                            if (dataList[position].NAME.isEmpty()) {
                                holder.view.profileimgReload.visibility = View.VISIBLE
//                                handleRetryImageLoad(
//                                    context,
//                                    dvmProfileListViewHolder.profile,
//                                    dvmProfileListViewHolder.profileReload,
//                                    Constants.alllistdata.get(dvmProfileListViewHolder.position).THUMBNAME
//                                )
                            }
                        } catch (e1: Exception) {
                            e1.printStackTrace()
                        }

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable, model: Any, target: Target<Drawable>,
                        dataSource: DataSource, isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }).apply(
                    RequestOptions().dontAnimate().fitCenter().priority(Priority.HIGH)
                        .placeholder(R.drawable.add_photo_female)
                )
                .into(holder.view.profileimg)
        }

        if (holder is LoadingViewHolder) {
            holder.view.layLoadmore.visibility =
                if (MainActivity.LoadmoreProgress) View.VISIBLE else View.GONE
        }


    }

    override fun getItemCount(): Int {
        try {
            if (dataList.size == null || dataList.size == 0)
                return 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dataList.size
    }


    fun notifyAdapterChange() {
        try {
            android.os.Handler().postDelayed(object : Runnable {
                override fun run() {
                    recyclerView.recycledViewPool.clear()
                    notifyDataSetChanged()
                }
            }, 50)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class LoadingViewHolder(val view: LoadmoreProgresadpterBinding) :
        RecyclerView.ViewHolder(view.root)

    class ViewHolder1(val view: ProfilelistItemAdpterBinding) : RecyclerView.ViewHolder(view.root)


}


