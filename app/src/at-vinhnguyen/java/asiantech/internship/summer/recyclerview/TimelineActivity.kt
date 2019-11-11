package asiantech.internship.summer.recyclerview

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-vinhnguyen`.activity_timeline.*

class TimelineActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {


    private val numberOfTimeLineItemLoad = 10
    private var isLoading = false
    private lateinit var listTimelineItem: MutableList<TimelineItem?>
    lateinit var subListTimelineItem: MutableList<TimelineItem?>
    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        initView()
        initScrollListener()
    }

    private fun initView() {
        recyclerViewTimeline.layoutManager = LinearLayoutManager(applicationContext).apply { orientation = RecyclerView.VERTICAL }
        recyclerViewTimeline.setHasFixedSize(true)
        //Swipe to refresh
        swipeContainer.setOnRefreshListener(this)
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        swipeContainer.post {
            swipeContainer.isRefreshing = true
            loadRecyclerViewData()
        }
    }

    private fun loadRecyclerViewData() {
        swipeContainer.isRefreshing = true
        listTimelineItem = mutableListOf(TimelineItem(R.drawable.profile_image, "nd.ving", R.drawable.pic9, true, 10, "I love you the more in that I believe you had liked me for my own sake and for nothing else."),
                TimelineItem(R.drawable.profile_image, "kkhanhday__", R.drawable.pic1, true, 23, "But man is not made for defeat. A man can be destroyed but not defeated."),
                TimelineItem(R.drawable.profile_image, "_haha_077", R.drawable.pic2, false, 21, "When you reach the end of your rope, tie a knot in it and hang on."),
                TimelineItem(R.drawable.profile_image, "dongsangtrann", R.drawable.pic3, true, 5, "The journey of a thousand miles begins with one step."),
                TimelineItem(R.drawable.profile_image, "hi.bongne", R.drawable.pic4, false, 20, "Honesty is the first chapter in the book of wisdom."),
                TimelineItem(R.drawable.profile_image, "195_thuynt", R.drawable.pic5, false, 12, "There is only one corner of the universe you can be certain of improving, and that's your own self."),
                TimelineItem(R.drawable.profile_image, "lochomeis", R.drawable.pic6, true, 23, "Coming together is a beginning; keeping together is progress; working together is success."),
                TimelineItem(R.drawable.profile_image, "_khanh_vu", R.drawable.pic7, false, 220, "Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better."),
                TimelineItem(R.drawable.profile_image, "minhh.van", R.drawable.pic8, true, 210, "God gave us the gift of life; it is up to us to give ourselves the gift of living well."),
                TimelineItem(R.drawable.profile_image, "gnahtuhp", R.drawable.pic9, false, 722, "Change your life today. Don't gamble on the future, act now, without delay."),
                TimelineItem(R.drawable.profile_image, "minhoang_tiutiu", R.drawable.pic10, true, 23, "Not all those who wander are lost."),
                TimelineItem(R.drawable.profile_image, "_hphuong_98", R.drawable.pic11, false, 324, "I have not failed. I've just found 10,000 ways that won't work."),
                TimelineItem(R.drawable.profile_image, "xavanlong", R.drawable.pic12, true, 123, "Tell me and I forget. Teach me and I remember. Involve me and I learn."),
                TimelineItem(R.drawable.profile_image, "tmtt_02082000", R.drawable.pic14, true, 12, "Whoever is happy will make others happy too."),
                TimelineItem(R.drawable.profile_image, "meow.9898__", R.drawable.pic15, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "__youngbae__", R.drawable.pic16, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "huynhnguyenhaisan156", R.drawable.pic13, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "_cheeseischi_", R.drawable.pic17, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "so._.2909", R.drawable.pic18, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "_sunflower_2408", R.drawable.pic19, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "c.passy329", R.drawable.pic20, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "th.huong12", R.drawable.pic21, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "bu.i_tinhnhi", R.drawable.pic22, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "december.sg", R.drawable.pic23, false, 20, "There is nothing on this earth more to be prized than true friendship."),
                TimelineItem(R.drawable.profile_image, "duebass_", R.drawable.pic24, false, 20, "There is nothing on this earth more to be prized than true friendship.")
        )
        listTimelineItem.shuffle()
        subListTimelineItem = listTimelineItem.subList(0, numberOfTimeLineItemLoad)
        timelineAdapter = TimelineAdapter(subListTimelineItem, applicationContext)
        recyclerViewTimeline.adapter = timelineAdapter
        swipeContainer.isRefreshing = false
    }

    override fun onRefresh() {
        loadRecyclerViewData()
    }

    private fun initScrollListener() {
        val listener = object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading) {
                    if (linearLayoutManager.findLastVisibleItemPosition() == subListTimelineItem.size - 1) {
                        loadMore()
                        isLoading = true
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        }
        recyclerViewTimeline.addOnScrollListener(listener)
    }

    private fun loadMore() {
        subListTimelineItem.add(null)
        timelineAdapter.notifyItemInserted(subListTimelineItem.size - 1)
        val handler = Handler()
        handler.postDelayed({
            subListTimelineItem.removeAt(subListTimelineItem.size - 1)
            val scrollPosition = subListTimelineItem.size
            timelineAdapter.notifyItemRemoved(scrollPosition)
            subListTimelineItem.addAll(listTimelineItem.subList(scrollPosition, scrollPosition + numberOfTimeLineItemLoad))
            timelineAdapter.notifyDataSetChanged()
            isLoading = false
        }, 1500)
    }
}
