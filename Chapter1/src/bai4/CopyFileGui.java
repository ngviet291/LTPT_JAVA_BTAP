import javax.swing.*;
import java.io.*;

public class CopyFileGui extends JFrame {

    private JTextField tfFrom;
    private JTextField tfTo;
    private JButton btnCopyFIl;
    private JProgressBar progressBar;

    public CopyFileGui() {
        initUI();
    }

    private void initUI() {
        setTitle("Copying File");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box b, b1, b2, b3, b4;

        add(b = Box.createVerticalBox());
        b.add(b1 = Box.createHorizontalBox());
        b.add(b2 = Box.createHorizontalBox());
        b.add(b3 = Box.createHorizontalBox());
        b.add(b4 = Box.createHorizontalBox());

        b1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("From")));
        b2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("To")));

        b4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        b1.add(tfFrom = new JTextField(20));
        b2.add(tfTo = new JTextField(20));
        b3.add(btnCopyFIl = new JButton("Copy..."));
        b4.add(progressBar = new JProgressBar(0, 100));
        progressBar.setStringPainted(true);

        tfFrom.setText("D:/HKI2-NAM3/LTPT/doctest/test1.docx");
        tfTo.setText("D:/HKI2-NAM3/LTPT/doctest/test2.docx");

        btnCopyFIl.addActionListener(e -> {
            String from = tfFrom.getText();
            String to = tfTo.getText();

            CopyFileTask task = new CopyFileTask(from, to, progressBar);
            task.addPropertyChangeListener(evt ->{
                if("progress".equalsIgnoreCase(evt.getPropertyName())){
                    progressBar.setValue((Integer)evt.getNewValue());
                }
            });
            task.execute();
        });
    }
    class CopyFileTask extends SwingWorker<Void,Integer>{
        private String fromFile;
        private String toFile;
        private JProgressBar progressBar;
        
        public CopyFileTask(String fromFile, String toFile, JProgressBar progressBar) {
            this.fromFile = fromFile;
            this.toFile = toFile;
            this.progressBar = progressBar;
        }

        @Override
        protected Void doInBackground() throws Exception {
            File fromF = new File(fromFile);
            File toF = new File(toFile);
            if (!fromF.exists()) {
                JOptionPane.showMessageDialog(CopyFileGui.this, "Khong co file","Error",JOptionPane.ERROR_MESSAGE);
                return null;
            }
            long fileSize = fromF.length();
            long totalBytesRead=0;
            try (
                BufferedInputStream bis= new BufferedInputStream(new FileInputStream(fromFile));
                BufferedOutputStream bos= new BufferedOutputStream( new FileOutputStream(toF))
            ){  
                //Đọc theo từng 8kb Không đọc hết 1 lần để tiết kiệm bộ nhớ
                byte[] buffer = new byte[8192];
                int bytesRead;

                while ((bytesRead = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                    totalBytesRead+=bytesRead;
                    // Làm thanh tiến trình hoàn thành
                    int progress = (int) ((totalBytesRead * 100) / fileSize);
                    setProgress(progress);
                    Thread.sleep(100);
                }
                JOptionPane.showMessageDialog(CopyFileGui.this,"Da copy thanh cong","Success",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void done() {
            progressBar.setValue(0);
            btnCopyFIl.setEnabled(true);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            CopyFileGui ex = new CopyFileGui();
            ex.setVisible(true);
        });
        
    }

}