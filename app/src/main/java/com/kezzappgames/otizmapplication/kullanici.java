package com.kezzappgames.otizmapplication;

public class kullanici {

    String isim,soyisim;
    int Id;

    public kullanici()
    {

    }

    public String getSoyisim() {
        return soyisim;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }


    public kullanici(String isim, String soyisim) {
        this.isim = isim;
        this.soyisim = soyisim;
    }
    public String toString(){
        return ""+Id + " - " +isim+ " - " +soyisim;
    }
}
