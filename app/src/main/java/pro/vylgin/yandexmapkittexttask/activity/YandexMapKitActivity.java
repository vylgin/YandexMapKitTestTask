package pro.vylgin.yandexmapkittexttask.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import pro.vylgin.yandexmapkittexttask.R;
import pro.vylgin.yandexmapkittexttask.network.model.Location;
import pro.vylgin.yandexmapkittexttask.network.model.Task;
import pro.vylgin.yandexmapkittexttask.network.request.TasksRetrofitSpiceRequest;
import pro.vylgin.yandexmapkittexttask.network.response.TasksResponse;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.OnBalloonListener;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class YandexMapKitActivity extends BaseActivity implements OnBalloonListener {

    public static final String TAG = YandexMapKitActivity.class.getSimpleName();

    private MapView mapView;
    private MapController mapController;
    private Menu menu;
    private boolean isRefreshing;

    private OverlayManager overlayManager;
    private ArrayList<Pair<Task, BalloonItem>> taskBallonItems;
    private Overlay overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        initMap();
        sendRequest();
    }

    private void sendRequest() {
        TasksRetrofitSpiceRequest userRequest = new TasksRetrofitSpiceRequest();
        getSpiceManager().execute(userRequest, "tasksRequest", DurationInMillis.ALWAYS_EXPIRED, new TasksRequestListener());
        isRefreshing = true;
    }

    private void initMap() {
        mapController = mapView.getMapController();
        overlayManager = mapController.getOverlayManager();
        overlayManager.getMyLocation().setEnabled(false);
    }

    public void showObjectsOnMap(ArrayList<Task> tasks){
        taskBallonItems = new ArrayList<>();
        if (overlay != null) {
            overlay.clearOverlayItems();
        }

        Resources res = getResources();
        overlay = new Overlay(mapController);

        for (Task task : tasks) {
            Location taskLocation = task.getLocation();
            OverlayItem overlayItem = new OverlayItem(
                    new GeoPoint(taskLocation.getLat(), taskLocation.getLon()),
                    res.getDrawable(R.mipmap.ic_launcher));

            BalloonItem balloonItem = new BalloonItem(this, overlayItem.getGeoPoint());
            balloonItem.setText(task.getTitle());
            balloonItem.setOnBalloonListener(this);

            overlayItem.setBalloonItem(balloonItem);

            overlay.addOverlayItem(overlayItem);
            taskBallonItems.add(new Pair<>(task, balloonItem));
        }

        overlayManager.addOverlay(overlay);
        mapController.notifyRepaint();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        if (isRefreshing) {
            menu.findItem(R.id.action_update).setActionView(new ProgressBar(this));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_update) {
            Toast.makeText(this, "Обновление...", Toast.LENGTH_SHORT).show();

            isRefreshing = true;
            item.setActionView(new ProgressBar(this));

            sendRequest();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBalloonViewClick(BalloonItem balloonItem, View view) {
        for (Pair<Task, BalloonItem> taskBallonItem : taskBallonItems) {
            if (taskBallonItem.second.equals(balloonItem)) {
                Task task = taskBallonItem.first;
                TaskInfoActivity.show(this, task);
                break;
            }
        }
    }

    @Override
    public void onBalloonShow(BalloonItem balloonItem) {
    }

    @Override
    public void onBalloonHide(BalloonItem balloonItem) {
    }

    @Override
    public void onBalloonAnimationStart(BalloonItem balloonItem) {
    }

    @Override
    public void onBalloonAnimationEnd(BalloonItem balloonItem) {
    }

    private class TasksRequestListener implements RequestListener<TasksResponse> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            isRefreshing = false;
            Log.d(TAG, spiceException.getCause().getMessage());
        }

        @Override
        public void onRequestSuccess(TasksResponse tasksResponse) {
            showObjectsOnMap(tasksResponse.getTasks());

            MenuItem updateMenuItem = menu.findItem(R.id.action_update);
            updateMenuItem.setActionView(null);

            isRefreshing = false;
        }
    }
}
