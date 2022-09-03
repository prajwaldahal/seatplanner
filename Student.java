public class Student {
    private int studentId;

    private String faculty;

    private String name;

    public Student(int studentId, String name, String faculty) {
        this.studentId = studentId;
        this.faculty = faculty;
        this.name = name;
    }

    public int getStudentid() {
        return this.studentId;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public String getName() {
        return this.name;
    }
}
