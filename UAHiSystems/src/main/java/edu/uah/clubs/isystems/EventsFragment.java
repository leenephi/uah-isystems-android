package edu.uah.clubs.isystems;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smithsonln on 11/28/13.
 */
public class EventsFragment extends Fragment {

    private TextView mDateView;

    // The columns we want to pull from the Calendar db
    private static final String[] COLUMNS = new String[] {
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DESCRIPTION,
            CalendarContract.Events.ACCOUNT_NAME};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        Cursor cursor = getActivity().getContentResolver().query(
                CalendarContract.Events.CONTENT_URI, COLUMNS, null, null, null);
//                CalendarContract.Events.ACCOUNT_NAME + "=?",
//                new String[] {"lns0012@uah.edu"}, null);

        ListView listView = (ListView) root.findViewById(R.id.events_listview);

        cursor.moveToFirst();


        if (cursor.getCount() > 0) {
            listView.setAdapter(new MyCursorAdapter(getActivity(), R.layout.event_list_item, cursor,
                    new String[] {
                            CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART,
                            CalendarContract.Events.DESCRIPTION, CalendarContract.Events.ACCOUNT_NAME},
                    new int[] {
                            R.id.event_title, R.id.event_dtstart,
                            R.id.event_description, R.id.event_account_name}));

        }

        mDateView = (TextView) root.findViewById(R.id.event_dtstart);


        return root;
    }

    public class MyCursorAdapter extends SimpleCursorAdapter {


        public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
            super(context, layout, c, from, to);
        }

        public MyCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }

        @Override
        public void setViewText(TextView v, String text) {
            if (v.getId() == R.id.event_dtstart) {
                long dateMillis = Long.parseLong(text);
                SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
                text = format.format(new Date(dateMillis));
                v.setText(text);
            } else {
                super.setViewText(v, text);
            }
        }
    }
}
