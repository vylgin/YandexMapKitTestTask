package pro.vylgin.yandexmapkittexttask.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import pro.vylgin.yandexmapkittexttask.R;
import pro.vylgin.yandexmapkittexttask.helper.TypefaceHelper;
import pro.vylgin.yandexmapkittexttask.network.model.Price;
import pro.vylgin.yandexmapkittexttask.network.model.Task;

public class TaskInfoActivity extends AppCompatActivity {

    public static final String TASK_JSON_TAG = "TASK_JSON_TAG";

    private TextView titleTextView;
    private TextView descriptionTextView;
    private LinearLayout priceLinearLayout;
    private TextView pricesTextView;
    private TextView dateTextView;
    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        initToolbar();

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        priceLinearLayout = (LinearLayout) findViewById(R.id.priceLinearLayout);
        pricesTextView = (TextView) findViewById(R.id.pricesTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        applyTypeface();

        Task task = getTaskFromIntent();
        showTaskInfo(task);
    }

    private void initToolbar() {
        Toolbar taskInfoToolbar = (Toolbar) findViewById(R.id.taskInfoToolbar);
        setSupportActionBar(taskInfoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskInfoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Task getTaskFromIntent() {
        Bundle bundle = getIntent().getExtras();
        String jsonTask = bundle.getString(TASK_JSON_TAG);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonTask, Task.class);
    }

    private void applyTypeface() {
        Typeface robotoRegularTypeface = TypefaceHelper.getRobotoRegular(this);
        Typeface robotoMediumTypeface = TypefaceHelper.getRobotoMedium(this);
        titleTextView.setTypeface(robotoRegularTypeface);
        descriptionTextView.setTypeface(robotoRegularTypeface);
        pricesTextView.setTypeface(robotoMediumTypeface);
        dateTextView.setTypeface(robotoRegularTypeface);
        locationTextView.setTypeface(robotoRegularTypeface);
    }

    private void showTaskInfo(Task task) {
        titleTextView.setText(task.getText());
        descriptionTextView.setText(task.getLongText());

        initPriceLinearLayout(task);

        dateTextView.setText(getDate(task));
        locationTextView.setText(task.getLocationText());
    }

    private void initPriceLinearLayout(Task task) {
        if (task.getPrices() != null && !task.getPrices().isEmpty()) {
            priceLinearLayout.setVisibility(View.VISIBLE);
            for (Price price : task.getPrices()) {
                addPriceView(price);
            }
        }
    }

    private void addPriceView(Price price) {
        View view = getLayoutInflater().inflate(R.layout.price_item, priceLinearLayout, false);
        priceLinearLayout.addView(view);

        TextView priceTextView = (TextView) view.findViewById(R.id.priceTextView);
        TextView priceDescriptionTextView = (TextView) view.findViewById(R.id.priceDescriptionTextView);

        Typeface robotoRegularTypeface = TypefaceHelper.getRobotoRegular(this);
        priceTextView.setTypeface(robotoRegularTypeface);
        priceDescriptionTextView.setTypeface(robotoRegularTypeface);

        priceTextView.setText(String.valueOf(price.getPrice()));
        priceDescriptionTextView.setText(price.getDescription());
    }

    private String getDate(Task task) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(task.getDate());

        return calendar.get(Calendar.DAY_OF_MONTH) + " " + getMonth(calendar) + " " + calendar.get(Calendar.YEAR);
    }

    private String getMonth(Calendar calendar) {
        int monthPosition = calendar.get(Calendar.MONTH);
        String[] monthsArray = getResources().getStringArray(R.array.months);
        List<String> months = new ArrayList<>(Arrays.asList(monthsArray));

        return months.get(monthPosition);
    }

    public static void show(Context context, Task task) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(task);

        Intent intent = new Intent(context, TaskInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(TaskInfoActivity.TASK_JSON_TAG, json);
        intent.putExtras(bundle);

        context.startActivity(intent);
    }

}
