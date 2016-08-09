package azubakov.edu.caloriescalc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import azubakov.edu.caloriescalc.R;
import azubakov.edu.caloriescalc.db.CalorieDAO;
import azubakov.edu.caloriescalc.models.Calorie;

public class CaloriesDetailsActivity extends AppCompatActivity {

    EditText etDate, etCaloriesPlus, etCaloriesMinus, etQuantityWater;
    EditText etWeightFood, etGender,etWeight,etAge,etHeight, etActivity;
    Button btnSaveOrUpdate;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_details);

        etDate = (EditText) findViewById(R.id.etDate);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etCaloriesPlus = (EditText) findViewById(R.id.etCaloriesPlus);
        etCaloriesMinus = (EditText) findViewById(R.id.etCaloriesMinus);
        etQuantityWater = (EditText) findViewById(R.id.etQuantityWater);
        etWeightFood = (EditText) findViewById(R.id.etWeightFood);
        etGender = (EditText) findViewById(R.id.etGender);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etActivity = (EditText) findViewById(R.id.etActivity);
        btnSaveOrUpdate = (Button) findViewById(R.id.btnSaveOrUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("_ID");

        if (id != null) {
            //init the dao:
            CalorieDAO dao = new CalorieDAO(this);
            //get the song from the dao by id
            Calorie c = dao.query(id);

            //fill the edittext using the calorie:
            etDate.setText(c.getDate());
            etHeight.setText(c.getHeight().toString());
            etCaloriesPlus.setText(c.getCaloriesplus().toString());
            etCaloriesMinus.setText(c.getCaloriesminus().toString());
            etQuantityWater.setText(c.getQuantitywater().toString());
            etWeightFood.setText((c.getWeightfood().toString()));
            etGender.setText(c.getGender().toString());
            etWeight.setText(c.getWeight().toString());
            etAge.setText(c.getAge().toString());
            etHeight.setText(c.getAge().toString());
            etActivity.setText(c.getActivity().toString());

        }

        btnSaveOrUpdate.setText(id != null ? "Update" : "Insert");
    }

    public void save(View view) {
       CalorieDAO dao = new CalorieDAO(this);

        Calorie c = new Calorie(etDate.getText().toString(),
                                  Double.valueOf(etCaloriesPlus.getText().toString()),
                                  Double.valueOf(etCaloriesMinus.getText().toString()),
                                  Double.valueOf(etQuantityWater.getText().toString()),
                                  Double.valueOf(etWeightFood.getText().toString()),
                                  Integer.valueOf(etGender.getText().toString()),
                                  Double.valueOf(etWeight.getText().toString()),
                                  Double.valueOf(etAge.getText().toString()),
                                  Double.valueOf(etHeight.getText().toString()),
                                  Integer.valueOf(etActivity.getText().toString())
                                  );
        //if (_id!=null) call update instead.
        if (id != null) {
            dao.update(id, c);
        } else {
            dao.insert(c);
        }

        Intent mainIntent = new Intent(this, CalorieRecyclerActivity.class);
        startActivity(mainIntent);

    }
}