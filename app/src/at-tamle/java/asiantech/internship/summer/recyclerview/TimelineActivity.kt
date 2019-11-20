package asiantech.internship.summer.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-tamle`.activity_timeline.*
import kotlin.random.Random

class TimelineActivity : AppCompatActivity(), FavoriteOnClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    private var timelineItems: MutableList<TimelineItem?> = ArrayList()
    private lateinit var timelineItemAdapter: TimelineAdapter
    private val listImages = mutableListOf(
            R.drawable.img_food_1,
            R.drawable.img_food_2,
            R.drawable.img_food_3,
            R.drawable.img_food_4,
            R.drawable.img_food_5,
            R.drawable.img_food_6,
            R.drawable.img_food_7,
            R.drawable.img_food_8,
            R.drawable.img_food_9,
            R.drawable.img_food_10
    )
    private val listAvatar = mutableListOf(
            R.drawable.img_chibi_boa,
            R.drawable.img_chibi_cute,
            R.drawable.img_chibi_hinata,
            R.drawable.img_chibi_nami,
            R.drawable.img_chibi_rappit,
            R.drawable.img_chibi_sakura
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        initView()
    }

    private fun initView() {
        randomNewItems()
        rvTimeline.layoutManager = LinearLayoutManager(this)
        timelineItemAdapter = TimelineAdapter(rvTimeline, this, timelineItems)
        rvTimeline.adapter = timelineItemAdapter

        timelineItemAdapter.setLoadMore(this)
        swipeContainer.setOnRefreshListener(this)
    }

    override fun onLoadMore() {
        if (timelineItems.size < 30) {
            timelineItems.add(null)
            timelineItemAdapter.notifyItemInserted(timelineItems.size - 1)

            Handler().postDelayed(Runnable {
                timelineItems.removeAt(timelineItems.size - 1)
                timelineItemAdapter.notifyItemRemoved(timelineItems.size)

                val index = timelineItems.size
                val end = index + 10
                val avatar = Random.nextInt(listAvatar.size)
                val num = Random.nextInt(listImages.size)
                for (i in index until end) {
                    TimelineItem("Name ${i}",
                            listImages[num],
                            "ゴーヤーチャンプルー ${i}", (0..100).random(),
                            false, listAvatar[avatar]
                    )

                }
                timelineItemAdapter.notifyDataSetChanged()
                timelineItemAdapter.setLoaded()
            }, 1000)
        } else {
            Toast.makeText(this, R.string.toast_stop_here, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRefresh() {
        Handler().postDelayed(Runnable {
            timelineItems.clear()
            randomNewItems()
            timelineItemAdapter.setLoaded()
            timelineItemAdapter.notifyDataSetChanged()
            swipeContainer.isRefreshing = false
        }, 3000)
    }

    fun randomNewItems() {
        for (i in 0..9) {
            val num = Random.nextInt(listImages.size)
            val avatar = Random.nextInt(listAvatar.size)
            timelineItems.add(
                    TimelineItem("Name ${i}",
                            listImages[num],
                            "ゴーヤーチャンプルー ${i}", (0..100).random(),
                            false, listAvatar[avatar]
                    )
            )
        }
    }
}