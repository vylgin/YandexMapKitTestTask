package pro.vylgin.yandexmapkittexttask.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.appcompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import pro.vylgin.yandexmapkittexttask.R
import ru.yandex.yandexmapkit
import ru.yandex.yandexmapkit.MapController
import ru.yandex.yandexmapkit.OverlayManager
import ru.yandex.yandexmapkit.overlay.Overlay
import ru.yandex.yandexmapkit.overlay.OverlayItem
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem
import ru.yandex.yandexmapkit.overlay.balloon.OnBalloonListener
import ru.yandex.yandexmapkit.utils.GeoPoint

/**
 * Увы, пользоваться классом не получилось из-за ошибки Error:The following classes have incomplete hierarchies:
 * ru.yandex.yandexmapkit.MapController, unresolved: [StartupListener] в jar yandex map kit,
 * если раскомментировать 40 строку
 */
public class MainActivity : AppCompatActivity(), OnBalloonListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)

        showObjectOnMap()
    }

    private fun showObjectOnMap() {
//        var mapController = map.getMapController()
//        var overlayManager = mapController.getOverlayManager()
//        overlayManager.getMyLocation().setEnabled(false)
//
//        val kremlin = OverlayItem(GeoPoint(55.752004, 37.617017), getResources().getDrawable(android.R.drawable.arrow_down_float))
//        val balloonKremlin = BalloonItem(this, kremlin.getGeoPoint())
//        balloonKremlin.setText("Московский Кремль. Здесь можно ещё много о чём написать.")
//        balloonKremlin.setOnBalloonListener(this)
//        kremlin.setBalloonItem(balloonKremlin)
//        val overlay = Overlay(mapController)
//        overlay.addOverlayItem(kremlin)
//        overlayManager.addOverlay(overlay)
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

        return super<AppCompatActivity>.onOptionsItemSelected(item)
    }

    override fun onBalloonViewClick(balloonItem: BalloonItem?, view: View?) {
        val item = balloonItem?.getOverlayItem()
        debug(item);
    }

    override fun onBalloonAnimationEnd(p0: BalloonItem?) {
    }

    override fun onBalloonAnimationStart(p0: BalloonItem?) {
    }

    override fun onBalloonHide(p0: BalloonItem?) {
    }

    override fun onBalloonShow(p0: BalloonItem?) {
    }

}
