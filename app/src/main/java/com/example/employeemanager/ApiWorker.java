package com.example.employeemanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ApiWorker extends Worker {
    private int employeeId;
    private EmployeeInfo info;
    int action=0;

    public ApiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        Data inputData = getInputData();

        this.action = inputData.getInt("action", 0);
        this.info = (EmployeeInfo) inputData.getKeyValueMap().get("info");
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        Log.d("ApiWorker", "halo?");
        return Result.success();
    }

    private void Post()
    {

    }

    private void Get()
    {

    }

    private void Delete()
    {

    }

    private void Put()
    {

    }



}
