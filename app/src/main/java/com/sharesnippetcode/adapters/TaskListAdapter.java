package com.sharesnippetcode.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharesnippetcode.core.Task;
import com.sharesnippetcode.tasky.R;

import java.util.List;

/**
 * Created by phucthai on 3/17/15.
 */
public class TaskListAdapter extends BaseAdapter {
    private Activity context = null;
    private List<Task> tasks = null;//new ArrayList<Task>();

    public TaskListAdapter(Activity activity, List<Task> tasks) {
        this.context = activity;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;

        if(convertView == null){
            view = this.context.getLayoutInflater().
                    inflate(R.layout.activity_task_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            viewHolder.notesTextView = (TextView)view.findViewById(R.id.notesTextView);
            view.setTag(viewHolder);
        } else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        Task task = (Task) getItem(position);
        viewHolder.nameTextView.setText(task.getName());
        viewHolder.notesTextView.setText(task.getNotes());

        return view;
    }

    private class ViewHolder{
        public TextView nameTextView;
        public TextView notesTextView;
    }
}
