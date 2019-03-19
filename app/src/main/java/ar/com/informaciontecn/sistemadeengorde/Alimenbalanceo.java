package ar.com.informaciontecn.sistemadeengorde;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    private TextView texto, EnergianmanteKG[],EnergiaGananciaKG[],PMgtotales[];
    private EditText Alimentokg[] , peso[];
    private Double sumador,suma1=0.0,suma2=0.0,suma3=0.0;
    private Requerimientonutrientes current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimenbalanceo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        parentLinearLayout =  findViewById(R.id.parent_alimentos);
        texto =  findViewById(R.id.total);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Seleccion= extras.getParcelableArrayList("alimentos");

        Iterator<Requerimientonutrientes> it = Seleccion.iterator();

        cont=0;
        Alimentokg=new EditText[Seleccion.size()+1];
        peso=new EditText[Seleccion.size()+1];
        EnergianmanteKG=new TextView[Seleccion.size()+1];
        EnergiaGananciaKG=new TextView[Seleccion.size()+1];
        PMgtotales=new TextView[Seleccion.size()+1];

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);



        parentLinearLayout.addView(layout, parentLinearLayout.getChildCount()-2);
        TT(layout,150);

        while (it.hasNext()) {
            cont++;
            current = it.next();
            System.out.println(current.getFeed());
            FilaAlimentos(current,cont);
            System.out.println(cont);

        }


    }

    private void FilaAlimentos(final Requerimientonutrientes current, final int cont1){
        int ver=parentLinearLayout.getChildCount()-2; System.out.println("Total de layout  p: "+ver);

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.HORIZONTAL);


        System.out.println("Total de layout  2: "+ver);
        parentLinearLayout.addView(layout, parentLinearLayout.getChildCount()-2);
        System.out.println("despues de agregar : "+parentLinearLayout.getChildCount());

      /*  final TextView desc= new TextView(this);
        desc.setText((String.valueOf(current.getFeed())));
        desc.setId(current.getFeed());
        desc.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,5));
        layout.addView(desc);*/

        final TextView bu = new TextView(this);
        bu.setText(current.getDescripcion());
        bu.setId(current.getFeed());
        bu.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));
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


        peso[cont1]=new EditText(Alimenbalanceo.this);
        peso[cont1].setId(current.getFeed());
        peso[cont1].setContentDescription(String.format("%d",current.getFeed()));
        peso[cont1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        peso[cont1].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));

        peso[cont1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double kg=0.0,Enmgkg ,ENg,Engkg,PMg;
                    suma1=0.0;suma2=0.0;suma3=0.0;
                if(peso[cont1].getText().toString().isEmpty()){
                    kg=0.0;
                } else{
                    kg= Double.valueOf(peso[cont1].getText().toString());
                }
                Enmgkg= Double.valueOf(current.getNEm().replace(",","."))*kg;
                Engkg= Double.valueOf(current.getNEg().replace(",","."))*kg;
                PMg= Double.valueOf(current.getCP().replace(",","."))*kg;



                EnergianmanteKG[cont1].setText(String.format("%.2f",Enmgkg));

                EnergiaGananciaKG[cont1].setText(String.format("%.2f",Engkg));

                PMgtotales[cont1].setText(String.format("%.2f",PMg));

                for (int i = 1; i < peso.length; i++) {
                    if (!peso[i].getText().toString().isEmpty())
                        suma1 = suma1 + Double.valueOf(peso[i].getText().toString());

                    if (!EnergianmanteKG[i].getText().toString().isEmpty())
                        suma2 = suma2 + Double.valueOf(EnergianmanteKG[i].getText().toString().replace(",","."));

                    if (!PMgtotales[i].getText().toString().isEmpty())
                        suma2 = suma2 + Double.valueOf(PMgtotales[i].getText().toString().replace(",","."));

                    System.out.println(PMgtotales[i].getText().toString().replace(",","."));

                }
                texto.setText("Total = " + String.valueOf(suma1 + "-" + suma2 + "-" + suma3));


            }

        });
        layout.addView(peso[cont1]);

        ENMKG(layout ,cont1);
        EngKG(layout ,cont1);
        PMGRamos(layout ,cont1);

    }


    private void ENMKG( LinearLayout layout , int cont1){
        EnergianmanteKG[cont1]=new TextView(Alimenbalanceo.this);
        EnergianmanteKG[cont1].setId(cont1+100);
        EnergianmanteKG[cont1].setContentDescription(String.format("%d",cont1)+"_mas");
        EnergianmanteKG[cont1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        EnergianmanteKG[cont1].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));
        layout.addView( EnergianmanteKG[cont1]);
    }
    private void EngKG( LinearLayout layout , int cont1){
        EnergiaGananciaKG[cont1]=new TextView(Alimenbalanceo.this);
        EnergiaGananciaKG[cont1].setId(cont1+100);
        EnergiaGananciaKG[cont1].setContentDescription(String.format("%d",cont1)+"_mas");
        EnergiaGananciaKG[cont1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        EnergiaGananciaKG[cont1].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));
        layout.addView(EnergiaGananciaKG[cont1]);
    }
    private void PMGRamos( LinearLayout layout , int cont1){
        PMgtotales[cont1]=new TextView(Alimenbalanceo.this);
        PMgtotales[cont1].setId(cont1+100);
        PMgtotales[cont1].setContentDescription(String.format("%d",cont1)+"_mas");
        PMgtotales[cont1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        PMgtotales[cont1].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,10));
        layout.addView(PMgtotales[cont1]);
    }
    private void TT( LinearLayout layout , int cont1) {
        texto = new TextView(Alimenbalanceo.this);
        texto.setId(cont1 + 120);
        texto.setContentDescription(String.format("%d", cont1) + "_mas");


        layout.addView(texto);
    }
}
