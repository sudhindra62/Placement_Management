package src;
public interface PlacementOperations {
    void addStudent(String name, double cgpa) throws Exception;
    boolean checkEligibility(int studentId, int companyId) throws Exception;
    void applyCompany(int studentId, int companyId) throws Exception;
}
