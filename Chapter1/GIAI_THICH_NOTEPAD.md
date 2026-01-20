# GIẢI THÍCH CHI TIẾT - SIMPLE NOTEPAD VỚI MULTI-THREADING

## 📋 TỔNG QUAN

Ứng dụng Simple Notepad là một trình soạn thảo văn bản đơn giản sử dụng Java Swing, có khả năng:

- Tạo file mới, mở file, lưu file, in file
- **Sử dụng Multi-threading để load file lớn (20MB+) không bị treo giao diện**

---

## 🏗️ CẤU TRÚC CLASS

```
MySimpleNotepad.java
├── Class MySimpleNotepad (Main class - JFrame)
│   ├── Các biến thành viên
│   ├── Constructor
│   ├── initUI() - Khởi tạo giao diện
│   ├── actionPerformed() - Xử lý sự kiện
│   ├── openFile() - Mở file
│   ├── saveFile() - Lưu file
│   └── Class SimpleNoteTask (SwingWorker - Multi-threading)
│       ├── doInBackground() - Load file ở background
│       └── done() - Hiển thị kết quả lên UI
└── main() - Khởi chạy ứng dụng
```

---

## 📝 GIẢI THÍCH TỪNG PHẦN CODE

### 1️⃣ KHAI BÁO PACKAGE VÀ IMPORT

```java
package bai5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
```

**Giải thích:**

- `package bai5;` - Khai báo class thuộc package bai5
- `javax.swing.*` - Thư viện GUI (JFrame, JTextArea, JButton...)
- `java.awt.*` - Thư viện giao diện cơ bản (Layout, Font, Color...)
- `java.awt.event.*` - Thư viện xử lý sự kiện (ActionListener, ActionEvent...)
- `java.io.*` - Thư viện đọc/ghi file (File, BufferedReader, FileWriter...)

---

### 2️⃣ KHAI BÁO CLASS CHÍNH

```java
public class MySimpleNotepad extends JFrame implements ActionListener {
```

**Giải thích:**

- `extends JFrame` - Kế thừa từ JFrame để tạo cửa sổ ứng dụng
- `implements ActionListener` - Để xử lý sự kiện click menu (actionPerformed)

---

### 3️⃣ CÁC BIẾN THÀNH VIÊN

```java
private JTextArea tpContent;
private JLabel lblStatus;
private JMenuItem itemNew, itemOpen, itemSave, itemPrint, itemExit;
```

**Giải thích:**

- `JTextArea tpContent` - Vùng văn bản chính để hiển thị/chỉnh sửa nội dung file
- `JLabel lblStatus` - Thanh trạng thái (hiển thị thông báo ở dưới cùng)
- `JMenuItem` - Các item trong menu (New, Open, Save, Print, Exit)

**Ví dụ minh họa:**

```
┌─────────────────────────────────────┐
│ File  Edit  Help          [Menu Bar]│
├─────────────────────────────────────┤
│                                     │
│  [tpContent - JTextArea]            │
│  Nội dung file hiển thị ở đây...   │
│                                     │
├─────────────────────────────────────┤
│ [lblStatus: "Ready..."]  [Status Bar]│
└─────────────────────────────────────┘
```

---

### 4️⃣ CONSTRUCTOR - KHỞI TẠO

```java
public MySimpleNotepad() {
    initUI();
}
```

**Giải thích:**

- Constructor được gọi khi tạo object: `new MySimpleNotepad()`
- Gọi `initUI()` để khởi tạo giao diện

---

### 5️⃣ HÀM initUI() - KHỞI TẠO GIAO DIỆN

#### 5.1. Thiết lập cửa sổ chính

```java
private void initUI() {
    setTitle("Ty teo simple Notepad");
    setSize(600, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
```

**Giải thích từng dòng:**

- `setTitle(...)` - Đặt tiêu đề cửa sổ
- `setSize(600, 500)` - Đặt kích thước cửa sổ (rộng 600px, cao 500px)
- `setLocationRelativeTo(null)` - Hiển thị cửa sổ giữa màn hình
- `setDefaultCloseOperation(EXIT_ON_CLOSE)` - Đóng ứng dụng khi click nút X

#### 5.2. Tạo vùng văn bản

