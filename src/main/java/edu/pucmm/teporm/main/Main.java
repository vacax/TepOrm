package edu.pucmm.teporm.main;

import edu.pucmm.teporm.entidades.Estudiante;
import edu.pucmm.teporm.servicios.EstudianteServices;
import java.util.List;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Estudiante> listarEstudiantes = new EstudianteServices().
                listarEstudiantes();
        
        for(Estudiante estudiante : listarEstudiantes){
            System.out.printf("%s - %s\n", estudiante.getMatricula(),
                    estudiante.getNombre());
        }
        
    }
}
