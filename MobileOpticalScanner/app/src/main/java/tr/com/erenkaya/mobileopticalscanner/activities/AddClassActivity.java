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
import tr.com.erenkaya.mobileopticalscanner.entities.MorClass;


/**
 * Created by Eren on 4.3.2017.
 */

public class AddClassActivity extends AppCompatActivity {
    MorClass morClass;
    EditText classNoETV, classPartETV, classAlternateNameETV;
    Spinner classSchoolsSpinner;
    Button classSaveButton;
    SchoolDatabaseAdapter morSchoolHelper;
    ClassDatabaseAdapter morClassHelper;
    List<String> schoolNames;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        classNoETV = (EditText) findViewById(R.id.classNoETV);
        classPartETV = (EditText) findViewById(R.id.classPartETV);
        classAlternateNameETV = (EditText) findViewById(R.id.classAlternateNameETV);
        classSchoolsSpinner = (Spinner) findViewById(R.id.classSchoolsSpinner);
        classSaveButton = (Button) findViewById(R.id.classSaveButton);
        morClass = new MorClass();
        morSchoolHelper = new SchoolDatabaseAdapter(this);
        morClassHelper = new ClassDatabaseAdapter(this);
        schoolNames = new ArrayList<>();
        try {
            schoolNames = morSchoolHelper.getSchoolNames();
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.message(this, e.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolNames);
        classSchoolsSpinner.setAdapter(arrayAdapter);
        classSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass(v);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void addClass(View v) {
        if (isEmpty(classNoETV)) {
            Message.styleableMessage(this, "Sınıf Numarasını Girdiğinizden Emin Olunuz.");
            return;
        } else if (isEmpty(classPartETV)) {
            Message.styleableMessage(this, "Şube Adını Girdiğinizden Emin Olunuz.");
            return;
        } else if (classSchoolsSpinner.getSelectedItem().toString() == "Seçiniz") {
            Message.styleableMessage(this, "Okul Seçiminizi Yapınız.");
            return;
        }
        morClass.setClassNo(classNoETV.getText().toString());
        morClass.setClassPart(classPartETV.getText().toString());
        morClass.setClassAlternateName(classAlternateNameETV.getText().toString());
        morClass.setClassSchoolId(morSchoolHelper.findByName(classSchoolsSpinner.getSelectedItem().toString()));


        long id = morClassHelper.insertData(morClass);
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
        getMenuInflater().inflate(R.menu.class_add_menu, menu);
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
