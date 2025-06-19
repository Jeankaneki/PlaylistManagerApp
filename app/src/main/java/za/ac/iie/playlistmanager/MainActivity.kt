package za.ac.iie.playlistmanager

// Student Number: ST10492515
// Full Name: Jean Nolobe Mwana



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Log tag to debugging this activity
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Logging the creation of the activity
        Log.d(TAG, "onCreate: Activity created.")

        // Getting references to the buttons from the layout
        val btnAddSong: Button = findViewById(R.id.btnAddSong)
        val btnViewPlaylist: Button = findViewById(R.id.btnViewPlaylist)
        val btnExit: Button = findViewById(R.id.btnExit)

        // Set up the click listener for the "Add Song" button
        btnAddSong.setOnClickListener {
            Log.i(TAG, "Add Song button clicked.")
            showAddSongDialog()
        }

        // Set up the click listener for the "View Playlist" button
        btnViewPlaylist.setOnClickListener {
            Log.i(TAG, "View Playlist button clicked. Navigating to DetailedViewActivity.")
            // Intent to navigate to the DetailedViewActivity
            val intent = Intent(this, DetailedViewActivity::class.java)
            startActivity(intent)
        }

        // Set up the click listener for the "Exit" button
        btnExit.setOnClickListener {
            Log.i(TAG, "Exit button clicked. Closing the application.")
            // Finishes the activity and closes the app
            finish()
        }
    }


      //Displays an AlertDialog to get song details from the user.

    private fun showAddSongDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_song, null)
        val etSongTitle = dialogView.findViewById<EditText>(R.id.etSongTitle)
        val etArtistName = dialogView.findViewById<EditText>(R.id.etArtistName)
        val etRating = dialogView.findViewById<EditText>(R.id.etRating)
        val etComments = dialogView.findViewById<EditText>(R.id.etComments)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add a New Song")
            .setView(dialogView)
            .setPositiveButton("Add", null) // Set to null to override and prevent auto-dismiss
            .setNegativeButton("Cancel") { dialog, _ ->
                Log.d(TAG, "Add song dialog cancelled.")
                dialog.dismiss()
            }
            .create()

        dialog.show()

        // Override the positive button's click listener for validation
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // Extracting user input
            val title = etSongTitle.text.toString().trim()
            val artist = etArtistName.text.toString().trim()
            val ratingStr = etRating.text.toString().trim()
            val comments = etComments.text.toString().trim()

            // This is Error Handling
            if (title.isEmpty() || artist.isEmpty() || ratingStr.isEmpty()) {
                // Constructive feedback if fields are empty
                Log.w(TAG, "Validation failed: Some fields are empty.")
                Toast.makeText(this, "Please fill in Title, Artist, and Rating.", Toast.LENGTH_LONG).show()
                return@setOnClickListener // Keep the dialog open
            }

            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating !in 1..5) {
                // Constructive feedback for invalid rating
                Log.w(TAG, "Validation failed: Invalid rating '$ratingStr'.")
                Toast.makeText(this, "Please enter a rating between 1 and 5.", Toast.LENGTH_LONG).show()
                return@setOnClickListener // Keep the dialog open
            }

            // If validation passes
            Log.d(TAG, "Validation successful. Adding song.")
            PlaylistManager.addSong(title, artist, rating, comments)
            Toast.makeText(this, "Song added successfully!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Close the dialog
        }
    }
}
