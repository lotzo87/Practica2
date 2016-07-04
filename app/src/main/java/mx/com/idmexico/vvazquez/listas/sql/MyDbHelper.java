package mx.com.idmexico.vvazquez.listas.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by sistemas on 03/07/2016.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "DbSqlite";
    private final static int DATABASE_VERSION = 1;

    private int rscId;
    private String appName;
    private String devName;
    private String description;
    private int isInstalled;

    public static final String TABLE_APP_NAME ="TBL_APP";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_APP_NAME = "appname";
    public static final String COLUMN_DEV_NAME = "developer";
    public static final String COLUMN_DESCRIP = "description";
    public static final String COLUMN_RESOURCE = "resource";
    public static final String COLUMN_INSTALLED = "installed";


    private static final String CREATE_TABLE_APP = String.format("create table %s " +
            "(%s integer primary key autoincrement," +
            "%s text not null," +
            "%s text not null," +
            "%s text not null," +
            "%s integer not null," +
            "%s integer not null)",
            TABLE_APP_NAME, COLUMN_ID, COLUMN_APP_NAME, COLUMN_DEV_NAME, COLUMN_DESCRIP, COLUMN_RESOURCE, COLUMN_INSTALLED);


    private static final String DELETE_TABLE_APP =
            String.format("DROP TABLE IF EXISTS ",TABLE_APP_NAME);


    public MyDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE_APP);
        onCreate(db);
    }

}
