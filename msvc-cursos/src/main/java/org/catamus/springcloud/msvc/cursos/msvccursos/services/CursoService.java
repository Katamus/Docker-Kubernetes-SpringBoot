package org.catamus.springcloud.msvc.cursos.msvccursos.services;

import org.catamus.springcloud.msvc.cursos.msvccursos.models.Usuario;
import org.catamus.springcloud.msvc.cursos.msvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();

    Optional<Curso> porId(Long id);

    Curso guardar(Curso curso);

    void eliminar(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);

    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}
