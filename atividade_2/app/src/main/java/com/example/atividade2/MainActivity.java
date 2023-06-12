package com.example.atividade2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_send);
        editText = (EditText) findViewById(R.id.Text_msg);
    }
    public void send(View view){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("data", editText.getText().toString());
        startActivity(i);
    }
}