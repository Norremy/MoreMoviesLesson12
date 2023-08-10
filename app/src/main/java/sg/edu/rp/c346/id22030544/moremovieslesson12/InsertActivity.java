package sg.edu.rp.c346.id22030544.moremovieslesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InsertActivity extends AppCompatActivity {
EditText etTitle, etGenre, etYear;
Button btnInsert, btnShow;
Spinner spnRating;
    String rating = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btnInsert  = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShowList);
        etGenre = findViewById(R.id.editTextInsertGenre);
        etTitle = findViewById(R.id.editTextInsertTitle);
        etYear = findViewById(R.id.editTextInsertYear);
        spnRating = findViewById(R.id.spinnerInsertRating);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
//                Log.i("MyActivity", year );

                DBHelper db = new DBHelper(InsertActivity.this);


                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                String rating = spnRating.getSelectedItem().toString();

                db.insertMovie(title, genre, rating, year);



//                spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                        rating = parentView.getItemAtPosition(position).toString();
//                        // Use the selectedEntry in your application logic or store it in a variable
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parentView) {
//                        // Do nothing
//                    }
//                });






                Log.i("InsertAct", rating);
                db.close();
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}