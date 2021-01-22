package project.irvandandung.bioskopmovie


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*
import project.irvandandung.bioskopmovie.movie_page.MoviePageFragment
import project.irvandandung.bioskopmovie.setting_page.SettingPageFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navView : NavigationView
    private var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, MoviePageFragment())
                    .commit()
        }

        navView.setNavigationItemSelectedListener(this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Go to Bioskop Location Feature", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            val uri = Uri.parse("bioskopmovie://maps")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var title = getString(R.string.nav_header_title)
        var stateFragment : Fragment? = null
        when (item.itemId){
            R.id.nav_movie -> {
                stateFragment = fragment
                fragment = MoviePageFragment()
                title = getString(R.string.nav_header_title)
            }
            R.id.nav_movie_favorite -> {
                val uri = Uri.parse("bioskopmovie://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            R.id.nav_setting ->{
                stateFragment = fragment
                fragment = SettingPageFragment()
                title = getString(R.string.text_setting_menu)
            }
        }

        stateFragment?.let { supportFragmentManager.beginTransaction().remove(it).commit() }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, it)
                .commit()
        }

        supportActionBar?.title = title

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}