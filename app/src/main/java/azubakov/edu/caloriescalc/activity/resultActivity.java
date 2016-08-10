package azubakov.edu.caloriescalc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import azubakov.edu.caloriescalc.R;

public class resultActivity extends AppCompatActivity {





    EditText etDate, etCaloriesPlus, etCaloriesMinus, etQuantityWater;
    EditText etWeightFood, etGender,etWeight,etAge,etHeight, etActivity;
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

    String data;
    Double calorieplus,calorieminus,quantitywater, weightfood, weight, height,age;
    Integer gender,activity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViews();
        //to take from intent
        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        String caloriesplusstring = intent.getStringExtra("caloriesplus");
        calorieplus = Double.valueOf(caloriesplusstring);
        String caloriesminusstring = intent.getStringExtra("caloriesminus");
        calorieminus = Double.valueOf(caloriesminusstring);
        String quantitywaterstring = intent.getStringExtra("quantitywater");
        quantitywater = Double.valueOf(quantitywaterstring);
        String weightfoodstring = intent.getStringExtra("weightfood");
        weightfood = Double.valueOf(weightfoodstring);
        String genderstring = intent.getStringExtra("gender");
        gender = Integer.valueOf(genderstring);
        String weightstring = intent.getStringExtra("weight");
        weight = Double.valueOf(weightstring);
        String agestring = intent.getStringExtra("age");
        age = Double.valueOf(agestring);
        String heightsring = intent.getStringExtra("height");
        height = Double.valueOf(heightsring);
        String activitysring = intent.getStringExtra("activity");
        activity = Integer.valueOf(activitysring);

        Calculate();

    }

    public void Calculate() {

        //Double Weight = getWeight();
        Double Weight = weight;
        //Double Height = getHeight();
        Double Height = height;

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

    }


    private double  HarrisBendyCalorOld()
    {
        Double HarBenCarOld = 0D;
        //Integer Gender = getGender();
        Integer Gender = gender;
        //Double Weight = getWeight();
        Double Weight = weight;
        //Double Age = getAge();
        Double Age = age;
        //Double Height = getHeight();
        Double Height = height;
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
        //Integer Gender = getGender();
        Integer Gender = gender;
        //Double Weight = getWeight();
        Double Weight = weight;
        //Double Age = getAge();
        Double Age = age;
        //Double Height = getHeight();
        Double Height = height;
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
        //Integer Gender = getGender();
        Integer Gender = gender;
        //Double Weight = getWeight();
        Double Weight = weight;
        //Double Age = getAge();
        Double Age = age;
        //Double Height = getHeight();
        Double Height = height;
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

    private Double getRealActivity()
    {
        Double RealActivity  = 0D;
        //String ActivityString = etActivity.getText().toString();
        //Integer Activity = Integer.valueOf(ActivityString);
        Integer Activity = activity;

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


    public void back(View view) {
        Intent intent = new Intent(this, CaloriesDetailsActivity.class);
        startActivity(intent);
    }
}
