package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpID;
    JLabel lblname, lblphone, lblemail;
    JButton delete, back;

    RemoveEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelempID = new JLabel("Employee Id");
        labelempID.setBounds(50, 50, 100, 30);
        add(labelempID);

        cEmpID = new Choice();
        cEmpID.setBounds(200, 50, 150, 30);
        add(cEmpID);

        try {
            Conn c = new Conn();
            String query = "select * from employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpID.add(rs.getString("empID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(200, 100, 150, 30);
        add(lblname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(200, 150, 150, 30);
        add(lblphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(200, 200, 150, 30);
        add(lblemail);

        cEmpID.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from employee where empID = '" + cEmpID.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        lblname.setText(rs.getString("name"));
                        lblphone.setText(rs.getString("phone"));
                        lblemail.setText(rs.getString("email"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
          delete.setBackground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
          back.setBackground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
       Image i2 = i1.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
       ImageIcon i3  = new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       image.setBounds(350 ,0, 600, 400);
       add(image);

        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from employee where empID = '" + cEmpID.getSelectedItem() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                setVisible(false);
                new Home(); // Navigate to home screen (replace with your implementation)
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home(); 
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
