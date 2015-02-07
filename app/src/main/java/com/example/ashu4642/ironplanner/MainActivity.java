package com.example.ashu4642.ironplanner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainActivity extends Activity
        implements com.example.ashu4642.ironplanner.NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private com.example.ashu4642.ironplanner.NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ashu4642.ironplanner.R.layout.activity_main);
        System.out.println("Amir");

        mNavigationDrawerFragment = (com.example.ashu4642.ironplanner.NavigationDrawerFragment)
                getFragmentManager().findFragmentById(com.example.ashu4642.ironplanner.R.id.navigation_drawer);
        //mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                com.example.ashu4642.ironplanner.R.id.navigation_drawer,
                (DrawerLayout) findViewById(com.example.ashu4642.ironplanner.R.id.drawer_layout));
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff6479a8));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = PlaceholderFragment.newInstance(position+1);
        /* if(position == 1) {
            CalendarListFragment elfrag = (CalendarListFragment)fragment;
            elfrag.setUp(R.id.container);
            fragment = elfrag;
        } */
        fragmentManager.beginTransaction()
                .replace(com.example.ashu4642.ironplanner.R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(com.example.ashu4642.ironplanner.R.string.title_section1);
                break;
            case 2:
                mTitle = getString(com.example.ashu4642.ironplanner.R.string.title_section2);
                break;
            case 3:
                mTitle = getString(com.example.ashu4642.ironplanner.R.string.title_section3);
                break;
            case 4:
                mTitle = getString(com.example.ashu4642.ironplanner.R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(com.example.ashu4642.ironplanner.R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.ashu4642.ironplanner.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static View mView = null;
        public static int whichScreen = 0;
        public static GregorianCalendar c = new GregorianCalendar();
        public static int month = c.get(Calendar.MONTH);
        public static int day = c.get(Calendar.DAY_OF_WEEK);
        public static int date = c.get(Calendar.DATE);
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //if(sectionNumber == 2)
            //    fragment = new CalendarListFragment();
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(com.example.ashu4642.ironplanner.R.layout.fragment_main, container, false);
            int j = getArguments().getInt(ARG_SECTION_NUMBER);
            whichScreen = j;
            switch (j) {
                case 1:
                    rootView = inflater.inflate(com.example.ashu4642.ironplanner.R.layout.fragment_main, container, false);
                    mView = rootView;
                    updateMain();
                    break;
                case 2:
                    rootView = inflater.inflate(com.example.ashu4642.ironplanner.R.layout.fragment_calendar, container, false);
                    mView = rootView;
                    updateCalendar();
                    break;
                /*case 3:
                    mTitle = getString(R.string.title_section3);
                    break;
                case :
                    mTitle = getString(R.string.title_section4);
                    break;*/
            }
            return rootView;
        }

        public void updateMain() {
            TextView tx = (TextView)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.editText12));
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                    "October", "November", "December"};
            String s = months[c.get(Calendar.MONTH)] + " ";
            s += c.get(Calendar.DATE) +", " + c.get(Calendar.YEAR);
            tx.setText(s);
            final LinearLayout square4 = (LinearLayout)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.square4));
            square4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    /*
                    SWITCH FRAGMENTS AND
                     */
                }
            });
        }

        public void updateCalendar() {
            updateCalendarHelper();
            ListView lv = (ListView)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.listView));
            lv.setAdapter(new ArrayAdapter<String>(
                getActivity().getActionBar().getThemedContext(),
                com.example.ashu4642.ironplanner.R.layout.row2,
                com.example.ashu4642.ironplanner.R.id.rowtext,
                //R.layout.exercise_list_item,
                //R.id.exercise_text,
                new String[]{
                        "Exercise 1",
                        "Exercise 2",
                        "Exercise 3",
                        "Exercise 4"
                        /*getString(R.string.title_section1),
                        getString(R.string.title_section2),
                        getString(R.string.title_section3),
                        getString(R.string.title_section4) */
                }
            ));
            TextView buttonL = (TextView)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.left));
            TextView buttonR = (TextView)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.right));
            buttonL.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //startDetail(0, true);  // true = create new
                    date -= 1;
                    if(date <= 0) {
                        date = 31;
                        month--;
                        if(month == -1)
                            month = 11;
                    }
                    day -= 1;
                    if(day == 0)
                        day = 7;
                    updateCalendarHelper();
                }
            });
            buttonR.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //startDetail(0, true);  // true = create new
                    date += 1;
                    if(date > 31) {
                        date = 1;
                        month++;
                    }
                    day += 1;
                    if(day == 8)
                        day = 1;
                    updateCalendarHelper();
                }
            });
        }

        public void updateCalendarHelper() {
            TextView tx = (TextView)(mView.findViewById(com.example.ashu4642.ironplanner.R.id.date));
            Calendar c = new GregorianCalendar();
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String s = days[day-1] + " ("; //String s = days[c.get(Calendar.DAY_OF_WEEK)-1] + " (";
            s += (month+1) + "/" + date + ")"; //s += (c.get(Calendar.MONTH)+1) + "/" + (c.get(Calendar.DATE)) + ")";
            tx.setText(s);
        }

        @Override
        public void onResume() {
            super.onResume();
            switch(whichScreen) {
                case 1:
                    updateMain();
                    break;
                case 2:
                    updateCalendar();
                    break;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
