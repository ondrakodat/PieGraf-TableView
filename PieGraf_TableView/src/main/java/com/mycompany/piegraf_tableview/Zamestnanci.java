/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piegraf_tableview;

/**
 *
 * @author user
 */
public class Zamestnanci {
    private String jmeno;
     private String prijmeni;
      private int plat;

    public Zamestnanci(String jmeno, String prijmeni, int plat) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.plat = plat;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int getPlat() {
        return plat;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }
      
      
}

