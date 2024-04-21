package ejercicio2.persistent;

import com.opencsv.CSVReader;
import ejercicio2.model.Employee;
import ejercicio2.model.EmployeeDAO;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCsvDAO implements EmployeeDAO {
    private final String path;

    public EmployeeCsvDAO(String path) {
        this.path = path;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> csvData = new ArrayList<Employee>();
        try (CSVReader reader = new CSVReader(new FileReader(this.path))) {
            String[] row = null;
            boolean firstRow = true;
            while ((row = reader.readNext()) != null) {
                if(!firstRow){
                    csvData.add(new Employee(row[0],row[1], formattedStringToDate(row[2]), row[3]));
                }
                firstRow = false;
            }
            csvData.removeFirst();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvData;
    }
    private LocalDate formattedStringToDate(String strDate){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(strDate, format);
    }
}
