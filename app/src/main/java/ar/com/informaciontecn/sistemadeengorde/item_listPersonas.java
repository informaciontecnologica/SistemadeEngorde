package ar.com.informaciontecn.sistemadeengorde;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import ar.com.informaciontecn.sistemadeengorde.adapter.ListPersonaAdapter;
import ar.com.informaciontecn.sistemadeengorde.database.DatabaseHelper;

import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

public class item_listPersonas extends AppCompatActivity {
    private ListView Vpersonas;
   private ListPersonaAdapter adaptader;
    private List<Requerimientonutrientes> mRequerimientoList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_personas);

        Vpersonas = findViewById(R.id.lista_persona);

        mDBHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        Log.v("MainActividad", database.getParent());

        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            Log.v("MainActividad", "db exite");
            if (copyDatabase(this)) {

                Toast.makeText(this, "copia database sucedio", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "copia database error", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        mRequerimientoList = mDBHelper.getListaRequerimiento();
        adaptader = new ListPersonaAdapter(this,mRequerimientoList);
       Vpersonas.setAdapter(adaptader);

    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);

            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActividad", "db copiada");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
