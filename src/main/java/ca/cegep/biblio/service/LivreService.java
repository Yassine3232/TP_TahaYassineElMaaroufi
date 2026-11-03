package ca.cegep.biblio.service;

import ca.cegep.biblio.exception.LivreIntrouvableException;
import ca.cegep.biblio.model.Livre;
import ca.cegep.biblio.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Logique métier de la bibliothèque. */
@Service
public class LivreService {

    private static final int ANNEE_MIN = 1450;
    private static final int ANNEE_MAX = 2100;
    private static final double TARIF_SCIENCE = 0.50;
    private static final double TARIF_LITTERATURE = 0.25;
    private static final double TARIF_HISTOIRE = 0.75;
    private static final double PLAFOND_SCIENCE = 20;
    private static final double PLAFOND_LITTERATURE = 10;
    private static final double PLAFOND_HISTOIRE = 30;

    private final LivreRepository repository;

    public LivreService(LivreRepository repository) {
        this.repository = repository;
    }

    public List<Livre> lister() {
        return repository.findAll();
    }

    public Livre trouver(Long id) {
        return repository.findById(id).orElseThrow(() -> new LivreIntrouvableException(id));
    }

    public Livre creer(String titre, String auteur, String isbn, int anneePublication, String categorie) {
        valider(titre, auteur, anneePublication);

        if (anneePublication < ANNEE_MIN || anneePublication > ANNEE_MAX) {
            throw new IllegalArgumentException("L'année de publication est invalide");
        }
        Livre livre = new Livre(null, titre, auteur, isbn, anneePublication, categorie);
        return repository.save(livre);
    }

    private void valider(String titre, String auteur, int anneePublication) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }
        if (auteur == null || auteur.isBlank()) {
            throw new IllegalArgumentException("L'auteur est obligatoire");
        }
        if (anneePublication < ANNEE_MIN || anneePublication > ANNEE_MAX) {
            throw new IllegalArgumentException("L'année de publication est invalide");
        }
    }

    public Livre modifier(Long id, String titre, String auteur, String isbn, int anneePublication, String categorie) {
        Livre livre = trouver(id);
        valider(titre, auteur, anneePublication);
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setIsbn(isbn);
        livre.setAnneePublication(anneePublication);
        livre.setCategorie(categorie);
        return repository.save(livre);
    }

    public void supprimer(Long id) {
        trouver(id);
        repository.deleteById(id);
    }

    public Livre emprunter(Long id) {
        Livre livre = trouver(id);
        if (!livre.isDisponible()) {
            throw new IllegalStateException("Ce livre est déjà emprunté");
        }
        livre.setDisponible(false);
        return repository.save(livre);
    }

    public Livre retourner(Long id) {
        Livre livre = trouver(id);
        if (livre.isDisponible()) {
            throw new IllegalStateException("Ce livre n'est pas emprunté");
        }
        livre.setDisponible(true);
        return repository.save(livre);
    }

    public double calculerFraisRetard(String categorie, int joursRetard) {
        double frais = 0;
        if(joursRetard <= 0) {
            return frais;
        }
        return switch (categorie) {
            case "SCIENCE" -> plafonner(joursRetard * TARIF_SCIENCE, PLAFOND_SCIENCE);
            case "LITTERATURE" -> plafonner(joursRetard * TARIF_LITTERATURE, PLAFOND_LITTERATURE);
            case "HISTOIRE" -> plafonner(joursRetard * TARIF_HISTOIRE, PLAFOND_HISTOIRE);
            default -> plafonner(joursRetard * TARIF_LITTERATURE, PLAFOND_LITTERATURE);
        };
    }
    private double plafonner(double montant, double plafond) {
        return Math.min(montant, plafond);
    }
}
