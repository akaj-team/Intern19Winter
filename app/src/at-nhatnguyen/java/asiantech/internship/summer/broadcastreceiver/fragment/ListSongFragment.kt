package asiantech.internship.summer.broadcastreceiver.fragment

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import asiantech.internship.summer.R
import asiantech.internship.summer.broadcastreceiver.Service.MusicService
import asiantech.internship.summer.broadcastreceiver.adapter.ListSongAdapter
import asiantech.internship.summer.broadcastreceiver.model.SongModel
import asiantech.internship.summer.broadcastreceiver.model.Utils
import kotlinx.android.synthetic.`at-nhatnguyen`.fragment_list_song.*

class ListSongFragment : Fragment() {


    private val REQUEST_CODE_READ = 100
    private lateinit var listSong: MutableList<SongModel>
    private lateinit var intent: Intent
    private var isPlay = false

    companion object {
        fun newInstance() = ListSongFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        runTimePermission()
        listSongDevice()
        val adapter = ListSongAdapter(listSong, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        setOnclick(adapter)
       // playSong()
    }

    private fun listSongDevice() {
        listSong = mutableListOf()
        val contentResolver = context?.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver?.query(uri, null, null, null, null)

        if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            do {
                val currentId = cursor.getLong(id)
                val path = ContentUris.withAppendedId(uri, currentId)
                val currentTitle = cursor.getString(title)
                val currentArtist = cursor.getString(artist)
                val currentDuration = cursor.getInt(duration)
                listSong.add(SongModel(currentTitle, currentArtist, currentDuration, path.toString(), currentId))
            } while (cursor.moveToNext())
            cursor.close()
        }
    }

    private fun runTimePermission() {
        val permission = context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ)
    }

    private fun setOnclick(adapter: ListSongAdapter) {
        adapter.click(object : ListSongAdapter.OnClick {
            override fun click(songModel: SongModel) {
                tvSongName.text = songModel.songName
                tvArtist.text = songModel.artist
                tvDuration.text = adapter.getDuration(songModel.duration)
                val bitmap = context?.let { Utils.songImg(it, Uri.parse(songModel.path)) }
                imgSongIcon.setImageBitmap(bitmap)
                if (bitmap == null) {
                    imgSongIcon.setImageResource(R.drawable.ic_song)
                }

                fragmentManager?.beginTransaction()?.
                        replace(R.id.frlActivity,PlayMp3Fragment.newInstance(songModel))?.addToBackStack(null)?.
                        commit()
                //starService(songModel)
            }
        })
    }

    private fun starService(songModel:SongModel){
        intent = Intent(context,MusicService::class.java)
        context?.stopService(intent)
        intent.putExtra("URI",songModel.path.toString())
    }

     fun playSong(){
        imgPlay.setOnClickListener {
            if (isPlay == false){
                context?.startService(intent)
                imgPlay.setImageResource(R.drawable.ic_pause_circle_filled)
                isPlay = true
            }else{
                context?.stopService(intent)
                imgPlay.setImageResource(R.drawable.ic_play_arrow)
                isPlay = false
            }
        }
    }

}
