package edu.css.mylegosets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LegoSetDetail extends AppCompatActivity {

    Button buttonBack;
    TextView tvSetName, tvSetNum, tvSetTheme, tvSetPieces, tvSetFigs;
    ImageView ivSetImg;
    String imgURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_detail);

        Bundle bundle = getIntent().getExtras();
        LegoSet legoSet = (LegoSet)   bundle.getSerializable("Set");

        // link each editText variable to the xml layout
        ivSetImg = (ImageView) findViewById(R.id.ivSetImg);
        tvSetName = (TextView) findViewById(R.id.tvSetNameReturn);
        tvSetNum = (TextView) findViewById(R.id.tvSetNumReturn);
        tvSetTheme = (TextView) findViewById(R.id.tvThemeReturn);
        tvSetPieces = (TextView) findViewById(R.id.tvSetNumPiecesReturn);
        tvSetFigs = (TextView) findViewById(R.id.tvSetNumMiniFigsReturn);

        tvSetName.setText(legoSet.getSetName());
        tvSetNum.setText(legoSet.getSetNumber());
        tvSetTheme.setText(legoSet.getTheme());
        tvSetPieces.setText(legoSet.getNumPieces());
        tvSetFigs.setText(legoSet.getNumMiniFigs());
        imgURL = legoSet.getImgUrl();
        Picasso.get().load(imgURL).into(ivSetImg);


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
