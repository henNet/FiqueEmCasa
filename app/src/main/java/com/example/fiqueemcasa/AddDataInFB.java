package com.example.fiqueemcasa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataInFB extends AppCompatActivity {

    /* Para a conexao do banco */
    FirebaseDatabase database;
    DatabaseReference dataBaseCompanys, dataBaseServices;
    private static final String dataBaseCompanysName = "empresas";
    private static final String dataBaseServicesName = "servicos";
    private static final String typeServiceComida = "comida";
    private static final String typeServiceGas = "gas";

    /* Elementos gráficos */
    EditText name, adress;
    CheckBox comida, gas;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        /* Faz a conexao com o bando e pega uma referencia da base de dados criada */
        database = FirebaseDatabase.getInstance();
        dataBaseCompanys = database.getReference(dataBaseCompanysName);
        dataBaseServices = database.getReference(dataBaseServicesName);

        /* Pega a referência dos elementos da tela */
        name = (EditText) findViewById(R.id.editTextNome);
        adress = (EditText) findViewById(R.id.editTextEndereco);
        comida = (CheckBox) findViewById(R.id.checkBoxComida);
        gas = (CheckBox) findViewById(R.id.checkBoxGas);
        btnAdd = (Button)findViewById(R.id.buttonCadastrarForm);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Empresa emp = new Empresa();

                emp.setName(name.getText().toString());
                emp.setAdress(adress.getText().toString());
                if(comida.isChecked())
                    emp.setType(typeServiceComida);

                if(gas.isChecked())
                    emp.setType(typeServiceGas);

                addToDataBase(emp);
            }
        });
    }

    /* Adiniciona Empresas e seus servicos nas respectivas bases de dados */
    public void addToDataBase(final Empresa dados)
    {
        /* Dessa forma eh adicionado no banco uma chave primária */
        dataBaseCompanys.push().setValue(dados, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                //Problem with saving the data
                if (databaseError != null) {
                    toastMessage("ERRO ao cadastrar!");
                } else {
                    //Adiciona o ID e o nome da empresa no banco de servicos
                    for(String type : dados.getType()) {
                        dataBaseServices.child(type).child(databaseReference.getKey()).setValue(dados.getName());
                    }
                    toastMessage("Cadastrado com Sucesso!");
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
