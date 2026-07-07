# Like Hero To Zero

Prototyp einer Webanwendung zur Anzeige und Pflege von CO₂-Emissionsdaten.

Das Projekt wurde im Rahmen einer Fallstudie entwickelt. Es setzt die beiden priorisierten MUST-User-Stories des Product Backlogs um.

## Funktionen

### Öffentliche CO₂-Abfrage

Die öffentliche Startseite ist ohne Login erreichbar.

Nutzer:innen können ein Land auswählen und den aktuellsten im Datensatz verfügbaren CO₂-Ausstoß anzeigen lassen.

Die Anwendung ermittelt automatisch den Datensatz mit dem höchsten verfügbaren Jahr.

Aktuell stehen folgende Länder zur Auswahl:

- Deutschland
- Frankreich
- Italien

Für jedes Land sind CO₂-Daten der Jahre 2020 bis 2024 vorhanden.

### Geschützter Wissenschaftler-Bereich

Registrierte Wissenschaftler:innen können sich anmelden und neue CO₂-Datensätze erfassen.

Neue Datensätze werden dauerhaft in einer relationalen MySQL-Datenbank gespeichert.

Der geschützte Bereich ist nur nach erfolgreicher Anmeldung erreichbar.

## Umgesetzte User Stories

### MUST 1

> Als umweltpolitisch interessierte:r Bürger:in will ich den aktuellsten im Datensatz verfügbaren CO₂-Ausstoß des Landes nachlesen können, über dessen Staatsbürgerschaft ich verfüge.

Umgesetzt durch:

- öffentliche Startseite ohne Login
- Auswahl eines Landes
- Abruf der Daten aus MySQL
- automatische Ermittlung des aktuellsten verfügbaren Datenjahres
- Anzeige des CO₂-Wertes in Kilotonnen

### MUST 2

> Als registrierte:r, Daten beitragende:r Wissenschaftler:in will ich die jüngsten Daten aus meiner Klimaforschung in dem Datensatz hinterlegen.

Umgesetzt durch:

- Login mit Spring Security
- geschützten Wissenschaftler-Bereich
- Formular zur Eingabe neuer Datensätze
- Auswahl des Landes
- Eingabe des Jahres
- Eingabe des CO₂-Wertes
- persistente Speicherung in MySQL

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