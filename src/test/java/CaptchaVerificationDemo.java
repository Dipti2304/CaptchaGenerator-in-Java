import com.mewebstudio.captcha.Captcha;
import com.mewebstudio.captcha.Config;
import com.mewebstudio.captcha.GeneratedCaptcha;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CaptchaVerificationDemo {
    private static String currentCaptchaCode;

    public static void main(String[] args) {
        try {
            // Create captcha instance
            Captcha captcha = new Captcha();
            Config config = captcha.getConfig();
            config.setWidth(200);
            config.setHeight(80);
            config.setLength(6);

            // Generate captcha
            GeneratedCaptcha generatedCaptcha = captcha.generate();
            currentCaptchaCode = generatedCaptcha.getCode();
            BufferedImage captchaImage = generatedCaptcha.getImage();

            // Create GUI
            JFrame frame = new JFrame("Captcha Verification Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(10, 10));
            frame.setSize(300, 200);

            // Add captcha image
            JLabel imageLabel = new JLabel(new ImageIcon(captchaImage));
            frame.add(imageLabel, BorderLayout.NORTH);

            // Add input field
            JTextField inputField = new JTextField(10);
            frame.add(inputField, BorderLayout.CENTER);

            // Add verify button
            JButton verifyButton = new JButton("Verify Captcha");
            verifyButton.addActionListener(e -> {
                String userInput = inputField.getText().trim();
                boolean isCorrect = verifyCaptcha(userInput);
                
                if (isCorrect) {
                    JOptionPane.showMessageDialog(frame, "Correct! Captcha verified.");
                    // Generate new captcha after successful verification
                    refreshCaptcha(captcha, imageLabel);
                    inputField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect! Please try again.");
                }
            });
            frame.add(verifyButton, BorderLayout.SOUTH);

            // Add refresh button
            JButton refreshButton = new JButton("Refresh Captcha");
            refreshButton.addActionListener(e -> {
                refreshCaptcha(captcha, imageLabel);
                inputField.setText("");
            });
            frame.add(refreshButton, BorderLayout.EAST);

            // Show frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Print initial captcha code (for testing purposes)
            System.out.println("Initial Captcha code: " + currentCaptchaCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean verifyCaptcha(String userInput) {
        return userInput.equals(currentCaptchaCode);
    }

    private static void refreshCaptcha(Captcha captcha, JLabel imageLabel) {
        GeneratedCaptcha newCaptcha = captcha.generate();
        currentCaptchaCode = newCaptcha.getCode();
        imageLabel.setIcon(new ImageIcon(newCaptcha.getImage()));
        System.out.println("New Captcha code: " + currentCaptchaCode);
    }
}