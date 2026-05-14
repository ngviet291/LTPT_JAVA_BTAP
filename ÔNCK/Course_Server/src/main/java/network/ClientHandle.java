package network;

import entity.Department;
import service.DepartmentService;
import service.StudentService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandle implements Runnable {
    private Socket socket;
    public ClientHandle(Socket socket) {
        this.socket= socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ) {
            DepartmentService departmentService = new DepartmentService();
            StudentService studentService = new StudentService();
            while (true) {
                Request request;
                try {
                    request = (Request) ois.readObject();
                } catch (EOFException e) {
                    System.out.println("Client disconnected: " + socket.getInetAddress());
                    break;
                }

                Response response = null;
                switch (request.getCommandType()) {
                    case GET_NUMBER_OF_STUDENTS_BY_DEPARTMENT -> {
                        response = Response.builder().data(departmentService.getNumberOfStudentsByDepartment()).build();
                    }
                    case GET_AVERAGE_SCORE_OF_STUDENTS -> {
                        response = Response.builder().data(studentService.getAverageScoreOfStudents()).build();
                    }
                    case LIST_DEPARTMENTS_WITHOUT_STUDENTS -> {
                        response = Response.builder().data(departmentService.listDepartmentsWithoutStudents()).build();
                    }
                    case LIST_STUDENTS_STUDYING_COURSE_WITH_HIGHEST_SCORE -> {
                        String name = request.getData().toString();
                        response = Response.builder().data(studentService.listStudentsStudyingCourseWithHighestScore(name)).build();
                    }
                }
                oos.writeObject(response);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
