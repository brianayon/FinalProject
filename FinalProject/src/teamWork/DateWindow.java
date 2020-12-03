package teamWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;

public class DateWindow extends JFrame implements PropertyChangeListener {

    protected JFormattedTextField date = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));

    public DateWindow()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(368,362);
        setTitle("Pick a Date");

        Container cp = getContentPane();
        FlowLayout flowLayout = new FlowLayout();

        cp.setLayout(flowLayout);
        cp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        date.setValue(new Date());
        date.setPreferredSize(new Dimension(130,30));
        cp.add(date);

        JButton calButton = new JButton("Pick a Date");
        cp.add(calButton);

        CalendarWindow calendarWindow = new CalendarWindow();
        calendarWindow.setUndecorated(true);
        calendarWindow.addPropertyChangeListener(this);

        calButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                calendarWindow.setLocation(date.getLocationOnScreen().x,
                        (date.getLocationOnScreen().y) + date.getHeight());

                Date selectedDate = (Date) date.getValue();
                calendarWindow.resetSelection(selectedDate);
                calendarWindow.setVisible(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("selectedDate"))
        {
            java.util.Calendar cal = (java.util.Calendar)evt.getNewValue();
            Date selDate = cal.getTime();

            date.setValue(selDate);
        }
    }
}
