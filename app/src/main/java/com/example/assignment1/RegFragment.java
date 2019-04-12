/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.assignment1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import androidx.fragment.app.Fragment;

public class RegFragment extends Fragment implements View.OnClickListener{
    final static String ARG_POSITION = "position"; // Positioning for the fragments
    int mCurrentPosition = -1;
    private View myView;// Global for further uses
    private EditText fname, lname, email, pass1, pass2; // Adding all the UI components to the class
    private TextView dob ; // Adding all the UI components to the class
    private Button submit;// Submitting the registration form
    private RadioGroup rg ;// Radio buttons for gender selection
    private RadioButton genderSelected;// Radio buttons for gender selection
    private String gender;// For internal variable processing
    private int radioID;// For internal variable processing
    private Context context; // Getting local context
    private String sName, sLame, sDob, sEmail, sPass1, sPass2; // For local processes
    final Calendar myCalendar = Calendar.getInstance();// For internal variable processing
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        myView = inflater.inflate(R.layout.fragment_reg, container, false); // Initialising the view the right parameters
        context = myView.getContext(); // getting context from current view
        fname = (EditText) myView.findViewById(R.id.fname);  // Name
        lname = (EditText) myView.findViewById(R.id.lname); // Last name
        email = (EditText) myView.findViewById(R.id.email);  // Emails id
        dob = (TextView) myView.findViewById(R.id.dob); // Date of birth
        pass1 = (EditText) myView.findViewById(R.id.pass1);// password
        pass2 = (EditText) myView.findViewById(R.id.pass2); // password conf
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
        rg = (RadioGroup) myView.findViewById(R.id.radioGroup); // Radiogroup for gender selection
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {// When a certain button is selected
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioID = rg.getCheckedRadioButtonId();// Getting the RadioButton checked ID
                genderSelected = (RadioButton) myView.findViewById(radioID);// Finding the button
                gender = genderSelected.getText().toString();// Set the (global) string for later usage
            }
        });
        submit = (Button) myView.findViewById(R.id.submit);// For submitting the form
        submit.setOnClickListener(new View.OnClickListener() {// When the submit button is hit these actions follow
            @Override
            public void onClick(View v) {// Retrieving the information that has been added
                 sName = fname.getText().toString();
                 sLame = lname.getText().toString();
                 sDob = dob.getText().toString();
                 sEmail = email.getText().toString();
                 sPass1 = pass1.getText().toString();
                 sPass2 = pass2.getText().toString();
                 if(!sPass1.equals(sPass2)) {
                     Toast toast = Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG);
                     toast.setGravity(Gravity.BOTTOM, 0, 225);
                     LinearLayout toastContentView = (LinearLayout) toast.getView();
                     ImageView imageView = new ImageView(context);
                     imageView.setImageResource(R.drawable.deny);
                     imageView.setAdjustViewBounds(true);
                     imageView.setMaxHeight(150);
                     imageView.setMaxWidth(150);
                     toastContentView.addView(imageView, 0);
                     toast.show();
                 }
                if(sName.isEmpty() || sLame.isEmpty() || sDob.isEmpty() || sEmail.isEmpty() || sPass2.isEmpty() || sPass1.isEmpty()) {// Validating all the inputs
                    Toast toast = Toast.makeText(context, "Incorrect details", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM, 0, 225);
                    LinearLayout toastContentView = (LinearLayout) toast.getView();
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(R.drawable.deny);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(150);
                    toastContentView.addView(imageView, 0);
                    toast.show();
                } else { // Notifying the user that the details have been accepted
                    addUser(myView);// by using a custom toast with an image
                    Toast toast = Toast.makeText(context, "Details have been accepted", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 225);
                    LinearLayout toastContentView = (LinearLayout) toast.getView();
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(R.drawable.accept);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(150);
                    toastContentView.addView(imageView, 0);
                    toast.show();
                    SummaryFragment nextFrag= new SummaryFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {// Allowing the to extract the chosen dates
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        dob.setOnClickListener(new View.OnClickListener() {// When user clicks to pick their DOB a calendar will pop up - asking for input
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return myView; // Inflate the layout for this fragment
    }
    public void addUser(View view) {
        MyDBHandler dbHandler = new MyDBHandler(myView.getContext(), null, null, 2);
        User user = new User(1, sName, sLame, sEmail, sDob, gender, sPass1);
        dbHandler.addHandler(user);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) myView.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void updateLabel() {// Formatting the given date to UK format
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dob.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) { // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {// Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }
    public boolean isNoNumberAtBeginning(String s){
        Log.i("Chosen One", " " + s);
        return  s.matches(".*[a-zA-Z]+.*");
    }
    public void updateArticleView(int position) { mCurrentPosition = position; }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);// Save the current article selection in case we need to recreate the fragment
    }
    @Override
    public void onClick(View v) {  }
}
