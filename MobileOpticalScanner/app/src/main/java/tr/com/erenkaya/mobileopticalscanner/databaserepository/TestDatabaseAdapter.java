package tr.com.erenkaya.mobileopticalscanner.databaserepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.utils.Message;
import tr.com.erenkaya.mobileopticalscanner.entities.MorTest;


/**
 * Created by Eren on 28.2.2017.
 */

public class TestDatabaseAdapter {
    
    MorHelper morHelper;

    public TestDatabaseAdapter(Context context) {
        morHelper = new MorHelper(context);
    }

    public long insertData(Context context,MorTest morTest) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Message.message(context,morTest.toString());
        contentValues.put(MorHelper.T_TITLE, morTest.getTestTitle());
        contentValues.put(MorHelper.T_BOOKLET, morTest.getTestBooklet());
        contentValues.put(MorHelper.T_FORM, morTest.getTestForm());
        contentValues.put(MorHelper.T_NETCALC, morTest.getTestNetCalculateNum());
        contentValues.put(MorHelper.T_QCOUNT, morTest.getTestQuestionCount());
        contentValues.put(MorHelper.T_QPOINT, morTest.getTestQuestionPoint());
        contentValues.put(MorHelper.T_SCHOOLID, morTest.getTestSchoolId());
        contentValues.put(MorHelper.T_CLASSID, morTest.getTestClassId());
        long id = sqLiteDatabase.insert(MorHelper.TABLE_NAME_TEST, null, contentValues);
        return id;
    }

    public List<MorTest> getAllData() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select everything from SCHOOL
        String[] columns = {morHelper.T_UID, morHelper.T_TITLE,
                morHelper.T_BOOKLET,morHelper.T_FORM,morHelper.T_NETCALC,morHelper.T_QCOUNT,morHelper.T_QPOINT, morHelper.T_SCHOOLID,
                morHelper.T_CLASSID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_TEST, columns, null, null, null, null, null);
        List<MorTest> morTestList = new ArrayList<>();
        MorTest morTest;
        while (cursor.moveToNext()) {
            morTest = new MorTest();
            morTest.setTestId(cursor.getInt(cursor.getColumnIndex(morHelper.T_UID)));
            morTest.setTestTitle(cursor.getString(cursor.getColumnIndex(morHelper.T_TITLE)));
            morTest.setTestBooklet(cursor.getInt(cursor.getColumnIndex(morHelper.T_BOOKLET)));
            morTest.setTestForm(cursor.getString(cursor.getColumnIndex(morHelper.T_FORM)));
            morTest.setTestQuestionCount(cursor.getInt(cursor.getColumnIndex(morHelper.T_QCOUNT)));
            morTest.setTestQuestionPoint(cursor.getInt(cursor.getColumnIndex(morHelper.T_QPOINT)));
            morTest.setTestNetCalculateNum(cursor.getInt(cursor.getColumnIndex(morHelper.T_NETCALC)));
            morTest.setTestSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.T_SCHOOLID)));
            morTest.setTestClassId(cursor.getLong(cursor.getColumnIndex(morHelper.T_CLASSID)));
            morTestList.add(morTest);
        }
        return morTestList;
    }

    public List<String> getSchoolNames() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns = {morHelper.T_TITLE};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_TEST, columns, null, null, null, null, null);
        List<String> testTitles = new ArrayList<>();
        testTitles.add("Seçiniz");

        while (cursor.moveToNext()) {
            testTitles.add(cursor.getString(cursor.getColumnIndex(morHelper.T_TITLE)));

        }
        return testTitles;
    }

    public String findById(int id) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where id=id;
        String[] columns = {morHelper.T_UID, morHelper.T_TITLE,
                morHelper.T_BOOKLET,morHelper.T_FORM,morHelper.T_NETCALC,morHelper.T_QCOUNT,morHelper.T_QPOINT, morHelper.T_SCHOOLID,
                morHelper.T_CLASSID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_TEST, columns,
                morHelper.T_UID + "='" + id + "'", null, null, null, null);
        String testTitle = "Başlangıç değeri";
        MorTest morTest = new MorTest();
        while (cursor.moveToNext()) {
            morTest.setTestId(cursor.getInt(cursor.getColumnIndex(morHelper.T_UID)));
            morTest.setTestTitle(cursor.getString(cursor.getColumnIndex(morHelper.T_TITLE)));
            morTest.setTestBooklet(cursor.getInt(cursor.getColumnIndex(morHelper.T_BOOKLET)));
            morTest.setTestForm(cursor.getString(cursor.getColumnIndex(morHelper.T_FORM)));
            morTest.setTestQuestionCount(cursor.getInt(cursor.getColumnIndex(morHelper.T_QCOUNT)));
            morTest.setTestQuestionPoint(cursor.getInt(cursor.getColumnIndex(morHelper.T_QPOINT)));
            morTest.setTestNetCalculateNum(cursor.getInt(cursor.getColumnIndex(morHelper.T_NETCALC)));
            morTest.setTestSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.T_SCHOOLID)));
            morTest.setTestClassId(cursor.getLong(cursor.getColumnIndex(morHelper.T_CLASSID)));
            testTitle = morTest.getTestTitle().toString();
        }
        return testTitle;
    }

    public int findByName(String testTitle) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where name=?
        String[] columns = {morHelper.T_UID};
        String[] selectionArgs = {testTitle};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_TEST, columns,
                morHelper.T_TITLE + "=?", selectionArgs, null, null, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(morHelper.T_UID));
        }
        return id;
    }

    public int update(String oldSchoolName, String newSchoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(morHelper.T_TITLE, newSchoolName);
        String[] whereArgs = {oldSchoolName};
        //Update TABLE_NAME_TEST Set name="yine2" where name="yine"
        int count = sqLiteDatabase.update(morHelper.TABLE_NAME_TEST, contentValues, morHelper.T_TITLE + " =?", whereArgs);

        return count;
    }
    public int findTestCountBySchoolId(long schoolId){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns={morHelper.T_SCHOOLID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_TEST, columns,
                morHelper.T_SCHOOLID + "='" + schoolId + "'", null, null, null, null);
        int classCounter=0;

        while(cursor.moveToNext()){
            classCounter++;
        }
        return classCounter;
    }

    public int delete() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] whereArgs = {"yine2"};
        int count = sqLiteDatabase.delete(morHelper.TABLE_NAME_TEST, morHelper.T_TITLE + " =?", whereArgs);
        return count;
    }

}
