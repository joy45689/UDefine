package com.example.udefine;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.udefine.Database.LayoutList;
import com.example.udefine.Database.NoteList;
import com.example.udefine.Database.Notes;
import com.example.udefine.Database.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewNote extends AppCompatActivity {
    private widgetManager widgetsManager;
    private ViewModel mViewModel;
    private LayoutList mLayoutList;
    private NoteList mNoteList;
    private Notes mNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create ViewModel instance
        //mViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        // TODO: grab layout component ID from DB
        /*
         *  widget type:
         *  1. Title + editText
         *  2. Title + Date/Time Picker
         *  3. Title + Tag
         *  4. Title + PlainText
         */
        // component_list should be a layout class with title name
        int component_list[] = {1, 2, 3, 4, 2, 3};
        String component_title[] = {"?", "??", "???", "", "????", "?????"};
        LinearLayout parentLinear = findViewById(R.id.newNoteLayout);
        widgetsManager = new widgetManager(this, parentLinear,
                                                         getSupportFragmentManager());
        widgetsManager.generate(component_list, component_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(NewNote.this, LayoutSelection.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public void saveNote(View view) {
        ArrayList<Notes> n = widgetsManager.getLayoutValue(0);
        // TODO: Store to DB
        // 給 notelist  值 => note table
        //mNoteList = new NoteList("Title", "08/06/2019,14:30", null, 1);
        // insert note list
        //mViewModel.insertNoteList(mNoteList);
        // get last note id
        //int id = mViewModel.getLastNoteID();
        //  TODO: 一個欄位 insert 一次 => ArrayList<Notes>
        //mNotes = new Notes(id, "title", "title");


        finish();
    }

    public void cancelNote(View view) {
        finish();
    }
}
