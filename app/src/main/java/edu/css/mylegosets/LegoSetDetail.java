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

/**
 * This gets and displays the selected set details in a detail activity
 *
 * @author Jon Bernhardt
 */

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

        //map layout elements
        ivSetImg = (ImageView) findViewById(R.id.ivSetImg);
        tvSetName = (TextView) findViewById(R.id.tvSetNameReturn);
        tvSetNum = (TextView) findViewById(R.id.tvSetNumReturn);
        tvSetTheme = (TextView) findViewById(R.id.tvThemeReturn);
        tvSetPieces = (TextView) findViewById(R.id.tvSetNumPiecesReturn);
        tvSetFigs = (TextView) findViewById(R.id.tvSetNumMiniFigsReturn);

        //set text and image for layout elements
        tvSetName.setText(legoSet.getSetName());
        tvSetNum.setText(legoSet.getSetNumber());
        tvSetTheme.setText(legoSet.getTheme());
        tvSetPieces.setText(legoSet.getNumPieces());
        tvSetFigs.setText(legoSet.getNumMiniFigs());
        imgURL = legoSet.getImgUrl();
        Picasso.get().load(imgURL).into(ivSetImg);      //plug in to set ImageView to source from URL

        /**
         * set up the back button listener to go to main activity
         */
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
