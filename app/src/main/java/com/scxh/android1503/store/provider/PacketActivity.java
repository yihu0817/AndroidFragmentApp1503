package com.scxh.android1503.store.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scxh.android1503.R;

public class PacketActivity extends Activity {
    private TextView mShowQuanTxt;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet_layout);

        mShowQuanTxt = (TextView) findViewById(R.id.packet_show_quan_txt);

        ContentObserver contentObserver = new ContentObserver(mHandler) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                searchDB();
            }
        };

        getContentResolver().registerContentObserver(TicketContentProvider.CONTENT_URI,true,contentObserver);
    }

    public void onAddQuanClickLisentner(View v){
        String ticketContent = "满5百送3百";
        String ticketName = "优惠券";

        ContentValues values = new ContentValues();
        values.put(TicketDB.COLUMN_TICKET_NAME, ticketName);
        values.put(TicketDB.COLUMN_TICKET_CONTENT, ticketContent);
        getContentResolver().insert(TicketContentProvider.CONTENT_URI, values);

    }

    public void onFindQuanClickLisentner(View view){
        searchDB();
    }

    public void searchDB() {
        Cursor c = getContentResolver().query(TicketContentProvider.CONTENT_URI, null, null, null, null);
        if (c.getColumnCount() > 0) {
            while (c.moveToNext()) {
                String ticketName = c.getString(c.getColumnIndex(TicketDB.COLUMN_TICKET_NAME));
                String ticketContent = c.getString(c.getColumnIndex(TicketDB.COLUMN_TICKET_CONTENT));

                Log.e("tag", "name           content");
                Log.e("tag", ticketName + "      " + ticketContent);
            }
        }
    }
}
