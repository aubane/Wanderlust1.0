package ie.wit.aubane.wanderlust10.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wanderlust.db";
    private static final int 	DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE_TABLE_TRIPS = "create table trips "
            + "(id integer primary key autoincrement,"
            + "name text not null,"
            + "destination text not null,"
            + "start_date text not null,"
            + "end_date text not null);";

    private static final String DATABASE_CREATE_TABLE_ENTRIES = "create table entries "
            + "(id integer primary key autoincrement,"
            + "content text not null,"
            + "trip_id int not null);";

    public DBDesigner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TABLE_TRIPS);
        database.execSQL(DATABASE_CREATE_TABLE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBDesigner.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS trips");
        db.execSQL("DROP TABLE IF EXISTS entries");
        onCreate(db);
    }
}
