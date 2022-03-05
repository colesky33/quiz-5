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

        // Want to create a button to get the whole list of states and provinces
        int i = today.states.size();
        // Frame that contains all GUI components------------------------

        JFrame gui = new JFrame("Covid Data");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setPreferredSize(new Dimension(800, 300));
        gui.pack();
        gui.setVisible(true);

        Font font1 = new Font("Courier New", Font.BOLD, 32);
        Font font2 = new Font("Courier New", Font.BOLD, 24);
        Font font3 = new Font("Courier New", Font.PLAIN, 18);
        Font font4 = new Font("Courier New", Font.PLAIN, 40);

        JLabel description = new JLabel("U.S. State/Province Covid Data For: " + today.date);
        description.setFont(font1);
        gui.getContentPane().add(BorderLayout.NORTH, description);

        JButton covidDataBtn = new JButton("Get all state/province covid for today");
        covidDataBtn.setFont(font2);
        gui.getContentPane().add(BorderLayout.WEST, covidDataBtn);

        JTextArea stateLabel = new JTextArea();
        stateLabel.setFont(font4);

        covidDataBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int j = 0; j < today.states.size(); j++) {
                    stateLabel.append("State: " + today.states.get(j) + "\n");
                    stateLabel.append("Confirmeds: " + today.confirmeds.get(j) + "\n");
                    stateLabel.append("Deaths: " + today.confirmeds.get(j) + "\n");
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

        JScrollPane listScroller = new JScrollPane(stateLabel);

        gui.getContentPane().add(BorderLayout.CENTER, listScroller);

        //re doing the whole GUI

        JFrame gui2 = new JFrame("Covid Data 2");
        gui2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui2.setPreferredSize(new Dimension(800, 300));
        gui2.getContentPane().setBackground(new Color(255, 255, 255));
        gui2.pack();
        gui2.setVisible(true);

        JTextArea textArea = new JTextArea();
        textArea.setFont(font3);
        textArea.append("Date: " + today.date + "\n" + "\n");

        JPanel searchPanel = new JPanel();

        JLabel label = new JLabel("Enter State or Province Name: ");
        searchPanel.add(label);

        JTextField stateField = new JTextField(50);
        searchPanel.add(stateField);

        gui2.getContentPane().add(BorderLayout.SOUTH, searchPanel);

        stateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredState = stateField.getText();
                ArrayList<String> customStates = new ArrayList<String>();
                customStates.add(enteredState);
                CovidDataStorage customList = today.getCustomList(customStates);
                textArea.append("State or Province: " + customList.states.get(0) + "\n");
                textArea.append("Confirmed Cases: " + customList.confirmeds.get(0) + "\n");
                textArea.append("Deaths: " + customList.deaths.get(0) + "\n");
                textArea.append("\n");
                stateField.setText("");
            }
        });

        JScrollPane scrollBar = new JScrollPane(textArea);
        gui2.getContentPane().add(BorderLayout.CENTER, scrollBar);

        // Want to create a search box that the user can enter a state and get the
        // data from that state
    }
}