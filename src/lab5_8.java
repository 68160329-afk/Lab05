import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class lab5_8 {

    static JFrame frame;
    static JTextArea textArea;
    static File currentFile = null;

    public static void main(String[] args) {

        // ===== Frame =====
        frame = new JFrame("นายวิธวินท์ ศรีโกไสย 68160329 n49");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== TextArea =====
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ===== Menu Bar =====
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem itemNew = new JMenuItem("New");
        JMenuItem itemOpen = new JMenuItem("Open");
        JMenuItem itemSave = new JMenuItem("Save");
        JMenuItem itemSaveAs = new JMenuItem("Save As");
        JMenuItem itemExit = new JMenuItem("Exit");

        menuFile.add(itemNew);
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemSaveAs);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);

        // ===== Action: New =====
        itemNew.addActionListener(e -> {
            textArea.setText("");
            currentFile = null;
        });

        // ===== Action: Open =====
        itemOpen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                    textArea.setText("");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Cannot open file", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ===== Action: Save =====
        itemSave.addActionListener(e -> {
            if (currentFile == null) {
                saveAs();
            } else {
                saveFile(currentFile);
            }
        });

        // ===== Action: Save As =====
        itemSaveAs.addActionListener(e -> saveAs());

        // ===== Action: Exit =====
        itemExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // ===== Save As Method =====
    static void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveFile(currentFile);
        }
    }

    // ===== Save File Method =====
    static void saveFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(frame, "File saved successfully");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Cannot save file", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

