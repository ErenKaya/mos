package tr.com.erenkaya.mobileopticalscanner.databaserepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tr.com.erenkaya.mobileopticalscanner.entities.MorClass;


/**
 * Created by Eren on 28.2.2017.
 */

public class ClassDatabaseAdapter {
    MorHelper morHelper;

    public ClassDatabaseAdapter(Context context) {
        morHelper = new MorHelper(context);
    }

    public long insertData(MorClass morClass) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MorHelper.C_NO, morClass.getClassNo());
        contentValues.put(MorHelper.C_PART, morClass.getClassPart());
        contentValues.put(MorHelper.C_SCHOOLID, morClass.getClassSchoolId());
        contentValues.put(MorHelper.C_ALTERN, morClass.getClassAlternateName());
        long id = sqLiteDatabase.insert(MorHelper.TABLE_NAME_CLASS, null, contentValues);
        return id;
    }

    public List<MorClass> getAllData() {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select everything from SCHOOL
        String[] columns = {morHelper.C_UID, morHelper.C_NO,
                morHelper.C_PART, morHelper.C_SCHOOLID,
                morHelper.C_ALTERN};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns, null, null, null, null, null);
        List<MorClass> morClassList = new ArrayList<>();
        MorClass morClass;
        while (cursor.moveToNext()) {
            morClass = new MorClass();
            morClass.setClassId(cursor.getInt(cursor.getColumnIndex(morHelper.C_UID)));
            morClass.setClassNo(cursor.getString(cursor.getColumnIndex(morHelper.C_NO)));
            morClass.setClassPart(cursor.getString(cursor.getColumnIndex(morHelper.C_PART)));
            morClass.setClassSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.C_SCHOOLID)));
            morClass.setClassAlternateName(cursor.getString(cursor.getColumnIndex(morHelper.C_ALTERN)));
            morClassList.add(morClass);
        }
        return morClassList;
    }
    public List<String> getClassNames(){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns = {morHelper.C_NO,morHelper.C_PART};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns, null, null, null, null, null);
        List<String> classNames=new ArrayList<>();
        classNames.add("Seçiniz");
        StringBuilder stringBuilder=new StringBuilder();
        while(cursor.moveToNext()){
            stringBuilder.append(cursor.getString(cursor.getColumnIndex(morHelper.C_NO)))
                    .append("-")
                    .append(cursor.getString(cursor.getColumnIndex(morHelper.C_PART)));
            classNames.add(stringBuilder.toString());
            stringBuilder.delete(0,stringBuilder.length());
        }
        return classNames;
    }

    public String findById(int id) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where id=id;
        String[] columns = {morHelper.C_UID, morHelper.C_NO,
                morHelper.C_PART, morHelper.C_SCHOOLID,
                morHelper.C_ALTERN};
        int[] selectionArgs = {id};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns,
                morHelper.C_UID + "='" + id + "'", null, null, null, null);
        String className = "Başlangıç değeri";
        MorClass morClass = new MorClass();
        while (cursor.moveToNext()) {
            morClass.setClassId(cursor.getInt(cursor.getColumnIndex(morHelper.C_UID)));
            morClass.setClassNo(cursor.getString(cursor.getColumnIndex(morHelper.C_NO)));
            morClass.setClassPart(cursor.getString(cursor.getColumnIndex(morHelper.C_PART)));
            morClass.setClassSchoolId(cursor.getLong(cursor.getColumnIndex(morHelper.C_SCHOOLID)));
            morClass.setClassAlternateName(cursor.getString(cursor.getColumnIndex(morHelper.C_ALTERN)));
            className = morClass.getClassNo().toString();
        }
        return className;
    }
    public int findByName(String className) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where name=?
        String[] columns = {morHelper.C_UID, morHelper.C_NO,
                morHelper.C_PART, morHelper.C_SCHOOLID,
                morHelper.C_ALTERN};
        String[] selectionArgs={className};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns,
                morHelper.C_NO + "=?", selectionArgs, null, null, null);
        int id=0;
        while (cursor.moveToNext()) {
            id=cursor.getInt(cursor.getColumnIndex(morHelper.C_UID));
        }
        return id;
    }
    public int findClassIdBySchoolId(long schoolId) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        //Select Everything from SCHOOL where name=?
        String[] columns = {morHelper.C_SCHOOLID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns,
                morHelper.C_SCHOOLID + "='"+schoolId+"'", null, null, null, null);
        int id=0;
        while (cursor.moveToNext()) {
            id=cursor.getInt(cursor.getColumnIndex(morHelper.C_UID));
        }
        return id;
    }
    // get schoolId and return how many we have class in this school
    public int findClassCountBySchoolId(long schoolId){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] columns={morHelper.C_SCHOOLID};
        Cursor cursor = sqLiteDatabase.query(morHelper.TABLE_NAME_CLASS, columns,
                morHelper.C_SCHOOLID + "='" + schoolId + "'", null, null, null, null);
        int classCounter=0;

        while(cursor.moveToNext()){
            classCounter++;
        }
        return classCounter;
    }

    public int update(String oldSchoolName, String newSchoolName) {
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(morHelper.C_NO, newSchoolName);
        String[] whereArgs={oldSchoolName};
        //Update TABLE_NAME_CLASS Set name="yine2" where name="yine"
        int count=sqLiteDatabase.update(morHelper.TABLE_NAME_CLASS, contentValues, morHelper.C_NO + " =?", whereArgs);

        return count;
    }
    public int delete(){
        SQLiteDatabase sqLiteDatabase = morHelper.getWritableDatabase();
        String[] whereArgs={"yine2"};
        int count=sqLiteDatabase.delete(morHelper.TABLE_NAME_CLASS,morHelper.C_NO+" =?",whereArgs);
        return count;
    }

}
