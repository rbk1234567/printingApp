import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.text.MessageFormat;

public class Main extends JFrame {

    private static JFrame mainWindow = new JFrame("printingApp");
    private static JButton button = new JButton("PRINT");
    private static JTable table;

    public static void main(String [] args)
    {
        prepareTableData();

        mainWindow.setSize(300,300);
        mainWindow.setLayout(new FlowLayout());
        mainWindow.add(button);
        mainWindow.add(table.getTableHeader());
        mainWindow.add(table);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });

        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }

    private static void prepareTableData() {
        String[] header = {"Very long First Name",
                "Very long Last Name",
                "Very long Sport",
                "Very long # of Years",
                "Very long Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        table = new JTable(data,header);

        //scaling print margins work here
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(450);
    }

    private static void print() {

        //scaling print margins doesnt work here
        /*
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(450);
        */

        PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        PrinterJob printerJob = PrinterJob.getPrinterJob();


        set.add(new MediaPrintableArea(0f, 0f, 570, 820, MediaPrintableArea.MM));
        set.add(MediaSizeName.ISO_A4);

        printerJob.setJobName("TABLE");





            try {

                table.print(JTable.PrintMode.FIT_WIDTH,null,new MessageFormat("-{0}-"),false,set,false);


                //table.print(JTable.PrintMode.FIT_WIDTH,null,new MessageFormat("-{0}-"));
            } catch (PrinterException e) {
                e.printStackTrace();
            }


    }
}
