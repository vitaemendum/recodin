# Užduotis kandidatui į Full Stack (Java + React) programuotojo vietą

Ši užduotis yra skirta kandidatui, siekiančiam į Full Stack (Java + React) programuotojo vietą. Užduoties tikslas yra sukurti užduočių (to-do) sąrašo programą, pasiekiamą per naršyklę (web-app).

## Reikalavimai funkcionalumui

- Galima pridėti naują užduotį.
- Galima ištrinti užduotį.
- Galima pažymėti užduotį kaip padarytą.

## Reikalavimai užduoties išpildymui

- Pasirinkimas technologijų ir framework'ų yra laisvas, tačiau rekomenduojame naudoti React ir Java (Spring Boot).
- Programinis kodas turi būti tvarkingas ir lengvai skaitomas.
- Panaudotos gerosios programavimo praktikos: naudojamos objektinio ar funkcinio programavimo paradigmos, pritaikomi programinio kodo perpanaudojimo, paprastumo ir kiti pagrindiniai principai.
- Naudojama duomenų bazė (pvz.: H2, PostgreSQL, MySQL).
- Sukurti unit ir integraciniai testai.

### Naudotos technologijos 
 - Vitejs;
 - Reactjs 18;
 - Java 17;
 - Spring boot 3.1.2;
 - PostgreSQL 15.3;
 - Docker 24;

## Kaip pasileisti programą su Docker Compose

1. Nusikopijuokite projektą į savo kompiuterį: `https://github.com/vitaemendum/recodin.git`
2. Įeikite į projekto katalogą: `cd recodin`
3. `.env` faile pritaikykite aplinkos kintamuosius.
4. Paleiskite aplikaciją su Docker Compose: `docker-compose up --build`.
5. Backend dalis per: `http://localhost:8080/api/tasks`.
6. Aplikacija bus pasiekiamą naršyklėje per: `http://localhost:5173`. 