package ca.cegep.biblio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/** Transforme les exceptions du service en réponses HTTP lisibles (404, 400, 409). */
@RestControllerAdvice
public class GestionnaireErreurs {

    @ExceptionHandler(LivreIntrouvableException.class)
    public ResponseEntity<Map<String, String>> introuvable(LivreIntrouvableException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erreur", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> requeteInvalide(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erreur", e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> conflit(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erreur", e.getMessage()));
    }
}
