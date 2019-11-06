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
    private lateinit var mTimeLineItems: MutableList<TimeLineItem?>
    private lateinit var mTimeLineItemSrcs: MutableList<TimeLineItem?>
    private lateinit var mTimeLineAdapter: TimeLineAdapter
    private val mVisibleThreshold = 10
    private var mIsLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        initData()
        initAdapter()
        initScrollListener()

        swipeContainer.setOnRefreshListener {
            Handler().postDelayed({
                mTimeLineAdapter.clear()
                mTimeLineAdapter.addAll(mTimeLineItemSrcs.apply {
                    shuffle()
                    subList(0, mVisibleThreshold)
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
        mTimeLineItemSrcs.shuffle()
        mTimeLineItems = mTimeLineItemSrcs.subList(0, mVisibleThreshold)
        mTimeLineAdapter = TimeLineAdapter(mTimeLineItems)
        recyclerViewTimeLine.adapter = mTimeLineAdapter
    }

    private fun initScrollListener() {
        recyclerViewTimeLine.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (!mIsLoading) {
                    if (lastVisibleItem == mTimeLineItems.size - 1) {
                        //bottom of list!
                        loadMore()
                        mIsLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        mTimeLineItems.add(null)
        mTimeLineAdapter.notifyItemInserted(mTimeLineItems.size - 1)

        Handler().postDelayed({
            mTimeLineItems.removeAt(mTimeLineItems.size - 1)
            val scrollPosition = mTimeLineItems.size
            mTimeLineAdapter.notifyItemRemoved(scrollPosition)

            var currentSize = scrollPosition
            val nextLimit = currentSize + mVisibleThreshold

            while (currentSize < nextLimit) {
                mTimeLineItems.add(mTimeLineItemSrcs[Random().nextInt(mTimeLineItemSrcs.size)])
                currentSize++
            }

            mTimeLineAdapter.notifyDataSetChanged()
            mIsLoading = false
        }, 2000)
    }

    private fun initData() {
        mTimeLineItemSrcs = mutableListOf()
        mTimeLineItemSrcs.apply {
            add(TimeLineItem("7-Ingredient Chili", R.drawable.img01, R.drawable.img_profile, 17, false))
            add(TimeLineItem("All-American Sloppy Joes", R.drawable.img02, R.drawable.img_profile, 15, true))
            add(TimeLineItem("Balthazar Bloody Mary", R.drawable.img03, R.drawable.img_profile, 18, true))
            add(TimeLineItem("Beef and Vegetable Stew", R.drawable.img04, R.drawable.img_profile, 14, false))
            add(TimeLineItem("Bloody Mary Lentil", R.drawable.img05, R.drawable.img_profile, 13, false))
            add(TimeLineItem("Bloody Mary Mocktail", R.drawable.img06, R.drawable.img_profile, 11, false))
            add(TimeLineItem("Bloody Nightmare Cocktail", R.drawable.img07, R.drawable.img_profile, 16, true))
            add(TimeLineItem("Cajun Chicken & Fettuccine", R.drawable.img08, R.drawable.img_profile, 19, false))
            add(TimeLineItem("Chicago-Style Bloody Mary", R.drawable.img09, R.drawable.img_profile, 41, false))
            add(TimeLineItem("Chicken, Chorizo & Vegetable Tagliatelle", R.drawable.img10, R.drawable.img_profile, 71, true))
            add(TimeLineItem("Classic V8 Bloody Mary", R.drawable.img11, R.drawable.img_profile, 68, false))
            add(TimeLineItem("Creamy Vegetable Pasta", R.drawable.img12, R.drawable.img_profile, 12, false))
            add(TimeLineItem("Crunchy Black & White Bean Salad", R.drawable.img13, R.drawable.img_profile, 43, true))
            add(TimeLineItem("Deconstructed Stuffed Peppers", R.drawable.img14, R.drawable.img_profile, 22, false))
            add(TimeLineItem("Dried Cherry Gingerbread", R.drawable.img15, R.drawable.img_profile, 14, false))
            add(TimeLineItem("Easy Spaghetti Bolognese", R.drawable.img16, R.drawable.img_profile, 54, true))
            add(TimeLineItem("Easy Turkey and Vegetable Rice", R.drawable.img17, R.drawable.img_profile, 33, false))
            add(TimeLineItem("Festive Beetroot", R.drawable.img18, R.drawable.img_profile, 24, false))
            add(TimeLineItem("Gazpacho", R.drawable.img19, R.drawable.img_profile, 43, true))
            add(TimeLineItem("Greek Style Beef Stew", R.drawable.img20, R.drawable.img_profile, 12, false))
            add(TimeLineItem("Cranberry Stuffing Balls", R.drawable.img21, R.drawable.img_profile, 4, false))
            add(TimeLineItem("Hearty Vegetarian Chilli", R.drawable.img22, R.drawable.img_profile, 32, false))
            add(TimeLineItem("Italian Pasta E Fagioli", R.drawable.img23, R.drawable.img_profile, 26, true))
            add(TimeLineItem("Italian-Style Rice ", R.drawable.img24, R.drawable.img_profile, 16, false))
        }
    }
}
