import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class lab5_4 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Program with JTextArea");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea(8, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnShow = new JButton("Show message");
        JButton btnSave = new JButton("Save");   // ⭐ ปุ่ม Save

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                JOptionPane.showMessageDialog(frame, text,
                        "Your message:", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("D:\\message.txt");
                    writer.write(textArea.getText());
                    writer.close();

                    JOptionPane.showMessageDialog(frame,
                            "Saved to D:\\message.txt successfully");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Error saving file", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panelButton = new JPanel();
        panelButton.add(btnSave);
        panelButton.add(btnShow);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
