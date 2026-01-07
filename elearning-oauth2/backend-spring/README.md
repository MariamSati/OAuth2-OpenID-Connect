# Backend Spring Boot (elearning-backend)

Ce microservice est une API protégée par OAuth2 (Resource Server), qui utilise les tokens JWT émis par Keycloak.

## Endpoints

- GET /courses → accessible par ROLE_STUDENT et ROLE_ADMIN
- POST /courses → accessible par ROLE_ADMIN uniquement
- GET /me → retourne les claims du token (id, rôles, email, etc.)

## Démarrage

Pré-requis : Java 17, Maven, Keycloak démarré (voir dossier `../keycloak`)

1. Se placer dans `backend-spring`
2. Lancer :

   mvn spring-boot:run

L'application écoute sur le port `8081` (configurable dans `application.yml`).

## Notes techniques
- La classe `SecurityConfig` convertit les rôles Keycloak (dans `realm_access.roles`) en autorités Spring `ROLE_...`.
- Les méthodes sont protégées par `@PreAuthorize` et utilisent `hasAuthority('ROLE_...')`.
- Les données de cours sont stockées en mémoire (liste) pour simplifier le TP.

Bon, c'est simple et propre pour un TP étudiant : on démarre Keycloak, puis le backend, puis le frontend.
