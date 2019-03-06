package ar.com.informaciontecn.sistemadeengorde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

public class Alimenbalanceo extends AppCompatActivity {
    private ArrayList<Requerimientonutrientes> Seleccion;
    private LinearLayout parentLinearLayout;
    private  int  cont;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimenbalanceo);

        parentLinearLayout =  findViewById(R.id.parent_alimentos);
         texto =  findViewById(R.id.total);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Seleccion= extras.getParcelableArrayList("alimentos");



        System.out.print("\n\n\n-------------------------------------------------------------------------------\n");
        System.out.print("balanceo");
        System.out.println("-------------------------------------------------------------------------------\n");
        System.out.println(Seleccion.size());
        System.out.println(Seleccion.get(0).getFeed());
        System.out.println("-------------------------------------------------------------------------------\n");
        System.out.println("enviado");

        Iterator<Requerimientonutrientes> it = Seleccion.iterator();


        while (it.hasNext()) {
            Requerimientonutrientes current = it.next();
            System.out.println(current.getDescripcion());
            FilaAlimentos(current, Seleccion.size());
        }

    }

    private void FilaAlimentos(Requerimientonutrientes current,int cont1){
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        parentLinearLayout.addView(layout, parentLinearLayout.getChildCount()-1);

        System.out.println(current.getDescripcion());
        final TextView desc= new TextView(this);
        desc.setText((String.valueOf(current.getFeed())));
        desc.setId(current.getFeed());
        desc.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
        layout.addView(desc);

        final TextView bu = new TextView(this);
        bu.setText(current.getDescripcion());
        bu.setId(current.getFeed());
        bu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,20));
        layout.addView(bu);

        final TextView bu1 = new TextView(this);
        bu1.setText(current.getNEm());
        bu1.setId(current.getFeed());
        bu1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
        layout.addView(bu1);

        final TextView bu2 = new TextView(this);
        bu2.setText(current.getNEg());
        bu2.setId(current.getFeed());
        bu2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
        layout.addView(bu2);

        final TextView bu3 = new TextView(this);
        bu3.setText(current.getCP());
        bu3.setId(current.getFeed());
        bu3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
        layout.addView(bu3);

        final EditText bu4 = new EditText(this);
        System.out.println(cont);
        bu4.setId(current.getFeed());
        bu4.setContentDescription(String.format("%d",current.getFeed()));
        bu4.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        bu4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));

        bu4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               /*if(!s.toString().isEmpty()) {
                   System.out.println(calculo(bu4.getText().toString()));
               }*/
                EditText et;
                et = new EditText(Alimenbalanceo.this);

                for (int i = 0; i <2; i++) {
                    String descripcion = (String) et.getContentDescription(); //Aqui se cual es por la desc que puse
                    String id=  String.format("%d",et.getId());
                    //Ahora saco su contenido y sabiendo que et es, tengo control total
                    String text = et.getText().toString();
                    texto.setText("Descripcion:" + descripcion +",  id : \"+id +\",  Texto :" +text +"\n");
                    System.out.print("Descripcion:" + descripcion +",  id : "+id +",  Texto : " +text +"\n");

                }


            }

        });
        layout.addView(bu4);



    }

}
