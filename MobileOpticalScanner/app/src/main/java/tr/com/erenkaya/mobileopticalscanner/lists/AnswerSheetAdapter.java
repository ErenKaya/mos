package tr.com.erenkaya.mobileopticalscanner.lists;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.R;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.ClassDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.SchoolDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.databaserepository.StudentDatabaseAdapter;
import tr.com.erenkaya.mobileopticalscanner.entities.AnswerSheet;
import tr.com.erenkaya.mobileopticalscanner.entities.MorTest;


/**
 * Created by Eren on 9.3.2017.
 */

public class AnswerSheetAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<AnswerSheet> answerSheetList;
    TextView asQuestionNoTView;
    RadioButton asOptionA,asOptionB,asOptionC,asOptionD,asOptionE;
    private Context context;

    public AnswerSheetAdapter(Context context, List<AnswerSheet> answerSheetList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.answerSheetList = answerSheetList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return answerSheetList.size();
    }

    @Override
    public Object getItem(int position) {
        return answerSheetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowLayout;
        rowLayout = mInflater.inflate(R.layout.answersheet_row, null);
        asQuestionNoTView = (TextView) rowLayout.findViewById(R.id.asQuestionNoTView);
        asOptionA = (RadioButton) rowLayout.findViewById(R.id.asOptionA);
        asOptionB = (RadioButton) rowLayout.findViewById(R.id.asOptionB);
        asOptionC = (RadioButton) rowLayout.findViewById(R.id.asOptionC);
        asOptionD = (RadioButton) rowLayout.findViewById(R.id.asOptionD);
        asOptionE = (RadioButton) rowLayout.findViewById(R.id.asOptionE);
        AnswerSheet answerSheet=answerSheetList.get(position);
        asQuestionNoTView.setText(String.valueOf(answerSheet.getAnswerSheetNo()));
        asOptionA.setChecked(answerSheet.isAnswerSheetOptionA());
        asOptionB.setChecked(answerSheet.isAnswerSheetOptionB());
        asOptionC.setChecked(answerSheet.isAnswerSheetOptionC());
        asOptionD.setChecked(answerSheet.isAnswerSheetOptionD());
        asOptionE.setChecked(answerSheet.isAnswerSheetOptionE());

        return rowLayout;
    }
}
