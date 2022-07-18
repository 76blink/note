package com.example.phonenote.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonenote.MainActivity;
import com.example.phonenote.R;
import com.example.phonenote.category.DataBase;
import com.example.phonenote.dao.ControlSQL;

public class SeeContent extends AppCompatActivity {

    private DataBase dbHelper;

    public int usedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_content);
        Intent intented = getIntent();
        usedId = intented.getIntExtra("id",0);
        dbHelper = new DataBase(this,"Note.db",null,1);
        CardView EDIT = (CardView) findViewById(R.id.edit1);
        CardView DELETE = (CardView) findViewById(R.id.delete1);
        CardView BACK = (CardView) findViewById(R.id.back1);
        TextView edit = (TextView) findViewById(R.id.markdown1);
        edit.setText(intented.getStringExtra("markdown"));
        TextView title1 = (TextView) findViewById(R.id.title1);
        title1.setText(intented.getStringExtra("title"));
        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id2;
                String title2=null,markdown2=null;
                id2 = usedId;
                title2 = intented.getStringExtra("title");
                markdown2 = intented.getStringExtra("markdown");
                Intent intent = new Intent(SeeContent.this, NoteEdit.class);
                intent.putExtra("id",id2);
                intent.putExtra("title",title2);
                intent.putExtra("markdown",markdown2);
                startActivity(intent);
                finish();
            }
        });
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlSQL.delete(dbHelper,usedId);
                Intent intent = new Intent(SeeContent.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeeContent.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(SeeContent.this,MainActivity.class);
        startActivity(intent);
    }
}
