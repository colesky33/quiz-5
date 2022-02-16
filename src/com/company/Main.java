package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        //making the web request to the API
        CovidRequest request = new CovidRequest();
        //obtaining the covid data for all states & provinces within USA
        CovidDataStorage today = new CovidDataStorage();
        today.states = request.getStateInfo();
        today.confirmeds = request.getConfirmedInfo();
        today.deaths = request.getDeathInfo();

        //printing all state and province covid information
        /*for (int i = 0; i < today.states.size(); i++)
        {
            System.out.println("State or Province: " + today.states.get(i));
            System.out.println("  Confirmed Cases: " + today.confirmeds.get(i));
            System.out.println(" Confirmed Deaths: " + today.deaths.get(i));
            System.out.println("");
        }
        */
        //System.out.println("Date: " + request.date);

        //custom list of state data
        //user defined list of states for which you would like to receive covid data from
//-----------------------------------------------------------------------------------------------
//-----------------------------insert custom list of states & or provinces here------------------
        ArrayList<String> customStates = new ArrayList<String>();
        customStates.add(0, "Michigan");
        customStates.add(1, "Ohio");
        customStates.add(2, "California");
        customStates.add(3, "Florida");
        customStates.add(4, "Texas");
//-----------------------------------------------------------------------------------------------
        //creating a data storage object of just the data from the states inside customStates ArrayList
        //passing in the list of states the user wants
        //passing in the covid data from today
        CovidDataStorage customList = new CovidDataStorage(customStates, today);

        //printing the user-defined custom list of states and provinces
        System.out.println("Custom list of states: ");

        for (int i = 0; i < customStates.size(); i++)
        {
            System.out.println("State or Province: " + customList.states.get(i));
            System.out.println("  Confirmed Cases: " + customList.confirmeds.get(i));
            System.out.println(" Confirmed Deaths: " + customList.deaths.get(i));
            System.out.println("");
        }
    }
}
