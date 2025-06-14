import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Map<String, Integer> subjectMarks;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.subjectMarks = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addSubjectMark(String subject, int mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        subjectMarks.put(subject, mark);
    }

    public int getTotalMarks() {
        return subjectMarks.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getAverageMarks() {
        return subjectMarks.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Subjects:\n");
        subjectMarks.forEach((subject, mark) -> sb.append("  ").append(subject).append(": ").append(mark).append("\n"));
        sb.append("Total Marks: ").append(getTotalMarks()).append("\n");
        sb.append("Average: ").append(String.format("%.2f", getAverageMarks())).append("\n");
        return sb.toString();
    }
}
