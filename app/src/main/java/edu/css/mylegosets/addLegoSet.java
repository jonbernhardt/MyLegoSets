package edu.css.mylegosets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class addLegoSet extends AppCompatActivity {

    Button btnSave, btnGetSet;
    EditText etQuery;
    TextView tvSetNum, tvSetName, tvSetPieces, tvSetTheme, tvSetFigs;
    ImageView ivSetImg;

    LegoFirebaseData legoDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_set_activity);

        // link each editText variable to the xml layout
        tvSetNum = (TextView) findViewById(R.id.tvSetNumDetail);
        tvSetName = (TextView) findViewById(R.id.tvSetNameReturn);
        tvSetTheme = (TextView) findViewById(R.id.tvThemeReturn);
        tvSetPieces = (TextView) findViewById(R.id.tvSetNumPiecesReturn);
        tvSetFigs = (TextView) findViewById(R.id.tvSetNumMiniFigsReturn);
        ivSetImg = (ImageView) findViewById(R.id.ivSetImg);

        legoDataSource = new LegoFirebaseData();
        legoDataSource.open();

        // add find button listener and code

        // set up the button listener
        btnSave = (Button) findViewById(R.id.btnAddSet);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add the fish to the database
                String species = editTextSpecies.getText().toString();
                String weight = editTextWeight.getText().toString();
                String dateCaught = editTextDate.getText().toString();
                fishDataSource.createFish(species, weight, dateCaught);
                //fishDataSource.createFish(species, weight, dateCaught, lattitude.toString(), longiture.toString());
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
}
