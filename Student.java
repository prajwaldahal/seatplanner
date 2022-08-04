public class Student {
    private int studentId;
    private String faculty,name;

    public Student(int studentId, String name, String faculty) {
        this.studentId = studentId;
        this.faculty = faculty;
        this.name = name;
    }

    public int getStudentid() {
        return studentId;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getName() {
        return name;
    }
}
