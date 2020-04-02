package com.example.fiqueemcasa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataInFB extends AppCompatActivity {

    /* Para a conexao do banco */
    FirebaseDatabase database;
    DatabaseReference myRef, dataType;
    private static final String dataBaseName = "empresas";
    private static final String dataTypeName = "servicos";

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
        myRef = database.getReference(dataBaseName);
        dataType = database.getReference(dataTypeName);

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
                emp.setType("comida");

                addToDataBase(emp);
            }
        });
    }

    public void addToDataBase(final Empresa dados)
    {
        /* Dessa forma eh adicionado no banco uma chave primária */
        myRef.push().setValue(dados, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                //Problem with saving the data
                if (databaseError != null) {
                    toastMessage("PUTS");
                } else {
                    //Data uploaded successfully on the server
                    toastMessage("DEU CEEEERRTTTOOO");
                    dataType.child(dados.getType()).child(databaseReference.getKey()).setValue(dados.getName());
                }
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
