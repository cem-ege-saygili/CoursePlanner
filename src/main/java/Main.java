import domain.*;

import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nRunnable\n\n");

        /*ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

        cList.add(c6);
        cList.add(c2);cList.add(c7);cList.add(c3);
        cList.add(c1);
        cList.add(c4); cList.add(c5);*/

        String[] courseNames = new String[] {"A", "B",
                "C", "D", "E", "F", "G"};
        Map<String, Integer[]> timeTables = new HashMap<String, Integer[]>();
        timeTables.put("A", new Integer[]{1, 2} );
        timeTables.put("B", new Integer[]{1, 4} );
        timeTables.put("C", new Integer[]{3, 4} );
        timeTables.put("D", new Integer[]{3, 7} );
        timeTables.put("E", new Integer[]{7, 8} );
        timeTables.put("F", new Integer[]{11, 14} );
        timeTables.put("G", new Integer[]{13, 14} );

        Integer[] priorityValues = new Integer[] {1,2,3,4,5};

        JComboBox<String> courseNamesComboBox = new JComboBox<>(courseNames);
        JComboBox<Integer> priorityValuesComboBox = new JComboBox<>(priorityValues);
        JLabel lblPriority = new JLabel();
        lblPriority.setText("Priority: ");
        JLabel lblStartTime = new JLabel();
        lblStartTime.setText("Starts at: ");
        JLabel lblEndTime = new JLabel();
        lblEndTime.setText("Ends at: ");

//        NumberFormat format = NumberFormat.getInstance();
//        NumberFormatter formatter = new NumberFormatter(format);
//        formatter.setValueClass(Integer.class);
//        formatter.setMinimum(0);
//        formatter.setMaximum(5);
//        formatter.setAllowsInvalid(false);
//        // If you want the value to be committed on each keystroke instead of focus lost
//        formatter.setCommitsOnValidEdit(true);
//        JFormattedTextField txtFieldPriority = new JFormattedTextField(formatter);



        JFrame frame = new JFrame("CourseScheduler v.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btnGenerateSchedules = new JButton("Press to Find Out Non-Overlapping Schedule(s)");
        courseNamesComboBox.setBounds(0,0,100,100);
        priorityValuesComboBox.setBounds(100,0,100,100);
        btnGenerateSchedules.setBounds(250,25,350,50);
        frame.setSize(600,600);
        frame.getContentPane().add(courseNamesComboBox);frame.getContentPane().add(priorityValuesComboBox);
        frame.add(btnGenerateSchedules);
        frame.setLayout(null);
        frame.setVisible(true);



// add to the parent container (e.g. a JFrame):


// get the selected item:
        String selectedBook = (String) courseNamesComboBox.getSelectedItem();
        System.out.println("You seleted the book: " + selectedBook);

        ArrayList<Course> cList = new ArrayList<Course>();
        Course c5 = new Course("E", 7, 8, 2);
        Course c3 = new Course("C", 3, 4, 4);
        Course c2 = new Course("B", 1, 4, 5);
        Course c7 = new Course("G", 13, 14, 4);
        Course c4 = new Course("D", 3, 7, 3);
        Course c1 = new Course("A", 1, 2, 1);
        Course c6 = new Course("F", 11, 14, 5);

        cList.add(c6);
        cList.add(c2);cList.add(c7);cList.add(c3);
        cList.add(c1);
        cList.add(c4); cList.add(c5);

        Scheduler s1 = new Scheduler(cList);
        s1.generateOptimumCoursePlan();
    }

}
