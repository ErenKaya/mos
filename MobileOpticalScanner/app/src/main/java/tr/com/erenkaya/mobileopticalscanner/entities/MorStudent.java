package tr.com.erenkaya.mobileopticalscanner.entities;

/**
 * Created by Eren on 5.3.2017.
 */

public class MorStudent {
    private int studentId;
    private String studentName;
    private String studentSurname;
    private int studentSchoolNumber;
    private String studentPhoneNumber;
    private long studentClassId;
    private long studentSchoolId;

    public MorStudent(int studentId, String studentName, String studentSurname,
                      int studentSchoolNumber, String studentPhoneNumber, long studentClassId, long studentSchoolId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentSchoolNumber = studentSchoolNumber;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentClassId = studentClassId;
        this.studentSchoolId = studentSchoolId;
    }

    public MorStudent() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public int getStudentSchoolNumber() {
        return studentSchoolNumber;
    }

    public void setStudentSchoolNumber(int studentSchoolNumber) {
        this.studentSchoolNumber = studentSchoolNumber;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public long getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(long studentClassId) {
        this.studentClassId = studentClassId;
    }

    public long getStudentSchoolId() {
        return studentSchoolId;
    }

    public void setStudentSchoolId(long studentSchoolId) {
        this.studentSchoolId = studentSchoolId;
    }

    @Override
    public String toString() {
        return "MorStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", studentSchoolNumber=" + studentSchoolNumber +
                ", studentPhoneNumber='" + studentPhoneNumber + '\'' +
                ", studentClassId=" + studentClassId +
                ", studentSchoolId=" + studentSchoolId +
                '}';
    }
}
