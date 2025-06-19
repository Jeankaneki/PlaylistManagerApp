package za.ac.iie.playlistmanager


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class DetailedViewActivity : AppCompatActivity() {

    // Log tag for debugging this activity
    private val TAG = "DetailedViewActivity"

    // Views from the layout
    private lateinit var tvPlaylistDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        Log.d(TAG, "onCreate: Activity created.")

        // Initialize views
        tvPlaylistDetails = findViewById(R.id.tvPlaylistDetails)
        val btnShowList: Button = findViewById(R.id.btnShowList)
        val btnCalculateAverage: Button = findViewById(R.id.btnCalculateAverage)
        val btnBack: Button = findViewById(R.id.btnBack)

        // Set up the click listener to display the list of songs
        btnShowList.setOnClickListener {
            Log.i(TAG, "Show List button clicked.")
            displayPlaylist()
        }

        // Set up the click listener to calculate and display the average rating
        btnCalculateAverage.setOnClickListener {
            Log.i(TAG, "Calculate Average button clicked.")
            calculateAndDisplayAverage()
        }

        // Set up the click listener to return to the main screen
        btnBack.setOnClickListener {
            Log.i(TAG, "Back button clicked. Returning to MainActivity.")
            // Finishes this activity and returns to the previous one in the stack
            finish()
        }
    }


    private fun displayPlaylist() {
        // Using a StringBuilder for efficient string concatenation in a loop
        val playlistBuilder = StringBuilder()

        // Check if the playlist is empty
        if (PlaylistManager.songTitles.isEmpty()) {
            tvPlaylistDetails.text = "The playlist is currently empty."
            Log.w(TAG, "displayPlaylist: Playlist is empty.")
            return
        }

        // Loop through each song in the playlist
        for (i in 0 until PlaylistManager.songTitles.size) {
            val title = PlaylistManager.songTitles[i]
            val artist = PlaylistManager.artistNames[i]
            val rating = PlaylistManager.ratings[i]
            val comment = PlaylistManager.comments[i]

            playlistBuilder.append("Title: $title\n")
            playlistBuilder.append("Artist: $artist\n")
            playlistBuilder.append("Rating: $rating/5\n")
            playlistBuilder.append("Comments: $comment\n")
            playlistBuilder.append("--------------------\n\n")
        }

        tvPlaylistDetails.text = playlistBuilder.toString()
        Log.d(TAG, "displayPlaylist: Successfully displayed ${PlaylistManager.songTitles.size} songs.")
    }

    private fun calculateAndDisplayAverage() {
        val ratings = PlaylistManager.ratings

        // Error handling for an empty playlist to avoid division by zero
        if (ratings.isEmpty()) {
            tvPlaylistDetails.text = "Cannot calculate average: The playlist is empty."
            Log.e(TAG, "calculateAndDisplayAverage: Cannot calculate, playlist is empty.")
            return
        }

        var totalRating = 0
        // Loop to sum up all the ratings
        for (rating in ratings) {
            totalRating += rating
        }

        // Calculate the average
        val averageRating = totalRating.toDouble() / ratings.size

        // Format the average to two decimal places for better presentation
        val df = DecimalFormat("#.##")
        val formattedAverage = df.format(averageRating)

        val resultText = "Average Playlist Rating: $formattedAverage / 5"
        tvPlaylistDetails.text = resultText
        Log.d(TAG, "calculateAndDisplayAverage: Average rating is $formattedAverage")
    }
}
