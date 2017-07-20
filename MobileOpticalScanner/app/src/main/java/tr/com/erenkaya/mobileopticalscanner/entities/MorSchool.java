package tr.com.erenkaya.mobileopticalscanner.entities;

/**
 * Created by Eren on 28.2.2017.
 */

public class MorSchool {
    private int schoolId;
    private String schoolName;
    private String schoolType;
    private long schoolCode;
    private String schoolCity;
    private String schoolDistrict;

    public MorSchool(int schoolId, String schoolName, String schoolType, long schoolCode, String schoolCity, String schoolDistrict) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        this.schoolCode = schoolCode;
        this.schoolCity = schoolCity;
        this.schoolDistrict = schoolDistrict;
    }

    public MorSchool() {
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public long getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(long schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    @Override
    public String toString() {
        return "MorSchool{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", schoolType='" + schoolType + '\'' +
                ", schoolCode=" + schoolCode +
                ", schoolCity='" + schoolCity + '\'' +
                ", schoolDistrict='" + schoolDistrict + '\'' +
                '}';
    }
}
