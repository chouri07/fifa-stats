package com.example.casa.booksearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    TextView tv1;
    Button search, fav;
    EditText edtitle, author;


    private void mudarEcra(Class<?> subAtividade, String edtitle, String author) {

        Intent x = new Intent(this, subAtividade);
        x.putExtra("titulo", edtitle);
        x.putExtra("author", author);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search = (Button) findViewById(R.id.search);
        fav = (Button) findViewById(R.id.fav);
        tv1 = (TextView) findViewById(R.id.tv1);
        edtitle = (EditText) findViewById(R.id.edtitle);
        author = (EditText) findViewById(R.id.author);

        mydb = new DatabaseHelper(this);




        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                mudarEcra(proc.class, edtitle.getText().toString(), author.getText().toString());


        }
        });

        fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}
