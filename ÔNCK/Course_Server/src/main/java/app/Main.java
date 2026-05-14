package app;

import entity.Department;
import entity.Student;
import network.ClientHandle;
import service.DepartmentService;
import service.StudentService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
//        StudentService studentService = new StudentService();
//        Map<Student,Double> map = studentService.getAverageScoreOfStudents();
//        map.forEach(((student, aDouble) -> System.out.println(student+"   :   "+aDouble)));
//        DepartmentService departmentService= new DepartmentService();
//        Map<Department,Long> departmentLongMap = departmentService.getNumberOfStudentsByDepartment();
//        departmentLongMap.forEach((department, aLong) -> System.out.println(department+ "   :   "+aLong));
//        List<Department> departments = departmentService.listDepartmentsWithoutStudents();
//        for(Department department:departments){
//            System.out.println(department);
//        }
//        List<Student> students = studentService.listStudentsStudyingCourseWithHighestScore("Microeconomics");
//        for (Student student :students){
//            System.out.println(student);
//        }
        try (ServerSocket serverSocket = new ServerSocket(0721)){
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());
                executorService.execute(new ClientHandle(socket));
            }
        }
    }
}
