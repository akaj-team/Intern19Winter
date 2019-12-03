package asiantech.internship.summer.savedata

import android.content.Context

class Preference(context: Context) {
    companion object {
        private const val PREFS_NAME = "tam"
        const val ID_USER = "idUser"
    }

    private val pref by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    internal fun getInt(key: String): Int {
        return pref.getInt(key, 0)
    }

    internal fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    internal fun clear() {
        pref.edit().clear().apply()
    }
}
