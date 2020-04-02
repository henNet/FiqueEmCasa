package com.example.fiqueemcasa;

public class Empresa {

    private String name;
    private String adress;
    private String type;

    /* Obrigatorio ter um construtor padrão sem parametros */
    public Empresa(){}

    public Empresa(String name, String end, String type){
        this.name = name;
        this.adress = end;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setType(String type) {
        this.type = type;
    }
}
