import com.mewebstudio.captcha.Captcha;
import com.mewebstudio.captcha.Config;
import com.mewebstudio.captcha.GeneratedCaptcha;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CaptchaDemo {
    public static void main(String[] args) {
        try {
            // Create captcha instance
            Captcha captcha = new Captcha();
            
            // Configure settings
            Config config = captcha.getConfig();
            config.setWidth(200);
            config.setHeight(80);
            config.setLength(6);
            
            // Generate captcha
            GeneratedCaptcha generatedCaptcha = captcha.generate();
            
            // Save image to file
            File outputFile = new File("captcha.png");
            ImageIO.write(generatedCaptcha.getImage(), "png", outputFile);
            
            // Show image in a window
            JFrame frame = new JFrame("Captcha Preview");
            JLabel label = new JLabel(new ImageIcon(generatedCaptcha.getImage()));
            frame.getContentPane().add(label);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            
            // Print the code
            System.out.println("Captcha code: " + generatedCaptcha.getCode());
            System.out.println("Image saved as: " + outputFile.getAbsolutePath());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}