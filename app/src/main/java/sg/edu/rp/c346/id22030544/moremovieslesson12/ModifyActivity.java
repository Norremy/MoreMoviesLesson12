package sg.edu.rp.c346.id22030544.moremovieslesson12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {
    EditText etTitle, etGenre, etYear;
    Button btnDelete, btnUpdate, btnCancel;
    Spinner spnRating;
    TextView tvID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        etGenre = findViewById(R.id.editTextGenre);
        spnRating  = findViewById(R.id.spinnerRating);
        etTitle = findViewById(R.id.editTextTitle);
        etYear = findViewById(R.id.editTextYear);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        btnUpdate = findViewById(R.id.buttonUpdate);
        tvID = findViewById(R.id.textViewID);

        Intent i = getIntent();
//        int receivedValue = i.getIntExtra("pos", 1);
        Movie data = (Movie) i.getSerializableExtra("data");

        DBHelper db = new DBHelper(ModifyActivity.this);
        ArrayList<Movie> songs = db.getMovies();
        db.close();
        tvID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(Integer.toString(data.getYear()));
        String rating = data.getRating();
        Log.i("main", rating);
        if(rating.equals("PG13")){
            spnRating.setSelection(0);
        }
        else if(rating.equals("NC16")){
            spnRating.setSelection(1);
        }
        else if(rating.equals("M18")) {
            spnRating.setSelection(2);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(spnRating.getSelectedItem().toString());

                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + data.getTitle());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DELETEE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = data.getId();
                        DBHelper dbh = new DBHelper(ModifyActivity.this);
                        dbh.deleteMovie(id);
                        Toast.makeText(getApplicationContext(), "Movie has been deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Movie not deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to disacard changes " );
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = data.getId();
                        Toast.makeText(getApplicationContext(), "CHANGES DISCARD", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                myBuilder.setNegativeButton("DO NOT DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}