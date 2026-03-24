package bai5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MySimpleNotepad extends JFrame implements ActionListener {

    private JTextArea tpContent;
    private JLabel lblStatus;

    private JMenuItem itemNew, itemOpen, itemSave, itemPrint, itemExit;

    public MySimpleNotepad() {
        initUI();
    }

    private void initUI() {
        setTitle("Ty teo simple Notepad");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== TEXT AREA =====
        tpContent = new JTextArea();
        tpContent.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tpContent);
        add(scrollPane, BorderLayout.CENTER);

        // ===== STATUS BAR =====
        lblStatus = new JLabel("Ready...");
        lblStatus.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        add(lblStatus, BorderLayout.SOUTH);

        // ===== MENU BAR =====
        JMenuBar menuBar = new JMenuBar();

        JMenu mFile = new JMenu("File");
        JMenu mEdit = new JMenu("Edit");
        JMenu mHelp = new JMenu("Help");
        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open");
        itemSave = new JMenuItem("Save");
        itemPrint = new JMenuItem("Print");
        itemExit = new JMenuItem("Exit");

        itemNew.addActionListener(this);
        itemOpen.addActionListener(this);
        itemSave.addActionListener(this);
        itemPrint.addActionListener(this);
        itemExit.addActionListener(this);

        mFile.add(itemNew);
        mFile.add(itemOpen);
        mFile.add(itemSave);
        mFile.addSeparator();
        mFile.add(itemPrint);
        mFile.addSeparator();
        mFile.add(itemExit);

        menuBar.add(mFile);
        menuBar.add(mEdit);
        menuBar.add(mHelp);
        setJMenuBar(menuBar);
    }

    // ===== EVENT HANDLING =====
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == itemNew) {
            tpContent.setText("");
            lblStatus.setText("New file");
        }
        if (e.getSource() == itemOpen) {
            openFile();
        }
        if (e.getSource() == itemSave) {
            saveFile();
        }

        if (e.getSource() == itemPrint) {
            try {
                tpContent.print();
                lblStatus.setText("Printing...");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == itemExit) {
            System.exit(0);
        }
    }

    // Mở file
    private void openFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            lblStatus.setText("Đang tải file: " + file.getName() + "...");

            // Sử dụng Thread để load file (không block UI)
            SimpleNoteTask task = new SimpleNoteTask(file);
            task.execute();
        }
    }

    // Lưu file
    private void saveFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(tpContent.getText());
                lblStatus.setText("Đã lưu: " + file.getName());
                JOptionPane.showMessageDialog(this, "Lưu file thành công!");
            } catch (Exception ex) {
                lblStatus.setText("Lỗi khi lưu file!");
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
    }

    // Class này giúp load file lớn mà không làm treo giao diện
    class SimpleNoteTask extends SwingWorker<String, Void> {
        private File file;

        public SimpleNoteTask(File file) {
            this.file = file;
        }

        @Override
        protected String doInBackground() throws Exception {
            // Đọc file trong background thread
            StringBuilder content = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }

            return content.toString();
        }

        @Override
        protected void done() {
            try {
                // Lấy kết quả và hiển thị lên TextArea
                String content = get();
                tpContent.setText(content);
                lblStatus.setText("Đã tải xong: " + file.getName());
            } catch (Exception e) {
                lblStatus.setText("Lỗi khi đọc file!");
                JOptionPane.showMessageDialog(MySimpleNotepad.this,
                        "Lỗi: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MySimpleNotepad().setVisible(true);
        });
    }
}
