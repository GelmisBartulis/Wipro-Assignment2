package com.example.assignment1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Adding all the UI components to the class
    private EditText fname, lname, email;
    private TextView dob ;

    // Submitting the registration form
    private Button submit;

    // Radio buttons for gender selection
    private RadioGroup rg ;
    private RadioButton genderSelected;

    // For internal variable processing
    private String gender;
    private int radioID;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Initialise any UI components for registration form

        // Name
        fname = (EditText) findViewById(R.id.fname);

        // Last name
        lname = (EditText) findViewById(R.id.lname);

        // Emails id
        email = (EditText) findViewById(R.id.email);

        // Date of birth
        dob = (TextView) findViewById(R.id.dob);


        // Radiogroup for gender selection
        rg = (RadioGroup) findViewById(R.id.radioGroup);


        // When a certain button is selected
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Getting the RadioButton checked ID
                radioID = rg.getCheckedRadioButtonId();

                // Finding the button
                genderSelected = (RadioButton) findViewById(radioID);

                // Set the (global) string for later usage
                gender = genderSelected.getText().toString();

            }
        });


        // For submitting the form
        submit = (Button) findViewById(R.id.submit);

        // When the submit button is hit these actions follow
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Details have been recorded", Toast.LENGTH_SHORT).show();
                }


                // SharedPreferences allows for localStorage
                // The data will be stored as this is only volatile storage type
                SharedPreferences.Editor editor = getSharedPreferences("regDetails", MODE_PRIVATE).edit();
                editor.putString("fname",  fname.getText().toString());
                editor.putString("lname", lname.getText().toString());
                editor.putString("dob", dob.getText().toString());
                editor.putString("gender", gender);

                if(isValidEmail(email.getText().toString()))
                    editor.putString("email", email.getText().toString());


                editor.apply();

                // Initialising an intent for showing the summary page with the results
                Intent intentSummary = new Intent(getApplicationContext(), Summary.class);
                startActivity(intentSummary);

                Toast.makeText(getApplicationContext(), "Details have been recorded", Toast.LENGTH_SHORT).show();

            }

        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    // Formatting the given date to UK format
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        dob.setText(sdf.format(myCalendar.getTime()));
    }


    // Validating the email
    // Method from Android
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
