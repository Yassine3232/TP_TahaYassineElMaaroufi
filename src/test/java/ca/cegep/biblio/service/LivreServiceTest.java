package ca.cegep.biblio.service;
import ca.cegep.biblio.model.Livre;
import ca.cegep.biblio.repository.LivreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * TP partie C : à vous d'implémenter les 3 tests ci-dessous.
 * Retirez @Disabled de chaque test une fois son implémentation écrite.
 * Respectez le modèle Arrange / Act / Assert.
 */
@ExtendWith(MockitoExtension.class)
class LivreServiceTest {

    @Mock
    private LivreRepository repository;

    @InjectMocks
    private LivreService service;

    // ---------- 1 test simple (sans mock) ----------

    @Test
    void calculerFraisRetard_longRetard_plafonneLeMontant() {
        // À tester : pour une catégorie donnée, un très grand nombre de jours de retard
        // ne dépasse jamais le plafond de cette catégorie.
        // Indice : la méthode ne dépend pas du repository.
        
        //Arrange: SCIENCE = 0,50$/jour, plafond 20 $
        //Act
        double frais = service.calculerFraisRetard("SCIENCE", 100);
        //Assert
        assertEquals(20.0, frais, 0.001);
    }

    // ---------- 1 test sur une exception (assertThrows) ----------

    @Test
    void emprunter_livreDejaEmprunte_lanceIllegalStateException() {
        // À tester : emprunter un livre dont disponible == false lance une IllegalStateException.
        // Indice : le repository (mock) doit retourner ce livre pour son id.

        // Arrange
        Livre livre = new Livre(1L, "Taha Yassine", "Taha Yassine", "123", 2026, "");
        livre.setDisponible(false);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(livre));
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> service.emprunter(1L));
        verify(repository, never()).save(any()); 
    }

    // ---------- 1 test avec mock (when / thenReturn / verify) ----------

    @Test
    void emprunter_livreDisponible_marqueIndisponibleEtSauvegarde() {
        // Arrange
        Livre livre = new Livre(1L, "Taha Yassine", "Taha Yassine", "123", 2026, "");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(livre));
        when(repository.save(any(Livre.class))).thenReturn(livre);
        // Act
        Livre result = service.emprunter(1L);
        // Assert
        assertFalse(result.isDisponible());
        verify(repository, times(1)).save(livre);
    }
}


