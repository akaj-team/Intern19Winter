package asiantech.internship.winter.recyclerview

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_time_line.*
import java.util.*

class TimeLineActivity : AppCompatActivity() {
    private lateinit var timeLineItems: MutableList<TimeLineItem?>
    private lateinit var timeLineItemSrcs: MutableList<TimeLineItem?>
    private lateinit var adapterTimeLine: TimeLineAdapter
    private val visibleThreshold = 10
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        initData()
        initAdapter()
        initScrollListener()

        swipeContainer.setOnRefreshListener {
            Handler().postDelayed({
                adapterTimeLine.clear()
                adapterTimeLine.addAll(timeLineItemSrcs.apply {
                    shuffle()
                    subList(0, visibleThreshold)
                })
                swipeContainer.isRefreshing = false
            }, 2000)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            swipeContainer.setColorSchemeColors(
                    resources.getColor(android.R.color.holo_red_dark, theme),
                    resources.getColor(android.R.color.holo_green_dark, theme),
                    resources.getColor(android.R.color.holo_blue_dark, theme)
            )
        }
    }

    private fun initAdapter() {
        timeLineItemSrcs.shuffle()
        timeLineItems = timeLineItemSrcs.subList(0, visibleThreshold)
        adapterTimeLine = TimeLineAdapter(timeLineItems)
        adapterTimeLine.onItemClick = { position ->
            timeLineItems[position]?.let {
                if (it.isLiked) {
                    it.isLiked = false
                    it.countLike--
                } else {
                    it.isLiked = true
                    it.countLike++
                }
                adapterTimeLine.notifyItemChanged(position)
            }
        }
        recyclerViewTimeLine.adapter = adapterTimeLine
    }

    private fun initScrollListener() {
        recyclerViewTimeLine.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (!isLoading) {
                    if (lastVisibleItem == timeLineItems.size - 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        timeLineItems.add(null)
        adapterTimeLine.notifyItemInserted(timeLineItems.size - 1)

        Handler().postDelayed({
            timeLineItems.removeAt(timeLineItems.size - 1)
            val scrollPosition = timeLineItems.size
            adapterTimeLine.notifyItemRemoved(scrollPosition)

            var currentSize = scrollPosition
            val nextLimit = currentSize + visibleThreshold

            while (currentSize < nextLimit) {
                timeLineItems.add(timeLineItemSrcs[Random().nextInt(timeLineItemSrcs.size)])
                currentSize++
            }

            adapterTimeLine.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }

    private fun initData() {
        timeLineItemSrcs = mutableListOf()
        timeLineItemSrcs.apply {
            add(TimeLineItem("7-Ingredient Chili", R.drawable.ic_01, R.drawable.img_profile, 17, false))
            add(TimeLineItem("All-American Sloppy Joes", R.drawable.ic_02, R.drawable.img_profile, 15, true))
            add(TimeLineItem("Balthazar Bloody Mary", R.drawable.ic_03, R.drawable.img_profile, 18, true))
            add(TimeLineItem("Beef and Vegetable Stew", R.drawable.ic_04, R.drawable.img_profile, 14, false))
            add(TimeLineItem("Bloody Mary Lentil", R.drawable.ic_05, R.drawable.img_profile, 13, false))
            add(TimeLineItem("Bloody Mary Mocktail", R.drawable.ic_06, R.drawable.img_profile, 11, false))
            add(TimeLineItem("Bloody Nightmare Cocktail", R.drawable.ic_07, R.drawable.img_profile, 16, true))
            add(TimeLineItem("Cajun Chicken & Fettuccine", R.drawable.ic_08, R.drawable.img_profile, 19, false))
            add(TimeLineItem("Chicago-Style Bloody Mary", R.drawable.ic_09, R.drawable.img_profile, 41, false))
            add(TimeLineItem("Chicken, Chorizo & Vegetable Tagliatelle", R.drawable.ic_10, R.drawable.img_profile, 71, true))
            add(TimeLineItem("Classic V8 Bloody Mary", R.drawable.ic_11, R.drawable.img_profile, 68, false))
            add(TimeLineItem("Creamy Vegetable Pasta", R.drawable.ic_12, R.drawable.img_profile, 12, false))
            add(TimeLineItem("Crunchy Black & White Bean Salad", R.drawable.ic_13, R.drawable.img_profile, 43, true))
            add(TimeLineItem("Deconstructed Stuffed Peppers", R.drawable.ic_14, R.drawable.img_profile, 22, false))
            add(TimeLineItem("Dried Cherry Gingerbread", R.drawable.ic_15, R.drawable.img_profile, 14, false))
            add(TimeLineItem("Easy Spaghetti Bolognese", R.drawable.ic_16, R.drawable.img_profile, 54, true))
            add(TimeLineItem("Easy Turkey and Vegetable Rice", R.drawable.ic_17, R.drawable.img_profile, 33, false))
            add(TimeLineItem("Festive Beetroot", R.drawable.ic_18, R.drawable.img_profile, 24, false))
            add(TimeLineItem("Gazpacho", R.drawable.ic_19, R.drawable.img_profile, 43, true))
            add(TimeLineItem("Greek Style Beef Stew", R.drawable.ic_20, R.drawable.img_profile, 12, false))
            add(TimeLineItem("Cranberry Stuffing Balls", R.drawable.ic_21, R.drawable.img_profile, 4, false))
            add(TimeLineItem("Hearty Vegetarian Chilli", R.drawable.ic_22, R.drawable.img_profile, 32, false))
            add(TimeLineItem("Italian Pasta E Fagioli", R.drawable.ic_23, R.drawable.img_profile, 26, true))
            add(TimeLineItem("Italian-Style Rice ", R.drawable.ic_24, R.drawable.img_profile, 16, false))
        }
    }
}