```java
    // ===== TEXT AREA =====
    tpContent = new JTextArea();
    tpContent.setFont(new Font("Consolas", Font.PLAIN, 14));
    JScrollPane scrollPane = new JScrollPane(tpContent);
    add(scrollPane, BorderLayout.CENTER);
```

**Giải thích:**

- `new JTextArea()` - Tạo vùng văn bản để hiển thị nội dung file
- `setFont(...)` - Đặt font chữ Consolas, cỡ 14
- `new JScrollPane(tpContent)` - Thêm thanh cuộn cho TextArea
- `add(..., BorderLayout.CENTER)` - Đặt TextArea vào giữa cửa sổ

**Ví dụ:**

```
Khi file có nhiều dòng:
┌───────────────────────┐
│ Dòng 1...             │
│ Dòng 2...             │
│ Dòng 3...             │
│ ...                   │
│ Dòng 1000...          ║◀ Scrollbar
└───────────────────────┘
```

#### 5.3. Tạo thanh trạng thái

```java
    // ===== STATUS BAR =====
    lblStatus = new JLabel("Ready...");
    lblStatus.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
    add(lblStatus, BorderLayout.SOUTH);
```

**Giải thích:**

- `new JLabel("Ready...")` - Tạo label hiển thị "Ready..."
- `setBorder(...)` - Thêm padding (top=3, left=5, bottom=3, right=5)
- `add(..., BorderLayout.SOUTH)` - Đặt ở dưới cùng

#### 5.4. Tạo Menu Bar

```java
    // ===== MENU BAR =====
    JMenuBar menuBar = new JMenuBar();

    JMenu mFile = new JMenu("File");
    JMenu mEdit = new JMenu("Edit");
    JMenu mHelp = new JMenu("Help");
```

**Giải thích:**

- `JMenuBar` - Thanh menu chính
- `JMenu` - Các menu con (File, Edit, Help)

```java
    itemNew = new JMenuItem("New");
    itemOpen = new JMenuItem("Open");
    itemSave = new JMenuItem("Save");
    itemPrint = new JMenuItem("Print");
    itemExit = new JMenuItem("Exit");
```

**Giải thích:**

- `JMenuItem` - Các item trong menu File

```java
    itemNew.addActionListener(this);
    itemOpen.addActionListener(this);
    itemSave.addActionListener(this);
    itemPrint.addActionListener(this);
    itemExit.addActionListener(this);
```

**Giải thích:**

- `addActionListener(this)` - Đăng ký sự kiện click cho mỗi item
- Khi click, hàm `actionPerformed()` sẽ được gọi

```java
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
```

**Giải thích:**

- `mFile.add(...)` - Thêm item vào menu File
- `addSeparator()` - Thêm dấu gạch ngang phân cách
- `setJMenuBar(menuBar)` - Đặt menu bar cho JFrame

**Kết quả:**

```
┌─────────────────────────────────┐
│ File ▼  Edit  Help              │  ◀ Menu Bar
│ ├─ New                          │
│ ├─ Open                         │
│ ├─ Save                         │
│ ├─────────── (separator)        │
│ ├─ Print                        │
│ ├─────────── (separator)        │
│ └─ Exit                         │
└─────────────────────────────────┘
```

---

### 6️⃣ XỬ LÝ SỰ KIỆN - actionPerformed()

```java
@Override
public void actionPerformed(ActionEvent e) {
```

**Giải thích:**

- Hàm này được gọi tự động khi user click vào menu item
- `ActionEvent e` chứa thông tin về sự kiện (item nào được click)

#### 6.1. Xử lý New

```java
    if (e.getSource() == itemNew) {
        tpContent.setText("");
        lblStatus.setText("New file");
    }
```

**Giải thích:**

- `e.getSource() == itemNew` - Kiểm tra xem item "New" có được click không
- `setText("")` - Xóa trắng TextArea
- Cập nhật status: "New file"

#### 6.2. Xử lý Open

```java
    if (e.getSource() == itemOpen) {
        openFile();
    }
```

**Giải thích:**

- Gọi hàm `openFile()` để mở file

#### 6.3. Xử lý Save

```java
    if (e.getSource() == itemSave) {
        saveFile();
    }
```

**Giải thích:**

- Gọi hàm `saveFile()` để lưu file

#### 6.4. Xử lý Print

