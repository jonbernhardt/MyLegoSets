package edu.css.mylegosets;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LegoFirebaseData {
    public static final String LegoDataTag = "Set Data";
    private DatabaseReference setDatabase = open();

    public DatabaseReference open()  {
        // Get an instance of the database and a reference to the fish data in it
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("set");
        return myRef;
    }

    public void close() {

    }

    public LegoSet createSet( String setNum, String setName, String setTheme, String setPieces, String setImgURL, String setMiniFigs) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = setDatabase.push().getKey();
        // ---- set up the fish object
        LegoSet newSet = new LegoSet(key, setNum, setName, setTheme, setPieces, setImgURL, setMiniFigs);

        // ---- write the vote to Firebase
        setDatabase.child(key).setValue(newSet);
        return newSet;
    }


    public void deleteSet(LegoSet set) {
        String key = set.getKey();
        setDatabase.child(key).removeValue();

    }

    public List<LegoSet> getAllSets(DataSnapshot dataSnapshot) {
        List<LegoSet> setList = null;
        String key = "0";
        while (setDatabase.child(key) != null){
            LegoSet set = dataSnapshot.getValue(LegoSet.class);

        }
        return setList;
    }
}
