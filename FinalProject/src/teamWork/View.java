package teamWork;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends JFrame implements MenuListener, ActionListener{
    static JMenuBar mb;

    static JMenu fileMenu, aboutMenu;

    static JMenuItem loadRoster, addAttendance, save, plotData;

    static JFrame frame, fileChooser, about;

    static File file;
    Scanner fileIn;
    int response;
    JFileChooser chooser = new JFileChooser(".");
    //static String[][] result;
     
     static String[][] result = {{"1210101010", "Javier", "Gonzalez", "Computer Science", "Graduate", "javiergs"},
    	{"0000000000", "John", "Doe", "Engineering", "Undergraduate", "jdoe24"}};
    		
    static String[] categories = {"ID", "First Name", "Last Name", "Program", "Level", "ASURITE"};

    static JTable jt = new JTable(result, categories);
    static JScrollPane sp=new JScrollPane(jt);


    //static String[][] result;
    public View()
    {
    	
    	
    }



    public static void main(String[] args)
    {
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

        View m = new View();
        loadRoster.addActionListener(m);
        aboutMenu.addMenuListener(m);

        // add menu items to fileMenu
        fileMenu.add(loadRoster);
        fileMenu.add(addAttendance);
        fileMenu.add(save);
        fileMenu.add(plotData);


        // add menu to menu bar
        mb.add(fileMenu);
        mb.add(aboutMenu);

        // add menubar to frame
        frame.setJMenuBar(mb);

        //jt.setCellSelectionEnabled(true);


        frame.add(sp);
        // set the size of the frame
        frame.setSize(500, 500);
        frame.setVisible(true);


        
        //result = m.readArray();

    
    }
    
    public void addTable()

    {
    	//JTable jt = new JTable(result, categories);
        //JScrollPane sp=new JScrollPane(jt);
    	//jt.setCellSelectionEnabled(true);

        //frame.add(sp);

    }
    
    public void readArray() throws FileNotFoundException
    {
    	
    	Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
    	//Scanner in = new Scanner(new File("names.csv"));
    	ArrayList<String[]> lines = new ArrayList<>();
    	while(in.hasNextLine()) {
    	    String line = in.nextLine().trim();
    	    String[] splitted = line.split(",");
    	    for(int i = 0; i<splitted.length; i++) {
    	        //get rid of additional " at start and end
    	        splitted[i] = splitted[i].substring(0, splitted[i].length());
    	    }
    	    lines.add(splitted);
    	}

    	
    	result = new String[lines.size()][];
    	for(int i = 0; i<result.length; i++) {
    	    result[i] = lines.get(i);
    	}

    	System.out.println(Arrays.deepToString(result));

        //addTable();
    	//return result;


    }
    
    public void askFile()
    {

    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES","*csv","csv");
        chooser.setFileFilter(filter);
        response = chooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION)
        {
            file = chooser.getSelectedFile();

            try
            {
                fileIn = new Scanner(file);
                if(file.isFile()){

                    while(fileIn.hasNextLine())
                    {
                        String line = fileIn.nextLine();
                        System.out.println(line);
                    }
                }
                else{
                    System.out.println("That was not a file");
                }
                fileIn.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == loadRoster)
        {
        	askFile();

        	try {
				readArray();
				//addTable();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }

    }

    // methods required by MenuListener
    @Override
    public void menuSelected(MenuEvent me)
    {
        if (me.getSource().equals(aboutMenu))
            {
                JOptionPane.showMessageDialog(about, "CSE360 Final Project\nRachael Kang\nBrian Ayon\nChris Zabel\nRobert",
                                                     "Team Information", JOptionPane.INFORMATION_MESSAGE);
            }
    }

    @Override
    public void menuDeselected(MenuEvent e)
    {

    }

    @Override
    public void menuCanceled(MenuEvent e)
    {

    }
}
