        package com.company;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;
        import javax.swing.*;

//-------------------Data storage class creation--------------------
//used for holding all the data from the API request & creating custom objects
//of which the user defines the states that are included
public class CovidDataStorage
{
    public ArrayList<String> states = new ArrayList<String>();
    public ArrayList<Integer> deaths = new ArrayList<Integer>();
    public ArrayList<Integer> confirmeds = new ArrayList<Integer>();
    public String date;

    //default constructor, called in the CovidRequest class when creating a data storage object
    //intended for separately storing all the information returned by the API request in the members of this class.
    public CovidDataStorage()
    {
    }

    //constructor for making new CovidDataStorage objects from the "master" ArrayLists of all data returned from the API
    //provide the constructor an array list of states/provinces (must exact spelling from master list)
    //constructor then will create a new CovidDataStorage object with only the information of the states the user has defined
    public CovidDataStorage getCustomList(ArrayList<String> sts)
    {
        CovidDataStorage returnObject = new CovidDataStorage();
        for (int i = 0; i < sts.size(); i++)
        {
            for (int j = 0; j < this.states.size(); j++)
            {
                if (Objects.equals(sts.get(i), this.states.get(j)))
                {
                    returnObject.states.add(this.states.get(j));
                    returnObject.deaths.add(this.deaths.get(j));
                    returnObject.confirmeds.add(this.confirmeds.get(j));
                }
            }
        }
        return(returnObject);
    }

    //get the state/province with the most deaths information
    public CovidDataStorage getMostDeaths()
    {
        CovidDataStorage mostDeath = new CovidDataStorage();
        int largestDeathCount = 0;
        for (int i = 0; i < this.deaths.size(); i++)
        {
            if (largestDeathCount <= this.deaths.get(i))
            {
                largestDeathCount = this.deaths.get(i);
                mostDeath.deaths.clear();
                mostDeath.states.clear();
                mostDeath.confirmeds.clear();

                mostDeath.deaths.add(this.deaths.get(i));
                mostDeath.states.add(this.states.get(i));
                mostDeath.confirmeds.add(this.confirmeds.get(i));
            }
        }
        return(mostDeath);
    }

    //get the state/province with the most confirmed cases information
    public CovidDataStorage getMostConfirmds()
    {
        CovidDataStorage mostConfrimeds = new CovidDataStorage();
        int largestConfirmedCount = 0;
        for (int i = 0; i < this.confirmeds.size(); i++)
        {
            if (largestConfirmedCount <= this.confirmeds.get(i))
            {
                largestConfirmedCount = this.confirmeds.get(i);
                mostConfrimeds.confirmeds.clear();
                mostConfrimeds.states.clear();
                mostConfrimeds.deaths.clear();

                mostConfrimeds.confirmeds.add(this.confirmeds.get(i));
                mostConfrimeds.states.add(this.states.get(i));
                mostConfrimeds.deaths.add(this.deaths.get(i));
            }
        }
        return(mostConfrimeds);
    }
}
