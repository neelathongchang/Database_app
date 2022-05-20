package com.example.appwithdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditData extends AppCompatActivity {
    EditText textId, textName, textSurname, textPhone, textEmail;
    ConnectDatabase db;
    Button submit, cancel, delete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        db = new ConnectDatabase(this);
        textId = findViewById(R.id.txtEId);
        textName = findViewById(R.id.txtEName);
        textSurname = findViewById(R.id.txtESurname);
        textPhone = findViewById(R.id.txtEPhone);
        textEmail = findViewById(R.id.txtEEmail);
        submit = findViewById(R.id.btnUpdate);
        cancel = findViewById(R.id.btnECancel);
        delete = findViewById(R.id.btnDelete);

        submit.setOnClickListener(view -> {
            String id, name, surname, phone, email;
            id = textId.getText().toString();
            name = textName.getText().toString();
            surname = textSurname.getText().toString();
            phone = textPhone.getText().toString();
            email = textEmail.getText().toString();
            boolean update = db.updateData(id, name, surname, phone, email);
            if(update){
                Toast.makeText(getApplicationContext(), "Data has been updated", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Data has not been updated", Toast.LENGTH_LONG).show();
            }
        });
        delete.setOnClickListener(view -> {
            String id;
            id = textId.getText().toString();
            boolean delete = db.deleteData(id);
            if(delete){
                Toast.makeText(getApplicationContext(), "Data has been deleted", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Data can not delete", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(view -> clearData());
    }
    private void clearData(){
        textId.setText("");
        textName.setText("");
        textSurname.setText("");
        textPhone.setText("");
        textEmail.setText("");
    }
}