```java
    if (e.getSource() == itemPrint) {
        try {
            tpContent.print();
            lblStatus.setText("Printing...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
```

**Giải thích:**

- `tpContent.print()` - Mở dialog in ấn của hệ thống
- `try-catch` - Bắt lỗi nếu có

#### 6.5. Xử lý Exit

```java
    if (e.getSource() == itemExit) {
        System.exit(0);
    }
```

**Giải thích:**

- `System.exit(0)` - Thoát ứng dụng
- `0` nghĩa là thoát thành công

---

### 7️⃣ HÀM openFile() - MỞ FILE ⭐⭐⭐

```java
private void openFile() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(this);
```

**Giải thích:**

- `JFileChooser` - Dialog chọn file của hệ thống
- `showOpenDialog(this)` - Hiển thị dialog "Open file"
- Trả về `JFileChooser.APPROVE_OPTION` nếu user chọn file

```java
    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        lblStatus.setText("Đang tải file: " + file.getName() + "...");
```

**Giải thích:**

- `getSelectedFile()` - Lấy file user đã chọn
- Cập nhật status: "Đang tải file..."

```java
        // Sử dụng Thread để load file (không block UI)
        SimpleNoteTask task = new SimpleNoteTask(file);
        task.execute();
    }
}
```

**Giải thích - QUAN TRỌNG:**

- ❌ **KHÔNG dùng**: `String content = readFile(file); tpContent.setText(content);`
  - Vì nếu file lớn (20MB), việc đọc file sẽ mất vài giây
  - Trong lúc đó, giao diện sẽ BỊ ĐÓNG BĂNG, không thể click gì
- ✅ **DÙNG SwingWorker** (Multi-threading):
  - Tạo thread riêng để đọc file
  - UI vẫn hoạt động bình thường
  - Khi đọc xong, tự động hiển thị lên TextArea

**Minh họa:**

```
┌─────────────────────────────────┐
│         Open File Dialog        │
├─────────────────────────────────┤
│  📁 Documents                   │
│  📁 Downloads                   │
│  📄 test_20mb.txt  ◀ Chọn file  │
│  📄 MyFile.txt                  │
│                                 │
│         [Open] [Cancel]         │
└─────────────────────────────────┘
```

---

### 8️⃣ HÀM saveFile() - LƯU FILE

```java
private void saveFile() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showSaveDialog(this);
```

**Giải thích:**

- `showSaveDialog(this)` - Hiển thị dialog "Save file"

```java
    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(tpContent.getText());
            lblStatus.setText("Đã lưu: " + file.getName());
            JOptionPane.showMessageDialog(this, "Lưu file thành công!");
```

**Giải thích:**

- `BufferedWriter` - Ghi file hiệu quả (buffer data trước khi ghi)
- `new FileWriter(file)` - Mở file để ghi
- `bw.write(tpContent.getText())` - Lấy nội dung từ TextArea và ghi vào file
- `try (...)` - Auto close file khi xong (không cần `bw.close()`)
- `JOptionPane.showMessageDialog()` - Hiển thị popup thông báo

```java
        } catch (Exception ex) {
            lblStatus.setText("Lỗi khi lưu file!");
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }
}
```

**Giải thích:**

- `catch` - Bắt lỗi nếu không ghi được file

---

### 9️⃣ CLASS SimpleNoteTask - MULTI-THREADING ⭐⭐⭐⭐⭐

```java
class SimpleNoteTask extends SwingWorker<String, Void> {
    private File file;

    public SimpleNoteTask(File file) {
        this.file = file;
    }
```

**Giải thích:**

- `SwingWorker<String, Void>` - Class đặc biệt của Swing để làm việc với Thread
  - `String` - Kiểu dữ liệu trả về từ background thread
  - `Void` - Không publish progress (có thể dùng `Integer` để hiện progress bar)
- Constructor nhận file cần đọc

**TẠI SAO DÙNG SwingWorker?**

❌ **KHÔNG dùng Thread thông thường:**

```java
new Thread(() -> {
    String content = readFile(file);
    tpContent.setText(content);  // ❌ LỖI! Không được update UI từ thread khác
}).start();
```

✅ **DÙNG SwingWorker:**

