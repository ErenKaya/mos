package tr.com.erenkaya.mobileopticalscanner.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
import tr.com.erenkaya.mobileopticalscanner.databaserepository.StudentDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorStudent;


/**
 * Created by Eren on 5.3.2017.
 */

public class AddStudentActivity extends AppCompatActivity {
    EditText studentNameETV, studentSurnameETV, studentSchoolNumberETV, studentTelephoneNoETV;
    Spinner studentSchoolsSpinner, studentClassSpinner;
    Button studentSaveButton;
    SchoolDatabaseAdapter morSchoolHelper;
    ClassDatabaseAdapter morClassHelper;
    StudentDatabaseAdapter morStudentHelper;
    List<String> classNames, schoolNames;
    MorStudent morStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentNameETV = (EditText) findViewById(R.id.studentNameETV);
        studentSurnameETV = (EditText) findViewById(R.id.studentSurnameETV);
        studentSchoolNumberETV = (EditText) findViewById(R.id.studentSchoolNumberETV);
        studentTelephoneNoETV = (EditText) findViewById(R.id.studentTelephoneNoETV);
        studentSchoolsSpinner = (Spinner) findViewById(R.id.studentSchoolsSpinner);
        studentClassSpinner = (Spinner) findViewById(R.id.studentClassSpinner);
        studentSaveButton = (Button) findViewById(R.id.studentSaveButton);
        morSchoolHelper = new SchoolDatabaseAdapter(this);
        morClassHelper = new ClassDatabaseAdapter(this);
        morStudentHelper = new StudentDatabaseAdapter(this);
        morStudent=new MorStudent();
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
        studentSchoolsSpinner.setAdapter(schoolSpinnerAdapter);
        studentClassSpinner.setAdapter(classSpinnerAdapter);
        studentSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent(v);
            }


        });

    }

    private void addStudent(View v) {
        if (isEmpty(studentNameETV)) {
            Message.styleableMessage(this, "Sınıf Numarasını Girdiğinizden Emin Olunuz.");
            return;
        } else if (isEmpty(studentSurnameETV)) {
            Message.styleableMessage(this, "Şube Adını Girdiğinizden Emin Olunuz.");
            return;
        } else if (studentSchoolsSpinner.getSelectedItem().toString() == "Seçiniz") {
            Message.styleableMessage(this, "Okul Seçiminizi Yapınız.");
            return;
        } else if (studentClassSpinner.getSelectedItem().toString() == "Seçiniz") {
            Message.styleableMessage(this, "Okul Seçiminizi Yapınız.");
            return;
        }
        morStudent.setStudentName(studentNameETV.getText().toString());
        morStudent.setStudentSurname(studentSurnameETV.getText().toString());
        morStudent.setStudentSchoolNumber(Integer.parseInt(studentSchoolNumberETV.getText().toString()));
        morStudent.setStudentPhoneNumber(studentTelephoneNoETV.getText().toString());
        morStudent.setStudentSchoolId(morSchoolHelper.findByName(studentSchoolsSpinner.getSelectedItem().toString()));
        String studentClassIdS[]=studentClassSpinner.getSelectedItem().toString().split("-");
        morStudent.setStudentClassId(morClassHelper.findByName(studentClassIdS[0]));
//        Message.message(this,morStudent.toString());





        long id = morStudentHelper.insertData(this,morStudent);
        if (id < 0) {
            Message.message(this, "Veri Ekleme Başarısız");
        } else {
            Message.message(this, "Veri Ekleme Başarılı");
        }


    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

}
