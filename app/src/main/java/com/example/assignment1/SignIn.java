package com.example.assignment1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    private TextView welcome;
    private EditText username, password;
    private Button login;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// Requesting orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);// Setting content
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                loadUser(user, pass);
            }
        });
    }
    private void loadUser(String user, String pass) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 2);
        if(!dbHandler.findHandler(1).getUserName().isEmpty()) {
            String[] myData = {dbHandler.findHandler(1).getUserName(), dbHandler.findHandler(1).getUserLname(), dbHandler.findHandler(1).getUserEmail(), dbHandler.findHandler(1).getUserDob(), dbHandler.findHandler(1).getUserGender(), dbHandler.findHandler(1).getUserPassword()};
            if (myData[0].equals(user)) {
                if (myData[myData.length - 1].equals(pass)) {
                    Toast toast = Toast.makeText(this, "All details match! Welcome " + user, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 225);
                    LinearLayout toastContentView = (LinearLayout) toast.getView();
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(R.drawable.accept);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(150);
                    toastContentView.addView(imageView, 0);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(this, "Username not found", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 225);
                LinearLayout toastContentView = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.deny);
                imageView.setAdjustViewBounds(true);
                imageView.setMaxHeight(150);
                imageView.setMaxWidth(150);
                toastContentView.addView(imageView, 0);
                toast.show();
            }
        }
    }
}