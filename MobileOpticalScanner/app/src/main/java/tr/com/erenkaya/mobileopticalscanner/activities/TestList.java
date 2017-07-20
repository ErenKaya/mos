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
import tr.com.erenkaya.mobileopticalscanner.databaserepository.TestDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorTest;
import tr.com.erenkaya.mobileopticalscanner.lists.TestListAdapter;

public class TestList extends AppCompatActivity {
    private List<MorTest> testList;
    private TestDatabaseAdapter morTestHelper;
    private ListView listView;
    private TextView mMessage;
    private TestListAdapter testListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMessage = (TextView) findViewById(R.id.messageTV);
        morTestHelper = new TestDatabaseAdapter(this);

        try {
            getItems();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        if (testListAdapter.getCount() == 0) {

            showMessage(getText(R.string.label_empty_list).toString());

        } else {

            hideMessage();
        }

    }

    private void getItems() {
        testList = morTestHelper.getAllData();
        listView = (ListView) findViewById(R.id.listView);
        testListAdapter = new TestListAdapter(this, testList);
        listView.setAdapter(testListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Intent showAddStudent = new Intent(getApplicationContext(), AddTestActivity.class);
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
