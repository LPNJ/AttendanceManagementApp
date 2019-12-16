package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberingActivity extends AppCompatActivity {

    /** */
    private TextView mNumber;
    /** */
    private Button mRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbering);

        mNumber = findViewById(R.id.EventNumber);
        mNumber.setText(getIntent().getStringExtra(IntentKey.EVENT_NUMBER));

        class NumberingActivityOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumberingActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        }

        mRegistration = findViewById(R.id.registration_create);
        NumberingActivityOnClickListener listener = new NumberingActivityOnClickListener();
        mRegistration.setOnClickListener(listener);

    }
}
