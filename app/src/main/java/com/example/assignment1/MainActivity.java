package com.example.assignment1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText fname, lname, email, pass1, pass2;// Adding all the UI components to the class
    private TextView dob ; // Submitting the registration form
    private Button submit; // Submitting the registration form
    private RadioGroup rg ; // Radio buttons for gender selection
    private RadioButton genderSelected;
    private String gender; // For internal variable processing
    private int radioID; // For internal variable processing
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.fname); // Name
        lname = findViewById(R.id.lname); // Last name
        email = findViewById(R.id.email); // Emails id
        dob   = findViewById(R.id.dob);   // Date of birth
        pass1 = findViewById(R.id.pass1); // Password
        pass2 = findViewById(R.id.pass2); // Confirm password
        fname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { hideKeyboard(v); }
            }
        });
        lname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { hideKeyboard(v); }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { hideKeyboard(v); }
            }
        });
        pass1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { hideKeyboard(v); }
            }
        });
        pass2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) { hideKeyboard(v); }
            }
        });
        rg = findViewById(R.id.radioGroup);  // Radiogroup for gender selection
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {// When a certain button is selected
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioID = rg.getCheckedRadioButtonId();// Getting the RadioButton checked ID
                genderSelected =  findViewById(radioID);// Finding the button
                gender = genderSelected.getText().toString();// Set the (global) string for later usage
            }
        });
        submit = findViewById(R.id.submit);// For submitting the form
        submit.setOnClickListener(new View.OnClickListener() {// When the submit button is hit these actions follow
            @Override
            public void onClick(View v) {// Retrieving the information that has been added

                String sName  = fname.getText().toString();
                String sLame  = lname.getText().toString();
                String sDob   = dob.getText().toString();
                String sEmail = email.getText().toString();
                String sPass1 = pass1.getText().toString();
                String sPass2 = pass2.getText().toString();

                if(sName.isEmpty() || sLame.isEmpty() || sDob.isEmpty() || sEmail.isEmpty() || sPass1.isEmpty() || sPass2.isEmpty()) { // Validating all the inputs
                    Log.i("Call", "Incorrect");
                    showIncorrectDetails("Some fields are missing");
                } else if(sPass1.equals(sPass2) && checkPassword(sPass1) && checkPassword(sPass2) && checkString(sName) && checkString(sLame) && checkEmail(sEmail) && !sDob.isEmpty()) {
                    Log.i("Call", "Correct");
                    showCorrectDetails(sName, sLame, sDob, sEmail, gender, sPass1);
                } else if(!sPass1.equals(sPass2)){
                    Log.i("Call", "Pass not match");
                    showIncorrectDetails("The passwords do not match");
                } else {
                    Log.i("Call", "Pass not match");
                    showIncorrectDetails("There was a problem");
                }
            } // end of right info else
        }); // end of click listener

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {// Allowing the to extract the chosen dates
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        dob.setOnClickListener(new View.OnClickListener() { // When user clicks to pick their DOB a calendar will pop up - asking for input
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void showIncorrectDetails(String message){
        Toast toast = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 225);
        LinearLayout toastContentView = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.deny);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxHeight(150);
        imageView.setMaxWidth(150);
        toastContentView.addView(imageView, 0);
        toast.show();
    }
    public void showCorrectDetails(String sName, String sLame, String sDob, String sEmail, String gender, String sPass1){
        SharedPreferences.Editor editor = getSharedPreferences("regDetails", MODE_PRIVATE).edit();// The data will be stored as this is only volatile storage type
        editor.putString("fname", sName);
        editor.putString("lname", sLame);
        editor.putString("dob", sDob);
        editor.putString("email", sEmail);
        editor.putString("gender", gender);
        editor.putString("pass1", sPass1);
        editor.apply();
        Toast toast = Toast.makeText(getApplicationContext(), "Details have been accepted", Toast.LENGTH_SHORT);// by using a custom toast with an image
        toast.setGravity(Gravity.BOTTOM, 0, 225);
        LinearLayout toastContentView = (LinearLayout) toast.getView();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.accept);
        imageView.setAdjustViewBounds(true);
        imageView.setMaxHeight(150);
        imageView.setMaxWidth(150);
        toastContentView.addView(imageView, 0);
        toast.show();
        Intent intentSummary = new Intent(getApplicationContext(), Summary.class); // Initialising an intent for showing the summary page with the results
        startActivity(intentSummary);
    }


    public boolean checkString(String str) {
        if (str == null) {
            return false;
        } else return str.matches("[A-Za-z]");
    }
    public boolean checkPassword(String str) {
        if (str == null) {
            return false;
        } else return str.matches("[/[0-9]/,/[a-z]/,/[A-Z]/,/[!%&*\\s]/, /^.{8,20}$/]");
    }
    public static final Pattern VALID_EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean checkEmail(String str) {
        Matcher matcher = VALID_EMAIL_PATTERN.matcher(str);
        return matcher.find();
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void updateLabel() {   // Formatting the given date to UK format
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dob.setText(sdf.format(myCalendar.getTime()));
    }
}