package com.scxh.android1503.store.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class TicketContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.scxh.android1503.ticketprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private SQLiteDatabase mSQLDB;
    public TicketContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mSQLDB.insert(TicketDB.TABLE_NAME,null,values);
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public boolean onCreate() {
        TicketDB db = new TicketDB(getContext());
        mSQLDB = db.getReadableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
       return mSQLDB.query(TicketDB.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
