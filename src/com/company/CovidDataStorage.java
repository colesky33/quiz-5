package com.company;

import java.util.ArrayList;
import java.util.Objects;

//-------------------Data storage class creation--------------------
//used for holding all the data from the API request & creating custom objects
//of which the user defines the states that are included
public class CovidDataStorage
{
    public ArrayList<String> states = new ArrayList<String>();
    public ArrayList<Integer> deaths = new ArrayList<Integer>();
    public ArrayList<Integer> confirmeds = new ArrayList<Integer>();

    //default constructor, called in the CovidRequest class when creating a data storage object
    //intended for separately storing all the information returned by the API request in the members of this class
    //This method effectively prevents the user from altering the "master list" of data, which is a private member of
    //the request class. Thus, if the user wishes to utilize any state data they MUST use this class to do so.
    public CovidDataStorage()
    {

    }

    //constructor for making new CovidDataStorage objects from the "master" ArrayLists of all data returned from the API
    //provide the constructor an array list of states/provinces (must exact spelling from master list)
    //constructor then will create a new CovidDataStorage object with only the information of the states the user has defined
    public CovidDataStorage(ArrayList<String> sts, CovidDataStorage masterList)
    {
        for (int i = 0; i < sts.size(); i++)
        {
            for (int j = 0; j < masterList.states.size(); j++)
            {
             if (Objects.equals(sts.get(i), masterList.states.get(j)))
             {
                 states.add(sts.get(i));
                 deaths.add(masterList.deaths.get(j));
                 confirmeds.add(masterList.confirmeds.get(j));
             }
            }
        }
    }
}
