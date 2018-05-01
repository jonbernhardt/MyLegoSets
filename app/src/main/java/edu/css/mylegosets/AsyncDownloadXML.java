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

            // Save a pointer to the main Lego Set Activity which is passed in as a parameter
            actLegoSet = new_actLegoSet[0];

            // create the XML Pull Parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

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
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("name")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setName = xpp.getText();
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("theme")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setTheme = xpp.getText();
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("pieces")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setPieces = xpp.getText();
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("minifigs")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setMiniFigs = xpp.getText();
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }
                    if (tag.equals("imageURL")){
                        eventType = xpp.next(); 		// go to next element which should be the text
                        setImg = xpp.getText();
                        publishProgress(setNumber,setName, setTheme, setPieces, setMiniFigs, setImg);	// Update the display
                    }

                }
                eventType = xpp.next();
            }
            return "Successfully located Lego Set";

        } catch (IOException e) {
            return(e.getMessage());
        } catch (XmlPullParserException e) {
            return(e.getMessage());
        }  catch (Exception e) {
            return(e.getMessage());
        }

    }

    @Override
    protected void onProgressUpdate(String... update) {
        actLegoSet.setSetNumber(update[0]);
        actLegoSet.setSetName(update[1]);
        actLegoSet.setSetTheme(update[2]);
        actLegoSet.setSetPieces(update[3]);
        actLegoSet.setSetFigs(update[4]);
        actLegoSet.setSetImg(update[5]);
    }

    @Override
    protected void onPostExecute(String result) {
        actLegoSet.setStatus(result);
    }




}
