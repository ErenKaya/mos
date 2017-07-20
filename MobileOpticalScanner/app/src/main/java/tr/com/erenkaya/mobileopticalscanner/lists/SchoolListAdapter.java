package tr.com.erenkaya.mobileopticalscanner.lists;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.ClassDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.StudentDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.TestDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorSchool;


/**
 * Created by Eren on 9.3.2017.
 */

public class SchoolListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MorSchool> schoolList;
    TextView rlSchoolNameTV, rlSchoolStudentCountTV, rlSchoolCodeTV, rlSchoolClassCountTV;
    private ClassDatabaseAdapter classDatabaseAdapter;
    private StudentDatabaseAdapter studentDatabaseAdapter;
    private Context context;

    public SchoolListAdapter(Context context, List<MorSchool> schoolList) {
        this.schoolList = new ArrayList<MorSchool>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.schoolList = schoolList;
        this.context = context;
        this.classDatabaseAdapter = new ClassDatabaseAdapter(context);
        this.studentDatabaseAdapter = new StudentDatabaseAdapter(context);
    }


    @Override
    public int getCount() {
        return schoolList.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowLayout;
        rowLayout = mInflater.inflate(R.layout.school_list_row, null);
        rlSchoolNameTV = (TextView) rowLayout.findViewById(R.id.rlSchoolNameTV);
        rlSchoolStudentCountTV = (TextView) rowLayout.findViewById(R.id.rlSchoolStudentCountTV);
        rlSchoolCodeTV = (TextView) rowLayout.findViewById(R.id.rlSchoolCodeTV);
        rlSchoolClassCountTV = (TextView) rowLayout.findViewById(R.id.rlSchoolClassCountTV);
        MorSchool morSchool = schoolList.get(position);
        rlSchoolNameTV.setText(morSchool.getSchoolName());
        rlSchoolClassCountTV.setText(String.valueOf(classDatabaseAdapter.findClassCountBySchoolId(morSchool.getSchoolId())));
        try{
            rlSchoolStudentCountTV.setText(String.valueOf(studentDatabaseAdapter.findStudentCountByClassId
                    (classDatabaseAdapter.findClassIdBySchoolId(morSchool.getSchoolId()))));
        }catch (IllegalStateException ex){
            rlSchoolStudentCountTV.setText("0");
        }
        rlSchoolCodeTV.setText(String.valueOf(morSchool.getSchoolCode()));

        return rowLayout;
    }
}
