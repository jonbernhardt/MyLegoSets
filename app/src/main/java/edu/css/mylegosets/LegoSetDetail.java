package edu.css.mylegosets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LegoSetDetail extends AppCompatActivity {

    Button buttonBack;
    TextView tvSetName, tvSetNum, tvSetTheme, tvSetPieces, tvSetFigs;
    ImageView ivSetImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_detail);

        Bundle bundle = getIntent().getExtras();
        LegoSet legoSet = (LegoSet)   bundle.getSerializable("Set");

        // link each editText variable to the xml layout
        tvSetName = (TextView) findViewById(R.id.tvSetNameReturn);
        tvSetNum = (TextView) findViewById(R.id.tvSetNumReturn);
        tvSetTheme = (TextView) findViewById(R.id.tvThemeReturn);
        tvSetPieces = (TextView) findViewById(R.id.tvSetNumPiecesReturn);
        tvSetFigs = (TextView) findViewById(R.id.tvSetNumMiniFigsReturn);

        tvSetName.setText(LegoSet.getSetName());
        tvSetNum.setText(LegoSet.getSetNumber());
        tvSetTheme.setText(LegoSet.getTheme());
        tvSetPieces.setText(LegoSet.getNumPieces());
        tvSetFigs.setText(LegoSet.getNumMiniFigs());


        // set up the button listener
        buttonBack = (Button) findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

    }
}
