package tr.com.erenkaya.mobileopticalscanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.SchoolDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorSchool;
import tr.com.erenkaya.mobileopticalscanner.lists.SchoolListAdapter;

public class SchoolList extends AppCompatActivity {
    private List<MorSchool> schoolList;
    private SchoolDatabaseAdapter morSchoolHelper;
    private ListView schoolListView;
    private TextView mMessage;
    private SchoolListAdapter schoolListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoollist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        morSchoolHelper = new SchoolDatabaseAdapter(this);
        mMessage =(TextView)findViewById(R.id.messageTV);
        try{
            getItems();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        if (schoolListAdapter.getCount() == 0) {

            showMessage(getText(R.string.label_empty_list).toString());

        } else {

            hideMessage();
        }


    }

    private void getItems() {
        schoolList = morSchoolHelper.getAllData();
        schoolListView = (ListView) findViewById(R.id.listView);
        schoolListAdapter = new SchoolListAdapter(this, schoolList);
        schoolListView.setAdapter(schoolListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.school_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add){
            Intent showAddSchool = new Intent(getApplicationContext(),AddSchoolActivity.class);
            startActivity(showAddSchool);
        }
        else if(id==android.R.id.home){
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
