package edu.css.mylegosets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncDownloadXML extends AsyncTask<AddLegoSet, String, String> {

    AddLegoSet actLegoSet;

    @Override
    protected String doInBackground(AddLegoSet... new_actLegoSet) {
        try {
            Log.v("Lego","AsyncDownloadXML doInBackground");

            // Save a pointer to the main Weather Activity which is passed in as a parameter
            actLegoSet = new_actLegoSet[0];

            // create the XML Pull Parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            //String  weatherStrURL =  "http://w1.weather.gov/xml/current_obs/KDLH.xml";
            String queryString = "https://brickset.com/api/v2.asmx/getSets?apiKey=VQCb-PEVh-NM7r&userHash=&query=" + actLegoSet.etQuery.getText().toString() + "&theme=&subtheme=&setNumber=&year=&owned=&wanted=&orderBy=&pageSize=&pageNumber=&userName=";
            URL queryURL =  new URL(queryString);

            InputStream stream = queryURL.openStream();
            xpp.setInput(stream, null);
            int eventType = xpp.getEventType();

            String setName = "Updaing...";			// Temperature Update String
            String setNumber = "Updaing...";
            String setTheme = "Updaing...";
            String setImg = "Updaing...";
            String setPieces= "Updaing...";// Wind Update String
            String setMiniFigs =  "0";        // Visibility Update String
            publishProgress(setNumber, setName, setTheme, setPieces, setImg, setMiniFigs);

            while (eventType != XmlPullParser.END_DOCUMENT) {

                // look for a start tag
                if(eventType == XmlPullParser.START_TAG) {
                    //Log.v("Gibbons","Start tag found");
                    // get the tag name and process it
                    String tag = xpp.getName();
                    if (tag.equals("number")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setNumber = xpp.getText();
                        //Log.v("Gibbons","Wind MPH" +windStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("name")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setName = xpp.getText();
                        //Log.v("Gibbons","Temp" + tempStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("theme")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setTheme = xpp.getText();
                        //Log.v("Gibbons","Vis" + visStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("pieces")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setPieces = xpp.getText();
                        //Log.v("Gibbons","Wind MPH" +windStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("minifigs")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setMiniFigs = xpp.getText();
                        //Log.v("Gibbons","Vis" + visStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("thumbnailURL")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setImg = xpp.getText();
                        //Log.v("Gibbons","Temp" + tempStr);
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }

                }
                eventType = xpp.next();
            }
            return "Successfully updated weather";

        } catch (IOException e) {
            //Log.v("Gibbons","AsyncDownloadXML doInBackground IOException");
            //Log.v("Gibbons",e.getMessage());
            return(e.getMessage());
        } catch (XmlPullParserException e) {
            //Log.v("Gibbons","AsyncDownloadXML doInBackground XmlPullParserException");
            //Log.v("Gibbons",e.getMessage());
            return(e.getMessage());
        }  catch (Exception e) {
            //Log.v("Gibbons","AsyncDownloadXML doInBackground Exception");
            //Log.v("Gibbons",e.getMessage());
            return(e.getMessage());
        }

    }

    @Override
    protected void onProgressUpdate(String... update) {
        //Log.v("Gibbons","in onProgressUpdate");
        actLegoSet.setSetNumber(update[0]);
        actLegoSet.setSetName(update[1]);
        actLegoSet.setSetTheme(update[2]);
        actLegoSet.setSetPieces(update[3]);
        actLegoSet.setSetFigs(update[4]);
        actLegoSet.setSetImg(update[5]);
    }

    @Override
    protected void onPostExecute(String result) {
        //Log.v("Gibbons","in onPostExecute");
        actLegoSet.setStatus(result);
    }




}
