package com.example.udefine;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.udefine.Database.Notes;
import com.example.udefine.Database.ViewModel;

public class EditNote extends AppCompatActivity {
    // from EditNote intent
    public static final String EDIT_NOTE_ID =
            "com.example.android.udefine.extra.EDITNOTEID";
    private widgetManager widgetsManager;
    private ViewModel mViewModel;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        noteId = intent.getIntExtra(EDIT_NOTE_ID, 0);
        Log.d("editnote", Integer.toString(noteId));
        // TODO: grab layout component ID and title, content value from DB
        /*
         *  widget type:
         *  1. Title + editText
         *  2. Title + Date/Time Picker
         *  3. Title + Tag
         *  4. Title + PlainText
         */
        // component_list should be a layout class with title name
        int component_list[] = {1, 2, 3};
        String component_title[] = {"test1", "ohohoh", "???"};
        LinearLayout parentLinear = findViewById(R.id.editNoteLayout);
        widgetsManager = new widgetManager(this, parentLinear,
                getSupportFragmentManager());
        widgetsManager.generate(component_list, component_title);


        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        Notes[] notes = mViewModel.getNotesFromNoteID(noteId);

        //TODO: 把取得的notes值,塞到layout裡面
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(EditNote.this, LayoutSelection.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    public void saveNote(View view) {
        //widgetsManager.getNoteContent();
        // TODO: update to DB
        //取得更新後的NoteList
//        mViewModel.updateNoteList();
        //取得更新後的ArrayList<Notes>
//        mViewModel.updateNote();
        finish();
    }

    public void cancelNote(View view) {
        finish();
    }

    public void deleteNote(View view) {
        // TODO: delete from DB
        mViewModel.deleteNote(noteId);
        finish();
    }
}
