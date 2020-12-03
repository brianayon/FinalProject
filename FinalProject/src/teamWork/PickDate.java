package teamWork;

import org.jdesktop.swingx.JXDatePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PickDate {
    public void pickDate () {
        JFrame frame = new JFrame("Calendar");
        JPanel panel = new JPanel();
        JButton button = new JButton("Confirm");
        JLabel label = new JLabel("Select date of attendance:");

        frame.setBounds(250, 250, 250, 100);

        // Initialize new date picker from JXDatePicker
        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("MM/dd/yyyy")); // Specify date format

        // Add elements to frame
        panel.add(label);
        panel.add(picker);
        panel.add(button);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Date selectedDate = picker.getDate(); // Store selected date as an object
                        int inputMonth = selectedDate.getMonth() + 1;
                        int inputDay = selectedDate.getDate();
                        int inputYear = selectedDate.getYear() + 1900;

                        // Call controller function to save date into student object
                        System.out.println(inputMonth + " " + " "+ inputDay + "" + inputYear);

                        frame.dispose(); // Close date picker GUI
                    }
                });
    }
}

