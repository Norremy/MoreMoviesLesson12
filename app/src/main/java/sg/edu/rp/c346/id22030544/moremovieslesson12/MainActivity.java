package sg.edu.rp.c346.id22030544.moremovieslesson12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btnFilter, btnRetrieve;
    private int myIntVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.ListView);
        btnFilter = findViewById(R.id.buttonFilter);
        btnRetrieve = findViewById(R.id.buttonRetrieve);

        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<Movie> movies = db.getMovies();
        db.close();

        CustomAdapter adapter = new CustomAdapter(this,
                R.layout.row, movies);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                adapter.clear();

                adapter.addAll(dbh.getMovies());


                adapter.notifyDataSetChanged();
                myIntVariable =0;


                lv.setAdapter(adapter);
                registerForContextMenu(lv);

            }});

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                ArrayList<Movie> movies = db.getMoviesfiltered();
                db.close();
                CustomAdapter adapter = new CustomAdapter(MainActivity.this,
                        R.layout.row, movies);
                lv.setAdapter(adapter);
                registerForContextMenu(lv);
                myIntVariable = 1;

            }
        });




    }
    // Implement onCreateOptionsMenu()
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Insert Movie");
        return super.onCreateOptionsMenu(menu);
    }

    // Implement onOptionsItemSelected()
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == 0) {
            Intent intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    // Implement onCreateContextMenu()
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"Edit");
        menu.add(0,1,1,"Delete");
    }

    // Implement onContextItemSelected()
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        int a = myIntVariable;
        Log.i("OnClick", "position = " + position);
        Log.i("OnClick", "position = " + a);

        if (itemID == 0) {
            if(myIntVariable == 0){
                DBHelper dbh = new DBHelper(MainActivity.this);

                ArrayList<Movie> movies = dbh.getMovies();
                Movie target = movies.get(position);


                Intent i = new Intent(MainActivity.this,
                        ModifyActivity.class);
                i.putExtra("data", target);
                startActivity(i);}
            else if (myIntVariable ==1) {
                DBHelper dbh = new DBHelper(MainActivity.this);

                ArrayList<Movie> movies = dbh.getMoviesfiltered();
                Movie target = movies.get(position);

                Intent i = new Intent(MainActivity.this,
                        ModifyActivity.class);
                i.putExtra("data", target);
                startActivity(i);

            }




        } else if (itemID == 1) {

            if(myIntVariable == 0){
                DBHelper dbh = new DBHelper(MainActivity.this);

                ArrayList<Movie> movies = dbh.getMovies();
                int id = movies.get(position).getId();
                dbh.deleteMovie(id);
                btnRetrieve.performClick();}
            else{DBHelper dbh = new DBHelper(MainActivity.this);

                ArrayList<Movie> movies = dbh.getMoviesfiltered();
                Movie target = movies.get(position);
                int id = target.getId();
                dbh.deleteMovie(id);
                btnRetrieve.performClick();}

        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        btnRetrieve.performClick();



    }
//


}