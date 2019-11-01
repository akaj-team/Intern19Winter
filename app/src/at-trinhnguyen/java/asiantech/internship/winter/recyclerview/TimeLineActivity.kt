package asiantech.internship.winter.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import asiantech.internship.summer.R
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_time_line.*

class TimeLineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        val timeLineItems = mutableListOf<TimeLineItem>()
        timeLineItems.apply {
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

        timeLineItems.shuffle()
        recyclerViewTimeLine.adapter = TimeLineAdapter(timeLineItems.subList(0, 10))
    }
}
