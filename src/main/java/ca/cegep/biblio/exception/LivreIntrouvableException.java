package ca.cegep.biblio.exception;

public class LivreIntrouvableException extends RuntimeException {

    public LivreIntrouvableException(Long id) {
        super("Aucun livre avec l'id " + id);
    }
}
