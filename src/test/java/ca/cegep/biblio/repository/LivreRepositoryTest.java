package ca.cegep.biblio.repository;

import ca.cegep.biblio.model.Livre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Exemple de test fourni : suivez le même modèle Arrange / Act / Assert pour les vôtres. */
class LivreRepositoryTest {

    @Test
    void save_attribueUnIdALUnNouveauLivre() {
        // Arrange
        LivreRepository repository = new LivreRepository();
        Livre nouveau = new Livre(null, "Dune", "Frank Herbert", "9782266320481", 1965, "ROMAN");

        // Act
        Livre sauvegarde = repository.save(nouveau);

        // Assert
        assertEquals(4L, sauvegarde.getId()); // 3 livres existent déjà au démarrage
        assertTrue(repository.findById(4L).isPresent());
    }
}
