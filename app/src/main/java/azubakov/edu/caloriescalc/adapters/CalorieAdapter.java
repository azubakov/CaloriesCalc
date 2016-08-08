package azubakov.edu.caloriescalc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import azubakov.edu.caloriescalc.R;
import azubakov.edu.caloriescalc.activity.CaloriesDetailsActivity;
import azubakov.edu.caloriescalc.db.CalorieDAO;
import azubakov.edu.caloriescalc.models.Calorie;

/**
 * Created by azubakov on 8/8/16.
 */
public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.CalorieViewHolder>  {
    private final LayoutInflater inflater;
    private ArrayList<Calorie> calories;
    private Context context;
    private CalorieDAO dao;

    public CalorieAdapter(Context context)
    {
       this.context = context;
       //CalorieDAO dao = new CalorieDAO(context);
       //calories = dao.query();
        requery();
        inflater = LayoutInflater.from(context);


    }

    public void requery()
    {
        dao = new CalorieDAO(context);
        calories = dao.query();
    }


    @Override
    public CalorieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.calorie_item,parent,false);
        return new CalorieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalorieViewHolder holder, final int position) {
        final Calorie c = calories.get(position);

        holder.tvDate.setText(c.getDate());
        holder.tvWater.setText(String.format("Water: %.0f ml.",c.getQuantitywater()));
        holder.tvCalorieGet.setText(String.format("Cal+: %.2f cal.",c.getCaloriesplus()));
        holder.tvCalorieOut.setText(String.format("Cal-: %.2f cal.",c.getCaloriesminus()));
        holder.tvWeightFood.setText(String.format("Food: %.2f gr.",c.getWeightfood()));
        holder.tvWeight.setText(String.format("Weight: %.2f kg.",c.getWeight()));
        holder._ID = c.getId();

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete from ArrayList:
                calories.remove(c);
                //Delete from database:
                dao.delete(c.getId());
                //Notify the adapter:
                notifyItemRemoved(position);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CaloriesDetailsActivity.class);
                intent.putExtra("_ID",c.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return calories.size();
    }


    public class CalorieViewHolder extends RecyclerView.ViewHolder {

        ImageView ivDelete;
        TextView tvDate;
        TextView tvWater;
        TextView tvCalorieGet;
        TextView tvCalorieOut;
        TextView tvWeightFood;
        TextView tvWeight;
        RelativeLayout layout;
        String _ID;


        public CalorieViewHolder(View v) {
            super(v);

            tvDate = (TextView) v.findViewById(R.id.tvDate);
            tvWater = (TextView) v.findViewById(R.id.tvWater);
            tvCalorieGet = (TextView) v.findViewById(R.id.tvCalorieGet);
            tvCalorieOut = (TextView) v.findViewById(R.id.tvCalorieOut);
            tvWeightFood = (TextView) v.findViewById(R.id.tvWeightFood);
            tvWeight = (TextView) v.findViewById(R.id.tvWeight);
            layout = (RelativeLayout) v.findViewById(R.id.layout);
            ivDelete = (ImageView) v.findViewById(R.id.ivDelete);

        }
    }
}
