package com.android.beertracker.manager;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stanislao on 17/11/17.
 */

public abstract class BaseManager {

    private List<AsyncTask<?, ?, ?>> taskList = null;

    protected BaseManager(){
        taskList = new ArrayList<>();
    }

    public void cancelOperations() {
        if(taskList != null && !taskList.isEmpty()) {
            for (AsyncTask<?, ?, ?> task : taskList) {
                task.cancel(true);
            }
        }
    }

    protected void addToTaskList(AsyncTask<?, ?, ?> task) {
        taskList.add(task);
    }

    protected void removeFromTaskList(AsyncTask<?, ?, ?> task) {
        taskList.remove(task);
    }
}