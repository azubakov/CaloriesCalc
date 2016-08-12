package azubakov.edu.caloriescalc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import azubakov.edu.caloriescalc.R;
import azubakov.edu.caloriescalc.adapters.CalorieAdapter;
import azubakov.edu.caloriescalc.db.CalorieDAO;

public class CalorieRecyclerActivity extends AppCompatActivity {


    private FloatingActionButton fab;
    CalorieDAO dao;
    RecyclerView recyclerView;

    //FloatingActionButton fabBack;

    @Override
    protected void onResume() {
        super.onResume();
        CalorieAdapter adapter = (CalorieAdapter) recyclerView.getAdapter();
        adapter.requery();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  Toast.makeText(CalorieRecyclerActivity.this, "It is from CalorieRecyclerActivity", Toast.LENGTH_SHORT).show();
        dao = new CalorieDAO(this);

        recyclerView = (RecyclerView) findViewById(R.id.calorieRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CalorieAdapter calorieAdapter = new CalorieAdapter(this);
        //recyclerView.setAdapter(new CalorieAdapter(this));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(CalorieRecyclerActivity.this, "sdf", Toast.LENGTH_SHORT).show();
                //remove from the arrayList
                //remove from the database
                //notify item removed(position)
                int position = viewHolder.getAdapterPosition();
            }
        });

        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(calorieAdapter);

        /*FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backDrawNav(View view);
            }
        });*/

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        if (id == R.id.action_logout) {
            //backDrawNav();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void gotoDetails(View view) {
        //Intent intent = new Intent(this, CaloriesDetailsActivity_old.class);
        Intent intent = new Intent(this, CaloriesDetailsBasicActivity.class);
        startActivity(intent);
    }

    /*public void backDrawNav(View view) {
        Intent intent = new Intent(this, CalorieNavDrawerActivity.class);
        startActivity(intent);
    }*/

    public void backDrNav(View view) {
        Intent intent = new Intent(this, CalorieNavDrawerActivity.class);
        startActivity(intent);

    }
}
