package asiantech.internship.winter.musicplayer.ui.online

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import asiantech.internship.summer.R
import asiantech.internship.winter.musicplayer.network.mockapi.SongMockApi
import asiantech.internship.winter.musicplayer.network.mockapi.SongMockApiResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.`at-trinhnguyen`.activity_music.*
import kotlinx.android.synthetic.`at-trinhnguyen`.bottom_sheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicOnlineActivity : AppCompatActivity() {
    private lateinit var adapterMusicOnline: MusicOnlineAdapter
    private var songResponses = mutableListOf<SongMockApiResponse>()
    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var lastPosition = -1
    private var currentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_online)

        initView()
        SongMockApi.songMockApiService.getSong()
                .enqueue(object : Callback<List<SongMockApiResponse>> {
                    override fun onFailure(call: Call<List<SongMockApiResponse>>, t: Throwable) {
                        Log.d("bbb", "mock fail")
                        call.cancel()
                    }

                    override fun onResponse(call: Call<List<SongMockApiResponse>>, response: Response<List<SongMockApiResponse>>) {
                        response.body()?.let {
                            songResponses.clear()
                            songResponses.addAll(it)
                            adapterMusicOnline.submitSongs(songResponses)
                        }

                    }
                })
    }

    private fun initView() {
        sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        recyclerView.let {
            adapterMusicOnline = MusicOnlineAdapter(this)
            it.adapter = adapterMusicOnline
            adapterMusicOnline.onMoreClick = this::eventMoreClick
            it.hasFixedSize()
        }

        tvDelete.setOnClickListener {
            val position = currentPosition
            songResponses[position].id?.let { id ->
                SongMockApi.songMockApiService.deleteSong(id)
                        .enqueue(object : Callback<SongMockApiResponse> {
                            override fun onFailure(call: Call<SongMockApiResponse>, t: Throwable) {
                                Log.d("bbb", t.toString())
                            }

                            override fun onResponse(call: Call<SongMockApiResponse>, response: Response<SongMockApiResponse>) {
                                response.body().let {
                                    Log.d("bbb", it.toString())
                                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                                    songResponses.removeAt(position)
                                    adapterMusicOnline.notifyItemRemoved(position)
                                }
                            }

                        })
            }
        }
    }

    private fun eventMoreClick(position: Int) {
        Toast.makeText(this, "${songResponses[position].id}", Toast.LENGTH_SHORT).show()
        currentPosition = position
        val song = songResponses[position]
        tvSongTitleSheet.text = song.title
        tvArtistSheet.text = song.artist
        Glide.with(this)
                .load(song.image)
                .transform(RoundedCorners(4))
                .priority(Priority.IMMEDIATE)
                .error(R.drawable.ic_song)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_song)
                .into(imgSongArtSheet)

        if (sheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            lastPosition = -1
        }
        if (currentPosition != lastPosition) {
            lastPosition = currentPosition
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            lastPosition = -1
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

}
