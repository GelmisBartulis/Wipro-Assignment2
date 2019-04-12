package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    private Button signIn, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        signIn = (Button)findViewById(R.id.signIn);
        register = (Button)findViewById(R.id.register);
        this.signIn.setOnClickListener(this);
        this.register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.signIn:
                Intent signInIntent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(signInIntent);
                break;
            case R.id.register:
                Intent registerIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(registerIntent);
                break;
        }
    }
}
