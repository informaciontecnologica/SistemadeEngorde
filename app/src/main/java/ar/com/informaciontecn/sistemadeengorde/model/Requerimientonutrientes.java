package ar.com.informaciontecn.sistemadeengorde.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Requerimientonutrientes implements Parcelable {
    private int feed;
    private String descripcion;
    private String Int , Ref, Conc,Forage, DM, NDF, Lignin, eNDF, TDN, ME, NEm, NEg, CP, SIP, solCP, NDFIP,ADFIP,Starch,Fat,Ash,A, B1,B2,B1a,B2a;
    private boolean seleccion;
    public Requerimientonutrientes(int feed, String descripcion, String NEm, String NEg, String CP) {
        this.feed = feed;
        this.descripcion = descripcion;
        this.NEm = NEm;
        this.NEg = NEg;
        this.CP = CP;
       this.seleccion=false;
    }

    protected Requerimientonutrientes(Parcel in) {
        feed = in.readInt();
        descripcion = in.readString();
        Int = in.readString();
        Ref = in.readString();
        Conc = in.readString();
        Forage = in.readString();
        DM = in.readString();
        NDF = in.readString();
        Lignin = in.readString();
        eNDF = in.readString();
        TDN = in.readString();
        ME = in.readString();
        NEm = in.readString();
        NEg = in.readString();
        CP = in.readString();
        SIP = in.readString();
        solCP = in.readString();
        NDFIP = in.readString();
        ADFIP = in.readString();
        Starch = in.readString();
        Fat = in.readString();
        Ash = in.readString();
        A = in.readString();
        B1 = in.readString();
        B2 = in.readString();
        B1a = in.readString();
        B2a = in.readString();
        seleccion = in.readByte() != 0;
    }

    public static final Creator<Requerimientonutrientes> CREATOR = new Creator<Requerimientonutrientes>() {
        @Override
        public Requerimientonutrientes createFromParcel(Parcel in) {
            return new Requerimientonutrientes(in);
        }

        @Override
        public Requerimientonutrientes[] newArray(int size) {
            return new Requerimientonutrientes[size];
        }
    };

    public int getFeed() {
        return feed;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNEm() {
        return NEm;
    }

    public void setNEm(String NEm) {
        this.NEm = NEm;
    }

    public String getNEg() {
        return NEg;
    }

    public void setNEg(String NEg) {
        this.NEg = NEg;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(feed);
        dest.writeString(descripcion);
        dest.writeString(Int);
        dest.writeString(Ref);
        dest.writeString(Conc);
        dest.writeString(Forage);
        dest.writeString(DM);
        dest.writeString(NDF);
        dest.writeString(Lignin);
        dest.writeString(eNDF);
        dest.writeString(TDN);
        dest.writeString(ME);
        dest.writeString(NEm);
        dest.writeString(NEg);
        dest.writeString(CP);
        dest.writeString(SIP);
        dest.writeString(solCP);
        dest.writeString(NDFIP);
        dest.writeString(ADFIP);
        dest.writeString(Starch);
        dest.writeString(Fat);
        dest.writeString(Ash);
        dest.writeString(A);
        dest.writeString(B1);
        dest.writeString(B2);
        dest.writeString(B1a);
        dest.writeString(B2a);
        dest.writeByte((byte) (seleccion ? 1 : 0));
    }
}


