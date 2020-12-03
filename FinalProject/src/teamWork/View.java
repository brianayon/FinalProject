package teamWork;
/*Description: The class creates a GUI consisted of student information
* obtained from a csv file the user entered. It is able to load a roster, add attendance 
* and save the data into a csv file where it can later be exported.
*Author: Brian Ayon, Rahcael Kang, Chris Zabel, Roberto Ramirez
*/

//necessary packages imported
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

   

    static String strDate;

    static File file;
    Scanner fileIn;
    int response;
    JFileChooser chooser = new JFileChooser(".");

    //2D arrays for handling the student information
    static String[][] result = {{"1210101010", "Javier", "Gonzalez", "Computer Science", "Graduate", "javiergs"},
            {"0000000000", "John", "Doe", "Engineering", "Undergraduate", "jdoe24"}};

    static String[][] att = {{"1210101010,8:00"}, {"0000000000, 9:00"}};


    //Heades for the csv files
    static String[] categories = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};
    static String[] list = {"ASURITE", "Time"};

    //Adds the student info and header to a table
    static JTable jt = new JTable(result, categories);
    static JTable t2 = new JTable(att, list);
    // scroll pane is initialized to scroll trough list of students
    static JScrollPane sp = new JScrollPane(jt);
    static JScrollPane scroll = new JScrollPane(t2);

    //initializes more J components
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

        //Initialize the date window
        DateWindow pickDate = new DateWindow();

        View m = new View();
        loadRoster.addActionListener(m);
        addAttendance.addActionListener(m);
        // aboutMenu.addMenuListener(m);
        aboutMenu.addMouseListener(m);

        // add menu items to fileMenu
        fileMenu.add(loadRoster);
        fileMenu.addSeparator(); // creates a line between LoadRoster & attendance
        fileMenu.add(addAttendance);
        fileMenu.addSeparator(); //seperates
        fileMenu.add(save);
        fileMenu.addSeparator(); 
        fileMenu.add(plotData);

        JLabel display = new JLabel();
        display.add(aboutMenu);

        // add menu to menu bar
        mb.add(fileMenu);
        mb.add(aboutMenu);

        // add menubar to frame
        frame.setJMenuBar(mb);

        // set the size of the frame
        frame.setSize(500, 500);
        frame.setVisible(true);


    }
    
    //This method stores a 2D array in a table then adds it to the frame
    public void addTable() {
        JTable jt = new JTable(result, categories);
        JScrollPane sp=new JScrollPane(jt);
        jt.setCellSelectionEnabled(true);

        frame.add(sp);
        frame.setVisible(true);

    }
     
    //This method prompt the dat window to pick a date 
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

    //This method stores the attendance list in a table & adds it the frame 
    public void addAttendanceTable() {
        JTable t2 = new JTable(att, list);
        JScrollPane scroll=new JScrollPane(t2);
        t2.setCellSelectionEnabled(true);
        //frame.dispose();

        frame.add(scroll);
        frame.setVisible(true);

        askDate();

    }

    /* readArray method reads a file input from the user 
    * and stores it into an array of strings where it is 
    *split my a delimiter
    */
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

    /* This array is similar to the readArray however it
    * focuses the add attendence only
    */
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

    //This mehtod chooses a file and also determines if it is a csv file or not
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

    // This method invoked the actionListener for each menuItem 
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

            //if attendance clicked then it calls the asfile
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


    //This method invokes the MouseListener needed for the "About" JMenu
    public void mouseClicked(MouseEvent e) {

        //dialog box is prompted with team information
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
