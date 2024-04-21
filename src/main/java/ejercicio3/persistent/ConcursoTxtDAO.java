package ejercicio3.persistent;

import ejercicio3.model.Concurso;
import ejercicio3.model.ConcursoDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConcursoTxtDAO implements ConcursoDAO {
    public static final String SEPARADOR = ",";
    private String path;
    public ConcursoTxtDAO(String path){
        this.path = path;
    }
    @Override
    public List<Concurso> getConcursos() {
        List<Concurso> concursos = new ArrayList<Concurso>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String[] row = null;
            boolean firstRow = true;
            while ((row = br.readLine().split(SEPARADOR)) != null) {
                if(!firstRow) {
                    concursos.add(new Concurso(row[0], row[1], formattedStringToDate(row[2]), formattedStringToDate(row[3])));
                }
                firstRow = false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return concursos;
    }
    private LocalDate formattedStringToDate(String strDate){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(strDate, format);
    }
}
