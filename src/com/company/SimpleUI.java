package com.company;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

//recall interfaces are abstract classes in which you must implement the methods
public class SimpleUI extends CovidDataStorage implements Runnable
{
    public void run()
    {
        //creating a CovidRequest object so we can make the API request
        CovidRequest request = new CovidRequest();
        //obtaining the covid data for all states & provinces within USA
        CovidDataStorage today = request.makeRequest();

        // Want to create a button to get the whole list of states and provinces
        int i = today.states.size();
        // Frame that contains all GUI components------------------------

        JFrame gui = new JFrame("Covid Data");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setPreferredSize(new Dimension(800, 300));
        gui.getContentPane().setBackground(new Color(0, 0, 0));
        gui.pack();
        gui.setVisible(true);

        Font font1 = new Font("Courier New", Font.BOLD, 32);
        Font font2 = new Font("Courier New", Font.BOLD, 24);
        Font font3 = new Font("Courier New", Font.LAYOUT_LEFT_TO_RIGHT, 18);

        GridLayout layout1 = new GridLayout(1,4,1,10);
        gui.setLayout(layout1);

        JLabel description = new JLabel("U.S. State/Province Covid Data For :" + today.date);
        description.setFont(font1);

        description.setForeground(new Color(255,255,255));

        gui.add(description);

        JButton covidDataBtn = new JButton("Get all state/province covid for today");
        covidDataBtn.setFont(font2);
        gui.add(covidDataBtn);

        JPanel dataPanel = new JPanel();

        JScrollPane listScroller = new JScrollPane(dataPanel);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
        dataPanel.setBackground(Color.black);

        ArrayList<JLabel> stateLabels = new ArrayList<JLabel>();
        System.out.println(i);
        for (int j = 0; j < i; j++)
        {
            JLabel stateLabel = new JLabel("Press button to see state data");
            stateLabel.setForeground(new Color(255, 255, 255));
            stateLabel.setFont(font3);
            stateLabels.add(stateLabel);
            dataPanel.add(stateLabel);
        }

        gui.add(dataPanel);

        MyMouseListener myMouseListener = new MyMouseListener(stateLabels);
        covidDataBtn.addMouseListener(myMouseListener);

        // Want to create a search box that the user can enter a state and get the
        // data from that state
    }
}