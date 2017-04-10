package edu.pucmm.teporm.main;

import edu.pucmm.teporm.entidades.Estudiante;
import edu.pucmm.teporm.servicios.EstudianteServices;
import freemarker.template.SimpleDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EstudianteServices estudianteServices = new EstudianteServices();

        List<Estudiante> listarEstudiantes = estudianteServices.
                listarEstudiantes();

        for (Estudiante estudiante : listarEstudiantes) {
            System.out.printf("%s - %s\n", estudiante.getMatricula(),
                    estudiante.getNombre());
        }

        //
        Estudiante estudiante = estudianteServices.getEstudiantePorMatricula("2001-1136");
        System.out.printf("Consultando estudiante: %s - %s\n", estudiante.getMatricula(),
                estudiante.getNombre());
        
        //
        SimpleDateFormat formatoFecha=new SimpleDateFormat("mmss");
        String secuenciaMatricula = formatoFecha.format(new Date());
        
        //
        Estudiante estudianteNuevo = new Estudiante("2017-"+secuenciaMatricula,
                "Nuevo Estudiante");
        estudianteServices.insertarEstudiante(estudianteNuevo);
        
        estudianteServices.insertarCurso("Mi Curso");

    }

}
