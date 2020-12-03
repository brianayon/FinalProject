package teamWork;

import com.sun.tools.javac.comp.Flow;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JFormattedTextField;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends JFrame implements ActionListener, MouseListener {
    static JMenuBar mb;

    static JMenu fileMenu, aboutMenu;

    static JMenuItem loadRoster, addAttendance, save, plotData;

    static JFrame frame, fileChooser;

    //date picker


    //static JDatePicker
    //static JDatePickerImpl datePicker;

    static String strDate;

    static File file;
    Scanner fileIn;
    int response;
    JFileChooser chooser = new JFileChooser(".");
    //static String[][] result;

    static String[][] result = {{"1210101010", "Javier", "Gonzalez", "Computer Science", "Graduate", "javiergs"},
            {"0000000000", "John", "Doe", "Engineering", "Undergraduate", "jdoe24"}};

    static String[][] att = {{"1210101010,8:00"}, {"0000000000, 9:00"}};


    static String[] categories = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
    static String[] list = {"ASURITE", "Time"};

    static JTable jt = new JTable(result, categories);
    static JTable t2 = new JTable(att, list);
    static JScrollPane sp = new JScrollPane(jt);
    static JScrollPane scroll = new JScrollPane(t2);

    JFrame calFrame;
    JPanel panel;
    JButton button;
    JLabel label;


    //static String[][] result;
    public View() {


    }

    public static void main(String[] args) {
        frame = new JFrame("CSE Final Project");

        // create a menubar
        mb = new JMenuBar();

        // create a menu
        fileMenu = new JMenu("File");
        aboutMenu = new JMenu("About");


        // create fileMenu items
        loadRoster = new JMenuItem("Load a Roster");
        addAttendance = new JMenuItem("Add Attendance");
        save = new JMenuItem("Save");
        plotData = new JMenuItem("Plot Data");

        DateWindow pickDate = new DateWindow();

        View m = new View();
        loadRoster.addActionListener(m);
        addAttendance.addActionListener(m);
        // aboutMenu.addMenuListener(m);
        aboutMenu.addMouseListener(m);

        // add menu items to fileMenu
        fileMenu.add(loadRoster);
        fileMenu.add(addAttendance);
        fileMenu.add(save);
        fileMenu.add(plotData);

        JLabel display = new JLabel();
        display.add(aboutMenu);

        // add menu to menu bar
        mb.add(fileMenu);
        mb.add(aboutMenu);

        // add menubar to frame
        frame.setJMenuBar(mb);

        //jt.setCellSelectionEnabled(true);


        //this.pack();

        // set the size of the frame
        frame.setSize(500, 500);
        frame.setVisible(true);


        //result = m.readArray();


    }

    public void addTable() {
        JTable jt = new JTable(result, categories);
        JScrollPane sp=new JScrollPane(jt);
        jt.setCellSelectionEnabled(true);

        frame.add(sp);
        frame.setVisible(true);

    }

    public void askDate()
    {
        DateWindow dateWindow = null;
        try
        {
            dateWindow = new DateWindow();
            dateWindow.setVisible(true);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    public void addAttendanceTable() {
        JTable t2 = new JTable(att, list);
        JScrollPane scroll=new JScrollPane(t2);
        t2.setCellSelectionEnabled(true);
        //frame.dispose();

        frame.add(scroll);
        frame.setVisible(true);

        askDate();

    }

    public void readArray() throws FileNotFoundException {

        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        //Scanner in = new Scanner(new File("names.csv"));
        ArrayList<String[]> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] splitted = line.split(",");

            for (int i = 0; i < splitted.length; i++) {

                splitted[i] = splitted[i].substring(0, splitted[i].length());
            }
            lines.add(splitted);
        }


        result = new String[lines.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = lines.get(i);
        }

        System.out.println(Arrays.deepToString(result));

    }

    public void attendanceArray() throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        //Scanner in = new Scanner(new File("names.csv"));
        ArrayList<String[]> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] splitted = line.split(",");
            for (int i = 0; i < splitted.length; i++) {
                //get rid of additional " at start and end
                splitted[i] = splitted[i].substring(0, splitted[i].length());
            }
            lines.add(splitted);
        }


        att = new String[lines.size()][];
        for (int i = 0; i < att.length; i++) {
            att[i] = lines.get(i);
        }

        System.out.println(Arrays.deepToString(list));
        //addTable();
    }

    public void askFile() {

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "*csv", "csv");
        chooser.setFileFilter(filter);
        response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();

            try {
                fileIn = new Scanner(file);
                if (file.isFile()) {

                    while (fileIn.hasNextLine()) {
                        String line = fileIn.nextLine();
                        System.out.println(line);
                    }
                } else {
                    System.out.println("That was not a file");
                }
                fileIn.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == loadRoster) {
            askFile();

            try {
                readArray();
                addTable();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (evt.getSource() == addAttendance) {
            askFile();

            try {
                attendanceArray();
                addAttendanceTable();

                //askDate();


                //addAttendanceTable();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public void mouseClicked(MouseEvent e) {

        JOptionPane.showMessageDialog(aboutMenu, "CSE360 Final Project\nRachael Kang\nBrian Ayón\nChris Zabel\nRobert Ramirez",
                "Team Information", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }




}