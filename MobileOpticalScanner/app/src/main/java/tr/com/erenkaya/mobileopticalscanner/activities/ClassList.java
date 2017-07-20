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
import tr.com.erenkaya.mobileopticalscanner.databaserepository.ClassDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorClass;
import tr.com.erenkaya.mobileopticalscanner.lists.ClassListAdapter;

public class ClassList extends AppCompatActivity {
    private List<MorClass> classList;
    private ClassDatabaseAdapter morClassHelper;
    private ListView listView;
    private TextView mMessage;
    private ClassListAdapter classListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        morClassHelper = new ClassDatabaseAdapter(this);
        mMessage =(TextView)findViewById(R.id.messageTV);
        try{
            getItems();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        if (classListAdapter.getCount() == 0) {

            showMessage(getText(R.string.label_empty_list).toString());

        } else {

            hideMessage();
        }

    }
    private void getItems() {
        classList  = morClassHelper.getAllData();
        listView = (ListView) findViewById(R.id.listView);
        classListAdapter = new ClassListAdapter(this, classList);
        listView.setAdapter(classListAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.class_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add){
            Intent showAddSchool = new Intent(getApplicationContext(),AddClassActivity.class);
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
