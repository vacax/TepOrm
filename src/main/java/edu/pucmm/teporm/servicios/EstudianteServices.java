/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pucmm.teporm.servicios;

import edu.pucmm.teporm.entidades.Curso;
import edu.pucmm.teporm.entidades.Estudiante;
import edu.pucmm.teporm.entidades.Profesor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vacax
 */
public class EstudianteServices {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = null;
        EntityManager em = UnidadPersistenciaService.getInstancia().getEntityManager();
        //JPAQL .... HQL
        lista = em.createQuery("select e from Estudiante e").getResultList();
        em.close(); //cerrando el recurso EntityManager.
        return lista;
    }

    
    public Estudiante getEstudiantePorMatricula(String matricula) {
        //Consulta en Hql indicando un parametro.
        String hql = "from Estudiante e where e.matricula=:matricula";
        //Abriendo la conexión.
        EntityManager em = UnidadPersistenciaService.
                getInstancia().getEntityManager();
        //Realizando la consulta y enviando el valor de los parametros.
        Estudiante estudiante = em.createQuery(hql, Estudiante.class).
                setParameter("matricula", matricula).
                getSingleResult();
        //Cerrando el recurso.
        em.close();
        //Retornando la información.
        return estudiante;
    }
   
    
    public Estudiante insertarEstudiante(Estudiante estudiante){
         EntityManager em = UnidadPersistenciaService.
                getInstancia().getEntityManager();
         em.getTransaction().begin();
         em.persist(estudiante);
         em.getTransaction().commit();
         em.close();
         return estudiante;
    }
    
    public Curso insertarCurso(String nombreCurso){
        EntityManager em = UnidadPersistenciaService.
                getInstancia().getEntityManager();
         em.getTransaction().begin();
         
        //buscando un profesor.
        Profesor profesor = em.find(Profesor.class, "001-0001001-1");
        if(profesor == null){
            profesor = new Profesor("001-0001001-1", "Profesor");
            em.persist(profesor);
        } 
        
        Curso curso = new Curso();
        curso.setNombre(nombreCurso);
        curso.setProfesor(profesor);
        
        //agregando los estudiantes registrados.
        List<Estudiante> listarEstudiantes = listarEstudiantes();
        Set<Estudiante> listaSetEstudiante = new HashSet<>(listarEstudiantes);
        curso.setListaEstudiante(listaSetEstudiante);
        
        //Guardando la información...
        em.persist(curso);
        
        em.getTransaction().commit();
        em.close();
        
        return curso;
    }   

}
