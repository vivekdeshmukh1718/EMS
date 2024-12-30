package employee.management.system;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tffname, tfaddress, tfphone, tfsalary, tfaadhar, tfemail, tfdesignation;
    JDateChooser dcdob;
    JComboBox<String> cbeducation; // Corrected generic type
    JLabel lblempID;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(400, 150, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(400, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeleducation);

        String[] courses = {"BTECH", "BBA", "BCA", "MTECH", "B.COM", "MBA", "BSC", "MSC", "PHD"};
        cbeducation = new JComboBox<>(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(600, 300, 150, 30);
        add(cbeducation);

        JLabel labeldesignation = new JLabel("Designation");
        labeldesignation.setBounds(50, 350, 150, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(200, 350, 150, 30);
        add(tfdesignation);

        JLabel labelaadhar = new JLabel("Aadhar Number");
        labelaadhar.setBounds(400, 350, 150, 30);
        labelaadhar.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(600, 350, 150, 30);
        add(tfaadhar);

        JLabel labelempID = new JLabel("Employee ID");
        labelempID.setBounds(50, 400, 150, 30);
        labelempID.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempID);

        lblempID = new JLabel("" + number);
        lblempID.setBounds(200, 400, 150, 30);
        lblempID.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempID);

        add = new JButton("Add Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String name = tfname.getText();
        String fname = tffname.getText();

        // Validate date of birth
        String dob = null;
        if (dcdob.getDate() != null) {
            dob = new SimpleDateFormat("yyyy-MM-dd").format(dcdob.getDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid Date of Birth.");
            return; // Stop execution if DOB is not selected
        }

        String salary = tfsalary.getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String education = (String) cbeducation.getSelectedItem();
        String designation = tfdesignation.getText();
        String aadhar = tfaadhar.getText();
        String empID = lblempID.getText();

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO employee VALUES('" + name + "','" + fname + "','" + dob + "','" + salary + "','" +
                    address + "','" + email + "','" + phone + "','" + education + "','" + designation + "','" + aadhar + "','" + empID + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details added successfully!");
            setVisible(false);
            new Home(); // Assuming Home is another class to navigate
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (ae.getSource() == back) {
        setVisible(false);
        new Home(); // Assuming Home is another class to navigate
    }
}


    public static void main(String[] args) {
        new AddEmployee();
    }
}
