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

/**
 * This searches for and displays the selected set to add in the add set activity
 *
 * @author Jon Bernhardt
 */

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

        /**
         * set up the button listener for adding a set to the data base and returns to the main
         * activity
         */
        //
        btnSave = (Button) findViewById(R.id.btnAddSet);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Add the set to the database
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

        /**
         * set up the button listener for the back button to return to the main activity
         * without adding a set to the database
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

    /**
     * set up the button listener to search for a lego set using the users input in search text box
     */
    public void btnSearchClick (View v) throws XmlPullParserException, URISyntaxException, IOException {
        new AsyncDownloadXML().execute(this);
    }

    /**
     *  assigns the TextView text to display the results of the API query call
     */
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

    /**
     * assigns the ImageView to display the results of the API query call
     * this utilizes a Picasso plug in to set the ImageView using a image URL
     */
    public void setSetImg(String newImg) {
        imgURL = newImg;
        Picasso.get().load(imgURL).into(ivSetImg);
    }

    /**
     * creates a toast message to be displayed status of search
     */
    public void setStatus(String newStatus) {
        Toast toast=Toast.makeText(getApplicationContext(), newStatus, Toast.LENGTH_LONG );
        toast.show();
    }
}
