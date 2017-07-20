package tr.com.erenkaya.mobileopticalscanner.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.utils.Message;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.SchoolDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorSchool;


/**
 * Created by Eren on 26.2.2017.
 */

public class AddSchoolActivity extends AppCompatActivity {

    MorSchool morSchool;
    EditText schoolNameETV, schoolCodeETV, schoolCityETV, schoolDistrictETV;/*getDataETV;*/
    Spinner schoolTypeSpinner;
    Button schoolSaveButton;/* schoolShowButton,getDataButton,schoolUpdateButton,schoolDeleteButton;*/
    SchoolDatabaseAdapter morSchoolHelper;
    List<MorSchool> schoolList;
    ListView schoolListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_school);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        schoolSaveButton = (Button) findViewById(R.id.schoolSaveButton);
        schoolNameETV = (EditText) findViewById(R.id.schoolNameETV);
        schoolCodeETV = (EditText) findViewById(R.id.schoolCodeETV);
        schoolCityETV = (EditText) findViewById(R.id.schoolCityETV);
        schoolDistrictETV = (EditText) findViewById(R.id.schoolDistrictETV);
        schoolTypeSpinner = (Spinner) findViewById(R.id.schoolTypeSpinner);
        morSchoolHelper = new SchoolDatabaseAdapter(this);
        morSchool = new MorSchool();

        schoolSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSchool(v);
            }
        });


    }

    public void addSchool(View view) {
        if (isEmpty(schoolNameETV)) {
            Message.message(this, "Okul Adını girdiğinizden emin olun");
            return;
        } else if (isEmpty(schoolCodeETV)) {
            Message.message(this, "Kurum Kodunu girdiğinizden emin olun");
            return;
        }
        morSchool.setSchoolName(schoolNameETV.getText().toString());
        morSchool.setSchoolType(schoolTypeSpinner.getSelectedItem().toString());
        morSchool.setSchoolCode(Long.parseLong(schoolCodeETV.getText().toString()));
        morSchool.setSchoolCity(schoolCityETV.getText().toString());
        morSchool.setSchoolDistrict(schoolDistrictETV.getText().toString());


        long id = morSchoolHelper.insertData(morSchool);
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
        getMenuInflater().inflate(R.menu.school_add_menu, menu);
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
