# biblio-api

API REST de gestion d'une petite bibliothèque (Spring Boot 3.5, Java 25, architecture MVC).

## Démarrer

```bash
./mvnw spring-boot:run
```

- Swagger UI : http://localhost:8080/swagger-ui.html
- Les données sont gardées **en mémoire** (perdues à l'arrêt).

## Structure

| Package | Rôle |
|---|---|
| `model` | Le modèle : `Livre` |
| `repository` | Accès aux données (en mémoire) |
| `service` | Logique métier |
| `controller` | Contrôleur REST (+ `LivreRequest`, corps JSON des requêtes) |
| `exception` | Exceptions et traduction en codes HTTP |

## Endpoints

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/livres` | Lister les livres |
| GET | `/api/livres/{id}` | Obtenir un livre |
| POST | `/api/livres` | Ajouter un livre |
| PUT | `/api/livres/{id}` | Modifier un livre |
| DELETE | `/api/livres/{id}` | Supprimer un livre |
| POST | `/api/livres/{id}/emprunter` | Emprunter un livre |
| POST | `/api/livres/{id}/retourner` | Retourner un livre |
| GET | `/api/livres/{id}/frais-retard?joursRetard=N` | Frais de retard en $ |

---

> Remplacez le contenu ci-dessous par vos réponses du TP (voir `ENONCE_TP.md`).
