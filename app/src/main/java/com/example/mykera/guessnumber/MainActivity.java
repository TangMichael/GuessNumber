package com.example.mykera.guessnumber;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {
TextView result;
    EditText number;
    Button find;
    Button newGame;
    int random = (int)(Math.random()*100+1);
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView) findViewById(R.id. result);
        number = (EditText)findViewById(R.id. number);
        find = (Button)findViewById(R.id. find);
        newGame=(Button)findViewById(R.id.newGame);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
compare();


            }
        });



    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void compare() {
        boolean check = true;
        while (check) {
            hideSoftKeyboard(this);
            try {
                int input = Integer.parseInt(number.getText().toString());
                if (random > input) {
                    result.setText("Your number is too low");
                    check = false;
                    count++;
                } else if (random < input) {
                    count++;
                    result.setText("Your number is too high");
                    check = false;
                } else {
                    check = false;
                    count++;
                    result.setText("You found it in " + count + " tries!");
                }
            } catch (InputMismatchException e) {

                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                alert.setTitle("Error");
                alert.setMessage("Please enter a valid number");
                alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }


        }
    }


}