```java
SwingWorker<String, Void> worker = new SwingWorker<>() {
    @Override
    protected String doInBackground() {
        return readFile(file);  // ✓ Chạy trong background thread
    }

    @Override
    protected void done() {
        tpContent.setText(get());  // ✓ Update UI trong EDT (Event Dispatch Thread)
    }
};
worker.execute();
```

#### 9.1. Hàm doInBackground() - ĐỌC FILE Ở BACKGROUND

```java
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
```

**Giải thích từng dòng:**

1. `StringBuilder content = new StringBuilder();`
   - Dùng để ghép chuỗi hiệu quả (nhanh hơn dùng String + String)

2. `BufferedReader br = new BufferedReader(new FileReader(file))`
   - `FileReader` - Đọc file
   - `BufferedReader` - Đọc file theo buffer (nhanh hơn đọc từng ký tự)

3. `while ((line = br.readLine()) != null)`
   - Đọc từng dòng cho đến hết file
   - `readLine()` trả về `null` khi hết file

4. `content.append(line).append("\n")`
   - Thêm dòng vào StringBuilder
   - Thêm ký tự xuống dòng `\n`

5. `return content.toString();`
   - Trả về nội dung file dạng String

**QUAN TRỌNG:**

- Hàm này chạy trong **background thread** (thread riêng)
- Không block UI thread → Giao diện vẫn hoạt động bình thường
- File 20MB sẽ mất vài giây để đọc, nhưng user vẫn có thể click menu

#### 9.2. Hàm done() - HIỂN THỊ KẾT QUẢ LÊN UI

```java
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
```

**Giải thích:**

1. `String content = get();`
   - Lấy kết quả từ `doInBackground()` (String đã return)
   - Nếu có lỗi trong `doInBackground()`, `get()` sẽ throw Exception

2. `tpContent.setText(content);`
   - Hiển thị nội dung lên TextArea

3. `lblStatus.setText("Đã tải xong: " + file.getName());`
   - Cập nhật status bar

**QUAN TRỌNG:**

- Hàm này chạy trong **UI thread (EDT)**
- An toàn để update UI (setText, setVisible...)

---

### 🔟 LUỒNG HOẠT ĐỘNG (FLOW) - MULTI-THREADING

**Khi user click "Open":**

```
1. User click menu "Open"
   ↓
2. actionPerformed() được gọi
   ↓
3. openFile() được gọi
   ↓
4. Hiển thị JFileChooser, user chọn file test_20mb.txt
   ↓
5. Tạo SimpleNoteTask(file)
   ↓
6. Gọi task.execute()
   ↓
   ┌─────────────────────────────────┐
   │  THREAD CHÍNH (UI Thread/EDT)  │  THREAD BACKGROUND
   ├─────────────────────────────────┤─────────────────────────────
   │ - Giao diện vẫn hoạt động       │  doInBackground() {
   │ - User có thể click menu        │    - Đọc file từ đĩa
   │ - Có thể di chuyển cửa sổ       │    - Đọc dòng 1...
   │                                 │    - Đọc dòng 2...
   │                                 │    - ...
   │                                 │    - Đọc dòng 100,000
   │                                 │    return content;
   │                                 │  }
   │                                 │    ↓
   │  done() {                       │  (Chờ xong)
   │    String content = get();      │◀── Lấy kết quả
   │    tpContent.setText(content);  │
   │    lblStatus.setText("Xong!");  │
   │  }                              │
   └─────────────────────────────────┘
```

**SO SÁNH: Không dùng Multi-threading vs Dùng Multi-threading**

❌ **KHÔNG dùng Multi-threading:**

```java
private void openFile() {
    File file = chooseFile();
    String content = readFile(file);  // Mất 5 giây
    tpContent.setText(content);
    // → User phải chờ 5 giây, giao diện bị đóng băng!
}
```

✅ **DÙNG Multi-threading:**

```java
private void openFile() {
    File file = chooseFile();
    new SimpleNoteTask(file).execute();  // Chạy background
    // → UI vẫn hoạt động, user có thể làm việc khác
}
```

---

