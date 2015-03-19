package com.sharesnippetcode.tasky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sharesnippetcode.adapters.TaskListAdapter;
import com.sharesnippetcode.core.Task;
import com.sharesnippetcode.core.TaskDatabase;

import java.util.List;

public class HomeScreen extends ActionBarActivity {

    Button addTaskButton = null;
    ListView taskListView = null;
    ListAdapter taskList = null;
    List<Task> tasks = null;
    TaskDatabase taskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        taskDatabase = new TaskDatabase(this);

        addTaskButton = (Button) this.findViewById(R.id.addButton);
        taskListView = (ListView) this.findViewById(R.id.taskList);

        if(addTaskButton != null){
            addTaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeScreen.this, TaskDetailsScreen.class);
                    startActivity(intent);
                }
            });
        }

        if(taskListView != null){
            taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent taskDetails = new Intent(HomeScreen.this, TaskDetailsScreen.class);
                    taskDetails.putExtra("TaskID", tasks.get(position).getId());
                    startActivity(taskDetails);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        tasks = taskDatabase.getAllTasks();

        taskList = new TaskListAdapter(this, tasks);

        taskListView.setAdapter(taskList);
    }
}
