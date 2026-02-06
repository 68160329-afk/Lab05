import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class lab5_6 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Save File with JMenu");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== TextArea =====
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // ===== Menu Bar =====
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itemSave = new JMenuItem("Save");
        JMenuItem itemExit = new JMenuItem("Exit");

        menuFile.add(itemSave);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);

        // ===== Action Save =====
        itemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    try {
                        PrintWriter writer = new PrintWriter(file);
                        writer.write(textArea.getText());
                        writer.close();

                        JOptionPane.showMessageDialog(frame,
                                "Save file successfully.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Error. Unable to save file.");
                    }
                }
            }
        });

        // ===== Action Exit =====
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ===== Layout =====
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

