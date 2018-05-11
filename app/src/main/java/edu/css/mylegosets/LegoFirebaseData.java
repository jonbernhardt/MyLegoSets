package edu.css.mylegosets;

/**
 * this is the database connection, adds or removes a set and builds a list of lego sets based on
 * the data in the database
 *
 * @author Jon Bernhardt
 *
 */

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class LegoFirebaseData {
    public static final String LegoDataTag = "set";
    DatabaseReference setDatabase;

    /**
     * Get an instance of the database and a reference to the set data in it
     */
    public DatabaseReference open()  {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setDatabase = database.getReference(LegoDataTag);
        return setDatabase;
    }

    public void close() {

    }

    /**
     * create a key for a set entry in the data base and creates a new lego set object
     * stores the new lego set in the database
     */
    public LegoSet createSet( String setNum, String setName, String setTheme, String setPieces, String setImgURL, String setMiniFigs) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = setDatabase.child(LegoDataTag).push().getKey();
        // ---- set up the fish object
        LegoSet newSet = new LegoSet(key, setNum, setName, setTheme, setPieces, setImgURL, setMiniFigs);

        // ---- write the vote to Firebase
        setDatabase.child(key).setValue(newSet);
        return newSet;
    }

    /**
     *  searches for the set based on the key and removes the set from the database
     */
    public void deleteSet(LegoSet set) {
        String key = set.getKey();
        setDatabase.child(key).removeValue();

    }

    /**
     * builds a list of lego sets from the sets in the database
     */
    public List<LegoSet> getAllSets(DataSnapshot dataSnapshot) {
        List<LegoSet> setList = new ArrayList<LegoSet>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            LegoSet legoSet = data.getValue(LegoSet.class);
            setList.add(legoSet);
        }
        return setList;
    }
}
