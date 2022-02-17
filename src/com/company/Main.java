/*-------------------------
Author: Colin Olesky
Date: Feb 17, 2022
Quiz #5
--------------------------*/
package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        //creating a CovidRequest object so we can make the API request
        CovidRequest request = new CovidRequest();
        //obtaining the covid data for all states & provinces within USA
        CovidDataStorage today = request.makeRequest();

        //printing all state and province covid information
        /*for (int i = 0; i < today.states.size(); i++)
        {
            System.out.println("State or Province: " + today.states.get(i));
            System.out.println("  Confirmed Cases: " + today.confirmeds.get(i));
            System.out.println(" Confirmed Deaths: " + today.deaths.get(i));
            System.out.println("");
        }

        System.out.println("Date: " + request.date);*/

//-----------------------------------------------------------------------------------------------
//-----------------------------insert custom list of states & or provinces here------------------
        //custom list of state data
        //user defined list of states for which you would like to receive covid data from
        ArrayList<String> customStates = new ArrayList<String>();
        customStates.add(0, "Michigan");
        customStates.add(1, "Ohio");
        customStates.add(2, "California");
        customStates.add(3, "Florida");
        customStates.add(4, "Texas");
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------

        //creating a data storage object of just the data from the states inside customStates ArrayList
        //passing in the list of states the user wants
        //calling this on the covid data from today
        CovidDataStorage customList = today.getCustomList(customStates);

        //printing the user-defined custom list of states and provinces
        System.out.println("Custom list of states: ");

        for (int i = 0; i < customList.states.size(); i++)
        {
            System.out.println("State or Province: " + customList.states.get(i));
            System.out.println("  Confirmed Cases: " + customList.confirmeds.get(i));
            System.out.println("           Deaths: " + customList.deaths.get(i));
            System.out.println("");
        }

//-------------------Most deaths--------------------------------------
        CovidDataStorage mostDeaths = today.getMostDeaths();
        System.out.println("State with most deaths: " + mostDeaths.states);
        System.out.println("       Confirmed Cases: " + mostDeaths.confirmeds);
        System.out.println("                Deaths: " + mostDeaths.deaths);
        System.out.println("");

//-------------------Most cases----------------------------------------
        CovidDataStorage mostCases = today.getMostConfirmds();
        System.out.println("State with most confirmed cases: " + mostCases.states);
        System.out.println("                Confirmed Cases: " + mostCases.confirmeds);
        System.out.println("                         Deaths: " + mostCases.deaths);
        System.out.println("");

        //Date printing
        System.out.println("Date: " + today.date);
    }
}
