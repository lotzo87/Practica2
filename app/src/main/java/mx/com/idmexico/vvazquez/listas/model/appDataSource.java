package mx.com.idmexico.vvazquez.listas.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mx.com.idmexico.vvazquez.listas.sql.MyDbHelper;

/**
 * Created by sistemas on 03/07/2016.
 */
public class appDataSource {
    private final SQLiteDatabase db;

    public appDataSource(Context context) {
        MyDbHelper helper = new MyDbHelper(context);
        db=helper.getWritableDatabase();
    }
    public void saveApp(modelListItem modelAppList)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.COLUMN_APP_NAME,modelAppList.getAppName());
        contentValues.put(MyDbHelper.COLUMN_DEV_NAME,modelAppList.getDevName());
        contentValues.put(MyDbHelper.COLUMN_DESCRIP,modelAppList.getDescription());
        contentValues.put(MyDbHelper.COLUMN_INSTALLED,modelAppList.getIsInstalled());
        contentValues.put(MyDbHelper.COLUMN_RESOURCE,modelAppList.getRscId());
        db.insert(MyDbHelper.TABLE_APP_NAME,null,contentValues);
    }

    public void deleteApp(modelListItem modelAppList)
    {
        db.delete(MyDbHelper.TABLE_APP_NAME,MyDbHelper.COLUMN_ID+"=?",
                new String[]{String.valueOf(modelAppList.getId())});
    }

    public List<modelListItem> getApp(int appId)
    {
        List<modelListItem> modelAppList = new ArrayList<>();
        Cursor cursor =db.query(MyDbHelper.TABLE_APP_NAME, null,MyDbHelper.COLUMN_ID+ "=?",
                new String[] {String.valueOf(appId)},null,null,null,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_ID));
            String appname=cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_APP_NAME));
            String developer = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DEV_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DESCRIP));
            int resource = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_RESOURCE));
            int installed = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_INSTALLED));
            modelListItem modelApp = new modelListItem(id,resource,appname,developer,description,installed);
            modelAppList.add(modelApp);
        }
        return modelAppList;
    }

    public List<modelListItem> getAllApp()
    {
        List<modelListItem> modelAppList = new ArrayList<>();
        Cursor cursor =db.query(MyDbHelper.TABLE_APP_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_ID));
            String appname=cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_APP_NAME));
            String developer = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DEV_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_DESCRIP));
            int resource = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_RESOURCE));
            int installed = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbHelper.COLUMN_INSTALLED));
            modelListItem modelApp = new modelListItem(id,resource,appname,developer,description,installed);
            modelAppList.add(modelApp);
        }
        return modelAppList;
    }
}
