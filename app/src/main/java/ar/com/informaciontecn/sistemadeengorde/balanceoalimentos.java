package ar.com.informaciontecn.sistemadeengorde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

public class balanceoalimentos  extends AppCompatActivity {

    private ArrayList<Requerimientonutrientes> Seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanceo_alimentos);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Seleccion= extras.getParcelableArrayList("alimentos");


        System.out.print("\n\n\n-------------------------------------------------------------------------------\n");
        System.out.print("balanceo");
        System.out.print("-------------------------------------------------------------------------------\n");
        System.out.print(Seleccion);
        impresion();
    }

    private void impresion(){
        Iterator<? extends Requerimientonutrientes> it = Seleccion.iterator();
        System.out.print("enviado");
        while (it.hasNext()) {
            Requerimientonutrientes current = it.next();
            System.out.print(current.getDescripcion());

        }
    }
}
