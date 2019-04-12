package com.example.assignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {
    private Button next;// Buttons for further progression
    private ListView theListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// Allowing the application to request a ful screen mode with no titles
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_summary);// Setting the content view (UI)
        theListView = (ListView) findViewById(R.id.mobile_list);// ListView for ArrayAdapter
        next = (Button) findViewById(R.id.next);// Button for progression
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forward = new Intent(getApplicationContext(), FragmentActivityu.class);
                startActivity(forward);
            }
        });
        SharedPreferences prefs = getSharedPreferences("regDetails", MODE_PRIVATE);// These are retrieved from the local internal volatile phone memory
        String fnameT = prefs.getString("fname", "First name not defined"); // Getting the details from the ShredPreferences
        String lnameT = prefs.getString("lname", "Last name not defined"); // Getting the details from the ShredPreferences
        String emailT = prefs.getString("email", "Email not defined"); // Getting the details from the ShredPreferences
        String dobT = prefs.getString("dob", "Date of birth not defined"); // Getting the details from the ShredPreferences
        String genderT = prefs.getString("gender", "Gender not defined"); // Getting the details from the ShredPreferences
        String[] myData = {"First name: " + fnameT, "Last name: " + lnameT, "Email: " + emailT, "Date of birth: " + dobT, "Gender: " + genderT};// The data that has been saved and now being shown within the summary field
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, myData);// ArrayAdapter allows for custom list to be created
        theListView.setAdapter(myAdapter);
    }
}