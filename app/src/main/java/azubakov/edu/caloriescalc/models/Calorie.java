package azubakov.edu.caloriescalc.models;

import java.util.Date;

import azubakov.edu.caloriescalc.db.CalorieDBHelper;

/**
 * Created by azubakov on 8/5/16.
 */
public class Calorie {
    private String id;
    private String date;
    private Double caloriesplus;
    private Double caloriesminus;
    private Double quantitywater;
    private Double weightfood;
    private Integer gender;
    private Double weight;
    private Double age;
    private Double height;
    private Integer activity;

    public Calorie(String date, Double caloriesplus, Double caloriesminus,
                   Double quantitywater, Double weightfood, Integer gender,
                   Double weight, Double age, Double height,
                   Integer activity) {
        this.date = date;
        this.caloriesplus = caloriesplus;
        this.caloriesminus = caloriesminus;
        this.quantitywater = quantitywater;
        this.weightfood = weightfood;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.activity = activity;
    }

    public Calorie(String id, String date, Double caloriesplus,
                   Double caloriesminus, Double quantitywater, Double weightfood,
                   Integer gender, Double weight, Double age, Double height,
                   Integer activity) {
        this.id = id;
        this.date = date;
        this.caloriesplus = caloriesplus;
        this.caloriesminus = caloriesminus;
        this.quantitywater = quantitywater;
        this.weightfood = weightfood;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCaloriesplus() {
        return caloriesplus;
    }

    public void setCaloriesplus(Double caloriesplus) {
        this.caloriesplus = caloriesplus;
    }

    public Double getCaloriesminus() {
        return caloriesminus;
    }

    public void setCaloriesminus(Double caloriesminus) {
        this.caloriesminus = caloriesminus;
    }

    public Double getQuantitywater() {
        return quantitywater;
    }

    public void setQuantitywater(Double quantitywater) {
        this.quantitywater = quantitywater;
    }

    public Double getWeightfood() {
        return weightfood;
    }

    public void setWeightfood(Double weightfood) {
        this.weightfood = weightfood;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Calorie{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", caloriesplus=" + caloriesplus +
                ", caloriesminus=" + caloriesminus +
                ", quantitywater=" + quantitywater +
                ", weightfood=" + weightfood +
                ", gender=" + gender +
                ", weight=" + weight +
                ", age=" + age +
                ", height=" + height +
                ", activity=" + activity +
                '}';
    }
}
