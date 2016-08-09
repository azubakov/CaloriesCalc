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

        /*if (!isValidInputCaloriePlus())
            return;*/
              if (!isValidInputDate() ||
                !isValidInputCaloriePlus() || !isValidInputCalorieMinus() ||
                !isValidInputQuantityWater() || !isValidInputWeightFood() ||
                !isValidInputGender() || !isValidInputWeight() ||
                !isValidInputAge() ||  !isValidHeight() || !isValidInputActivity())
            return;

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


    private Integer getGender()
    {
        String GenderString = etGender.getText().toString();
        Integer Gender = Integer.valueOf(GenderString);
        return Gender;
    }


    private Double getWeight()
    {
        String WeightString = etWeight.getText().toString();
        Double Weight = Double.valueOf(WeightString);
        return Weight;
    }

    private Double getAge()
    {
        String AgeString = etAge.getText().toString();
        Double Age = Double.valueOf(AgeString);
        return Age;
    }

    private Double getHeight()
    {
        String HeightString = etHeight.getText().toString();
        Double Height = Double.valueOf(HeightString);
        return Height;
    }


    public String getDate()
    {
        String etDateString =  etDate.getText().toString();
        return etDateString;
    }



    public Double getCaloriePlus()
    {
        String etCaloriesPlusString =  etCaloriesPlus.getText().toString();
        Double CaloriesPlus = Double.valueOf(etCaloriesPlusString);
        return CaloriesPlus;
    }

    public Double getCalorieMinus()
    {
        String etCaloriesMinusString =  etCaloriesMinus.getText().toString();
        Double CaloriesMinus = Double.valueOf(etCaloriesMinusString);
        return CaloriesMinus;
    }

    public Double getQuantityWater()
    {
        String etQuantityWaterString =  etQuantityWater.getText().toString();
        Double QuantityWater = Double.valueOf(etQuantityWaterString);
        return QuantityWater;
    }

    public Double getWeightFood()
    {
        String etWeightFoodString =  etWeightFood.getText().toString();
        Double WeightFood = Double.valueOf(etWeightFoodString);
        return WeightFood;
    }

    public Integer getActivity()
    {
        String etCaloriesActivityString =  etActivity.getText().toString();
        Integer Activity = Integer.valueOf(etCaloriesActivityString);
        return Activity;
    }

    private boolean isValidInputDate() {
        boolean etDateValid = getDate().length() >= 2;

        if (!etDateValid)
            etDate.setError("Date must be at least 1 characters Long");

        return etDateValid;
    }

    private boolean isValidInputCaloriePlus() {

        String etCaloriesPlusString = etCaloriesPlus.getText().toString();
        boolean etCaloriesPlusValid = etCaloriesPlusString.length() >= 2;


        if (!etCaloriesPlusValid)
            etCaloriesPlus.setError("CaloriesPlus must be at least 1 characters Long");

        return etCaloriesPlusValid;
    }

    private boolean isValidInputCalorieMinus() {
        String etCaloriesMinusString = etCaloriesMinus.getText().toString();
        boolean etCaloriesMinusValid = etCaloriesMinusString.length() >= 2;


        if (!etCaloriesMinusValid)
            etCaloriesMinus.setError("CaloriesMinus must be at least 1 characters Long");

        return etCaloriesMinusValid;
    }



    private boolean isValidInputQuantityWater() {
        //boolean etQuantityWaterValid = getQuantityWater() >= 0;
        String etQuantityWaterString = etQuantityWater.getText().toString();
        boolean etQuantityWaterValid = etQuantityWaterString.length() >= 2;


        if (!etQuantityWaterValid)
            etQuantityWater.setError("Quantity water must be at least 1 characters Long");

        return etQuantityWaterValid;
    }



    private boolean isValidInputGender() {
        String etGenderString = etGender.getText().toString();
        boolean etGenderValid = etGenderString.length() == 1;


        if (!etGenderValid)
            etGender.setError("Gender must be exectly 1 characters Long from 1 to 2");

        return etGenderValid;
    }

    private boolean isValidInputWeight() {
        String etWeightString = etWeight.getText().toString();
        boolean etWeightValid = etWeightString.length() >= 2;

        if (!etWeightValid)
            etWeight.setError("Weight must be at least 1 characters Long");

        return etWeightValid;
    }

    private boolean isValidInputAge() {
        String etAgeString = etAge.getText().toString();
        boolean etAgeValid = etAgeString.length() >= 2;

        if (!etAgeValid)
            etAge.setError("Age must be at least 1 characters Long");

        return etAgeValid;
    }

    private boolean isValidHeight() {
        String etHeightString = etHeight.getText().toString();
        boolean etHeightValid = etHeightString.length() >= 2;

        if (!etHeightValid)
            etHeight.setError("Height must be at least 1 characters Long");

        return etHeightValid;
    }

    private boolean isValidInputActivity() {
        String etActivityString = etActivity.getText().toString();
        boolean etActivityValid = etActivityString.length() == 1;


        if (!etActivityValid)
            etActivity.setError("Activity must be exectly 1 characters Long  and from 1 to 5");

        return etActivityValid;
    }

    public boolean isValidInputWeightFood() {
        String etWeightFoodString = etWeightFood.getText().toString();
        boolean etWeightFoodValid = etWeightFoodString.length() >= 2;

        if (!etWeightFoodValid)
            etWeightFood.setError("Weight food must be at least 1 characters Long");

        return etWeightFoodValid;
    }


    public void NewActivity(View view) {
        Intent intent = new Intent(this, CalorieRecyclerActivity.class);
        startActivity(intent);
    }
}
