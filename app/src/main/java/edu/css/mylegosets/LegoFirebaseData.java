package edu.css.mylegosets;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class LegoFirebaseData {
    public static final String LegoDataTag = "set";
    DatabaseReference setDatabase;

    public DatabaseReference open()  {
        // Get an instance of the database and a reference to the fish data in it
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        setDatabase = database.getReference(LegoDataTag);
        return setDatabase;
    }

    public void close() {

    }

    public LegoSet createSet( String setNum, String setName, String setTheme, String setPieces, String setImgURL, String setMiniFigs) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = setDatabase.child(LegoDataTag).push().getKey();
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
        List<LegoSet> setList = new ArrayList<LegoSet>();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            LegoSet legoSet = data.getValue(LegoSet.class);
            setList.add(legoSet);
        }
        return setList;
    }
}
