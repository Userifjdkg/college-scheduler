package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TimetableSchedulerGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel headerLabel;
    private JButton viewTimetableButton;
    private JButton checkAssignmentsButton;

    public TimetableSchedulerGUI() {
        frame = new JFrame("Departmental Timetable Scheduler");
        panel = new JPanel();
        headerLabel = new JLabel("Welcome to the \n\n" + "NACOS College Scheduler.\n\n" + "What would you like to do today?");
        viewTimetableButton = new JButton("View Timetable");
        checkAssignmentsButton = new JButton("Check Upcoming Assignments");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        panel.setLayout(new FlowLayout());

        panel.add(headerLabel);
        panel.add(viewTimetableButton);
        panel.add(checkAssignmentsButton);

        frame.add(panel);

        viewTimetableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String department = getDepartment();
                if (department.equals("Computer Science")) {
                    saveTimetableCSV(getComputerScienceTimetable(), "computer_science_timetable.csv");
                } else if (department.equals("Cyber Security")) {
                    saveTimetableCSV(getCyberSecurityTimetable(), "cyber_security_timetable.csv");
                } else if (department.equals("Information Technology")) {
                    saveTimetableCSV(getInformationTechnologyTimetable(), "information_technology_timetable.csv");
                }
            }
        });

        frame.setVisible(true);

        checkAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame assignmentsFrame = new JFrame("Upcoming Assignments");
                JPanel assignmentsPanel = new JPanel();
                JLabel messageLabel = new JLabel("<html>Login with your registered Bingham email<br> to the Google Classroom website (https://classroom.google.com/u/0/h)<br> to see if you have any upcoming assignments.</html>");

                assignmentsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                assignmentsFrame.setSize(400, 200);

                assignmentsPanel.setLayout(new FlowLayout());
                assignmentsPanel.add(messageLabel);

                assignmentsFrame.add(assignmentsPanel);
                assignmentsFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }



    private String getDepartment() {
        String courseID = JOptionPane.showInputDialog(frame, "Enter your course ID number:");
        String department = "";

        if (courseID != null) {
            switch (courseID) {
                case "1111":
                    department = "Computer Science";
                    break;
                case "2222":
                    department = "Cyber Security";
                    break;
                case "3333":
                    department = "Information Technology";
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Invalid course ID number!", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        return department;
    }

    private String getComputerScienceTimetable() {
        return "Tuesday\n" +
                "10:00 AM - 11:55 AM: PHY202, Physics Lab\n" +
                "12:00 PM - 1:55 PM: MTH204, LH\n" +
                "2:00 PM - 3:55 PM: CMP212, Hardware Lab\n" +
                "4:00 PM - 5:55 PM: BST204, LH\n\n" +
                "Wednesday\n" +
                "8:00 AM - 9:55 AM: CMP204, CMP LH\n" +
                "10:00 AM - 11:55 AM: EPS228, PG LH\n\n" +
                "Thursday\n" +
                "10:00 AM - 11:55 AM: GST224, LH\n" +
                "12:00 PM - 1:55 PM: CMP206, CMP LH\n" +
                "2:00 PM - 3:55 AM: GST224, LH\n\n" +
                "Friday\n" +
                "2:00 PM - 4:55 PM: CMP202, CMP LH";
    }

    private String getCyberSecurityTimetable() {
        return "Monday\n" +
                "9:00 AM - 10:30 AM: CYB202, LH\n" +
                "11:00 AM - 12:30 PM: Linear Algebra, PG LH\n\n" +
                "Wednesday\n" +
                "8:00 AM - 9:55 AM: CMP204, CMP LH\n" +
                "10:00 AM - 11:55 AM: EPS228, PG LH\n\n" +
                "Friday\n" +
                "2:00 PM - 4:55 PM: CMP202, CMP LH";
    }

    private String getInformationTechnologyTimetable() {
        return "Monday\n" +
                "9:00 AM - 10:30 AM: Calculus, LH\n" +
                "11:00 AM - 12:30 PM: Linear Algebra, PG LH\n\n" +
                "Tuesday\n" +
                "9:00 AM - 10:30 AM: Probability Theory, CMP LH\n" +
                "11:00 AM - 12:30 PM: Number Theory, LH\n\n" +
                "Wednesday\n" +
                "8:00 AM - 9:55 AM: CMP204, CMP LH\n" +
                "10:00 AM - 11:55 AM: EPS228, PG LH\n\n" +
                "Friday\n" +
                "2:00 PM - 4:55 PM: CMP202, CMP LH";
    }

    private void saveTimetableCSV(String timetable, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(timetable);
            writer.close();
            JOptionPane.showMessageDialog(frame, "Timetable saved to device as " + filename, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving timetable!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

