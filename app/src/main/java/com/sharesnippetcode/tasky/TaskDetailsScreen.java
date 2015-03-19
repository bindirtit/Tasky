package com.sharesnippetcode.tasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sharesnippetcode.core.Task;
import com.sharesnippetcode.core.TaskDatabase;

/**
 * Created by phucthai on 3/16/15.
 */
public class TaskDetailsScreen extends Activity {

    TaskDatabase _taskDatabase;
    Button cancelDeleteButton;
    Button saveButton;
    EditText nameTextEdit;
    EditText notesTextEdit;
    Task task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_task_details);
        _taskDatabase = new TaskDatabase(this);

        int taskID = -1;
        Intent taskDetails = this.getIntent();
        taskID = taskDetails.getIntExtra("TaskID", 0);

        if(taskID > 0){
            task = _taskDatabase.getTask(taskID);
        }

        _taskDatabase = new TaskDatabase(this);

        cancelDeleteButton = (Button)findViewById(R.id.cancelDeleteButton);
        saveButton = (Button)findViewById(R.id.saveButton);
        nameTextEdit = (EditText)findViewById(R.id.nameText);
        notesTextEdit = (EditText)findViewById(R.id.notesText);

        if(cancelDeleteButton != null){
            cancelDeleteButton.setText((task != null) ? "Delete" : "Cancel");
            cancelDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CancelDelete();
                }
            });
        }

        if(task != null){
            nameTextEdit.setText(task.getName());
            notesTextEdit.setText(task.getNotes());
        }

        if(saveButton != null){
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save();
                }
            });
        }
    }

    private void CancelDelete() {
        if(task != null ){
            _taskDatabase.deleteTask(task);
        }
        this.finish();
    }

    private void save(){
        if(task == null){
            task = new Task();
            task.setNotes(notesTextEdit.getText().toString());
            task.setName(nameTextEdit.getText().toString());
            _taskDatabase.addTask(task);
        } else{
            task.setNotes(notesTextEdit.getText().toString());
            task.setName(nameTextEdit.getText().toString());
            _taskDatabase.updateTask(task);
        }

        this.finish();
    }
}
