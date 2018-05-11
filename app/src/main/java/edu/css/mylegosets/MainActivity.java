package edu.css.mylegosets;
/**
 * This is the main activity for the MyLegoSets app
 *
 * @author Jon Bernhardt
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSetDetail, btnDelete;          // two button widgets
    ListView listViewSets;                                  // listview to display all the sets in the database
    ArrayAdapter<LegoSet> legoSetAdapter;       //array adapter for sets
    List<LegoSet> setList;                      //list of sets
    int positionSelected;                       //list position selected
    LegoFirebaseData legoDataSource;            //connection to database
    DatabaseReference mySetDbRef;               //database reference for listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebaseDataChange();  //Set up Data change listener
        setupListView();            //Set up list view of sets
        setupAddButton();           //Add button
        setupDetailButton();        //Set detail button
        setupDeleteButton();        //Delete set button
    }

    /**
     * This sets up the listener for data change and pulls the data into the set list
     */
    private void setupFirebaseDataChange() {
        legoDataSource = new LegoFirebaseData();
        mySetDbRef = legoDataSource.open();
        mySetDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                setList = legoDataSource.getAllSets(dataSnapshot);
                legoSetAdapter = new LegoSetAdapter(MainActivity.this, android.R.layout.simple_list_item_single_choice, setList);
                listViewSets.setAdapter(legoSetAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * Defines the ListView and sets up item click listener for selection
     */
    private void setupListView() {
        listViewSets = (ListView) findViewById(R.id.lvSets);
        listViewSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
            }
        });
    }

    /**
     * Set up the button to add a new set using a seperate activity
     */
    private void setupAddButton() {
        btnAdd = (Button) findViewById(R.id.btnAddSet);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the add set activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), AddLegoSet.class);
                finish();
                startActivity(detailActIntent);
            }
        });
    }

    /**
     * Set up the button to display details on one set using a seperate activity
     */
    private void setupDetailButton() {
        btnSetDetail = (Button) findViewById(R.id.btnSetDetail);
        btnSetDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the set detail activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), LegoSetDetail.class);
                detailActIntent.putExtra("Set", setList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
    }

    /**
     * Set up the button to delete a set from the database and list
     */
    private void setupDeleteButton() {
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                legoDataSource.deleteSet(setList.get(positionSelected));
                legoSetAdapter.remove( setList.get(positionSelected) );
                legoSetAdapter.notifyDataSetChanged();
            }
        });
    }
}
