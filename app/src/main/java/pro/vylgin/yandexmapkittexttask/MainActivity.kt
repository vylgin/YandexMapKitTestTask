package pro.vylgin.yandexmapkittexttask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.appcompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.activity_main.*
import org.jetbrains.anko.toast

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()

        if (id == R.id.action_update) {
            toast("Обновление...")
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
