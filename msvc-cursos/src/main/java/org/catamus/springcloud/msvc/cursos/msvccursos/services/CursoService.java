package org.catamus.springcloud.msvc.cursos.msvccursos.services;

import org.catamus.springcloud.msvc.cursos.msvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();

    Optional<Curso> porId();

    Curso guardar(Curso curso);

    void eliminar(Long id);

}
