package tr.com.erenkaya.mobileopticalscanner.databaserepository;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tr.com.erenkaya.mobileopticalscanner.utils.Message;


/**
 * Created by Eren on 9.3.2017.
 */

public class MorHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "mosdatabase.db";
    protected static final String TABLE_NAME_SCHOOL = "SCHOOL";
    protected static final int DATABASE_VERSION = 2;
    protected static final String S_UID = "_id";
    protected static final String S_NAME = "schoolName";
    protected static final String S_TYPE = "schoolType";
    protected static final String S_CODE = "schoolCode";
    protected static final String S_CITY = "schoolCity";
    protected static final String S_DISTRICT = "schoolDistrict";
    protected static final String TABLE_NAME_STUDENT = "STUDENT";
    protected static final String ST_UID = "_id";
    protected static final String ST_NAME = "studentName";
    protected static final String ST_SURNAME = "studentSurname";
    protected static final String ST_SCHOOLNO = "studentSchoolNumber";
    protected static final String ST_PHONENO = "studentPhoneNumber";
    protected static final String ST_SCHOOLID = "studentClassId";
    protected static final String ST_CLASSID = "studentSchoolId";
    protected static final String TABLE_NAME_CLASS = "CLASS";
    protected static final String C_UID = "_id";
    protected static final String C_NO = "classNo";
    protected static final String C_PART = "classPart";
    protected static final String C_ALTERN = "classAlternate";
    protected static final String C_SCHOOLID = "classSchoolId";
    protected static final String TABLE_NAME_TEST = "TEST";
    protected static final String T_UID = "_id";
    protected static final String T_TITLE = "testTitle";
    protected static final String T_BOOKLET = "testBooklet";
    protected static final String T_FORM = "testForm";
    protected static final String T_NETCALC = "testNetCalculateNum";
    protected static final String T_QCOUNT = "testQuestionCount";
    protected static final String T_QPOINT = "testQuestionPoint";
    protected static final String T_SCHOOLID = "testSchoolId";
    protected static final String T_CLASSID = "testClassId";
    protected static final String DROP_TABLE_SCHOOL = "DROP TABLE IF EXISTS " + TABLE_NAME_SCHOOL;
    protected static final String DROP_TABLE_TEST = "DROP TABLE IF EXISTS " + TABLE_NAME_TEST;
    protected static final String DROP_TABLE_STUDENT = "DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT;
    protected static final String DROP_TABLE_CLASS = "DROP TABLE IF EXISTS " + TABLE_NAME_CLASS;
    protected static final String CREATE_TABLE_CLASS = "CREATE TABLE "
            + TABLE_NAME_CLASS + "("
            + C_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NO + " VARCHAR(255),"
            + C_PART + " VARCHAR(255),"
            + C_ALTERN + " VARCHAR(255),"
            + C_SCHOOLID + " BIGINT " + ");";
    protected static final String CREATE_TABLE_STUDENT = "CREATE TABLE "
            + TABLE_NAME_STUDENT + "("
            + ST_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ST_NAME + " VARCHAR(255),"
            + ST_SURNAME + " VARCHAR(255),"
            + ST_SCHOOLNO + " INTEGER,"
            + ST_PHONENO + " VARCHAR(255),"
            + ST_SCHOOLID + " BIGINT,"
            + ST_CLASSID + " BIGINT " + ");";
    protected static final String CREATE_TABLE_SCHOOL = "CREATE TABLE "
            + TABLE_NAME_SCHOOL + "("
            + S_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + S_NAME + " VARCHAR(255),"
            + S_TYPE + " VARCHAR(255),"
            + S_CODE + " BIGINT,"
            + S_CITY + " VARCHAR(255),"
            + S_DISTRICT + " VARCHAR(255) " + ");";
    protected static final String CREATE_TABLE_TEST = "CREATE TABLE "
            + TABLE_NAME_TEST + "("
            + T_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T_TITLE + " VARCHAR(255),"
            + T_FORM + " VARCHAR(255),"
            + T_BOOKLET + " INTEGER,"
            + T_NETCALC + " INTEGER,"
            + T_QCOUNT + " INTEGER,"
            + T_QPOINT + " INTEGER,"
            + T_SCHOOLID + " BIGINT,"
            + T_CLASSID + " BIGINT " + ");";

    private Context context;

    public MorHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_SCHOOL);
            db.execSQL(CREATE_TABLE_CLASS);
            db.execSQL(CREATE_TABLE_STUDENT);
            db.execSQL(CREATE_TABLE_TEST);
        } catch (SQLException e) {
            Message.message(context, e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_SCHOOL);
            db.execSQL(DROP_TABLE_CLASS);
            db.execSQL(DROP_TABLE_STUDENT);
            db.execSQL(DROP_TABLE_TEST);
            onCreate(db);
        } catch (SQLException e) {
            Message.message(context, e.toString());
        }

    }
}
