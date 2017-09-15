package com.squorpikkor.android.app.cubechronometer;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by VadimSquorpikkor on 31.08.2017.
 *
 */
class Dialog {

    private Context context;
    private Controller controller;
    private double temp;

    public double getTemp() {
        return temp;
    }

    Dialog(Context context, Controller controller) {
        this.context = context;
        this.controller = controller;
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
//                alertCommand = command;

                controller.getMethod(command);
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

    void okInputAlert(final String command) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                temp = Double.parseDouble(input.getText().toString().trim());
                controller.getMethod(command);

            }
        });

        alert.show();
    }
}
