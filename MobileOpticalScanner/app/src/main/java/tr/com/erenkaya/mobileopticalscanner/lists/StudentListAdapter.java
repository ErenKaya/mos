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
import tr.com.erenkaya.mobileopticalscanner.databaserepository.SchoolDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.StudentDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.MorStudent;


/**
 * Created by Eren on 9.3.2017.
 */

public class StudentListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MorStudent> studentList;
    TextView rlStudentNameTV, rlStudentTestCountTV, rlStudentSchoolNumberTV, rlStudentSchoolName;
    private SchoolDatabaseAdapter schoolDatabaseAdapter;
    private Context context;

    public StudentListAdapter(Context context, List<MorStudent> studentList) {
        this.studentList = new ArrayList<MorStudent>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.studentList = studentList;
        this.context = context;
        this.schoolDatabaseAdapter = new SchoolDatabaseAdapter(context);
    }


    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowLayout;
        rowLayout = mInflater.inflate(R.layout.student_list_row, null);
        rlStudentNameTV = (TextView) rowLayout.findViewById(R.id.rlStudentNameTV);
        rlStudentTestCountTV = (TextView) rowLayout.findViewById(R.id.rlStudentTestCountTV);
        rlStudentSchoolNumberTV = (TextView) rowLayout.findViewById(R.id.rlStudentSchoolNumberTV);
        rlStudentSchoolName = (TextView) rowLayout.findViewById(R.id.rlStudentSchoolName);
        MorStudent morStudent = studentList.get(position);
        rlStudentNameTV.setText(morStudent.getStudentName());
        rlStudentSchoolName.setText(schoolDatabaseAdapter.findById(morStudent.getStudentSchoolId()));
        rlStudentTestCountTV.setText(String.valueOf(morStudent.getStudentId()));
        rlStudentSchoolNumberTV.setText(String.valueOf(morStudent.getStudentSchoolNumber()));

        return rowLayout;
    }
}
