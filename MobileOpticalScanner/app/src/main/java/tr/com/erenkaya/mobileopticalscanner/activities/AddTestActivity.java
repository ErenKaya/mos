package tr.com.erenkaya.mobileopticalscanner.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.utils.Message;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.ClassDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.SchoolDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.TestDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorTest;


/**
 * Created by Eren on 12.3.2017.
 */

public class AddTestActivity extends AppCompatActivity {
    EditText testQuestionTitleETV, testQuestionCountETV, testQuestionPointETV;
    Spinner testFormSpinner, testBookletSpinner, testNetCalculatorSpinner, testSchoolSpinner, testClassSpinner;
    Button testSaveButton, testSaveAndGoButton;
    SchoolDatabaseAdapter morSchoolHelper;
    ClassDatabaseAdapter morClassHelper;
    TestDatabaseAdapter morTestHelper;
    List<String> classNames, schoolNames;
    MorTest morTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        testQuestionTitleETV = (EditText) findViewById(R.id.testQuestionTitleETV);
        testQuestionCountETV = (EditText) findViewById(R.id.testQuestionCountETV);
        testQuestionPointETV = (EditText) findViewById(R.id.testQuestionPointETV);
        testFormSpinner = (Spinner) findViewById(R.id.testFormSpinner);
        testBookletSpinner = (Spinner) findViewById(R.id.testBookletSpinner);
        testNetCalculatorSpinner = (Spinner) findViewById(R.id.testNetCalculatorSpinner);
        testSchoolSpinner = (Spinner) findViewById(R.id.testSchoolSpinner);
        testClassSpinner = (Spinner) findViewById(R.id.testClassSpinner);
        testSaveButton = (Button) findViewById(R.id.testSaveButton);
        testSaveAndGoButton = (Button) findViewById(R.id.testSaveAndGoButton);
        morSchoolHelper = new SchoolDatabaseAdapter(this);
        morClassHelper = new ClassDatabaseAdapter(this);
        morTestHelper = new TestDatabaseAdapter(this);
        morTest = new MorTest();
        classNames = new ArrayList<>();
        schoolNames = new ArrayList<>();
        try {
            schoolNames = morSchoolHelper.getSchoolNames();
            classNames = morClassHelper.getClassNames();
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.message(this, e.toString());
        }
        ArrayAdapter<String> schoolSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolNames);
        ArrayAdapter<String> classSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames);
        testSchoolSpinner.setAdapter(schoolSpinnerAdapter);
        testClassSpinner.setAdapter(classSpinnerAdapter);
        testSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTest(v);
            }
        });
    }


    private void addTest(View v) {
        if (isEmpty(testQuestionTitleETV)) {
            Message.styleableMessage(this, "Test Başlığı Girdiğinizden emin olunuz");
            return;
        } else if (isEmpty(testQuestionCountETV)) {
            Message.styleableMessage(this, "Soru sayısını girdiğinizden emin olunuz.");
            return;
        } else if (testSchoolSpinner.getSelectedItem().toString() == "") {
            Message.styleableMessage(this, "Okul Seçiminizi Yapınız.");
            return;
        } else if (testClassSpinner.getSelectedItem().toString() == "") {
            Message.styleableMessage(this, "Sınıf Seçiminizi Yapınız.");
            return;
        }
        morTest.setTestTitle(testQuestionTitleETV.getText().toString());
        morTest.setTestForm(testFormSpinner.getSelectedItem().toString());
        String getBookletNumber[] = testBookletSpinner.getSelectedItem().toString().split(" ");
        morTest.setTestBooklet(Integer.parseInt(getBookletNumber[0]));
        morTest.setTestNetCalculateNum(Integer.parseInt(testNetCalculatorSpinner.getSelectedItem().toString()));
        morTest.setTestQuestionCount(Integer.parseInt(testQuestionCountETV.getText().toString()));
        morTest.setTestQuestionPoint(Integer.parseInt(testQuestionPointETV.getText().toString()));
        morTest.setTestSchoolId(morSchoolHelper.findByName(testSchoolSpinner.getSelectedItem().toString()));
        String studentClassIdS[] = testClassSpinner.getSelectedItem().toString().split("-");
        morTest.setTestClassId(morClassHelper.findByName(studentClassIdS[0]));
//        Message.message(this,morTest.toString());


        long id = morTestHelper.insertData(this, morTest);
        if (id < 0) {
            Message.message(this, "Veri Ekleme Başarısız");
        } else {
            Message.message(this, "Veri Ekleme Başarılı");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Mobile Scanner");
            alertDialogBuilder
                    .setMessage("Cevap Anahtarı kaydedilmedi. Kaydetmek ister misiniz?")
                    .setCancelable(false)
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle b = new Bundle();
                            b.putInt("questionCount", morTest.getTestQuestionCount());
                            startActivity(new Intent(getApplicationContext(), AnswerSheetActivity.class).putExtras(b));
                        }
                    })
                    .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
