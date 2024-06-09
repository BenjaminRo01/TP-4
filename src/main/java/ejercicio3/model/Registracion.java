package ejercicio3.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Aspect
public class Registracion {
    @Before("execution(@Log * *(..))")
    public void registrar(JoinPoint joinPoint){
        String valores = "";
        for (Object o : joinPoint.getArgs()) {
            if (!o.toString().isEmpty()) {
                valores += o.toString() + "|";
            }
        }
        valores = (valores.isEmpty()) ? "sin parametros" : valores.substring(0, valores.length() - 1);

        String registro = '\"' + joinPoint.getSignature().getName() + "\",\""
                + valores + "\",\""
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "\"" + "\n";
        try {
            Files.write(Paths.get("src/main/resources/logueo.txt"),
                    registro.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
