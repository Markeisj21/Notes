package com.markeisjones.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String NOTE_POSITION    = "com.markeisjones.notes.NOTE_POSITION";
    private NoteInfo mNote;
    private boolean misNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinnerText = (Spinner) findViewById(R.id.spinner_main);

        List<CourseInfo> courseInfos = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseInfos);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerText.setAdapter(adapterCourses);

        readDisplayStateValues();

        EditText textNoteTitle = (EditText)findViewById(R.id.text_note_title);
        EditText textNoteText = (EditText) findViewById(R.id.text_note_text);

        if(!misNewNote){
            displayNote(spinnerText, textNoteTitle, textNoteText);
        }

    }

    private void displayNote(Spinner spinnerText, EditText textNoteTitle, EditText textNoteText) {

        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(mNote.getCourse());
        spinnerText.setSelection(courseIndex);

        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());




    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
       mNote = intent.getParcelableExtra(NOTE_POSITION);
        misNewNote = mNote == null;

    }
}
