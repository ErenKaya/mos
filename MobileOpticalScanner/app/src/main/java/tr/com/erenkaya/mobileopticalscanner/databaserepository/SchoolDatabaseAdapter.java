package tr.com.erenkaya.mobileopticalscanner.databaserepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.entities.MorSchool;


/**
 * Created by Eren on 28.2.2017.
 */

public class SchoolDatabaseAdapter {
    MorHelper morHelper;

    public SchoolDatabaseAdapter(Context context) {
        morHelper = new MorHelper(context);
    }

    public long insertData(MorSchool morSchool) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MorHelper.S_NAME, morSchool.getSchoolName());
        contentValues.put(MorHelper.S_TYPE, morSchool.getSchoolType());
        contentValues.put(MorHelper.S_CODE, morSchool.getSchoolCode());
        contentValues.put(MorHelper.S_CITY, morSchool.getSchoolCity());
        contentValues.put(MorHelper.S_DISTRICT, morSchool.getSchoolDistrict());
        long id = sqLiteDatabase.insert(MorHelper.TABLE_NAME_SCHOOL, null, contentValues);
        return id;
    }

    public List<MorSchool> getAllData() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select everything from SCHOOL
        String[] columns = {morHelper.S_UID, morHelper.S_NAME,
                morHelper.S_TYPE, morHelper.S_CODE,
                morHelper.S_CITY, morHelper.S_DISTRICT,};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_SCHOOL, columns, null, null, null, null, null);
        List<MorSchool> morSchoolList = new ArrayList<>();
        MorSchool morSchool;
        while (cursor.moveToNext()) {
            morSchool = new MorSchool();
            morSchool.setSchoolId(cursor.getInt(cursor.getColumnIndex(morHelper.S_UID)));
            morSchool.setSchoolName(cursor.getString(cursor.getColumnIndex(morHelper.S_NAME)));
            morSchool.setSchoolType(cursor.getString(cursor.getColumnIndex(morHelper.S_TYPE)));
            morSchool.setSchoolCode(cursor.getLong(cursor.getColumnIndex(morHelper.S_CODE)));
            morSchool.setSchoolCity(cursor.getString(cursor.getColumnIndex(morHelper.S_CITY)));
            morSchool.setSchoolDistrict(cursor.getString(cursor.getColumnIndex(morHelper.S_DISTRICT)));
            morSchoolList.add(morSchool);
    }
        return morSchoolList;
    }
    public List<String> getSchoolNames(){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns = {morHelper.S_NAME};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_SCHOOL, columns, null, null, null, null, null);
        List<String> schoolNames=new ArrayList<>();
        schoolNames.add("Seçiniz");

        while(cursor.moveToNext()){
            schoolNames.add(cursor.getString(cursor.getColumnIndex(morHelper.S_NAME)));

        }
        return schoolNames;
    }

    public String findById(long id) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where id=id;
        String[] columns = {morHelper.S_UID, morHelper.S_NAME,
                morHelper.S_TYPE, morHelper.S_CODE,
                morHelper.S_CITY, morHelper.S_DISTRICT,};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_SCHOOL, columns,
                morHelper.S_UID + "='" + id + "'", null, null, null, null);
        String schoolName = "Başlangıç değeri";
        MorSchool morSchool = new MorSchool();
        while (cursor.moveToNext()) {
            morSchool.setSchoolId(cursor.getInt(cursor.getColumnIndex(morHelper.S_UID)));
            morSchool.setSchoolName(cursor.getString(cursor.getColumnIndex(morHelper.S_NAME)));
            morSchool.setSchoolType(cursor.getString(cursor.getColumnIndex(morHelper.S_TYPE)));
            morSchool.setSchoolCode(cursor.getLong(cursor.getColumnIndex(morHelper.S_CODE)));
            morSchool.setSchoolCity(cursor.getString(cursor.getColumnIndex(morHelper.S_CITY)));
            morSchool.setSchoolDistrict(cursor.getString(cursor.getColumnIndex(morHelper.S_DISTRICT)));
            schoolName = morSchool.getSchoolName().toString();
        }
        return schoolName;
    }
    public int findByName(String schoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where name=?
        String[] columns = {morHelper.S_UID, morHelper.S_NAME,
                morHelper.S_TYPE, morHelper.S_CODE,
                morHelper.S_CITY, morHelper.S_DISTRICT,};
        String[] selectionArgs={schoolName};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_SCHOOL, columns,
                morHelper.S_NAME + "=?", selectionArgs, null, null, null);
        int id=0;
        while (cursor.moveToNext()) {
            id=cursor.getInt(cursor.getColumnIndex(morHelper.S_UID));
        }
        return id;
    }

    public int update(String oldSchoolName, String newSchoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(morHelper.S_NAME, newSchoolName);
        String[] whereArgs={oldSchoolName};
        //Update TABLE_NAME Set name="yine2" where name="yine"
        int count=sqLiteDatabase.update(morHelper.TABLE_NAME_SCHOOL, contentValues, morHelper.S_NAME + " =?", whereArgs);

        return count;
    }
    public int delete(){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] whereArgs={"yine2"};
        int count=sqLiteDatabase.delete(morHelper.TABLE_NAME_SCHOOL,morHelper.S_NAME+" =?",whereArgs);
        return count;
    }

}
