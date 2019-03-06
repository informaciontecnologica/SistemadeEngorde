package ar.com.informaciontecn.sistemadeengorde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class inicio extends AppCompatActivity {
    private Spinner sexo1, raza2;
    public double Energia_base, Peso_Metbolico, pastoreo, requerimiento ,ENm,indiceRaza,CefRaza , Cefsexo , Cefpastoreo , Cefstress ,pm2,Eng,General,  Novillo_frame_medio,Novillo_Frame_Grande,
    Hembra_Frame_Grande,  Hembra_Frame_Mediano ,GDP,ReqdiaMante,ReqdiaGana,RequerTotalPM,conversionalimento,consumoestimado;
    private  int peso ,stress_termico;
    private EditText et,et1,et2,GD;
    private TextView DetalleConsumo;
    private  Button Balanceo;
    private Spinner marco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

     final Button Calcular = findViewById(R.id.Calcular);

        et =  findViewById(R.id.peso);
        et1 =  findViewById(R.id.pastoreo);
        et2 =  findViewById(R.id.stress);
        GD =  findViewById(R.id.GDP);

        marco= findViewById(R.id.frame);
        DetalleConsumo=findViewById(R.id.ConsumoEstimado);
        Balanceo=findViewById(R.id.Balanceo);

        Calcular.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
              if(!TextUtils.isEmpty(et.getText())) {
                  calculo();

              } else {
                  Toast.makeText(inicio.this, "No puede ser vacio ", Toast.LENGTH_SHORT).show();
              }
            }

        });
        Balanceo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(inicio.this, item_listPersonas.class);
                startActivity(intent);


            }
        });
    }

     public void calculo () {

        DetalleConsumo.setText("");


         peso  = Integer.parseInt(et.getText().toString());
         pastoreo  = Float.parseFloat(et1.getText().toString());
         stress_termico  = Integer.parseInt(et2.getText().toString());
         GDP  = Double.parseDouble(GD.getText().toString());
         sexo1 =  findViewById(R.id.sexo);
         raza2 =  findViewById(R.id.raza);

         Peso_Metbolico= (float) Math.pow(peso, 0.75);
         Energia_base =Peso_Metbolico * (float) 0.077;
        switch (String.valueOf(raza2.getSelectedItem())){
            case "Bos taurus":
                indiceRaza=0.10;
                break;
            case "Cruza":
                indiceRaza=-0.05;
                break;
            case "Bos indicus":
                indiceRaza=-0.10;
             break;
        }
         CefRaza= (float) (Energia_base * indiceRaza);
         Cefsexo =(float) (Energia_base * 0);


        Cefpastoreo =(float) (0.6*peso*pastoreo)/1000000;
        Cefstress = (float) (0.0007*(20-stress_termico))*-1;

        ENm =  Energia_base + CefRaza + Cefsexo + Cefpastoreo +Cefstress ;


        pm2 = (float) Math.pow(GDP ,0.75);

        General = ((52.72*GDP)+(6.84*Math.pow(GDP,2)))*0.001*Peso_Metbolico;


         Novillo_frame_medio= 0.0557*Peso_Metbolico*Math.pow(GDP ,1.097);
         Novillo_Frame_Grande= 0.0493*Peso_Metbolico*Math.pow(GDP ,1.097);
         Hembra_Frame_Mediano = 0.0686*Peso_Metbolico*Math.pow(GDP ,1.119);
         Hembra_Frame_Grande = 0.0608*Peso_Metbolico*Math.pow(GDP ,1.119);
         ReqdiaMante = 3.8*Peso_Metbolico;
         ReqdiaGana=  1.695*GDP*(168-0.17*peso+0.00016*Math.pow(peso,2))*(1.22-0.12*GDP);
         Double frame =0.0;
         switch (marco.getSelectedItemPosition()){
             case 0:
                 frame= General;
                 break;
             case 1:
                 frame= Novillo_frame_medio;
                 break;
             case 2:
                 frame= Novillo_Frame_Grande;
                 break;
             case 3:
                 frame= Hembra_Frame_Mediano;
                 break;
             case 4:
                 frame= Hembra_Frame_Grande;
                 break;
         }


         RequerTotalPM= ReqdiaMante+ReqdiaGana;

        consumoestimado= 4.54+0.125*peso; //no usaremos esta formula .. se calculara con la concentración energetia de alimentos .

         Double CE =4.54+0.0125*peso;

      DetalleConsumo.setText( "\nTipo de Frame : "+marco.getSelectedItem()+"\nENm (MgCal/día) : "
              +String.format("%.2f",ENm)+"\nENg (MgCal/día): "+String.format("%.2f",frame)+
              "\nRequerimiento  Total Proteina Metabolica (gr./día) : "+String.format("%.2f",RequerTotalPM)
      );

        Balanceo.setFocusable(true);
        Balanceo.requestFocus();
        Balanceo.setVisibility(View.VISIBLE);

      /*  EditText Texto =  findViewById(R.id.Texto);
         Texto.setText("ENm  (MKal/día): "+ String.valueOf(requerimiento) );

         Toast.makeText(inicio.this,
                 "OnClickListener : " +
                         "\nrequerimiento  : "+ String.valueOf(requerimiento) +
                         "\nSpinner 2 : "+ String.valueOf(raza2.getSelectedItem()),
                 Toast.LENGTH_SHORT).show();*/

     }




}
