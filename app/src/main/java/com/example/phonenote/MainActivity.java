package com.example.phonenote;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.phonenote.category.DataBase;
import com.example.phonenote.category.Note;
import com.example.phonenote.category.NoteAdapter;
import com.example.phonenote.controller.NewNote;
import com.example.phonenote.controller.NoteEdit;
import com.example.phonenote.controller.SeeContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Note> NoteList = new ArrayList<>();
    private DataBase dbHelper;
    private String title1;
    private String markdown1;
    private int id1;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        NoteList.clear();
        ListView rw = (ListView) findViewById(R.id.note_menu_rw);
        CardView iv = (CardView) findViewById(R.id.add_note);
        dbHelper = new DataBase(this,"Note.db",null,1);
        dbHelper.getWritableDatabase();
        read();
        refresh(rw);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent1);
                finish();
            }
        });
        rw.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = NoteList.get(position);
                String title2 = note.getTitle();
                String markdown2 = note.getContent();
                int id2 = note.getId();
                Intent intent = new Intent(MainActivity.this, SeeContent.class);
                intent.putExtra("id",id2);
                intent.putExtra("title",title2);
                intent.putExtra("markdown",markdown2);
                startActivity(intent);
                finish();
            }
        });
    }

    protected void refresh(ListView rw){
        NoteAdapter adapter = new NoteAdapter(MainActivity.this,R.layout.note_item,NoteList);
        rw.setAdapter(adapter);
    }

    @SuppressLint("Range")
    protected void read(){
        int name = 1;
        dbHelper = new DataBase(this,"Note.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Note",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                id1 = cursor.getInt(cursor.getColumnIndex("id"));
                title1 = cursor.getString(cursor.getColumnIndex("title"));
                markdown1 = cursor.getString(cursor.getColumnIndex("markdown"));
                NoteList.add(new Note(title1, markdown1,id1));
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    public void onResume(){
        super.onResume();

    }

}