### 1️⃣1️⃣ HÀM main() - KHỞI CHẠY

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new MySimpleNotepad().setVisible(true);
    });
}
```

**Giải thích:**

1. `SwingUtilities.invokeLater(...)`
   - Đảm bảo UI được tạo trong EDT (Event Dispatch Thread)
   - Best practice cho Swing application

2. `() -> { ... }`
   - Lambda expression (cú pháp ngắn gọn)
   - Tương đương:

   ```java
   new Runnable() {
       public void run() {
           new MySimpleNotepad().setVisible(true);
       }
   }
   ```

3. `new MySimpleNotepad().setVisible(true)`
   - Tạo object MySimpleNotepad
   - Hiển thị cửa sổ

---

## 🎯 TÓM TẮT KIẾN THỨC

### ✅ Kiến thức Swing cơ bản

- **JFrame** - Cửa sổ chính
- **JTextArea** - Vùng văn bản
- **JMenuBar, JMenu, JMenuItem** - Menu
- **JLabel** - Nhãn văn bản
- **JFileChooser** - Dialog chọn file

### ✅ Kiến thức xử lý sự kiện

- **ActionListener** - Interface xử lý sự kiện
- **actionPerformed()** - Hàm được gọi khi có sự kiện

### ✅ Kiến thức đọc/ghi file

- **FileReader** - Đọc file
- **BufferedReader** - Đọc file theo buffer (hiệu quả)
- **FileWriter** - Ghi file
- **BufferedWriter** - Ghi file theo buffer

### ✅ Kiến thức Multi-threading ⭐⭐⭐

- **SwingWorker** - Class đặc biệt cho Swing multi-threading
- **doInBackground()** - Chạy công việc trong background thread
- **done()** - Xử lý kết quả trong UI thread
- **Tại sao cần?** - Tránh block UI khi xử lý công việc nặng

---

## 📊 SO SÁNH: Thread thông thường vs SwingWorker

| Thread thông thường                         | SwingWorker                                   |
| ------------------------------------------- | --------------------------------------------- |
| `new Thread(() -> { ... }).start()`         | `new SwingWorker<>() { ... }.execute()`       |
| ❌ Không được update UI trực tiếp           | ✅ `done()` chạy trong EDT, an toàn update UI |
| ❌ Phải dùng `SwingUtilities.invokeLater()` | ✅ Tự động xử lý                              |
| ❌ Khó publish progress                     | ✅ Có `publish()` và `process()`              |
| ❌ Khó cancel                               | ✅ Có `cancel()` và `isCancelled()`           |

---

## 🚀 CÁCH CHẠY

1. **Compile:**

   ```bash
   cd src
   javac bai5/*.java
   ```

2. **Tạo file test 20MB:**

   ```bash
   java bai5.CreateTestFile
   ```

3. **Chạy Notepad:**

   ```bash
   java bai5.MySimpleNotepad
   ```

4. **Test:**
   - Click menu **File → Open**
   - Chọn file `test_20mb.txt`
   - Quan sát: Giao diện vẫn hoạt động trong lúc load file!

---

## 💡 MỞ RỘNG

Có thể thêm:

1. **Progress Bar** - Hiển thị tiến trình load file
2. **Find/Replace** - Tìm kiếm và thay thế text
3. **Line Numbers** - Hiển thị số dòng
4. **Syntax Highlighting** - Tô màu syntax (cho code)
5. **Recent Files** - Danh sách file gần đây

---

## ❓ CÂU HỎI THƯỜNG GẶP

**Q: Tại sao không dùng Thread thông thường?**
A: Swing không thread-safe. Update UI phải trong EDT. SwingWorker tự động xử lý việc này.

**Q: File bao nhiêu MB thì cần dùng Multi-threading?**
A: Từ 1MB trở lên nên dùng. File nhỏ hơn có thể đọc trực tiếp.

**Q: SwingWorker có thể dùng cho công việc gì khác?**
A: Mọi công việc tốn thời gian: Download file, gọi API, xử lý ảnh, tính toán phức tạp...

**Q: Làm sao biết code đang chạy ở thread nào?**
A:

```java
System.out.println(Thread.currentThread().getName());
// Output: "AWT-EventQueue-0" → UI Thread
// Output: "SwingWorker-pool-1-thread-1" → Background Thread
```

---

## 📚 TÀI LIỆU THAM KHẢO

- Java Swing Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/
- SwingWorker Documentation: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/worker.html
- Multi-threading in Swing: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/

---

**Chúc bạn học tốt! 🎓**
