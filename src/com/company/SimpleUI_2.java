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
//Second UI APP:
//User enters state and or province names into a search bar and the corresponding
//covid data is displayed using a text area
//----------------------------------------------------------------------------------------------

public class SimpleUI_2 extends CovidDataStorage implements Runnable
{
    @Override
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
