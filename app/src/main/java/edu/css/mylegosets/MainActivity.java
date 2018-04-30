package edu.css.mylegosets;

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
    ListView listViewSets;                                  // listview to display all the fish in the database
    ArrayAdapter<LegoSet> legoSetAdapter;
    List<LegoSet> setList;
    int positionSelected;
    LegoFirebaseData legoDataSource;
    DatabaseReference mySetDbRef;
    LegoSet setSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebaseDataChange();
        setupListView();
        setupAddButton();
        setupDetailButton();
        setupDeleteButton();
    }

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

    private void setupListView() {
        listViewSets = (ListView) findViewById(R.id.lvSets);
        listViewSets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Lego Set selected at position " + positionSelected);
            }
        });
    }

    private void setupAddButton() {
        // Set up the button to add a new set using a seperate activity
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

    private void setupDetailButton() {
        // Set up the button to display details on one fish using a seperate activity
        btnSetDetail = (Button) findViewById(R.id.btnSetDetail);
        btnSetDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(view.getContext(), LegoSetDetail.class);
                detailActIntent.putExtra("Set", setList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
    }

    private void setupDeleteButton() {
        // Set up the button to display details on one fish using a seperate activity
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                legoDataSource.deleteSet(setList.get(positionSelected));
                legoSetAdapter.remove( setList.get(positionSelected) );
                legoSetAdapter.notifyDataSetChanged();
            }
        });
    }
}
