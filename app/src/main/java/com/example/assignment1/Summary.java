package com.example.assignment1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity implements View.OnClickListener {

    // Showing the details that have been validated and retrieved
    private TextView fname, lname, email, dob, gender, validation;

    // Buttons for further progression
    private Button accept, deny, next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_summary);

        fname = (TextView)findViewById(R.id.validFname);
        lname = (TextView)findViewById(R.id.validLname);
        email = (TextView)findViewById(R.id.validEmail);
        dob = (TextView)findViewById(R.id.validDob);
        gender = (TextView)findViewById(R.id.validGender);

        accept = (Button) findViewById(R.id.accept);
        deny = (Button) findViewById(R.id.deny);
        next = (Button) findViewById(R.id.next);




        // These are retrieved from the local internal volatile phone memory
        SharedPreferences prefs = getSharedPreferences("regDetails", MODE_PRIVATE);

        // Getting the details from the ShredPreferences
        String fnameT = prefs.getString("fname", "First name not defined");
        String lnameT = prefs.getString("lname", "Last name not defined");
        String emailT = prefs.getString("email", "Email not defined");
        String dobT = prefs.getString("dob", "Date of birth not defined");
        String genderT= prefs.getString("gender", "Gender not defined");


        fname.setText(fnameT);
        lname.setText(lnameT);
        email.setText(emailT);
        dob.setText(dobT);
        gender.setText(genderT);

    }


    /**
     *  an Onlick method which shows allows for further progression
     *  this method is activated when the user clicks on one
     *  of the buttons that are on the screens this is to allow
     *  the retrieval of the results
     *
     * @param v
     */
    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch(id) {
            case (R.id.accept):
                // proceed
                Toast.makeText(getApplicationContext(), "Details accepted ", Toast.LENGTH_SHORT);
                Log.i("Clicked on", id + "");
                break;

            case (R.id.deny):
                // deny and change details
                Log.i("Clicked on", id + "");
                break;

            case (R.id.next):
                // proceed to the next view
                Log.i("Clicked on", id + "");
                break;
        }
    }
}
