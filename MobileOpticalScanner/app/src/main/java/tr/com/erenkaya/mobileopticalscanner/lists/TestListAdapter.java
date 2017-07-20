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
import tr.com.erenkaya.mobileopticalscanner.entities.MorTest;


/**
 * Created by Eren on 9.3.2017.
 */

public class TestListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MorTest> testList;
    TextView rltestQuestionTitleTV, rlReadedTestCountTV, rlTestBookletTV, rlTestFormTV,rlTestClassCountTV,rlTestSchoolNameTV;
    private ClassDatabaseAdapter classDatabaseAdapter;
    private SchoolDatabaseAdapter schoolDatabaseAdapter;
    private StudentDatabaseAdapter studentDatabaseAdapter;
    private Context context;

    public TestListAdapter(Context context, List<MorTest> testList) {
        this.testList = new ArrayList<MorTest>();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.testList = testList;
        this.context = context;
        this.classDatabaseAdapter = new ClassDatabaseAdapter(context);
        this.schoolDatabaseAdapter = new SchoolDatabaseAdapter(context);
        this.studentDatabaseAdapter = new StudentDatabaseAdapter(context);
    }


    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public Object getItem(int position) {
        return testList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowLayout;
        rowLayout = mInflater.inflate(R.layout.test_list_row, null);
        rltestQuestionTitleTV = (TextView) rowLayout.findViewById(R.id.rltestQuestionTitleTV);
        rlReadedTestCountTV = (TextView) rowLayout.findViewById(R.id.rlReadedTestCountTV);
        rlTestBookletTV = (TextView) rowLayout.findViewById(R.id.rlTestBookletTV);
        rlTestFormTV = (TextView) rowLayout.findViewById(R.id.rlTestFormTV);
        rlTestClassCountTV = (TextView) rowLayout.findViewById(R.id.rlTestClassCountTV);
        rlTestSchoolNameTV = (TextView) rowLayout.findViewById(R.id.rlTestSchoolNameTV);
        MorTest morTest = testList.get(position);
        rltestQuestionTitleTV.setText(morTest.getTestTitle());
        rlTestFormTV.setText(morTest.getTestForm());
        rlReadedTestCountTV.setText(String.valueOf(morTest.getTestId()));
        rlTestBookletTV.setText(String.valueOf(morTest.getTestBooklet()));
        rlTestClassCountTV.setText(String.valueOf(classDatabaseAdapter.findClassCountBySchoolId(morTest.getTestSchoolId())));
        rlTestSchoolNameTV.setText(schoolDatabaseAdapter.findById(morTest.getTestSchoolId()));

        return rowLayout;
    }
}
