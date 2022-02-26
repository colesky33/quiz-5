package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyMouseListener extends CovidDataStorage implements MouseListener
{
    //creating a CovidRequest object so we can make the API request
    CovidRequest request = new CovidRequest();
    //obtaining the covid data for all states & provinces within USA
    CovidDataStorage today = request.makeRequest();

    ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    JLabel label = new JLabel();
    public final int i = today.states.size();
    int count;

    public MyMouseListener(ArrayList<JLabel> labels_in)
    {
        int count = 0;
        for (int j = 0; j < i; j++) {
            labelList.add(labels_in.get(j));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        JButton button = (JButton) e.getSource();
        System.out.println("Clicked!");
        for (int j = 0; j < i; j++)
        {
            labelList.get(j).setText(today.states.get(j) + ":    confirmed: " +
                    today.confirmeds.get(j)  + "    deaths: " + today.deaths.get(j));
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("Pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}