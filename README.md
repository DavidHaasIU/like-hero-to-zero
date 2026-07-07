# Like Hero To Zero

Prototyp einer Webanwendung zur Anzeige und Pflege weltweiter CO₂-Emissionsdaten.

Das Projekt wurde im Rahmen einer Fallstudie entwickelt. Es setzt die beiden priorisierten MUST-User-Stories des Product Backlogs um.

## Funktionen

### Öffentliche CO₂-Abfrage

Die öffentliche Startseite ist ohne Login erreichbar.

Nutzer:innen können ein Land auswählen und den aktuellsten im Datensatz verfügbaren CO₂-Ausstoß anzeigen lassen.

Die Anwendung ermittelt automatisch den Datensatz mit dem höchsten verfügbaren Jahr.

### Geschützter Wissenschaftler-Bereich

Registrierte Wissenschaftler:innen können sich anmelden und neue CO₂-Datensätze erfassen.

Neue Datensätze werden dauerhaft in einer relationalen MySQL-Datenbank gespeichert.

## Technologiestack

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- HTML
- CSS
- Git
- GitHub

## Architektur

Die Anwendung ist in mehrere Schichten gegliedert:

```text
Browser
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA / Hibernate
   ↓
MySQL