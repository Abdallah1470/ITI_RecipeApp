import android.content.Context

/**
 * Object to manage the last update timestamp for data.
 */
object DataUpdateManager {
    private const val PREFS_NAME = "data_update_prefs"
    private const val LAST_UPDATE_KEY = "last_update"

    /**
     * Retrieves the last update timestamp from shared preferences.
     *
     * @param context The context used to access shared preferences.
     * @return The last update timestamp in milliseconds.
     */
    fun getLastUpdateTimestamp(context: Context): Long {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(LAST_UPDATE_KEY, 0)
    }

    /**
     * Sets the last update timestamp in shared preferences.
     *
     * @param context The context used to access shared preferences.
     * @param timestamp The timestamp to be set in milliseconds.
     */
    fun setLastUpdateTimestamp(context: Context, timestamp: Long) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putLong(LAST_UPDATE_KEY, timestamp).apply()
    }
}