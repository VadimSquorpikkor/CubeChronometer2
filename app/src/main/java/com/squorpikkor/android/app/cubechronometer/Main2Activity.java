package com.squorpikkor.android.app.cubechronometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView text1, text2;
    Button button1, button2;

    String s;

    long startTime;
    long endTime;

    boolean stopButtonNotPressed;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (stopButtonNotPressed) {
                Log.e("LOGG!!", "Came inside while cycle");
                try {
                    Thread.sleep(100);
                    Log.e("LOGG!!!", (System.currentTimeMillis() - startTime) + " ms");
                    s = (System.currentTimeMillis() - startTime)/100 + " ms";

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            text1.setText(s);
                        }
                    });

                    endTime = System.currentTimeMillis() - startTime;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.e("LOGG!!", "Passing while cycle");
//            endTime = System.currentTimeMillis() - startTime;
        }
    };

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopButtonNotPressed = true;

        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button1:
                        stopButtonNotPressed = true;
                        thread = new Thread(runnable);
                        thread.start();
                        break;
                    case R.id.button2:
//                        openActivity();
                        Log.e("LOGG!!", "Before pressing stop button");
                        stopButtonNotPressed = false;
                        Log.e("LOGG!!", "After pressing stop button");
                        text2.setText(String.valueOf(endTime) + " ms");
                        break;
                }
            }
        };

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
    }

    void displayVar() {
        text1.setText(String.valueOf(startTime));
        text2.setText(String.valueOf(endTime));
    }

    void openActivity() {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("value", "Message!!!");
        startActivity(intent);

    }

    long getTime() {
        return System.currentTimeMillis();
    }

    void displayTime(long t) {
        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }

            text1.setText(String.valueOf(t));
        }
    }


    void displayVar(long k) {
        text1.setText(String.valueOf(k));
    }

    void displayVar(Class<?> t) {
        text1.setText(String.valueOf(t));
    }
}
