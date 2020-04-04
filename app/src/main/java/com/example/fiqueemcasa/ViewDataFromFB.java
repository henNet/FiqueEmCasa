package com.example.fiqueemcasa;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;

public class ViewDataFromFB extends AppCompatActivity {

    /* Para a conexao do banco */
    FirebaseDatabase database;
    DatabaseReference dataBaseCompanys, dataBaseServices;
    private static final String dataBaseCompanysName = "empresas";
    private static final String dataBaseServicesName = "servicos";
    private static final String typeServiceComida = "comida";
    private static final String typeServiceGas = "gas";

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar);

        /* Faz a conexao com o bando e pega uma referencia da base de dados criada */
        database = FirebaseDatabase.getInstance();
        dataBaseCompanys = database.getReference(dataBaseCompanysName);
        //dataBaseServices = database.getReference(dataBaseServicesName);

        // Read from the database
        dataBaseCompanys.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }

    private void showData(DataSnapshot dataSnapshot)
    {
        // Initializing list view with the custom adapter
        ArrayList<Item> itemList = new ArrayList<Item>();

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(R.layout.list, itemList);
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemArrayAdapter);

        for(DataSnapshot ds : dataSnapshot.getChildren()){

            //Empresa emp = new Empresa();

            //emp.setName(ds.getValue(Empresa.class).getName());
            //emp.setAdress(ds.getValue(Empresa.class).getAdress());

            itemList.add(new Item(ds.getValue(Empresa.class).getName()));
            //Toast.makeText(this, ds.getValue(Empresa.class).getName(), Toast.LENGTH_LONG).show();
        }
    }
}
