package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by VadimSquorpikkor on 31.08.2017.
 *
 */
public class Dialog {

    private Context context;
    private String alertCommand;

    public String getAlertCommand() {
        return alertCommand;
    }

    public Dialog(Context context) {
        this.context = context;
    }

    void okAlert(int message) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setMessage(message);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    void okCancelAlert(int message, final String command) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setMessage(message);

        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                alertCommand = command;
                dialog.cancel();
            }
        });

        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
    }
}
