package tr.com.erenkaya.mobileopticalscanner.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.entities.AnswerSheet;
import tr.com.erenkaya.mobileopticalscanner.lists.AnswerSheetAdapter;

public class AnswerSheetActivity extends AppCompatActivity {
    private AnswerSheetAdapter answerSheetAdapter;
    private ListView mListView;
    private TextView mMessage;
    private int questionNumber;
    private List<AnswerSheet> answerSheetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_sheet_template);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMessage = (TextView) findViewById(R.id.messageTV);
        try {
            getItems();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        if (answerSheetAdapter.getCount() == 0) {

            showMessage(getText(R.string.label_empty_list).toString());

        } else {

            hideMessage();
        }


    }

    private void getItems() {
        Bundle bundle = getIntent().getExtras();
        questionNumber = bundle.getInt("questionNumber");
        answerSheetList = new ArrayList<>();
        for (int i = 0; i < questionNumber; i++) {
            answerSheetList.add(new AnswerSheet(i, false, false, false, false, false));
        }
        mListView = (ListView) findViewById(R.id.listView);
        answerSheetAdapter = new AnswerSheetAdapter(this, answerSheetList);
        mListView.setAdapter(answerSheetAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.answersheet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {


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
