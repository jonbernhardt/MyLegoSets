package edu.css.mylegosets;

/**
 * Created by M069642 on 4/25/2018.
 */

public class LegoSet {

    private String setNumber;
    private String setName;
    private String theme;
    private String numPieces;

    public LegoSet () {

    }

    public LegoSet (String setNumber, String setName, String theme, String numPieces) {
        this.setNumber = setNumber;
        this.setName = setName;
        this.theme = theme;
        this.numPieces = numPieces;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(String numPieces) {
        this.numPieces = numPieces;
    }

    @Override
    public String toString() {
        return "LegoSet{" +
                "setNumber='" +  setNumber + '\'' +
                ", setName='" + setName + '\'' +
                ", theme='" + theme + '\'' +
                ", numPieces='" + numPieces + '\'' +
                '}';
    }
}
