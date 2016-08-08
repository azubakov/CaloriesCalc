package azubakov.edu.caloriescalc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import azubakov.edu.caloriescalc.models.Calorie;

/**
 * Created by azubakov on 8/6/16.
 */
//stam test
public class CalorieDAO {
    private SQLiteDatabase db;
    private Context context;

    public CalorieDAO(Context context)
    {
        this.context = context;
        CalorieDBHelper helper = new CalorieDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Calorie c) {


        ContentValues values = new ContentValues();
        values.put(CalorieContract.Calorie.COL_DATE, c.getDate());
        values.put(CalorieContract.Calorie.COL_CALORIESPLUS, c.getCaloriesplus());
        values.put(CalorieContract.Calorie.COL_CALORIESMINUS, c.getCaloriesminus());
        values.put(CalorieContract.Calorie.COL_QUANTITY_WATER, c.getQuantitywater());
        values.put(CalorieContract.Calorie.COL_QUANTITY_WATER, c.getQuantitywater());
        values.put(CalorieContract.Calorie.COL_GENDER, c.getGender());
        values.put(CalorieContract.Calorie.COL_WEIGHT, c.getWeight());
        values.put(CalorieContract.Calorie.COL_AGE, c.getAge());
        values.put(CalorieContract.Calorie.COL_HEGHT, c.getHeight());
        values.put(CalorieContract.Calorie.COL_ACTIVITY, c.getActivity());

        long insertedID = db.insert(CalorieContract.Calorie.TABLE_NAME, null, values);

        return insertedID;
    }

    public ArrayList<Calorie> query() {
        ArrayList<Calorie> calories = new ArrayList<>();

        //The Data set: The Query result:
        Cursor cursor = db.query(CalorieContract.Calorie.TABLE_NAME,null,null,null,null,null,null);

        //move to the first row:
        if (cursor.moveToFirst())
        {

            do {
                Calorie c = parseCursor(cursor);
                calories.add(c);
            } while (cursor.moveToNext());
        }
        return calories;
    }

    public Calorie query(String id) {
        //The Data set: The Query result:
        Cursor cursor = db.query(CalorieContract.Calorie.TABLE_NAME, null, " _id = ? ", new String[]{id}, null, null, null);
        return parseCursor(cursor);
    }

    public ArrayList<Calorie> queryByDate(String date) {
        //The Data set: The Query result:
        Cursor cursor = db.query(CalorieContract.Calorie.TABLE_NAME,
                null,
                CalorieContract.Calorie.COL_DATE + " LIKE ?",
                new String[]{date + "%"},
                null, null,
                CalorieContract.Calorie.COL_DATE +" DESC"
        );

        ArrayList<Calorie> calories = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                calories.add(parseCursor(cursor));
            } while (cursor.moveToNext());
        }
        return calories;
    }




    private Calorie parseCursor(Cursor cursor) {
        //Read the Line:
        String id = cursor.getString(cursor.getColumnIndex(CalorieContract.Calorie.COL_ID));
        String date = cursor.getString(cursor.getColumnIndex(CalorieContract.Calorie.COL_DATE));
        Double caloriesplus = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_CALORIESPLUS));
        Double caloriesminus = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_CALORIESMINUS));
        Double quantitywater = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_QUANTITY_WATER));
        Double weightfood = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_QUANTITY_WATER));
        Integer gender = cursor.getInt(cursor.getColumnIndex(CalorieContract.Calorie.COL_GENDER));
        Double weight = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_WEIGHT));
        Double age = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_AGE));
        Double height = cursor.getDouble(cursor.getColumnIndex(CalorieContract.Calorie.COL_HEGHT));
        Integer activity = cursor.getInt(cursor.getColumnIndex(CalorieContract.Calorie.COL_ACTIVITY));


        return new Calorie(id, date, caloriesplus, caloriesminus, quantitywater, weightfood, gender, weight, age, height,activity);
    }


    public void Query(View view) {
    }


    public int update(String id, Calorie c){
        ContentValues values = getContentValues(c);


        int rowsAffected = db.update(CalorieContract.Calorie.TABLE_NAME, values, CalorieContract.Calorie.COL_ID + " = ?", new String[]{id});
        return rowsAffected;
    }

    public int delete(String id) {
        int rowsAffected = db.delete(CalorieContract.Calorie.TABLE_NAME, CalorieContract.Calorie.COL_ID + " = ?", new String[]{id});
        return rowsAffected;

    }


    private ContentValues getContentValues(Calorie c) {
        ContentValues values = new ContentValues();
        values.put(CalorieContract.Calorie.COL_DATE, c.getDate());
        values.put(CalorieContract.Calorie.COL_CALORIESPLUS, c.getCaloriesplus());
        values.put(CalorieContract.Calorie.COL_CALORIESMINUS, c.getCaloriesminus());
        values.put(CalorieContract.Calorie.COL_GENDER, c.getGender());
        values.put(CalorieContract.Calorie.COL_WEIGHT, c.getWeight());
        values.put(CalorieContract.Calorie.COL_AGE, c.getAge());
        values.put(CalorieContract.Calorie.COL_HEGHT, c.getHeight());
        values.put(CalorieContract.Calorie.COL_ACTIVITY, c.getActivity());

        return values;
    }
}
