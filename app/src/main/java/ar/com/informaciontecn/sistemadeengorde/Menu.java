package ar.com.informaciontecn.sistemadeengorde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.com.informaciontecn.sistemadeengorde.database.DatabaseHelper;

public class Menu extends AppCompatActivity {
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final Button buttonI = findViewById(R.id.Inicio);
        final Button config = findViewById(R.id.Configuracion);
        final Button envia1 = findViewById(R.id.Envio);
        final Button salir = findViewById(R.id.Salir);

        final Button Seleccion= findViewById(R.id.Seleccion);
        TextView tete = findViewById(R.id.textView4);

        buttonI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(Menu.this, inicio.class);
                startActivity(intent);


            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(Menu.this, item_listPersonas.class);
                startActivity(intent);


            }
        });
        envia1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(Menu.this, inicio.class);
                startActivity(intent);


            }
        });
        Seleccion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(Menu.this, Seleccion_alimentos.class);
                startActivity(intent);


            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                finish();


            }
        });
    }


}