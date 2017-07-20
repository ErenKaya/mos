package tr.com.erenkaya.mobileopticalscanner.entities;

/**
 * Created by Eren on 3.3.2017.
 */

public class MorClass {
    private int classId;
    private String classNo;
    private String classPart;
    private String classAlternateName;
    private long classSchoolId;


    public MorClass() {
    }
    public MorClass(int classId, String classNo, String classPart, String classAlternateName, long classSchoolId) {

        this.classId = classId;
        this.classNo = classNo;
        this.classPart = classPart;
        this.classAlternateName = classAlternateName;
        this.classSchoolId = classSchoolId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassPart() {
        return classPart;
    }

    public void setClassPart(String classPart) {
        this.classPart = classPart;
    }

    public String getClassAlternateName() {
        return classAlternateName;
    }

    public void setClassAlternateName(String classAlternateName) {
        this.classAlternateName = classAlternateName;
    }

    public long getClassSchoolId() {
        return classSchoolId;
    }

    public void setClassSchoolId(long classSchoolId) {
        this.classSchoolId = classSchoolId;
    }

    @Override
    public String toString() {
        return "MorClass{" +
                "classId=" + classId +
                ", classNo='" + classNo + '\'' +
                ", classPart='" + classPart + '\'' +
                ", classAlternateName='" + classAlternateName + '\'' +
                ", classSchoolId=" + classSchoolId +
                '}';
    }
}
