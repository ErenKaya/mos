package tr.com.erenkaya.mobileopticalscanner.entities;

/**
 * Created by Eren on 13.3.2017.
 */

public class MorTest {
    private int testId;
    private String testTitle;
    private String testForm;
    private int testBooklet;
    private int testNetCalculateNum;
    private int testQuestionCount;
    private int testQuestionPoint;
    private long testSchoolId;
    private long testClassId;

    public MorTest(int testId, String testTitle, String testForm,
                   int testBooklet, int testNetCalculateNum, int testQuestionCount,
                   int testQuestionPoint, long testSchoolId, long testClassId) {
        this.testId = testId;
        this.testTitle = testTitle;
        this.testForm = testForm;
        this.testBooklet = testBooklet;
        this.testNetCalculateNum = testNetCalculateNum;
        this.testQuestionCount = testQuestionCount;
        this.testQuestionPoint = testQuestionPoint;
        this.testSchoolId = testSchoolId;
        this.testClassId = testClassId;
    }

    public MorTest() {
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestForm() {
        return testForm;
    }

    public void setTestForm(String testForm) {
        this.testForm = testForm;
    }

    public int getTestBooklet() {
        return testBooklet;
    }

    public void setTestBooklet(int testBooklet) {
        this.testBooklet = testBooklet;
    }

    public int getTestNetCalculateNum() {
        return testNetCalculateNum;
    }

    public void setTestNetCalculateNum(int testNetCalculateNum) {
        this.testNetCalculateNum = testNetCalculateNum;
    }

    public int getTestQuestionCount() {
        return testQuestionCount;
    }

    public void setTestQuestionCount(int testQuestionCount) {
        this.testQuestionCount = testQuestionCount;
    }

    public int getTestQuestionPoint() {
        return testQuestionPoint;
    }

    public void setTestQuestionPoint(int testQuestionPoint) {
        this.testQuestionPoint = testQuestionPoint;
    }

    public long getTestSchoolId() {
        return testSchoolId;
    }

    public void setTestSchoolId(long testSchoolId) {
        this.testSchoolId = testSchoolId;
    }

    public long getTestClassId() {
        return testClassId;
    }

    public void setTestClassId(long testClassId) {
        this.testClassId = testClassId;
    }

    @Override
    public String toString() {
        return "MorTest{" +
                "testId=" + testId +
                ", testTitle='" + testTitle + '\'' +
                ", testForm='" + testForm + '\'' +
                ", testBooklet=" + testBooklet +
                ", testNetCalculateNum=" + testNetCalculateNum +
                ", testQuestionCount=" + testQuestionCount +
                ", testQuestionPoint=" + testQuestionPoint +
                ", testSchoolId=" + testSchoolId +
                ", testClassId=" + testClassId +
                '}';
    }
}
