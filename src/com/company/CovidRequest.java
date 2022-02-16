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
    private URL url;
    private String str, line, state;
    public String date;
    private int death, confirmed;
    private ArrayList<String> states = new ArrayList<String>();
    private ArrayList<Integer> deaths = new ArrayList<Integer>();
    private ArrayList<Integer> confirmeds = new ArrayList<Integer>();
//------------------Class constructor-----------------------------------------------------
//creates an object holding three ArrayLists, one for all states/provinces, one for all deaths, one for all confirmed cases
    public CovidRequest()
    {
        //setup of the CovidRequest object, contains all data on all states and provinces
        url = null;
        line = null;
        state = "";
        date = "";
        str = "";
        confirmed = 0;
        death = 0;
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
                //System.out.println(line);
            }

//--------------------PARSING------------------------------------------
            //first getting a JSONobject for the entire returned JSON file saved in "str"
            JSONObject rtrn = new JSONObject(str);

            //testing to see if all the data correctly made it into the str String
            //System.out.println(str);

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
                //System.out.println("state/province:   " + state);
                states.add(state);
                confirmed = element.getInt("Confirmed");
                //System.out.println("confirmed cases:  " + confirmed);
                confirmeds.add(confirmed);
                death = element.getInt("Deaths");
                //System.out.println("confirmed deaths: " + death);
                deaths.add(death);
                //System.out.println("");
            }
            date = rtrn.getString("dt");
            //System.out.println("date: " + date);
        }
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//--------------------------------DATA transfer to data storage class
    //copy state information to the data storage class
    public ArrayList<String> getStateInfo()
    {
        ArrayList<String> sts = new ArrayList<String>();
        for (int i = 0; i < states.size(); i++)
        {
            sts.add(states.get(i));
        }
        return(sts);
    }
    //copy death information to the data storage class
    public ArrayList<Integer> getDeathInfo()
    {
        ArrayList<Integer> dth = new ArrayList<Integer>();
        for (int i = 0; i < deaths.size(); i++)
        {
            dth.add(deaths.get(i));
        }
        return(dth);
    }
    //copy confirmed case information to the data storage class
    public ArrayList<Integer> getConfirmedInfo()
    {
        ArrayList<Integer> conf = new ArrayList<Integer>();
        for (int i = 0; i < confirmeds.size(); i++)
        {
            conf.add(confirmeds.get(i));
        }
        return(conf);
    }
}