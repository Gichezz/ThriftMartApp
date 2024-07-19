
package ThriftMart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItem extends JFrame {

    public AddItem() {
        // Set Frame properties
        setSize(1150, 1000);
         // Adjusted size to fit all panels
        setLayout(null); 
        // Using null layout for precise positioning
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Item Page");

        // Change the background color of the JFrame
        getContentPane().setBackground(new Color(0,102,204));

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1150, 100);
        headerPanel.setBackground(new Color(0,102,204));
        headerPanel.setLayout(null);

        // Initialize the title labels
        JLabel l1 = new JLabel("Explore");
        JLabel l2 = new JLabel("Add Item Page");
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);

        // Set bounds for title labels
        l1.setBounds(500, 20, 200, 30);
        l2.setBounds(500, 50, 200, 30);

        // Add title labels to the header panel
        headerPanel.add(l1);
        headerPanel.add(l2);

        // Add header panel to the frame
        add(headerPanel);

        // Short cut to Create six panels with the same components
        for (int i = 0; i < 6; i++) {
            JPanel panel = createPanel();
            int x = (i % 2) * 570 + 10; 
            // Calculate x position (two panels side by side)
            int y = (i / 2) * 270 + 110;
             // Calculate y position (three rows)
            panel.setBounds(x, y, 550, 250);
            add(panel);
        }
    }

    // Method to create a panel with labels, text fields, and a space for a picture
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        // Using null layout for precise component placement
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        // Border for visibility
        panel.setBackground(new Color(255, 255, 255));
         // Set background color for the panel

        JLabel pictureLabel = new JLabel("Picture:");
        JLabel sellerNameLabel = new JLabel("Seller Name:");
        JLabel itemTypeLabel = new JLabel("Item Details:");
        JLabel emailLabel = new JLabel("Email ID:");
        JLabel phoneLabel = new JLabel("Phone No:");
        JLabel addressLabel = new JLabel("Address:");

        // Set font color to white
        pictureLabel.setForeground(Color.black);
        sellerNameLabel.setForeground(Color.black);
        itemTypeLabel.setForeground(Color.black);
        emailLabel.setForeground(Color.black);
        phoneLabel.setForeground(Color.black);
        addressLabel.setForeground(Color.black);

        JTextField sellerNameField = new JTextField();
        JTextField itemTypeField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();

        // Placeholder for the picture
        JLabel picturePlaceholder = new JLabel();
        picturePlaceholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        picturePlaceholder.setOpaque(true);
        picturePlaceholder.setBackground(Color.white);
        picturePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        picturePlaceholder.setText("No Image");

        JButton uploadButton = new JButton("Upload Picture");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate uploading a picture by changing the placeholder text
                picturePlaceholder.setText("Image Uploaded");
            }
        });

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a confirmation message when the button is clicked
                JOptionPane.showMessageDialog(null, "Item added successfully!");
            }
        });

        // Set bounds for labels, text fields, and buttons
        pictureLabel.setBounds(20, 20, 100, 30);
        picturePlaceholder.setBounds(130, 20, 100, 100); // Placeholder for the picture
        uploadButton.setBounds(240, 20, 150, 30);
        sellerNameLabel.setBounds(20, 130, 100, 30);
        sellerNameField.setBounds(130, 130, 150, 30);
        itemTypeLabel.setBounds(20, 170, 100, 30);
        itemTypeField.setBounds(130, 170, 150, 30);
        emailLabel.setBounds(20, 210, 100, 30);
        emailField.setBounds(130, 210, 150, 30);
        phoneLabel.setBounds(320, 60, 100, 30);
        phoneField.setBounds(430, 60, 100, 30);
        addressLabel.setBounds(320, 100, 100, 30);
        addressField.setBounds(430, 100, 100, 30);
        addItemButton.setBounds(430, 170, 100, 30);

        // Add components to the panel
        panel.add(pictureLabel);
        panel.add(picturePlaceholder);
        panel.add(uploadButton);
        panel.add(sellerNameLabel);
        panel.add(sellerNameField);
        panel.add(itemTypeLabel);
        panel.add(itemTypeField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(addItemButton);

        return panel;
    }

    public static void main(String[] args) {
        // Create and display the frame
            AddItem app = new AddItem();
            app.setVisible(true);
       
    }
}
