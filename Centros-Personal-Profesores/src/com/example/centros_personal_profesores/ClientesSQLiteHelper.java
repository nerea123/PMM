package com.example.centros_personal_profesores;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientesSQLiteHelper extends SQLiteOpenHelper {

        
        public ClientesSQLiteHelper(Context context, String name,
                        CursorFactory factory, int version) {
                super(context, name, factory, version);
                // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                
                //db.execSQL(cadSQL);
                
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                
                
        }

}