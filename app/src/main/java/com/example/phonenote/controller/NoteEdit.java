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
import android.widget.Toast;

import com.example.phonenote.MainActivity;
import com.example.phonenote.R;
import com.example.phonenote.category.DataBase;
import com.example.phonenote.dao.ControlSQL;

public class NoteEdit extends AppCompatActivity {

    private DataBase dbHelper;

    public int usedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_content);
        Intent intented = getIntent();
        usedId = intented.getIntExtra("id",0);
        Log.d("NoteEdit", String.valueOf(usedId));
        dbHelper = new DataBase(this,"Note.db",null,1);
        CardView OK = (CardView) findViewById(R.id.edit2);
        CardView DELETE = (CardView) findViewById(R.id.delete2);
        CardView BACK = (CardView) findViewById(R.id.back2);
        EditText edit = (EditText) findViewById(R.id.markdown2);
        edit.setText(intented.getStringExtra("markdown"));
        EditText title1 = (EditText) findViewById(R.id.title1);
        title1.setText(intented.getStringExtra("title"));
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len1 = edit.length();
                int len2 = title1.length();
                if(len1 == 0 || len2 == 0) {
                    Toast.makeText(getApplicationContext(), "未输入有效内容", Toast.LENGTH_LONG).show();
                }
                else {
                    String title = title1.getText().toString();
                    String markdown = edit.getText().toString();
                    ControlSQL.store(dbHelper,title,markdown,usedId);
                    Toast.makeText(getApplicationContext(), "存储完成！！！", Toast.LENGTH_LONG).show();
                    Log.d("NoteEdit","done");
                    Intent intent = new Intent(NoteEdit.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlSQL.delete(dbHelper,usedId);
                Log.d("NoteEdit","done");
                Intent intent = new Intent(NoteEdit.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteEdit.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(NoteEdit.this,MainActivity.class);
        startActivity(intent);
    }
}