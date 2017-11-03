package cr.ac.ucr.ecci.ci1312.xbase.support;

import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Proyecto de Bases de Datos 1
 * xBase
 * Autores:
 * Alemán Ramírez Esteban
 * Borchgrevink Leiva Alexia
 * Cubero Sánchez Josué
 * Durán Gregory Ian
 * Garita Centeno Alonso
 * Hidalgo Campos Jose
 * Mellado Xatruch Carlos
 * Muñoz Miranda Roy
 *
 * Primer ciclo 2017
 */
public class SecurityUtils {

    public static Administrator getCurrentLoggedAdmin(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof Administrator)
            return (Administrator) user;
        return null;
    }

    public static Student getCurrentLoggedStudent(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof Student)
            return (Student) user;
        return null;
    }

    public static void validatePassword(String password){
        char firstChar = password.charAt(0);
        boolean startsWithNum = firstChar >= 48 && firstChar <= 57;
        if (startsWithNum)
            throw new IllegalArgumentException("Invalid password, cannot start with a number.");

        boolean notLongEnough = password.length() < 8;
        if (notLongEnough)
            throw new IllegalArgumentException("Invalid password, at least 8 characters required.");

        boolean noNumbers = true;
        for (int i = 1; i < password.length(); i++) {
            char c = password.charAt(i);
            noNumbers = c >= 48 && c <= 57;
            if (!noNumbers) break;
        }
        if (noNumbers)
            throw new IllegalArgumentException("Invalid password, password should at least have a number.");
    }
}
