package com.example.appwithdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ConnectDatabase db;
EditText txtname, txtsurname, txtphone, txtemail;
Button btnsubmit, btncancel, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new ConnectDatabase(this);

        txtname = findViewById(R.id.txtName);
        txtsurname = findViewById(R.id.txtSurname);
        txtphone = findViewById(R.id.txtPhone);
        txtemail = findViewById(R.id.txtEmail);
        btnsubmit = findViewById(R.id.btnSub);
        btncancel = findViewById(R.id.btnCancel);
        btnEdit = findViewById(R.id.btnEdit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, surname, phone, email;
                name = txtname.getText().toString();
                surname = txtsurname.getText().toString();
                phone = txtphone.getText().toString();
                email = txtemail.getText().toString();
                boolean insert = db.insertData(name, surname, phone , email);
                if(insert == true){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data can not insert", Toast.LENGTH_LONG).show();
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtname.setText("");
                txtsurname.setText("");
                txtphone.setText("");
                txtemail.setText("");
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                startActivity(intent);
            }
        });
    }
}