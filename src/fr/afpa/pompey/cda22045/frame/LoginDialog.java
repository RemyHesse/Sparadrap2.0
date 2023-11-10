package fr.afpa.pompey.cda22045.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class LoginDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(usernameLabel, cs);

        usernameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(usernameField, cs);

        JLabel passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(passwordLabel, cs);

        passwordField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(passwordField, cs);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), passwordField.getPassword())) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Hi " + usernameField.getText() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        JPanel bp = new JPanel();
        bp.add(loginButton);
        bp.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private boolean authenticate(String username, char[] password) {
        // hardcoded username and password
        String correctUsername = "admin";
        char[] correctPassword = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        return username.equals(correctUsername) && Arrays.equals(password, correctPassword);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Dialog Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton loginButton = new JButton("Click to login");
        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LoginDialog dialog = new LoginDialog(frame);
                
                dialog.setVisible(true);
            }
        });
        JPanel panel = new JPanel();
        panel.add(loginButton);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
