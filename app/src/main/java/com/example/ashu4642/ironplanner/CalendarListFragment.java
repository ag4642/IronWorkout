package com.example.ashu4642.ironplanner;

/**
 * Created by Anirudh on 2/7/2015.
 */
import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by Anirudh on 1/23/2015.
 */
public class CalendarListFragment extends ListFragment  {

    private ListView mListView;
    private View mView;
    private ArrayAdapter<String> mAdapter;
    private Activity mActivity;
    private View mFragmentContainerView;
    public HashMap<String, Exercise> exerciseDB;
    public HashMap<String, ArrayList<String>> dayDB;
    public int date;
    public int day;
    public int month;
    public int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final int LOADER_ID = 1;

    public CalendarListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GregorianCalendar calendar = new GregorianCalendar();
        date = calendar.get(Calendar.DATE);
        day = calendar.get(Calendar.DAY_OF_WEEK);
        month = calendar.get(Calendar.MONTH);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
        mView = (View)inflater.inflate(R.layout.fragment_calendar, container, false);
        mActivity = getActivity();
        mListView = (ListView)(mView.findViewById(android.R.id.list));
        TextView tx = (TextView)(mView.findViewById(R.id.plus));
        tx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ExerciseListFragment fragment = new ExerciseListFragment();   //day, Name, Reps, Weight,
                fragment.setUp(exerciseDB, dayDB);                            //Name, Reps, Weight,
                fragment.fromCalendar(date+", "+day+", "+month);
                mActivity.getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
            }
        });
        final String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        final TextView tdate = (TextView)(mView.findViewById(R.id.date));
        tdate.setEnabled(false);
        tdate.setText(dayNames[day-1] + " (" + (month+1) + "/" + date + ")");
        TextView left = (TextView)(mView.findViewById(R.id.left));
        TextView right = (TextView)(mView.findViewById(R.id.right));
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                date--; day--;
                if(date <= 0) {
                    month--;
                    if(month < 0)
                        month = 11;
                    date = monthDays[month];
                }
                if(day < 1) day = 7;
                tdate.setText(dayNames[day-1] + " (" + (month+1) + "/" + date + ")");
                reloadDataSet();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                date++; day++;
                if(date > monthDays[month]) {
                    month++;
                    date++;
                    if(month > 11)
                        month = 0;
                }
                if(day > 7) day = 1;
                tdate.setText(dayNames[day-1] + " (" + (month+1) + "/" + date + ")");
                reloadDataSet();
            }
        });
        ArrayList<String> exerciseNames = new ArrayList<String>();
        //for(int i = 0; i < exerciseDB.size(); i++)        exerciseNames.add(exerciseDB.get(i).name);
        for(String s: dayDB.keySet()) {
            if(day == Integer.parseInt(dayDB.get(s).get(0)))
                exerciseNames.add(s);
        }
        mAdapter = new ArrayAdapter<String>(mActivity, R.layout.row, R.id.nameText, exerciseNames);
        setListAdapter(mAdapter);

        return mView;
    }

    public void reloadDataSet() {
        ArrayList<String> exerciseNames = new ArrayList<String>();
        //for(int i = 0; i < exerciseDB.size(); i++)        exerciseNames.add(exerciseDB.get(i).name);
        for(String s: dayDB.keySet()) {
            if(day == Integer.parseInt(dayDB.get(s).get(0)))
                exerciseNames.add(s);
        }
        mAdapter.clear();
        mAdapter.addAll(exerciseNames);
        mAdapter.notifyDataSetChanged();
    }

    public void setUp(int fragmentId) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mActivity = getActivity();
    }

    public void setUp2(HashMap<String, Exercise> exerciseD, HashMap<String, ArrayList<String>> dayD) {
        exerciseDB = exerciseD; dayDB = dayD;
    }

    public void updateDate(String s) {
        String[] pars = s.split(", ");
        date = Integer.parseInt(pars[0]);
        day = Integer.parseInt(pars[1]);
        month = Integer.parseInt(pars[2]);
    }

}
