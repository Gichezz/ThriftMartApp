
package ThriftMart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Home extends JFrame {

    private List<String> cartItems;
    private List<Double> cartPrices;
    private JButton cartButton;
    private JPanel mainContent;
    private JScrollPane scrollPane;

    // Define product details
    private String[] productNames = {
            "Wireless Headphones", "Leather Jacket", "Coffee Maker", "Mystery Novel", "Building Blocks Set", "Skincare Kit",
            "Smartphone", "Designer Handbag", "Vacuum Cleaner", "Cookbook", "Action Figure", "Makeup Palette"
    };
    private String[] productPrices = {
            "Ksh 9999", "Ksh 19999", "Ksh 4999", "Ksh 1299", "Ksh 2999", "Ksh 7999",
            "Ksh 69999", "Ksh 24999", "Ksh 15999", "Ksh 2499", "Ksh 1999", "Ksh3999"
    };
    private String[] sellerNames = {
            "TechCorp", "Fashionista", "HomeBrew", "Bookworm", "ToyLand", "BeautyCare",
            "GadgetWorld", "LuxuryBags", "CleanHome", "GourmetCook", "ActionHeroes", "GlamourLook"
    };
    private String[] productDescriptions = {
            "High-quality wireless headphones with noise cancellation and long battery life.",
            "Premium leather jacket with a stylish design, perfect for all seasons.",
            "Compact coffee maker with a programmable timer and multiple brew settings.",
            "Engaging mystery novel that keeps you on the edge of your seat from start to finish.",
            "Creative building blocks set that encourages imaginative play and learning.",
            "Complete skincare kit with natural ingredients for a healthy and glowing skin.",
            "Latest smartphone with high-end features and a sleek design.",
            "Elegant designer handbag made from high-quality materials.",
            "Powerful vacuum cleaner with advanced filtration system.",
            "Comprehensive cookbook with recipes for all skill levels.",
            "Detailed action figure with movable joints and accessories.",
            "Versatile makeup palette with a range of colors for any occasion."
    };
    private String[] productCategories = {
            "Electronics", "Fashion", "Home", "Books", "Toys", "Beauty",
            "Electronics", "Fashion", "Home", "Books", "Toys", "Beauty"
    };

    private JTextField searchBar;
    private JComboBox<String> hintComboBox;

    public Home() {
        // Initialize the cart items and prices list
        cartItems = new ArrayList<>();
        cartPrices = new ArrayList<>();

        // Set Frame properties
        setSize(1200, 1000); // Adjusted size to fit all panels
        setLayout(null); // Using null layout for precise positioning
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ThriftMart Home Page");

        // Change the background color of the JFrame
        getContentPane().setBackground(new Color(0,102,204));

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1200, 100);
        headerPanel.setBackground(new Color(0,102,204));
        headerPanel.setLayout(null);

        // Initialize the title labels
        JLabel l1 = new JLabel("ThriftMart");
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setForeground(Color.WHITE);
        l1.setBounds(10, 35, 200, 30);

        searchBar = new JTextField();
        searchBar.setBounds(200, 35, 450, 30);
        hintComboBox = new JComboBox<>();
        hintComboBox.setBounds(300, 70, 500, 30);
        hintComboBox.setEditable(true);
        hintComboBox.setModel(new DefaultComboBoxModel<>(productNames));
        hintComboBox.setVisible(false);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(660, 35, 100, 30);

        cartButton = new JButton("Cart (0)");
        cartButton.setBounds(770, 35, 100, 30);

        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(880, 35, 100, 30);

        // Add the "Add Item" button
        JButton addItemButton = new JButton("Add Item");
        addItemButton.setBounds(990, 35, 100, 30);
        // Add ActionListener to cart button to show the cart page
        cartButton.addActionListener(e -> showCartPage());

        // Add ActionListener to profile button to show the profile page
        profileButton.addActionListener(e -> showProfilePage());

        // Add ActionListener to add item button
        addItemButton.addActionListener(e -> showAddItemPage());

        // Add ActionListener to search button
        searchButton.addActionListener(e -> performSearch());

        // Show search hints as user types
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String text = searchBar.getText().toLowerCase();
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) hintComboBox.getModel();
                model.removeAllElements();
                for (String name : productNames) {
                    if (name.toLowerCase().contains(text)) {
                        model.addElement(name);
                    }
                }
                hintComboBox.setVisible(!text.isEmpty());
            }
        });

        // Add components to the header panel
        headerPanel.add(l1);
        headerPanel.add(searchBar);
        headerPanel.add(searchButton);
        headerPanel.add(cartButton);
        headerPanel.add(profileButton);
        headerPanel.add(addItemButton);
        headerPanel.add(hintComboBox);

        // Add header panel to the frame
        add(headerPanel);

        // Navigation Menu Button
        JButton navMenuButton = new JButton("Categories");
        navMenuButton.setBounds(10, 100, 150, 50);

        // Create a JPopupMenu for categories
        JPopupMenu categoryMenu = new JPopupMenu();
        String[] categories = {"All", "Electronics", "Fashion", "Home", "Books", "Toys", "Beauty"};
        for (String category : categories) {
            JMenuItem menuItem = new JMenuItem(category);
            categoryMenu.add(menuItem);
            menuItem.addActionListener(e -> filterProductsByCategory(category));
        }

        // Add ActionListener to show the menu on button click
        navMenuButton.addActionListener(e -> categoryMenu.show(navMenuButton, 0, navMenuButton.getHeight()));

        // Add navigation menu button to the frame
        add(navMenuButton);

        // Create main content panel with scrollable pane
        mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(new Color(240, 248, 255));

        scrollPane = new JScrollPane(mainContent);
        scrollPane.setBounds(10, 160, 1160, 740);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add product panels to the main content
        addProductPanels("All");

        // Add scroll pane to the frame
        add(scrollPane);

        // Footer
        JPanel footer = new JPanel();
        footer.setBounds(0, 910, 1200, 50);
        footer.setBackground(new Color(30, 144, 255));
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        String[] footerLinks = {"About Us", "Contact Us", "FAQs", "Customer Support"};
        for (String link : footerLinks) {
            JLabel label = new JLabel(link);
            label.setForeground(Color.WHITE);
            footer.add(label);
        }

        // Add footer to the frame
        add(footer);
    }

    // Method to create a panel with a product card layout
    public JPanel createProductPanel(String productName, String productPrice, String imagePath, String sellerName, String productDescription) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 350)); // Ensure the panel has a fixed size
        panel.setLayout(null); // Using null layout for precise component placement
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for visibility
        panel.setBackground(Color.WHITE);

        JLabel picturePlaceholder = new JLabel();
        picturePlaceholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        picturePlaceholder.setOpaque(true);
        picturePlaceholder.setBackground(Color.LIGHT_GRAY);
        picturePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        picturePlaceholder.setText("No Image");
        picturePlaceholder.setBounds(20, 20, 200, 150);

        // Here you could load an actual image, for simplicity, we're just using a placeholder text
        ImageIcon image = new ImageIcon(imagePath);
        Image scaledImage = image.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        picturePlaceholder.setIcon(new ImageIcon(scaledImage));

        JLabel productNameLabel = new JLabel("Product Name : " + productName);
        productNameLabel.setBounds(240, 20, 240, 30);

        JLabel productPriceLabel = new JLabel("Product Price : " + productPrice);
        productPriceLabel.setBounds(240, 60, 240, 30);

        JLabel sellerNameLabel = new JLabel("Seller: " + sellerName);
        sellerNameLabel.setBounds(240, 100, 240, 30);

        JTextArea productDescriptionArea = new JTextArea("Product Description : " + productDescription);
        productDescriptionArea.setLineWrap(true);
        productDescriptionArea.setWrapStyleWord(true);
        productDescriptionArea.setEditable(false);
        productDescriptionArea.setBounds(240, 140, 240, 60);

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(20, 220, 200, 30);

        // Add ActionListener to add to cart button
        addToCartButton.addActionListener(e -> {
            cartItems.add(productName + " - " + productPrice);
            cartPrices.add(Double.parseDouble(productPrice.replace("Ksh", "")));
            JOptionPane.showMessageDialog(null, productName + " added to cart.");
            updateCartButton();
        });

        // Add components to the panel
        panel.add(picturePlaceholder);
        panel.add(productNameLabel);
        panel.add(productPriceLabel);
        panel.add(sellerNameLabel);
        panel.add(productDescriptionArea);
        panel.add(addToCartButton);

        return panel;
    }

    // Method to update the cart button text with the number of items
    private void updateCartButton() {
        cartButton.setText("Cart (" + cartItems.size() + ")");
    }

    // Method to show the cart page
    public void showCartPage() {
        JFrame cartFrame = new JFrame("Cart");
        cartFrame.setSize(400, 600);
        cartFrame.setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : cartItems) {
            listModel.addElement(item);
        }

        JList<String> cartList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        cartFrame.add(scrollPane, BorderLayout.CENTER);

        // Calculate the total price
        double totalPrice = cartPrices.stream().mapToDouble(Double::doubleValue).sum();

        JLabel totalLabel = new JLabel("Total: Ksh " + totalPrice);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(e -> JOptionPane.showMessageDialog(cartFrame, "Purchase Successful!"));

        JPanel southPanel = new JPanel(new GridLayout(3, 1));
        southPanel.add(totalLabel);
        southPanel.add(buyButton);

        JButton removeButton = new JButton("Remove Selected Item");
        removeButton.addActionListener(e -> {
            int selectedIndex = cartList.getSelectedIndex();
            if (selectedIndex != -1) {
                cartItems.remove(selectedIndex);
                cartPrices.remove(selectedIndex);
                listModel.remove(selectedIndex);
                updateCartButton();
                double updatedTotalPrice = cartPrices.stream().mapToDouble(Double::doubleValue).sum();
                totalLabel.setText("Total: $" + updatedTotalPrice);
            }
        });

        southPanel.add(removeButton);

        cartFrame.add(southPanel, BorderLayout.SOUTH);

        cartFrame.setVisible(true);
    }

    // Method to show the profile page
    public void showProfilePage() {
        JFrame profileFrame = new JFrame("Profile");
        profileFrame.setSize(400, 600);
        profileFrame.setLayout(null);

        JLabel profileImage = new JLabel();
        profileImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        profileImage.setOpaque(true);
        profileImage.setBackground(Color.LIGHT_GRAY);
        profileImage.setHorizontalAlignment(SwingConstants.CENTER);
        profileImage.setText("No Image");
        profileImage.setBounds(100, 20, 200, 200);

        // Here you could load an actual image, for simplicity, we're just using a placeholder text
        ImageIcon image = new ImageIcon("user-image.png");
        Image scaledImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        profileImage.setIcon(new ImageIcon(scaledImage));

        JLabel nameLabel = new JLabel("Name: Luis Dulo");
        nameLabel.setBounds(100, 240, 200, 30);

        JLabel locationLabel = new JLabel("Location: Downtown, Kisii, Kenya");
        locationLabel.setBounds(100, 280, 200, 30);

        JLabel contactLabel = new JLabel("Contact: luisokal15@gmail.com");
        contactLabel.setBounds(100, 320, 200, 30);

        JTextArea descriptionArea = new JTextArea("Description: Seller of mobile phones and electronics and also buys used phones.");
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBounds(100, 360, 200, 100);

        // Create a logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(150, 480, 100, 30);
        logoutButton.addActionListener(e -> {
            // Handle logout logic here
            Login LoginFrame = new Login();
            LoginFrame.setVisible(true);
            LoginFrame.pack();
            LoginFrame.setLocationRelativeTo(null); 
            this.dispose();
            profileFrame.dispose(); // Close the profile frame
        });

        // Add components to the profile frame
        profileFrame.add(profileImage);
        profileFrame.add(nameLabel);
        profileFrame.add(locationLabel);
        profileFrame.add(contactLabel);
        profileFrame.add(descriptionArea);
        profileFrame.add(logoutButton);

        profileFrame.setVisible(true);
    }

    // Method to show the add item page
    public void showAddItemPage() {
        AddItem addItem = new AddItem();
        addItem.setVisible(true);
        addItem.pack();
        addItem.setLocationRelativeTo(null); 
        this.dispose();
    }

    

    // Method to filter products by category
    private void filterProductsByCategory(String category) {
        mainContent.removeAll();
        addProductPanels(category);
        mainContent.revalidate();
        mainContent.repaint();
    }

    // Method to add product panels to the main content
    private void addProductPanels(String category) {
        for (int i = 0; i < productNames.length; i++) {
            if (category.equals("All") || productCategories[i].equals(category)) {
                JPanel panel = createProductPanel(
                        productNames[i],
                        productPrices[i],
                        "sample-image.png",
                        sellerNames[i],
                        productDescriptions[i]
                );
                mainContent.add(panel);
            }
        }
    }

    // Method to perform search operation
    private void performSearch() {
        String query = searchBar.getText().toLowerCase();
        mainContent.removeAll();
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].toLowerCase().contains(query)) {
                JPanel panel = createProductPanel(
                        productNames[i],
                        productPrices[i],
                        "sample-image.png",
                        sellerNames[i],
                        productDescriptions[i]
                );
                mainContent.add(panel);
            }
        }
        mainContent.revalidate();
        mainContent.repaint();
    }

    public static void main(String[] args) {
        // Create and display the frame
        Home home = new Home();
        home.setVisible(true);
    }
}
