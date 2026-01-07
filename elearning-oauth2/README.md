# Sécurisation E-Learning avec OAuth2 / OpenID Connect

## Architecture

elearning-oauth2/
├── backend-spring/       (API Spring Boot protégée)
├── frontend-react/       (SPA React intégrée à Keycloak)
├── keycloak/             (export d'exemple + instructions)
└── README.md             (ce fichier)

Serveurs utilisés par défaut :
- Keycloak : http://localhost:8080 (start-dev ou docker image)
- Backend Spring Boot : http://localhost:8081
- Frontend React : http://localhost:3000

> Note : les ports sont configurables dans les README des sous-projets.

## Objectifs
- Mettre en place Keycloak (realm, client public, rôles, utilisateurs)
- Protéger l'API Spring Boot avec OAuth2 Resource Server (JWT)
- Protéger la SPA React via `keycloak-js` et envoyer le token dans Authorization
- Gérer rôles ROLE_STUDENT et ROLE_ADMIN

---

Je vais maintenant implémenter la structure et le code pour le backend, le frontend et la doc Keycloak. Ensuite je fournirai des instructions pour lancer et tester le projet localement.

Bon travail ! (style étudiant)