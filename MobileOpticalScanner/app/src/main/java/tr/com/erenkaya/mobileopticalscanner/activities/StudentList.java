package tr.com.erenkaya.mobileopticalscanner.activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.StudentDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorStudent;
import tr.com.erenkaya.mobileopticalscanner.lists.StudentListAdapter;

public class StudentList extends AppCompatActivity {
    private List<MorStudent> studentList;
    private StudentDatabaseAdapter morStudentHelper;
    private ListView listView;
    private TextView mMessage;
    private StudentListAdapter studentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        morStudentHelper = new StudentDatabaseAdapter(this);
        mMessage = (TextView) findViewById(R.id.messageTV);
        try {
            getItems();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        if (studentListAdapter.getCount() == 0) {

            showMessage(getText(R.string.label_empty_list).toString());

        } else {

            hideMessage();
        }

    }

    private void getItems() {
        studentList = morStudentHelper.getAllData();
        listView = (ListView) findViewById(R.id.listView);
        studentListAdapter = new StudentListAdapter(this, studentList);
        listView.setAdapter(studentListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Intent showAddStudent = new Intent(getApplicationContext(), AddStudentActivity.class);
            startActivity(showAddStudent);
        } else if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String message) {

        mMessage.setText(message);
        mMessage.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {

        mMessage.setVisibility(View.GONE);
    }
}
