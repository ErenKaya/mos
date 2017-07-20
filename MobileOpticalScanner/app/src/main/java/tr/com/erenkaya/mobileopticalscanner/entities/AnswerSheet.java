package tr.com.erenkaya.mobileopticalscanner.entities;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Eren on 24.2.2017.
 */

public class AnswerSheet {

    private int answerSheetNo;
    private boolean answerSheetOptionA;
    private boolean answerSheetOptionB;
    private boolean answerSheetOptionC;
    private boolean answerSheetOptionD;
    private boolean answerSheetOptionE;

    public AnswerSheet(int answerSheetNo, boolean answerSheetOptionA, boolean answerSheetOptionB,
                       boolean answerSheetOptionC, boolean answerSheetOptionD, boolean answerSheetOptionE) {
        this.answerSheetNo = answerSheetNo;
        this.answerSheetOptionA = answerSheetOptionA;
        this.answerSheetOptionB = answerSheetOptionB;
        this.answerSheetOptionC = answerSheetOptionC;
        this.answerSheetOptionD = answerSheetOptionD;
        this.answerSheetOptionE = answerSheetOptionE;
    }

    public int getAnswerSheetNo() {
        return answerSheetNo;
    }

    public void setAnswerSheetNo(int answerSheetNo) {
        this.answerSheetNo = answerSheetNo;
    }

    public boolean isAnswerSheetOptionA() {
        return answerSheetOptionA;
    }

    public void setAnswerSheetOptionA(boolean answerSheetOptionA) {
        this.answerSheetOptionA = answerSheetOptionA;
    }

    public boolean isAnswerSheetOptionB() {
        return answerSheetOptionB;
    }

    public void setAnswerSheetOptionB(boolean answerSheetOptionB) {
        this.answerSheetOptionB = answerSheetOptionB;
    }

    public boolean isAnswerSheetOptionC() {
        return answerSheetOptionC;
    }

    public void setAnswerSheetOptionC(boolean answerSheetOptionC) {
        this.answerSheetOptionC = answerSheetOptionC;
    }

    public boolean isAnswerSheetOptionD() {
        return answerSheetOptionD;
    }

    public void setAnswerSheetOptionD(boolean answerSheetOptionD) {
        this.answerSheetOptionD = answerSheetOptionD;
    }

    public boolean isAnswerSheetOptionE() {
        return answerSheetOptionE;
    }

    public void setAnswerSheetOptionE(boolean answerSheetOptionE) {
        this.answerSheetOptionE = answerSheetOptionE;
    }
}
