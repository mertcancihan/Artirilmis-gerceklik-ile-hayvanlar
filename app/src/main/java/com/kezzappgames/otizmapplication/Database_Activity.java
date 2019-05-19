package com.kezzappgames.otizmapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Database_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_);

        ListView listView = (ListView) findViewById(R.id.listObject);
        Button ekleBtn = (Button) findViewById(R.id.addButton);
        Button deleteBtn = (Button) findViewById(R.id.deleteButton);
        Button araBtn = (Button) findViewById(R.id.searchButton);
        Button listBtn = (Button) findViewById(R.id.listButton);
        EditText nameTxt = (EditText) findViewById(R.id.adEditText);
        EditText surnameTxt = (EditText) findViewById(R.id.soyadEditTxt);
        EditText searchName = (EditText) findViewById(R.id.isimAraText);
        EditText delName = (EditText) findViewById(R.id.isimAraaText);


        ekleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameTxt.getText().toString();
                String surname = surnameTxt.getText().toString();

                if(name.isEmpty() || surname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Alanları Boş Geçemezsiniz!",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    kullanici k = new kullanici(name, surname);
                    sqlite_layer sqliteLayer = new sqlite_layer(getApplicationContext());
                    long id = sqliteLayer.ekleKullanici(k);

                    if (id > 0) {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarılı! ID = " + id, Toast.LENGTH_LONG).show();
                        nameTxt.setText("");
                        surnameTxt.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarısız!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isimSil = delName.getText().toString();
                sqlite_layer sqliteLayer = new sqlite_layer(getApplicationContext());
                sqliteLayer.silKullanici(isimSil);

            }
        });


        araBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isimAra = searchName.getText().toString();
                sqlite_layer sqliteLayer = new sqlite_layer(getApplicationContext());
                List<String> datas = sqliteLayer.VeriListele(isimAra);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Database_Activity.this,android.R.layout.simple_list_item_1,android.R.id.text1,datas);
                listView.setAdapter(adapter);

            }
        });
    }


}
