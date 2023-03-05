package com.example.lasttp;

public class Personne {
    public String Nom;
    public int id;
    public Personne(String n){
        this.Nom=n;
    }

    public String Afficher() {
        return Nom;
    }
}
