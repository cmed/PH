package ma.pharmacie.ENTITIES;


import java.util.Date;

public class Pharmacien extends Personnel{
    private int idpharmacien;

    public int getIdpharmacien() {
        return idpharmacien;
    }

    public Pharmacien( ) {

    }

    public void setIdpharmacien(int idpharmacien) {
        this.idpharmacien = idpharmacien;
    }
}
