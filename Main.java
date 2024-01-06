import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        final JFrame frame =new JFrame("Hotel Booking System");
        final JLabel namelb,emaillb,mobilelb,genderlb, checkinlb,checkoutlb,paymentlb, addresslb, bookingId;
        final JTextField nametf, emailtf, mobiletf, gendertf, checkintf, checkouttf, paymenttf, addresstf;
        JButton bookbtn, clearbtn, exitbtn;

        namelb = new JLabel("Name :");
        emaillb = new JLabel("Email :");
        mobilelb = new JLabel("Mobile :");
        genderlb = new JLabel("Gender :");
        checkinlb = new JLabel("CheckIn :");
        checkoutlb = new JLabel("CheckOut :");
        paymentlb = new JLabel("Payment :");
        addresslb = new JLabel("Address :");
        bookingId = new JLabel();

        nametf = new JTextField();
        emailtf = new JTextField();
        mobiletf = new JTextField();
        gendertf = new JTextField();
        checkintf = new JTextField();
        checkouttf = new JTextField();
        paymenttf = new JTextField();
        addresstf = new JTextField();

        clearbtn = new JButton("Clear");
        bookbtn = new JButton("Book Now");
        exitbtn = new JButton("Exit");

        //set the bounds
        namelb.setBounds(20, 30, 100, 40);
        emaillb.setBounds(20, 70, 100, 40);
        mobilelb.setBounds(20, 110, 100, 40);
        addresslb.setBounds(20, 150, 100, 40);
        genderlb.setBounds(280, 30, 100, 40);
        checkinlb.setBounds(280, 70, 100, 40);
        checkoutlb.setBounds(280, 110, 100, 40);
        paymentlb.setBounds(280, 150, 100, 40);
        bookingId.setBounds(50, 300, 450, 40);

        nametf.setBounds(120, 30, 150, 40);
        emailtf.setBounds(120, 70, 150, 40);
        mobiletf.setBounds(120, 110, 150, 40);
        addresstf.setBounds(120, 150, 150, 40);
        gendertf.setBounds(400, 30, 150, 40);
        checkintf.setBounds(400, 70, 150, 40);
        checkouttf.setBounds(400, 110, 150, 40);
        paymenttf.setBounds(400, 150, 150, 40);

        clearbtn.setBounds(50,200, 150, 45 );
        bookbtn.setBounds(220, 200, 150, 45);
        exitbtn.setBounds(400, 200, 150, 45);
        frame.add(bookingId);
        frame.add(bookbtn);
        frame.add(clearbtn);
        frame.add(exitbtn);
        frame.add(namelb);
        frame.add(emaillb);
        frame.add(mobilelb);
        frame.add(addresslb);
        frame.add(genderlb);
        frame.add(checkinlb);
        frame.add(checkoutlb);
        frame.add(paymentlb);
        frame.add(nametf);
        frame.add(emailtf);
        frame.add(mobiletf);
        frame.add(addresstf);
        frame.add(gendertf);
        frame.add(paymenttf);
        frame.add(checkintf);
        frame.add(checkouttf);



        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(600, 500);
        frame.setVisible(true);

        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //to clear the form
                nametf.setText("");
                emailtf.setText("");
                addresstf.setText("");
                mobiletf.setText("");
                checkintf.setText("");
                checkouttf.setText("");
                paymenttf.setText("");
                gendertf.setText("");
            }
        });

        bookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add the validation of form can't be empty
                if (nametf.getText().toString().isEmpty() ||
                        emailtf.getText().toString().isEmpty() ||
                        mobiletf.getText().toString().isEmpty() ||
                        gendertf.getText().toString().isEmpty() ||
                        checkintf.getText().toString().isEmpty() ||
                        checkouttf.getText().toString().isEmpty() ||
                        paymenttf.getText().toString().isEmpty())
                {
                    bookingId.setText("Please fill the details");
                }
                else {
                    String url = "jdbc:mysql://localhost:3306/hotelsecj";
                    String username = "root";
                    String password = "";
                    try {
                        Connection connection = DriverManager.getConnection(url, username, password);
                        String sql = " insert into booking"
                                + " values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(sql);
                        preparedStmt.setString (1, nametf.getText().toString());
                        preparedStmt.setString (2, emailtf.getText().toString());
                        preparedStmt.setString   (3, mobiletf.getText().toString());
                        preparedStmt.setString(4, addresstf.getText().toString());
                        preparedStmt.setString    (5, checkintf.getText().toString());
                        preparedStmt.setString    (6, checkintf.getText().toString());
                        preparedStmt.setString    (7, checkintf.getText().toString());
                        preparedStmt.setString    (8, checkintf.getText().toString());
                        preparedStmt.setString    (9, checkintf.getText().toString());
                        preparedStmt.execute();
                        System.out.println("Db connected");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex+ "not Connected");
                    }

                    Random random = new Random();
                    int id = random.nextInt(999999);
                    bookingId.setText("Your booking is confirmed and bookind id " + id);
                }


            }
        });
    }
}