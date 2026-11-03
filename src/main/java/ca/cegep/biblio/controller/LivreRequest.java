package ca.cegep.biblio.controller;

import io.swagger.v3.oas.annotations.media.Schema;

/** Corps JSON reçu pour créer ou modifier un livre. */
public record LivreRequest(
        @Schema(example = "Les Misérables") String titre,
        @Schema(example = "Victor Hugo") String auteur,
        @Schema(example = "9782253096344") String isbn,
        @Schema(example = "1862") int anneePublication,
        @Schema(example = "ROMAN", description = "ROMAN, SCIENCE ou JEUNESSE") String categorie) {
}
