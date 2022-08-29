package afg.tecom.afgtecom.service;

import afg.tecom.afgtecom.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Optional<Categoria> findOne(Integer id);

    List<Categoria> findAll();

    Categoria save(Categoria dto);

    void delete(Integer id);

    void deleteAll();
}
