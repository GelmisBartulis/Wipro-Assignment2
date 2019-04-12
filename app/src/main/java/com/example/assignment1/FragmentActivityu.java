package com.example.assignment1;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivityu extends AppCompatActivity {
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// Requesting fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragment_activityu);// Setting the content view
        if (savedInstanceState == null) {
            RegFragment newFragment = new RegFragment();// Create fragment and give it an argument specifying the article it should show
            Bundle args = new Bundle();
            args.putInt(RegFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                        R.animator.fragment_slide_left_exit,
                        R.animator.fragment_slide_right_enter,
                        R.animator.fragment_slide_right_exit);
            transaction.replace(R.id.fragment_container, newFragment);// Replace whatever is in the fragment_container view with this fragment,
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}