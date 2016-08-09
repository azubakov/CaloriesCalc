package azubakov.edu.caloriescalc.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import azubakov.edu.caloriescalc.R;
import azubakov.edu.caloriescalc.db.CalorieDAO;
import azubakov.edu.caloriescalc.models.Calorie;


public class MainActivity extends AppCompatActivity {


    EditText etDate;
    EditText etGender;
    EditText etWeight;
    EditText etAge;
    EditText etHeight;
    EditText etActivity;
    EditText etCaloriesPlus;
    EditText etCaloriesMinus;
    EditText etQuantityWater;
    EditText etWeightFood;
    TextView tvBmi;
    TextView tvResultBmi;
    TextView tvResult1DescriptionBmi;
    TextView tvHarBenCarOld;
    TextView tvResultHarBenCarOld;
    TextView tvHarBenCarNew;
    TextView tvResultHarBenCarNew;
    TextView tvMif;
    TextView tvResultMif;
    TextView tvAvg;
    TextView tvResAvr;
    TextView tvMin;
    TextView tvResMin;
    TextView tvMax;
    TextView tvResMax;
    TextView tvRecomend;
    TextView tvResRecomend;
    TextView tvRecomendExtra;
    TextView tvResRecomendExtra;

    RelativeLayout layer;
    //CalorieDAO dao;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Registration elements
        findViews();




    }

    public void Calculate(View view) {

        Double Weight = getWeight();
        Double Height = getHeight();

        //Calculate BMI
        Double bmi = CalulateBmi(Weight,Height);
        //Calculating according to method Harris-Benedict 1918-19
        Double HarBenCarOld = HarrisBendyCalorOld();
        //Calculating according to method Harris-Benedikt 1984
        Double HarBenCarNew = HarrisBendyCalorNew();
        //Calculating according to method Mifflin-SanGeor
        Double MiflinCar = MiffilinSanGeorCalor();
        //Average from 3 methods calories
        Double AvrCal = AvrCal(HarBenCarOld,HarBenCarNew,MiflinCar);
        //Minimum from 3 methods;
        Double MinCal = MinCal(HarBenCarOld,HarBenCarNew,MiflinCar);
        //Maximum from 3 methods;
        Double MaxCal = MaxCal(HarBenCarOld,HarBenCarNew,MiflinCar);
        //to Slim
        Double Slim = Slim(MinCal);
        //to extra slim
        Double ExtraSlim = ExtraSlim(MinCal);
        //to get fat
        Double Fat = Fat(MaxCal);
        //to get extrafat
        Double ExtraFat = ExtraFat(MaxCal);
        //Recomendation
        Double Recommend = Recommendation(bmi,Slim,Fat,AvrCal);
        //Recommendation Extra
        Double RecommendExtra = RecommendationExtra(bmi,ExtraSlim,ExtraFat,AvrCal);
        /*Toast.makeText(MainActivity.this, "The BMI is:" + bmi, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The HarBenCarOld is:" + HarBenCarOld, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The HarBenCarNew is:" + HarBenCarNew, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Miflin is:" + MiflinCar, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Average is:" + AvrCal, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Minimum is:" + MinCal, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Maximum is:" + MaxCal, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Slimming is:" + Slim, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Extraslimmimg is:" + ExtraSlim, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The fatning is:" + Fat, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The extrafattning is:" + ExtraFat, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The Recommend is:" + Recommend, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "The extrafattning is:" + RecommendExtra, Toast.LENGTH_SHORT).show();*/


    }


    private void findViews() {
        etDate = (EditText) findViewById(R.id.etDate);
        etGender = (EditText) findViewById(R.id.etGender);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etActivity = (EditText) findViewById(R.id.etActivity);
        etCaloriesPlus = (EditText) findViewById(R.id.etCaloriesPlus);
        etCaloriesMinus = (EditText) findViewById(R.id.etCaloriesMinus);
        etQuantityWater = (EditText) findViewById(R.id.etQuantityWater);
        etWeightFood = (EditText) findViewById(R.id.etWeightFood);
        tvBmi = (TextView) findViewById(R.id.tvBmi);
        tvResultBmi = (TextView) findViewById(R.id.tvResultBmi);
        tvResult1DescriptionBmi = (TextView) findViewById(R.id.tvResult1Bmi);
        tvHarBenCarOld = (TextView) findViewById(R.id.tvHarBenCarOld);
        tvResultHarBenCarOld = (TextView) findViewById(R.id.tvResultHarBenCarOld);
        tvHarBenCarNew = (TextView) findViewById(R.id.tvHarBenCarNew);
        tvResultHarBenCarNew = (TextView) findViewById(R.id.tvResultHarBenCarNew);
        tvMif = (TextView) findViewById(R.id.tvMif);
        tvResultMif = (TextView) findViewById(R.id.tvResultMif);
        tvAvg = (TextView) findViewById(R.id.tvAvg);
        tvResAvr = (TextView) findViewById(R.id.tvResAvr);
        tvMin = (TextView) findViewById(R.id.tvMin);
        tvResMin = (TextView) findViewById(R.id.tvResMin);
        tvMax = (TextView) findViewById(R.id.tvMax);
        tvResMax = (TextView) findViewById(R.id.tvResMax);
        tvRecomend = (TextView) findViewById(R.id.tvRecomend);
        tvResRecomend = (TextView) findViewById(R.id.tvResRecomend);
        tvRecomendExtra = (TextView) findViewById(R.id.tvRecomendExtra);
        tvResRecomendExtra = (TextView) findViewById(R.id.tvResRecomendExtra);

        layer = (RelativeLayout) findViewById(R.id.layer);
    }




    private double  HarrisBendyCalorOld()
    {
        Double HarBenCarOld = 0D;
        Integer Gender = getGender();
        Double Weight = getWeight();
        Double Age = getAge();
        Double Height = getHeight();
        Double RealActivity = getRealActivity();

        if (Gender == 1)
        {
            Double bmrOld = 66.4730 + (13.7516 * Weight) + (5.0033 * Height) - (6.7550 * Age);
            HarBenCarOld = bmrOld * RealActivity;
        }

        if (Gender == 2)
        {
            Double bmrOld = 665.0955+ (9.5634 * Weight) + (1.8496 * Height) - (4.6756 * Age);
            HarBenCarOld = bmrOld * RealActivity;
        }

        tvResultHarBenCarOld.setText(String.format("%.2f", HarBenCarOld));
        return HarBenCarOld;
    }

    private double HarrisBendyCalorNew() {
        Double HarBenCarNew = 0D;
        Integer Gender = getGender();
        Double Weight = getWeight();
        Double Age = getAge();
        Double Height = getHeight();
        Double RealActivity = getRealActivity();


        if (Gender == 1)
        {
            Double bmrNew = 88.362 + (13.397 * Weight) + (4.799 * Height) - (5.677 * Age);
            HarBenCarNew = bmrNew * RealActivity;
        }

        if (Gender == 2)
        {
            Double bmrNew = 447.593+ (9.247 * Weight) + (3.098 * Height) - (4.330 * Age);
            HarBenCarNew = bmrNew * RealActivity;
        }
        tvResultHarBenCarNew.setText(String.format("%.2f", HarBenCarNew));
        return HarBenCarNew;
    }

    private Double MiffilinSanGeorCalor() {

        Double MiflinCar = 0D;
        Integer Gender = getGender();
        Double Weight = getWeight();
        Double Age = getAge();
        Double Height = getHeight();
        Double RealActivity = getRealActivity();

        if (Gender == 1)
        {
            Double bmrMiflin = ((10 * Weight) + (6.25 * Height) - (5 * Age)) + 5;
            MiflinCar = bmrMiflin * RealActivity;
        }

        if (Gender == 2)
        {
            Double bmrMiflin = ((10 * Weight) + (6.25 * Height) - (5 * Age)) - 161;;
            MiflinCar = bmrMiflin * RealActivity;
        }
        tvResultMif.setText(String.format("%.2f", MiflinCar));
        return MiflinCar;
    }


    private double CalulateBmi(Double Weight, Double Height)
    {
        String s1 = "";
        Double bmi = Weight / Math.pow((Height/100),2);
        tvResultBmi.setText(String.format("%.2f", bmi));
        if (bmi < 15) {
             s1 = "  Dystrophy - ";
        }
        else if (bmi > 15 && bmi < 20){
             s1 = "  Deficit of weight - ";
        }
        else if (bmi > 20 && bmi < 25){
             s1 = "  Normal weight - ";
        }
        else if (bmi > 25 && bmi < 30){
             s1 = "  Overweight - ";
        }
        else if (bmi > 30){
             s1 = "  Obesity - ";
        }
        tvResult1DescriptionBmi.setText(String.format("%.2f", bmi));
        tvResultBmi.setText(String.format("%s", s1));
            return bmi;
    }


    private double Recommendation(Double bmi,Double Slim,Double Fat, Double AvgCal)
    {
        double CalPerDay = 0D;

        if (bmi < 15) {
            //s1 = "  Dystrophy";
            CalPerDay = Fat;
        }
        else if (bmi > 15 && bmi < 20){
            //s1 = "  Deficit of weight";
            CalPerDay = Fat;
        }
        else if (bmi > 20 && bmi < 25){
            //s1 = "  Normal weight";
            CalPerDay = AvgCal;
        }
        else if (bmi > 25 && bmi < 30){
            //s1 = "  Overweight";
            CalPerDay = Slim;
        }
        else if (bmi > 30){
            //s1 = "  Obesity";
            CalPerDay = Slim;
        }

        tvResRecomend.setText(String.format("%.2f", CalPerDay));
        return CalPerDay;
    }

    private double RecommendationExtra(Double bmi,Double ExtraSlim,Double ExtraFat, Double AvgCal)
    {
        double CalPerDayExtra = 0D;

        if (bmi < 15) {
            //s1 = "  Dystrophy";
            CalPerDayExtra = ExtraFat;
        }
        else if (bmi > 30){
            //s1 = "  Obesity";
            CalPerDayExtra = ExtraSlim;
        }

        tvResRecomendExtra.setText(String.format("%.2f", CalPerDayExtra));;
        return CalPerDayExtra;
    }


    private Double AvrCal(Double HarBenCarOld,Double HarBenCarNew,Double MiflinCar) {

        Double AvrCal = (HarBenCarOld + HarBenCarNew + MiflinCar) / 3;
        tvResAvr.setText(String.format("%.2f",AvrCal));
        return AvrCal;

    }


    private Double MinCal(Double HarBenCarOld,Double HarBenCarNew,Double MiflinCar)
    {
        double smallest = HarBenCarOld;
        if (smallest > HarBenCarNew)
            smallest = HarBenCarNew;
        if (smallest > MiflinCar)
            smallest = MiflinCar;

        tvResMin.setText(String.format("%.2f",smallest));

        return smallest;
    }

    private Double MaxCal(Double HarBenCarOld,Double HarBenCarNew,Double MiflinCar)
    {
        double largest = HarBenCarOld;
        if (largest < HarBenCarNew)
            largest = HarBenCarNew;
        if (largest < MiflinCar)
            largest = MiflinCar;

        tvResMax.setText(String.format("%.2f",largest));


        return largest;
    }

    private Double Slim(Double MinCal)
    {
        Double Slim = MinCal - (0.2 * MinCal);
        return Slim;
    }

    private Double ExtraSlim(Double MinCal)
    {
        Double ExtraSlim = MinCal - (0.4 * MinCal);
        return ExtraSlim;
    }

    private Double Fat(Double MaxCal)
    {
        Double  Fat= MaxCal + (0.2 * MaxCal);
        return Fat;
    }

    private Double ExtraFat(Double MaxCal)
    {
        Double ExtraFat = MaxCal - (0.4 * MaxCal);
        return ExtraFat;
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


    private Double getRealActivity()
    {
        Double RealActivity  = 0D;
        String ActivityString = etActivity.getText().toString();
        Integer Activity = Integer.valueOf(ActivityString);

        if (Activity == 1)
        {
            RealActivity = 1.2;
        }
        else if (Activity == 2)
        {
            RealActivity = 1.375;
        }
        else if (Activity == 3)
        {
            RealActivity = 1.55;
        }
        else if (Activity == 4)
        {
            RealActivity = 1.725;
        }
        else if (Activity == 5)
        {
            RealActivity = 1.9;
        }

        return RealActivity;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calorie_db, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void Insert(View view) {


       // Toast.makeText(MainActivity.this, "stam", Toast.LENGTH_SHORT).show();
        if (!isValidInputDate() ||
                !isValidInputCaloriePlus() || !isValidInputCalorieMinus() ||
                !isValidInputQuantityWater() || !isValidInputWeightFood() ||
                !isValidInputGender() ||!isValidInputWeight() ||
                !isValidInputAge() || !isValidHeight() || !isValidInputActivity())
            return;

        Calorie calorie = new Calorie(getDate(),
                                      getCaloriePlus(), getCalorieMinus(),getQuantityWater(),
                                      getWeightFood(), getGender(), getWeight(),
                                      getAge(), getHeight(), getActivity());

        CalorieDAO dao = new CalorieDAO(this);
        long insertedID = dao.insert(calorie);

        Toast.makeText(MainActivity.this, "" + insertedID, Toast.LENGTH_SHORT).show();
        clearEditexts();
    }

    private void clearEditexts()
    {
        etDate.setText("");
        etCaloriesPlus.setText("");
        etCaloriesMinus.setText("");
        etQuantityWater.setText("");
        etWeightFood.setText("");
        etGender.setText("");
        etWeight.setText("");
        etAge.setText("");
        etHeight.setText("");
        etActivity.setText("");
    }

    public void Query(View view) {
        CalorieDAO dao = new CalorieDAO(this);
        ArrayList<Calorie> calories = dao.query();
        for (Calorie c : calories) {
            Toast.makeText(MainActivity.this, c.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void update(View view) {

     //   Toast.makeText(MainActivity.this, "stam"  , Toast.LENGTH_SHORT).show();
        /*Calorie calorie = new Calorie(getDate(), getFirstName(),getSecondtName(), getCaloriePlus());*/

        if (!isValidInputDate() ||
                !isValidInputCaloriePlus() || !isValidInputCalorieMinus() ||
                !isValidInputQuantityWater() || !isValidInputGender() ||
                !isValidInputWeight() || !isValidInputAge() ||
                !isValidHeight() || !isValidInputActivity())
            return;

          Calorie calorie = new Calorie(getDate(),
                  getCaloriePlus(), getCalorieMinus(),getQuantityWater(),
                  getWeightFood(),  getGender(), getWeight(),
                  getAge(), getHeight(), getActivity());

        CalorieDAO dao = new CalorieDAO(this);
        int rowsAffected = dao.update("2", calorie);
        Toast.makeText(MainActivity.this, ""  + rowsAffected, Toast.LENGTH_SHORT).show();
        clearEditexts();
    }


    public void Delete(View view) {
        CalorieDAO dao = new CalorieDAO(this);
        int rowsAffected = dao.delete("3");
        Toast.makeText(MainActivity.this, "" + rowsAffected, Toast.LENGTH_SHORT).show();
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
