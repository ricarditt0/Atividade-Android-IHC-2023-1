package com.example.atividade_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.sum);
        textView = findViewById(R.id.textView);
        et1 = (EditText) findViewById(R.id.txt_number1);
        et2 = (EditText) findViewById(R.id.txt_number2);
    }
    public void sumar(View view){
        int num1 = Integer.parseInt(et1.getText().toString());
        int num2 = Integer.parseInt(et2.getText().toString());
        int sum = num1 + num2;
        textView.setText(textView.getText().toString() + String.valueOf(sum));
    }
}