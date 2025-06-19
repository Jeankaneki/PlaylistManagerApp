package za.ac.iie.playlistmanager

import android.util.Log


// A singleton object to manage the playlist data.

object PlaylistManager {

    // Log tag for debugging
    private const val TAG = "PlaylistManager"

    // Using ArrayLists to dynamically store song details.
    // This is more flexible than fixed-size arrays.
    val songTitles = ArrayList<String>()
    val artistNames = ArrayList<String>()
    val ratings = ArrayList<Int>()
    val comments = ArrayList<String>()

    // initialize data
    init {
        // Pre-populating the playlist with example data
        addSong("Creation", "James Mc", 4, "Rap")
        addSong("Peace", "Jin K", 3, "Dance song")
        addSong("Lovers and love", "Artist C", 5, "Best Love song")
        addSong("Dangerous", "Monkey D", 2, "Memories")
    }

    fun addSong(title: String, artist: String, rating: Int, comment: String) {
        songTitles.add(title)
        artistNames.add(artist)
        ratings.add(rating)
        comments.add(comment)

        // Logging for debugging purposes to confirm a song was added.
        Log.d(TAG, "Added Song: Title='$title', Artist='$artist', Rating=$rating, Comment='$comment'")
    }
}