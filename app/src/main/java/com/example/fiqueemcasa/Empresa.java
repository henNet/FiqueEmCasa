package com.example.fiqueemcasa;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String name;
    private String adress;
    private List<String> type;

    /* Obrigatorio ter um construtor padrão sem parametros */
    public Empresa(){
        type = new ArrayList<String>();
    }

    public Empresa(String name, String end, List<String> type){
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

    public List<String> getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setType(String type) {
        this.type.add(type);
    }
}
