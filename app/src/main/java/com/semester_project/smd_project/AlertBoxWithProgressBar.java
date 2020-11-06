package com.semester_project.smd_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class AlertBoxWithProgressBar
{
    static Activity activity;
    static  AlertDialog alertbox;

    public AlertBoxWithProgressBar(Activity a)
    {
        this.activity = a;
    }

    public static void StartLoadingBar()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(activity);
        LayoutInflater inflating = activity.getLayoutInflater();
        build.setView(inflating.inflate(R.layout.progressbar, null, false));
        build.setCancelable(true);

        alertbox = build.create();
        alertbox.show();
    }

    public static void DissmissMe()
    {
        alertbox.dismiss();
    }
}
