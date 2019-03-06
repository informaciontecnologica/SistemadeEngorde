package ar.com.informaciontecn.sistemadeengorde;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ar.com.informaciontecn.sistemadeengorde.adapter.ListaAlimentos;
import ar.com.informaciontecn.sistemadeengorde.database.DatabaseHelper;
import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.String.format;

public class Seleccion_alimentos extends AppCompatActivity {
    private ListaAlimentos adaptader;
    private List<Requerimientonutrientes> mRequerimientoList,Seleccion;
    private DatabaseHelper mDBHelper;
    private Spinner spinner;
    private SQLiteDatabase mDatabase = null;
    private EditText cons;
    private ListView ListaView;
    private  TextView txtResultado;
    private CheckBox Sele;

    private LinearLayout parentLinearLayout;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_alimentos);

        parentLinearLayout =  findViewById(R.id.parent_linear_layout);

        mDBHelper = new DatabaseHelper(this);
        Button paginaSelealimentos = findViewById(R.id.SelecAlimentos);
        Seleccion = new ArrayList<Requerimientonutrientes>();
         cons= findViewById(R.id.txtReg);
         ListaView = findViewById(R.id.ListaAlimentos);

         ListaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                if (Seleccion.isEmpty()) {
                    Seleccion.add(new Requerimientonutrientes(mRequerimientoList.get(position).getFeed(), mRequerimientoList.get(position).getDescripcion(), mRequerimientoList.get(position).getNEm(), mRequerimientoList.get(position).getNEg(), mRequerimientoList.get(position).getCP()));
                    onAddField(arg1, mRequerimientoList.get(position).getFeed(), mRequerimientoList.get(position).getDescripcion(), mRequerimientoList.get(position).getNEm(), mRequerimientoList.get(position).getNEg(), mRequerimientoList.get(position).getCP());
                    String item = format("%s", mRequerimientoList.get(position).getDescripcion() + "\n" + adaptader.getItemId(position));
                    Log.v("consulta : ", item);
                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                }

                if (!Buscar(mRequerimientoList.get(position).getFeed())) {

                        Seleccion.add(new Requerimientonutrientes(mRequerimientoList.get(position).getFeed(), mRequerimientoList.get(position).getDescripcion(), mRequerimientoList.get(position).getNEm(), mRequerimientoList.get(position).getNEg(), mRequerimientoList.get(position).getCP()));
                        onAddField(arg1, mRequerimientoList.get(position).getFeed(), mRequerimientoList.get(position).getDescripcion(), mRequerimientoList.get(position).getNEm(), mRequerimientoList.get(position).getNEg(), mRequerimientoList.get(position).getCP());
                        String item = format("%s", mRequerimientoList.get(position).getDescripcion() + "\n" + adaptader.getItemId(position));
                        Log.v("consulta : ", item);
                        Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getBaseContext(), "ya esta", Toast.LENGTH_LONG).show();
                    }

                  /*  System.out.println("qqq");
                    for (int i = 0; i < Seleccion.size(); i++) {
                        System.out.println("Index: " + i + " - Item : " + Seleccion.get(i).getFeed());
                    }*/


            }
         });



        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        Log.v("MainActividad", database.getParent());

        if (false != database.exists()) {
            mDBHelper.getReadableDatabase();
            Log.v("MainActividad", "db exite");
            if (copyDatabase(this)) {

                Toast.makeText(this, "copia database sucedio", LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "copia database error", LENGTH_SHORT).show();

            }

        }


        cons.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String[] selectionArgs = new String[]{cons.getText().toString()+"%"};
                mRequerimientoList= mDBHelper.getListaConsulta(selectionArgs);
                seduction();

            }

        });

        paginaSelealimentos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
          Intent intent = new Intent(Seleccion_alimentos.this,Alimenbalanceo.class);
          intent.putParcelableArrayListExtra("alimentos", (ArrayList<? extends Parcelable>) Seleccion);
          startActivity(intent);
            }
        });

    }

    private  boolean Buscar(int feed){
        Iterator<Requerimientonutrientes> it = Seleccion.iterator();
        while (it.hasNext()) {
            Requerimientonutrientes current = it.next();
            if (current.getFeed()==feed) {
                return true;
            }

        }
        return false;

    }
    private boolean Remover(int feed){
        Iterator<Requerimientonutrientes> it = Seleccion.iterator();
        while (it.hasNext()) {
            Requerimientonutrientes current = it.next();
            if (current.getFeed()==feed) {
                it.remove();
                return true;
            }

        }
        return false;
    }

    public void onAddField(View v,int feed ,String descripcion, String NEm, String NEg, String CP) {

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        parentLinearLayout.addView(layout, parentLinearLayout.getChildCount() - 1);


        final TextView desc= new TextView(this);
        desc.setText(descripcion);
        desc.setId(feed);
        desc.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,90));
        layout.addView(desc);

        final Button  bu = new Button(this);
        bu.setText("X");
        bu.setId(feed);
        bu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));
        bu.setOnClickListener(new View.OnClickListener() {
        @SuppressLint("DefaultLocale")
        public void onClick(View v) {
        // TODO Auto-generated method stub
            Remover(bu.getId());
            System.out.println("delte");
            for (int i = 0; i < Seleccion.size(); i++) {
                System.out.println("Index: " + i + " - Item : " + Seleccion.get(i).getFeed());
            }
            parentLinearLayout.removeView((View) v.getParent());
            Toast.makeText(getBaseContext(),String.format("%d",bu.getId()), LENGTH_SHORT).show();
             }
        });
        layout.addView(bu);

    }
    public void onDelete(View v) {

        int w= ((View) v.getParent().getParent()).getId();

        Toast.makeText(getBaseContext(),String.format("%d", w), Toast.LENGTH_LONG).show();

//      parentLinearLayout.removeView((View) v.getParent());
    }

    private void seduction(){
    adaptader = new ListaAlimentos(this ,mRequerimientoList);

    ListaView.setAdapter(adaptader);
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
