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
    private static Printable printable;

    public static void main(String [] args)
    {

        prepareTableData();
        mainWindow.setSize(300,300);
        mainWindow.setLayout(new FlowLayout());

        printable = table.getPrintable(JTable.PrintMode.FIT_WIDTH,null,null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                print();
            }
        });

        mainWindow.add(button);





        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }

    private static void prepareTableData() {
        String[] header = {"Very long First Name",
                "Very long Last Name",
                "Very long Sport",
                "Very long # of Years",
                "Very long Vegetarian"};
        Object[] obj = {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)};

        //pagination doesnt work when more than one page must be printed
        Object[][] data = new Object[240][5];
        for(int i = 0; i<240 ;i++)
        {
            for(int y = 0;y<5;y++) {
                data[i][y] = obj[y];
            }
        }


        table = new JTable(data,header);

        //scaling print margins work here (changing columns width)

        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(450);
        table.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(450);

    }

    private static void print() {


        PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();



        set.add(new MediaPrintableArea(0f, 0f, 570, 820, MediaPrintableArea.MM));
        set.add(MediaSizeName.ISO_A4);

        //creates invisible frame to create renderable table view
        Frame invisibleFrame = new Frame();
        invisibleFrame.add(table.getTableHeader());
        invisibleFrame.add(table);
        invisibleFrame.setVisible(false);
        invisibleFrame.pack();





            try {

                table.print(JTable.PrintMode.FIT_WIDTH,null,new MessageFormat("-{0}-"),false,set,false);


                //table.print(JTable.PrintMode.FIT_WIDTH,null,new MessageFormat("-{0}-"));
            } catch (PrinterException e) {
                e.printStackTrace();
            }


    }
}
