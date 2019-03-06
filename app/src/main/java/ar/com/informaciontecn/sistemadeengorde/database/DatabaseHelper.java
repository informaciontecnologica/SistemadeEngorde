package ar.com.informaciontecn.sistemadeengorde.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

public  class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "requerimiento_nutrientes.db";
    public static final String DBLOCATION = "/data/data/ar.com.informaciontecn.sistemadeengorde/databases/";
    private Context mContext;
    private  SQLiteDatabase mDatabase;

    public DatabaseHelper (Context context){
        super(context, DBNAME, null,1);
        this.mContext=context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDatabase (){
        String dbPAth= mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase=SQLiteDatabase.openDatabase(dbPAth,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase (){
        if(mDatabase == null){
            mDatabase.close();
        }
    }
    public List<Requerimientonutrientes> getListaRequerimiento(){
        Requerimientonutrientes Requerimientos = null;
        List<Requerimientonutrientes> RequerimientoLista = new ArrayList<>();
        openDatabase();
        Cursor cursor=mDatabase.rawQuery("SELECT * FROM Beef", null);
        if (cursor.moveToFirst()) {
            //Loop through the table rows Requerimientonutrientes
            do {

//               Log.v("Registros",cursor.getString(1) +"-"+ cursor.getString(12)+"-"+cursor.getString(13)+"-"+ cursor.getString(14));

                Requerimientos = new Requerimientonutrientes(cursor.getInt(0),cursor.getString(1) , cursor.getString(11),cursor.getString(12), cursor.getString(13));
                RequerimientoLista.add(Requerimientos);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return RequerimientoLista;

    }
    public List<Requerimientonutrientes> getListaConsulta(String[] descripcion){
        Requerimientonutrientes Requerimientos = null;
        List<Requerimientonutrientes> RequerimientoLista = new ArrayList<>();
        openDatabase();
        Log.v("consulta","click");


        Cursor cursor=mDatabase.rawQuery("SELECT * FROM Beef where Descripcion like ? ",descripcion);
        if (cursor.moveToFirst()) {
            //Loop through the table rows Requerimientonutrientes
            do {

               Log.v("consulta",cursor.getString(1) +"-"+ cursor.getString(11)+"-"+cursor.getString(12)+"-"+ cursor.getString(13));

                Requerimientos = new Requerimientonutrientes(cursor.getInt(0),cursor.getString(1) , cursor.getString(11),cursor.getString(12), cursor.getString(13));
                RequerimientoLista.add(Requerimientos);

            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();
        return RequerimientoLista;

    }

}
