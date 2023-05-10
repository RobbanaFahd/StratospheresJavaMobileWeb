
package com.mycompany.entity;

/**
 *
 * @author Fahd
 */
public class Banquedesang {
    private int id ;
    private String name ;
    private String addresse ;
    private int tel ;
    private Float longitude ;
    private Float latitude ;

    public Banquedesang() {
    }

    public Banquedesang(int id, String name, String addresse, int tel, Float longitude, Float latitude) {
        this.id = id;
        this.name = name;
        this.addresse = addresse;
        this.tel = tel;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Banquedesang{" + "id=" + id + ", name=" + name + ", addresse=" + addresse + ", tel=" + tel + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }


    
    
    
}
