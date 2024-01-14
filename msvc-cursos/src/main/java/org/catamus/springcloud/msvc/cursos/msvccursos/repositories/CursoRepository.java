package org.catamus.springcloud.msvc.cursos.msvccursos.repositories;

import org.catamus.springcloud.msvc.cursos.msvccursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso,Long> {

    @Modifying
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuariosPorId(Long id);

}
