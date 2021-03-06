package com.example.matt.finalgradecalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });







        final EditText currentGrade = (EditText) findViewById(R.id.currentGradeText);
        final EditText desiredGrade = (EditText) findViewById(R.id.desiredGradeText);
        final EditText finalExamWeight = (EditText) findViewById(R.id.finalWeightText);
        final TextView requiredGradeResult = (TextView) findViewById(R.id.requiredGradeResultLabel);

        final Button clearButton = (Button) findViewById(R.id.clearButton);
        final Button calculateButton = (Button) findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DecimalFormat df = new DecimalFormat("#,###,##.000");
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);

                if (!currentGrade.getText().toString().equals("") && !desiredGrade.getText().toString().equals("") && !finalExamWeight.getText().toString().equals("")){
                    Double currentGradeD = Double.parseDouble(currentGrade.getText().toString())/100;
                    Double desiredGradeD = Double.parseDouble(desiredGrade.getText().toString())/100;
                    Double finalWeightD = Double.parseDouble(finalExamWeight.getText().toString())/100;

                    Double requiredGradeD = 100*(desiredGradeD - ((1-finalWeightD)*currentGradeD))/(finalWeightD);
                    requiredGradeResult.setText(df.format(requiredGradeD).toString() + "%");
                }
                else{

                    Snackbar.make(findViewById(android.R.id.content), "Please fill in all the required fields", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentGrade.setText("");
                desiredGrade.setText("");
                finalExamWeight.setText("");
                requiredGradeResult.setText("0.0%");
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
