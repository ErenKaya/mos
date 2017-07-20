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
import tr.com.erenkaya.mobileopticalscanner.entities.MorClass;


/**
 * Created by Eren on 9.3.2017.
 */

public class ClassListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MorClass> classList;
    TextView rlClassNameTV, rlClassStudentCountTV, rlClassSchoolNameTV, rlClassAlternateNameTV;
    private ClassDatabaseAdapter classDatabaseAdapter;
    private SchoolDatabaseAdapter schoolDatabaseAdapter;
    private StudentDatabaseAdapter studentDatabaseAdapter;
    private Context context;

    public ClassListAdapter(Context context, List<MorClass> classList) {
        this.classList = new ArrayList<MorClass>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.classList = classList;
        this.context = context;
        this.classDatabaseAdapter = new ClassDatabaseAdapter(context);
        this.schoolDatabaseAdapter = new SchoolDatabaseAdapter(context);
        this.studentDatabaseAdapter = new StudentDatabaseAdapter(context);
    }


    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Object getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowLayout;
        rowLayout = mInflater.inflate(R.layout.class_list_row, null);
        rlClassNameTV = (TextView) rowLayout.findViewById(R.id.rlClassNameTV);
        rlClassStudentCountTV = (TextView) rowLayout.findViewById(R.id.rlClassStudentCountTV);
        rlClassSchoolNameTV = (TextView) rowLayout.findViewById(R.id.rlClassSchoolNameTV);
        rlClassAlternateNameTV = (TextView) rowLayout.findViewById(R.id.rlClassAlternateNameTV);
        MorClass morClass = classList.get(position);
        rlClassNameTV.setText(new StringBuilder().append(morClass.getClassNo()).append("-").append(morClass.getClassPart()));
        rlClassAlternateNameTV.setText(morClass.getClassAlternateName());
        rlClassStudentCountTV.setText(String.valueOf(studentDatabaseAdapter.findStudentCountByClassId(morClass.getClassId())));
        rlClassSchoolNameTV.setText(String.valueOf(schoolDatabaseAdapter.findById(morClass.getClassSchoolId())));

        return rowLayout;
    }
}
