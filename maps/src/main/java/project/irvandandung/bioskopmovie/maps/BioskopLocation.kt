package project.irvandandung.bioskopmovie.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BioskopLocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bioskop_location)
        supportActionBar?.title = "Bioskop Location"
    }
}