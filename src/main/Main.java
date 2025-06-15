package main;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Game");
        window.setLayout(new BorderLayout());

        JLabel label = new JLabel("Hello World !!", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 64)); 
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        window.add(label, BorderLayout.CENTER);

        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setVisible(true);
    }
}
