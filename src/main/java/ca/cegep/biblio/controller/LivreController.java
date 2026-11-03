package ca.cegep.biblio.controller;

import ca.cegep.biblio.model.Livre;
import ca.cegep.biblio.service.LivreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** CONTRÔLEUR (le « C » de MVC) : reçoit les requêtes HTTP et délègue au service. */
@RestController
@RequestMapping("/api/livres")
@Tag(name = "Livres", description = "Gestion des livres de la bibliothèque")
public class LivreController {

    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister tous les livres")
    public List<Livre> lister() {
        return service.lister();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un livre par son id")
    public Livre trouver(@PathVariable Long id) {
        return service.trouver(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ajouter un livre")
    public Livre creer(@RequestBody LivreRequest requete) {
        return service.creer(requete.titre(), requete.auteur(), requete.isbn(),
                requete.anneePublication(), requete.categorie());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un livre")
    public Livre modifier(@PathVariable Long id, @RequestBody LivreRequest requete) {
        return service.modifier(id, requete.titre(), requete.auteur(), requete.isbn(),
                requete.anneePublication(), requete.categorie());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un livre")
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }

    @PostMapping("/{id}/emprunter")
    @Operation(summary = "Emprunter un livre")
    public Livre emprunter(@PathVariable Long id) {
        return service.emprunter(id);
    }

    @PostMapping("/{id}/retourner")
    @Operation(summary = "Retourner un livre")
    public Livre retourner(@PathVariable Long id) {
        return service.retourner(id);
    }

    @GetMapping("/{id}/frais-retard")
    @Operation(summary = "Calculer les frais de retard d'un livre (en $)")
    public Map<String, Object> fraisRetard(@PathVariable Long id, @RequestParam int joursRetard) {
        Livre livre = service.trouver(id);
        double frais = service.calculerFraisRetard(livre.getCategorie(), joursRetard);
        return Map.of("livreId", id, "joursRetard", joursRetard, "frais", frais);
    }
}
