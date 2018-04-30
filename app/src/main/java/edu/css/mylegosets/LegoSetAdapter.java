package edu.css.mylegosets;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LegoSetAdapter extends ArrayAdapter<LegoSet> {

    private List<LegoSet> setList;            // The list of set to display
    private Context context;                // The original activity that displays this
    private int layoutResource;

    public LegoSetAdapter(Context context, int resource, List<LegoSet> setList) {
        super(context, resource, setList);
        this.context = context;
        this.layoutResource = resource;
        this.setList = setList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        LegoSet legoSet = setList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.fish_row_layout, null);
        view = inflater.inflate(R.layout.lego_list_row, null);

        ImageView ivSetImg=(ImageView)view.findViewById(R.id.ivSetImg);
        TextView tvSetName=(TextView)view.findViewById(R.id.tvSetName);
        TextView tvSetNum=(TextView)view.findViewById(R.id.tvSetNum);
        TextView tvSetTheme=(TextView)view.findViewById(R.id.tvTheme);
        TextView tvSetPieces=(TextView)view.findViewById(R.id.tvSetNumPieces);
        TextView tvSetMiniFigs=(TextView)view.findViewById(R.id.tvSetNumMiniFigs);
        Picasso.get().load(legoSet.getImgUrl()).into(ivSetImg);
        tvSetName.setText(legoSet.getSetName());
        tvSetNum.setText(legoSet.getSetNumber());
        tvSetTheme.setText(legoSet.getTheme());
        tvSetPieces.setText(legoSet.getNumPieces());
        tvSetMiniFigs.setText(legoSet.getNumMiniFigs());


        return(view);
    }
}
