//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//                              QUIZ 5: API PARSING
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
package com.company;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
//------------------Class setup------------------------------------
public class CovidRequest
{
    public static final String covidURL = "https://covid2019-api.herokuapp.com/v2/current/US";
    public URL url;
    public String str, line, state;
    public String date;
    public int death, confirmed;

    //------------------Class constructor-----------------------------------------------------
    public CovidRequest()
    {
        //setup of the CovidRequest object
        url = null;
        line = null;
        state = "";
        date = "";
        str = "";
        confirmed = 0;
        death = 0;
    }

    public CovidDataStorage makeRequest()
    {
        //making a CovidDataStorage object to be returned, this will hold all of the parsed data
        CovidDataStorage today = new CovidDataStorage();
        //making the API request
        try
        {
            url = new URL("https://covid2019-api.herokuapp.com/v2/current/US");
            URLConnection myUrlConnection = url.openConnection();

            java.io.InputStream myInputStream = myUrlConnection.getInputStream();

            InputStreamReader myInputStreamReader;
            myInputStreamReader = new InputStreamReader(myInputStream);

            BufferedReader in = new BufferedReader(myInputStreamReader);

            while ((line = in.readLine()) != null)
            {
                str += line;
            }

//--------------------PARSING------------------------------------------
            //first getting a JSONobject for the entire returned JSON file saved in "str"
            JSONObject rtrn = new JSONObject(str);

            //obtaining the data JSON array
            JSONArray data = rtrn.getJSONArray("data");

            //for the entire list of data (which contains all states and provinces)
            //1. get the province or state name
            //2. get the number of confirmed cases associated with the province/state
            //3. get the number of deaths associated with the province/state
            //4. get the date of the request on the API
            //5. ensure that the correct deaths/cases are saved in accordance to the state
            for (int i = 0; i < data.length(); i++)
            {
                JSONObject element = data.getJSONObject(i);
                state = element.getString("Province_State");
                today.states.add(state);
                confirmed = element.getInt("Confirmed");
                today.confirmeds.add(confirmed);
                death = element.getInt("Deaths");
                today.deaths.add(death);
                System.out.println("");
            }
            date = rtrn.getString("dt");
            today.date = date;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return(today);
    }
}