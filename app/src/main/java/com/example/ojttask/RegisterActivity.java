package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Task.LoginTask;

public class RegisterActivity extends AppCompatActivity {

    AlertDialog mAlertDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("内容を確定しますか？")
                .setPositiveButton("YES", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("NO", null);

        mAlertDlg = builder.create();

        Button button = findViewById(R.id.regist);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mAlertDlg.show();
            }
        });
    }

}

