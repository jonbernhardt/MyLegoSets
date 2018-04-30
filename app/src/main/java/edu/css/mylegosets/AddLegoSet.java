package edu.css.mylegosets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class AddLegoSet extends AppCompatActivity {

    Button btnSave, btnGetSet, buttonBack;
    EditText etQuery;
    TextView tvSetNum, tvSetName, tvSetPieces, tvSetTheme, tvSetFigs;
    ImageView ivSetImg;
    String imgURL;

    LegoFirebaseData legoDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_set_activity);

        // link each editText variable to the xml layout
        tvSetNum = (TextView) findViewById(R.id.tvSetNumReturn);
        tvSetName = (TextView) findViewById(R.id.tvSetNameReturn);
        tvSetTheme = (TextView) findViewById(R.id.tvThemeReturn);
        tvSetPieces = (TextView) findViewById(R.id.tvSetNumPiecesReturn);
        tvSetFigs = (TextView) findViewById(R.id.tvSetNumMiniFigsReturn);
        ivSetImg = (ImageView) findViewById(R.id.ivSetImg);
        etQuery = (EditText) findViewById(R.id.etSetNumber);
        legoDataSource = new LegoFirebaseData();
        legoDataSource.open();



        // set up the button listener
        btnSave = (Button) findViewById(R.id.btnAddSet);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add the fish to the database
                String setNumber = tvSetNum.getText().toString();
                String setName = tvSetName.getText().toString();
                String setTheme = tvSetTheme.getText().toString();
                String setPiecesNumber = tvSetPieces.getText().toString();
                String setImgUrl = imgURL;
                String setFigsNumber = tvSetFigs.getText().toString();
                legoDataSource.createSet(setNumber, setName, setTheme, setPiecesNumber, setImgUrl, setFigsNumber);
                Intent mainActIntent = new Intent(view.getContext(), MainActivity.class);
                finish();
                startActivity(mainActIntent);
            }
        });

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

    // add find button listener and code

    //public void btnClick (View v) throws XmlPullParserException, ClientProtocolException, URISyntaxException, IOException {
    public void btnSearchClick (View v) throws XmlPullParserException, URISyntaxException, IOException {


        new AsyncDownloadXML().execute(this);


    }
    public void setSetNumber(String newNum) {
        tvSetNum.setText(newNum);
    }

    public void setSetName(String newName) {
        tvSetName.setText(newName);
    }

    public void setSetTheme(String newTheme) {
        tvSetTheme.setText(newTheme);
    }
    public void setSetPieces(String newPieces) {
        tvSetPieces.setText(newPieces);
    }

    public void setSetFigs(String newFigs) {
            tvSetFigs.setText(newFigs);
    }

    public void setSetImg(String newImg) {
        imgURL = newImg;
        Picasso.get().load(imgURL).into(ivSetImg);
    }


    public void setStatus(String newStatus) {
        Toast toast=Toast.makeText(getApplicationContext(), newStatus, Toast.LENGTH_LONG );
        toast.show();
    }
}
