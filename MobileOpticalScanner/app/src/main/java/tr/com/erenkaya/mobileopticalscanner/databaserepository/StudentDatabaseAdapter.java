package tr.com.erenkaya.mobileopticalscanner.databaserepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.entities.MorStudent;


/**
 * Created by Eren on 28.2.2017.
 */

public class StudentDatabaseAdapter {
    MorHelper morHelper;

    public StudentDatabaseAdapter(Context context) {
        morHelper = new MorHelper(context);
    }

    public long insertData(Context context,MorStudent morStudent) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MorHelper.ST_NAME, morStudent.getStudentName());
        contentValues.put(MorHelper.ST_SURNAME, morStudent.getStudentSurname());
        contentValues.put(MorHelper.ST_SCHOOLNO, morStudent.getStudentSchoolNumber());
        contentValues.put(MorHelper.ST_PHONENO, morStudent.getStudentPhoneNumber());
        contentValues.put(MorHelper.ST_SCHOOLID, morStudent.getStudentSchoolId());
        contentValues.put(MorHelper.ST_CLASSID, morStudent.getStudentClassId());
        long id = sqLiteDatabase.insert(MorHelper.TABLE_NAME_STUDENT, null, contentValues);
        return id;
    }

    public List<MorStudent> getAllData() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select everything from SCHOOL
        String[] columns = {morHelper.ST_UID, morHelper.ST_NAME,
                morHelper.ST_SURNAME,morHelper.ST_SCHOOLNO,morHelper.ST_PHONENO, morHelper.ST_SCHOOLID,
                morHelper.ST_CLASSID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_STUDENT, columns, null, null, null, null, null);
        List<MorStudent> morStudentList = new ArrayList<>();
        MorStudent morStudent;
        while (cursor.moveToNext()) {
            morStudent = new MorStudent();
            morStudent.setStudentId(cursor.getInt(cursor.getColumnIndex(morHelper.ST_UID)));
            morStudent.setStudentName(cursor.getString(cursor.getColumnIndex(morHelper.ST_NAME)));
            morStudent.setStudentSurname(cursor.getString(cursor.getColumnIndex(morHelper.ST_SURNAME)));
            morStudent.setStudentPhoneNumber(cursor.getString(cursor.getColumnIndex(morHelper.ST_PHONENO)));
            morStudent.setStudentSchoolNumber(cursor.getInt(cursor.getColumnIndex(morHelper.ST_SCHOOLNO)));
            morStudent.setStudentSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.ST_SCHOOLID)));
            morStudent.setStudentClassId(cursor.getLong(cursor.getColumnIndex(morHelper.ST_CLASSID)));
            morStudentList.add(morStudent);
        }
        return morStudentList;
    }

    public List<String> getSchoolNames() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns = {morHelper.ST_NAME};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_STUDENT, columns, null, null, null, null, null);
        List<String> schoolNames = new ArrayList<>();
        schoolNames.add("Seçiniz");

        while (cursor.moveToNext()) {
            schoolNames.add(cursor.getString(cursor.getColumnIndex(morHelper.ST_NAME)));

        }
        return schoolNames;
    }

    public String findById(int id) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where id=id;
        String[] columns = {morHelper.ST_UID, morHelper.ST_NAME,
                morHelper.ST_SURNAME, morHelper.ST_SCHOOLID, morHelper.ST_CLASSID,
                morHelper.ST_PHONENO};
        int[] selectionArgs = {id};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_STUDENT, columns,
                morHelper.ST_UID + "='" + id + "'", null, null, null, null);
        String schoolName = "Başlangıç değeri";
        MorStudent morStudent = new MorStudent();
        while (cursor.moveToNext()) {
            morStudent.setStudentId(cursor.getInt(cursor.getColumnIndex(morHelper.ST_UID)));
            morStudent.setStudentName(cursor.getString(cursor.getColumnIndex(morHelper.ST_NAME)));
            morStudent.setStudentSurname(cursor.getString(cursor.getColumnIndex(morHelper.ST_SURNAME)));
            morStudent.setStudentPhoneNumber(cursor.getString(cursor.getColumnIndex(morHelper.ST_PHONENO)));
            morStudent.setStudentSchoolNumber(cursor.getInt(cursor.getColumnIndex(morHelper.ST_SCHOOLNO)));
            morStudent.setStudentSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.ST_SCHOOLID)));
            morStudent.setStudentClassId(cursor.getLong(cursor.getColumnIndex(morHelper.ST_CLASSID)));
            schoolName = morStudent.getStudentName().toString();
        }
        return schoolName;
    }
    public int findStudentCountByClassId(long classId){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns={morHelper.ST_CLASSID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_STUDENT, columns,
                morHelper.ST_CLASSID + "='" + classId + "'", null, null, null, null);
        int classCounter=0;

        while(cursor.moveToNext()){
            classCounter++;
        }
        return classCounter;
    }

    public int findByName(String schoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where name=?
        String[] columns = {morHelper.ST_UID, morHelper.ST_NAME,
                morHelper.ST_SURNAME, morHelper.ST_SCHOOLID, morHelper.ST_CLASSID,
                morHelper.ST_PHONENO};
        String[] selectionArgs = {schoolName};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_STUDENT, columns,
                morHelper.ST_NAME + "=?", selectionArgs, null, null, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(morHelper.ST_UID));
        }
        return id;
    }

    public int update(String oldSchoolName, String newSchoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(morHelper.ST_NAME, newSchoolName);
        String[] whereArgs = {oldSchoolName};
        //Update TABLE_NAME_STUDENT Set name="yine2" where name="yine"
        int count = sqLiteDatabase.update(morHelper.TABLE_NAME_STUDENT, contentValues, morHelper.ST_NAME + " =?", whereArgs);

        return count;
    }

    public int delete() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] whereArgs = {"yine2"};
        int count = sqLiteDatabase.delete(morHelper.TABLE_NAME_STUDENT, morHelper.ST_NAME + " =?", whereArgs);
        return count;
    }

}
