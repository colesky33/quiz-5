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

//recall interfaces are abstract classes in which you must implement the methods
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

        //----------------------------------------------------------------------------------------------
        //First UI APP:
        //User can press a button to get a list of all state and province covid data
        //Also a reset button to clear the text area
        //----------------------------------------------------------------------------------------------

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

        //----------------------------------------------------------------------------------------------
        //Second UI APP:
        //User enters state and or province names into a search bar and the corresponding
        //covid data is displayed using a text area
        //----------------------------------------------------------------------------------------------

        //creating the second APP frame and setting it up
        JFrame gui2 = new JFrame("Covid Data 2");
        gui2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui2.setPreferredSize(new Dimension(800, 300));
        gui2.getContentPane().setBackground(new Color(255, 255, 255));
        gui2.pack();
        gui2.setVisible(true);

        //creating a text area to display the entered state or province information
        JTextArea stateInfo = new JTextArea();
        stateInfo.setFont(font3);
        stateInfo.append("Date: " + today.date + "\n" + "\n");

        //creating a panel for the search bar and label for the search bar
        JPanel searchPanel = new JPanel();

        //creating a search bar label
        JLabel searchLabel = new JLabel("Enter State or Province Name: ");
        searchPanel.add(searchLabel);

        //creating the search bar
        JTextField stateField = new JTextField(50);
        searchPanel.add(stateField);

        //creating the reset button
        JButton resetBtn2 = new JButton("Reset");
        searchPanel.add(resetBtn2);

        //adding the search panel to the app frame
        gui2.getContentPane().add(BorderLayout.SOUTH, searchPanel);

        //adding an action listener for the search bar
        stateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredState = stateField.getText();
                ArrayList<String> customStates = new ArrayList<String>();
                customStates.add(enteredState);
                CovidDataStorage customList = today.getCustomList(customStates);
                stateInfo.append("State or Province: " + customList.states.get(0) + "\n");
                stateInfo.append("Confirmed Cases: " + customList.confirmeds.get(0) + "\n");
                stateInfo.append("Deaths: " + customList.deaths.get(0) + "\n");
                stateInfo.append("\n");
                stateField.setText("");
            }
        });

        //creating the mouse listener for the reset button
        resetBtn2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stateInfo.setText("Date: " + today.date + "\n" + "\n");
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
        JScrollPane scrollBar = new JScrollPane(stateInfo);
        gui2.getContentPane().add(BorderLayout.CENTER, scrollBar);
    }
}