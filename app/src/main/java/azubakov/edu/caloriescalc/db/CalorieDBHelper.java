package azubakov.edu.caloriescalc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by azubakov on 8/5/16.
 */
public class CalorieDBHelper extends SQLiteOpenHelper {

    //Constructor
    public CalorieDBHelper(Context context) {
        super(context, "CalorieDB", null, 1);
    }

    //Methods that we need to implement
    @Override
    public void onCreate(SQLiteDatabase db) {
         String createCalorieTable = "CREATE TABLE " + CalorieContract.Calorie.TABLE_NAME + "(" +
                                                           CalorieContract.Calorie.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                                           CalorieContract.Calorie.COL_DATE + " TEXT , " +
                                                           CalorieContract.Calorie.COL_CALORIESPLUS + " REAL , " +
                                                           CalorieContract.Calorie.COL_CALORIESMINUS + " REAL , " +
                                                           CalorieContract.Calorie.COL_QUANTITY_WATER + " REAL , " +
                                                           CalorieContract.Calorie.COL_GENDER + " INTEGER , " +
                                                           CalorieContract.Calorie.COL_WEIGHT + " REAL , " +
                                                           CalorieContract.Calorie.COL_AGE + " REAL , " +
                                                           CalorieContract.Calorie.COL_HEGHT + " REAL , " +
                                                           CalorieContract.Calorie.COL_ACTIVITY + " INTEGER " +
                                                        " )";



       /* String createCalorieTable = "CREATE TABLE Calories(" +
                "   _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "   date TEXT, " +
                "   firstname TEXT" +
                " )";*/

        /*String createCalorieTable = "CREATE TABLE " + CalorieContract.Calorie.TABLE_NAME + "(" +
                CalorieContract.Calorie.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                CalorieContract.Calorie.COL_DATE + " TEXT, " +
                CalorieContract.Calorie.COL_FIRSTNAME + " TEXT " +
                " )";*/


                db.execSQL(createCalorieTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropCalories = "DROP TABLE " + CalorieContract.Calorie.TABLE_NAME + ";";
        db.execSQL(dropCalories);

        onCreate(db);

    }
}
