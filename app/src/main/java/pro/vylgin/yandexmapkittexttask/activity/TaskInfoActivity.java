package pro.vylgin.yandexmapkittexttask.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pro.vylgin.yandexmapkittexttask.R;
import pro.vylgin.yandexmapkittexttask.network.model.Task;

public class TaskInfoActivity extends AppCompatActivity {

    public static final String TASK_JSON_TAG = "TASK_JSON_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Toolbar taskInfoToolbar = (Toolbar) findViewById(R.id.taskInfoToolbar);
        setSupportActionBar(taskInfoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        taskInfoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);

        Bundle bundle = getIntent().getExtras();
        String jsonTask = bundle.getString(TASK_JSON_TAG);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Task task = gson.fromJson(jsonTask, Task.class);

        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getLongText());
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