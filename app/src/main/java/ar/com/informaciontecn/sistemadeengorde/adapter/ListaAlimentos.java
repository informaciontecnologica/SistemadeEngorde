package ar.com.informaciontecn.sistemadeengorde.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ar.com.informaciontecn.sistemadeengorde.R;
import ar.com.informaciontecn.sistemadeengorde.model.Requerimientonutrientes;

public class ListaAlimentos extends BaseAdapter {

    private int i=0;
    private Context mContext;
    private List<Requerimientonutrientes> mRequerimientoList;

    public ListaAlimentos(Context mContext, List<Requerimientonutrientes> mRequerimientoList) {
        this.mContext = mContext;
        this.mRequerimientoList = mRequerimientoList;
        i=0;

    }

    @Override
    public int getCount() {
        return mRequerimientoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRequerimientoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRequerimientoList.get(position).getFeed();
    }

   /* public List<ListaAlimentos> getDescripcion(int position) {
        List<ListaAlimentos> registro = new ArrayList<>();
        registro =new Requerimientonutrientes(mRequerimientoList.get(position).getDescripcion();
        return mRequerimientoList.get(position);
    }
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.contenidoalimentos, null);
        TextView feed = v.findViewById(R.id.feed);
        TextView descripcion = v.findViewById(R.id.descripcion);
        TextView NEm = v.findViewById(R.id.NEm);
        TextView NEg = v.findViewById(R.id.NEg);
        TextView CP = v.findViewById(R.id.CP);
//        CheckBox Se=v.findViewById(R.id.seleccionA);

        feed.setText(Integer.toString(mRequerimientoList.get(position).getFeed()));
        descripcion.setText(mRequerimientoList.get(position).getDescripcion());
        NEm.setText(mRequerimientoList.get(position).getNEm());
        NEg.setText(mRequerimientoList.get(position).getNEg());
        CP.setText(mRequerimientoList.get(position).getCP());
       /* CheckBox cb =new CheckBox(mContext.getApplicationContext());
        cb.setText("Ch_"+mRequerimientoList.get(position).getFeed());
        cb.setId(mRequerimientoList.get(position).getFeed());*/

        return v;
    }

}
