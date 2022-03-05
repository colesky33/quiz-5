//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
//                              QUIZ 6: UI APPLICATION WITH QUIZ 5 PARSED API
//-----------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------
package com.company;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//----------------------------------------------------------------------------------------------
//First UI APP:
//User can press a button to get a list of all state and province covid data
//Also a reset button to clear the text area
//----------------------------------------------------------------------------------------------
public class SimpleUI extends CovidDataStorage implements Runnable
{
    public void run()
    {
        //creating a CovidRequest object so we can make the API request
        CovidRequest request = new CovidRequest();
        //obtaining the covid data for all states & provinces within USA
        CovidDataStorage today = request.makeRequest();

        //creating fonts that I will use later

        Font font1 = new Font("Courier New", Font.BOLD, 32);
        Font font2 = new Font("Courier New", Font.BOLD, 24);
        Font font3 = new Font("Courier New", Font.PLAIN, 18);
        Font font4 = new Font("Courier New", Font.PLAIN, 40);

        //creating the JFrame and setting it up
        JFrame gui = new JFrame("Covid Data");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setPreferredSize(new Dimension(800, 300));
        gui.pack();
        gui.setVisible(true);

        //creating a label description for the app
        JLabel description = new JLabel("U.S. State/Province Covid Data For: " + today.date);
        description.setFont(font1);
        gui.getContentPane().add(BorderLayout.NORTH, description);

        //creating a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        //creating the button to retrieve all the covid data
        JButton covidDataBtn = new JButton("Get all state/province covid for today");
        covidDataBtn.setFont(font2);
        covidDataBtn.setPreferredSize(new Dimension(300, 100));
        buttonPanel.add(covidDataBtn);

        //creating the reset button
        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(font2);
        resetBtn.setPreferredSize(new Dimension(300, 100));
        buttonPanel.add(resetBtn);

        //adding the button panel to the app frame
        gui.getContentPane().add(BorderLayout.WEST, buttonPanel);

        //creating a text area to display all the state information
        JTextArea stateLabel = new JTextArea();
        stateLabel.setFont(font4);

        //creating the mouse listener for the retrieval button
        covidDataBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int j = 0; j < today.states.size(); j++) {
                    stateLabel.append("State: " + today.states.get(j) + "\n");
                    stateLabel.append("Confirmeds: " + today.confirmeds.get(j) + "\n");
                    stateLabel.append("Deaths: " + today.deaths.get(j) + "\n");
                    stateLabel.append("\n");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //creating the mouse listener for the reset button
        resetBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stateLabel.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //adding the text area to a scroll pane
        JScrollPane listScroller = new JScrollPane(stateLabel);

        //adding the scroll pane to the app frame
        gui.getContentPane().add(BorderLayout.CENTER, listScroller);
    }
}