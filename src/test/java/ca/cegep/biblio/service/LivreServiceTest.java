package ca.cegep.biblio.service;

import ca.cegep.biblio.repository.LivreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Disabled("À implémenter")
    void calculerFraisRetard_longRetard_plafonneLeMontant() {
        // À tester : pour une catégorie donnée, un très grand nombre de jours de retard
        // ne dépasse jamais le plafond de cette catégorie.
        // Indice : la méthode ne dépend pas du repository.
    }

    // ---------- 1 test sur une exception (assertThrows) ----------

    @Test
    @Disabled("À implémenter")
    void emprunter_livreDejaEmprunte_lanceIllegalStateException() {
        // À tester : emprunter un livre dont disponible == false lance une IllegalStateException.
        // Indice : le repository (mock) doit retourner ce livre pour son id.
    }

    // ---------- 1 test avec mock (when / thenReturn / verify) ----------

    @Test
    @Disabled("À implémenter")
    void emprunter_livreDisponible_marqueIndisponibleEtSauvegarde() {
        // À tester : emprunter un livre disponible le rend indisponible
        // et appelle repository.save(...) exactement une fois.
        // Définissez le comportement du mock avec when(...).thenReturn(...).
    }
}


