package com.example.ashu4642.ironplanner;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Anirudh on 2/7/2015.
 */
public class MainFragment extends ListFragment {

    private ListView mListView;
    private View mView;
    private ArrayAdapter mCursorAdapter;
    private Activity mActivity;
    private View mFragmentContainerView;
    public GregorianCalendar c = new GregorianCalendar();
    private static final int LOADER_ID = 1;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = (View)inflater.inflate(R.layout.fragment_main, container, false);
        mListView = (ListView)(mView.findViewById(android.R.id.list));
        mCursorAdapter = new ArrayAdapter<String>(
            getActivity().getActionBar().getThemedContext(),
            R.layout.row2,
            R.id.mothafuckajones,
            new String[] {
                "Exercise 1",
                "Exercise 2"
            }
        );
        setListAdapter(mCursorAdapter);
        TextView tx = (TextView)(mView.findViewById(R.id.editText12));
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        String s = months[c.get(Calendar.MONTH)] + " ";
        s += c.get(Calendar.DATE) +", " + c.get(Calendar.YEAR);
        tx.setText(s);
        final LinearLayout square4 = (LinearLayout)(mView.findViewById(R.id.square4));
        square4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    /*
                    SWITCH FRAGMENTS AND
                     */
            }
        });
        return mView;
    }

    public void setUp(int fragmentId) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mActivity = getActivity();
    }


}
