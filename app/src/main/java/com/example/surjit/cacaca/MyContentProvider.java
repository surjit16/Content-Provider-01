package com.example.surjit.cacaca;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.widget.Toast;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }

    SQLiteDatabase myDataBase;
    public static final String dbname = "mydb";
    public static final int dbv = 1;

    public static final String authority = "com.example.contentproviderCACA.myprovider";

    public static final Uri content_uri = Uri.parse("content://"+ authority+"/info");

     static  int per =1;
     static int per_id =2;

     static  UriMatcher  myuri = new UriMatcher(UriMatcher.NO_MATCH);
     static
     {
         myuri.addURI(authority, "info",per);
         myuri.addURI(authority,"info",per_id);
     }

      class MyDataBase extends SQLiteOpenHelper
      {
          Context ct;

          MyDataBase(Context ct)
          {
              super(ct,dbname,null,dbv);
              this.ct = ct;
          }

          @Override
          public void onCreate(SQLiteDatabase sqLiteDatabase) {

              Toast.makeText(ct,"IN Oncreate", Toast.LENGTH_SHORT).show();

              String s = "create table info (name text)";

              sqLiteDatabase.execSQL(s);
          }

          @Override
          public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

          }
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
        // TODO: Implement this to handle requests to insert a new row.
       // throw new UnsupportedOperationException("Not yet implemented");

        long row = myDataBase.insert("info",null,values);
        uri = ContentUris.withAppendedId(content_uri,row);
        getContext().getContentResolver().notifyChange(uri,null);

        return uri;
     }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        MyDataBase mydb = new MyDataBase(getContext());
        myDataBase  = mydb.getWritableDatabase();
        if(myDataBase != null)
        {
            return true;
        }
        else
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
       // throw new UnsupportedOperationException("Not yet implemented");

        SQLiteQueryBuilder myquery = new SQLiteQueryBuilder();
        myquery.setTables("info");

        Cursor cr = myquery.query(myDataBase,null,null,null,null,null,sortOrder);
        cr.setNotificationUri(getContext().getContentResolver(),uri);

        return cr;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
