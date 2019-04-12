package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class SummaryFragment extends Fragment implements View.OnClickListener{
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private View myView;
    private ListView theListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        myView = inflater.inflate(R.layout.fragment_summary, container, false);// Initialising the view the right parameters
        theListView = (ListView) myView.findViewById(R.id.mobile_list1);// ListView for ArrayAdapter
        loadUser(myView);
        return myView;// Inflate the layout for this fragment
    }
    private void loadUser(View view) {
        MyDBHandler dbHandler = new MyDBHandler(getContext(), null, null, 2);
        String[] myData = {"First name: " + dbHandler.findHandler(1).getUserName(), "Last name: " + dbHandler.findHandler(1).getUserLname(), "Email: " + dbHandler.findHandler(1).getUserEmail(), "Date of birth: " + dbHandler.findHandler(1).getUserDob(), "Gender: " + dbHandler.findHandler(1).getUserDob()};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(myView.getContext(), R.layout.activity_listview_light, myData); // ArrayAdapter allows for custom list to be created
        theListView.setAdapter(myAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition);
        }
    }
    public boolean isNoNumberAtBeginning(String s){
        return  s.matches(".*[a-zA-Z]+.*");
    }
    public void updateArticleView(int position) { mCurrentPosition = position; }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
    @Override
    public void onClick(View v) { }
}