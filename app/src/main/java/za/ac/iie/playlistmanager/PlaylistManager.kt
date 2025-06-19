package za.ac.iie.playlistmanager

import android.util.Log

/**
 * A singleton object to manage the playlist data.
 * This allows a single, consistent source of data across different activities.
 */
object PlaylistManager {

    // Log tag for debugging
    private const val TAG = "PlaylistManager"

    // Using ArrayLists to dynamically store song details.
    // This is more flexible than fixed-size arrays.
    val songTitles = ArrayList<String>()
    val artistNames = ArrayList<String>()
    val ratings = ArrayList<Int>()
    val comments = ArrayList<String>()

    /**
     * Initializes the playlist with some example data as per the assignment.
     */
    init {
        // Pre-populating the playlist with the example data
        addSong("Song A", "Artist A", 4, "Rap")
        addSong("Song B", "Artist B", 1, "Dance song")
        addSong("Song C", "Artist C", 2, "Best Love song")
        addSong("Song D", "Artist D", 3, "Memories")
    }

    /**
     * Adds a new song to the playlist.
     * All details are added to their respective ArrayLists.
     * @param title The title of the song.
     * @param artist The name of the artist.
     * @param rating The song's rating (1-5).
     * @param comment Any comments about the song.
     */
    fun addSong(title: String, artist: String, rating: Int, comment: String) {
        songTitles.add(title)
        artistNames.add(artist)
        ratings.add(rating)
        comments.add(comment)

        // Logging for debugging purposes to confirm a song was added.
        Log.d(TAG, "Added Song: Title='$title', Artist='$artist', Rating=$rating, Comment='$comment'")
    }
}