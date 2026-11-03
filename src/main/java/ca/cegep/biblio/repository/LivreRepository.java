package ca.cegep.biblio.repository;

import ca.cegep.biblio.model.Livre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Accès aux données : les livres sont gardés en mémoire (perdus à l'arrêt de l'application). */
@Repository
public class LivreRepository {

    private final Map<Long, Livre> livres = new LinkedHashMap<>();
    private long prochainId = 1;

    public LivreRepository() {
        save(new Livre(null, "Le Petit Prince", "Antoine de Saint-Exupéry", "9782070612758", 1943, "JEUNESSE"));
        save(new Livre(null, "Une brève histoire du temps", "Stephen Hawking", "9782080812117", 1988, "SCIENCE"));
        save(new Livre(null, "Bonheur d'occasion", "Gabrielle Roy", "9782890523982", 1945, "ROMAN"));
    }

    public List<Livre> findAll() {
        return new ArrayList<>(livres.values());
    }

    public Optional<Livre> findById(Long id) {
        return Optional.ofNullable(livres.get(id));
    }

    public Livre save(Livre livre) {
        if (livre.getId() == null) {
            livre.setId(prochainId++);
        }
        livres.put(livre.getId(), livre);
        return livre;
    }

    public void deleteById(Long id) {
        livres.remove(id);
    }
}
