package com.example.ashu4642.ironplanner;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Anirudh on 1/23/2015.
 */
public class CalendarListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private View mView;
    private ExerciseDatabase mDB;
    private SimpleCursorAdapter mCursorAdapter;
    private Activity mActivity;
    private View mFragmentContainerView;
    private static final int LOADER_ID = 1;

    public CalendarListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  {
        mView = (View)inflater.inflate(R.layout.fragment_calendar, container, false);
        mListView = (ListView)(mView.findViewById(R.id.listView));
        mDB = new ExerciseDatabase(mActivity);
        try {
            mDB.open();
        } catch(Exception e) {
            System.exit(0);
        }
        Cursor cursor = mDB.queryAll();
        String[] from = new String[] {ExerciseDatabase.KEY_TITLE, ExerciseDatabase.KEY_GROUP};
        int[] to = new int[] {R.id.nameText, R.id.groupText};
        mCursorAdapter = new SimpleCursorAdapter(mActivity, R.layout.row, cursor, from, to, 0);
        setListAdapter(mCursorAdapter);
        //getLoaderManager().initLoader(0, null, this);
        return mView;
    }

    public void setUp(int fragmentId) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mActivity = getActivity();
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), null, ExerciseDatabase.KEYS_ALL,
                null, null, null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
