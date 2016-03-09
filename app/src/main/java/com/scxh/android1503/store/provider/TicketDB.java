package com.scxh.android1503.store.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/9.
 */
public class TicketDB extends SQLiteOpenHelper {
    private static final String DBNAME = "ticketDB.db";
    private static final int DB_VERSION = 1;

    private static final String PRIMARY_KEY = "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT";
    public static  String TABLE_NAME = "ticket";
    public static String  id = "_id";
    public static String COLUMN_TICKET_NAME = "tickt_Name";
    public static String COLUMN_TICKET_CONTENT = "tickt_content";

    public TicketDB(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+ TABLE_NAME
                + " (" + id + "  " + PRIMARY_KEY
                + " , " + COLUMN_TICKET_NAME
                + " varchar(50) ,"
                + COLUMN_TICKET_CONTENT
                + " varchar(10) )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
