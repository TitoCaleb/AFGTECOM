package afg.tecom.afgtecom.service;


import afg.tecom.afgtecom.domain.Usuarios;
import java.util.List;
import java.util.Optional;

/**
 * Simple Interface for Usuarios.
 */
public interface UsuariosService {

    Optional<Usuarios> findOne(Integer id);

    List<Usuarios> findAll();

    Usuarios save(Usuarios dto);

    void delete(Integer id);

    void deleteAll();

}
