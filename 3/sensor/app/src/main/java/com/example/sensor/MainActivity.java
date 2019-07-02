package com.example.sensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button butty = null;
    EditText edy = null;
    static final String IS_BLUE = "";
    boolean isBlueNow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butty = (Button) findViewById(R.id.uniqueButton);
        butty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edy = findViewById(R.id.uniqueId);
                if (isBlueNow) {
                    edy.setBackgroundColor(getResources().getColor(R.color.colorNotBlue));
                    isBlueNow = false;
                } else {
                    edy.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                    isBlueNow = true;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(IS_BLUE, isBlueNow);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        isBlueNow = savedInstanceState.getBoolean(IS_BLUE);
        edy = findViewById(R.id.uniqueId);

        if (isBlueNow) {
            edy.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        }

    }
}
