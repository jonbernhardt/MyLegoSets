package edu.css.mylegosets;


import java.io.Serializable;

public class LegoSet implements Serializable {

    private String setNumber;
    private String setName;
    private String theme;
    private String numPieces;
    private String imgUrl;
    private String numMiniFigs;

    public LegoSet () {

    }

    public LegoSet (String setNumber, String setName, String theme, String numPieces, String imgUrl, String numMiniFigs) {
        this.setNumber = setNumber;
        this.setName = setName;
        this.theme = theme;
        this.numPieces = numPieces;
        this.imgUrl = imgUrl;
        this.numMiniFigs = numMiniFigs;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNumMiniFigs() {
        return numMiniFigs;
    }

    public void setNumMiniFigs(String numMiniFigs) {
        this.numMiniFigs = numMiniFigs;
    }

    @Override
    public String toString() {
        return "LegoSet{" +
                "setNumber='" +  setNumber + '\'' +
                ", setName='" + setName + '\'' +
                ", theme='" + theme + '\'' +
                ", numPieces='" + numPieces + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", numMiniFigs='" + numMiniFigs + '\'' +
                '}';
    }
}
