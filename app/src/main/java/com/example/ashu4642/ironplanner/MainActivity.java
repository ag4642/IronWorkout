package com.example.ashu4642.ironplanner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    //private ExerciseDatabase mDB;
    private Firebase ref;
    private Firebase exe;
    private Firebase day;
    public HashMap<String, Exercise> exerciseDB;
    public static ArrayList<Exercise> exerciseDB2;
    public HashMap<String, ArrayList<String>> dayDB; //day (0-6), name, reps, weight
    public static ArrayList<ArrayAdapter<String>> dayDB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://resplendent-heat-8997.firebaseio.com/");
        exe = ref.child("exe");
        day = ref.child("day");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                DataSnapshot sexe = snap.child("exe");
                DataSnapshot dexe = snap.child("day");
                boolean A = sexe.exists();
                boolean B = dexe.exists();
                if(!sexe.exists()) {
                    exerciseDB = new HashMap<String, Exercise>();
                    exe.setValue(exerciseDB);
                } else {
                    exerciseDB = (HashMap<String, Exercise>)(sexe.getValue());
                }
            if(!dexe.exists()) {
                dayDB = new HashMap<String, ArrayList<String>>();
                day.setValue(dayDB);
            } else {
                dayDB = (HashMap<String, ArrayList<String>>)(dexe.getValue());
            }
        }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        //getActionBar().setBackgroundDrawable(new ColorDrawable(0xff34495e));

    }

    @Override
    public void onDestroy() {
        exe.setValue(exerciseDB);
        day.setValue(dayDB);
        ref.onDisconnect();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        exe.setValue(exerciseDB);
        day.setValue(dayDB);
        super.onPause();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;
        switch(position) {
            case 1:
                fragment = new CalendarListFragment();
                CalendarListFragment tFragment = (CalendarListFragment)fragment;
                tFragment.setUp2(exerciseDB, dayDB);
                fragment = tFragment;
                break;
            case 2:
                fragment = new ExerciseListFragment();
                ExerciseListFragment tFragment2 = (ExerciseListFragment)fragment;
                tFragment2.setUp(exerciseDB, dayDB);
                tFragment2.fromCalendar(null);
                fragment = tFragment2;
                break;
            default:
                fragment = new MainFragment();
                MainFragment tFragment3 = (MainFragment)fragment;
                //tFragment.setUp2(ref);
                fragment = tFragment3;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
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
            getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_settings) {
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

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
