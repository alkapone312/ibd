# Internetowe Bazy Danych - Aplikacja hotelu

> Autorzy:
> - Jakub Kapłonek
> - Radosław Zimoch
> - Jakub Ptaszkowski

## Uruchomienie

```bash
docker compose up
```

### Dostępność aplikacji

Frontend:
```
https://localhost:8080
```

Backend:
```
https://localhost:8081
```

### Wejście do kontenera

Java:
```bash
./java.sh
```

Node:
```bash
./node.sh
```

## Opis biznesowy

Klient jest osobą korzystającą z usług hotelu. Klient w hotelu może rezerwować pokoje na określoną liczbę dni. 
Na pokój składa się numer pokoju, cena bazowa, bazowa ilość miejsc w pokoju oraz dodatkowe parametry wpływające 
na cenę końcową takie jak osobna łazienka, wielkość pomieszczenia, dobry widok, dodatkowe wyposażenie pokoju itp. 
Niektóre wyposażenie może być wybieralne przez klienta takie jak np. czajnik elektryczny lub dodatkowe łóżko. 
Klient wtedy może wybrać dodatkowe wyposażenie pokoju i na bieżąco kontrolować zmieniającą się cenę. W celu 
pomyślnego przejścia procesu rezerwacji klient musi znajdować się w spisie klientów do takiego klienta znajdującego 
się w spisie zostaje wtedy przypisane zamówienie. Dodatkowo klient będzie miał możliwość rezygnacji z zamówienia 
do 1 dnia przed sfinalizowaniem rezerwacji. Na rezerwację będzie składać się status rezerwacji, przedział czasowy 
w którym złożona jest rezerwacja, użytkownik przypisany do rezerwacji i pokój przypisany do rezerwacji. 
W momencie kiedy pokój jest przypisany do rezerwacji w danym okresie, w tym samym okresie inny klient nie może 
zarezerwować tego samego pokoju. Klient jest w stanie przeglądać ofertę hotelu nie znajdując się w spisie klientów. 
Pokoje mogą zostać poddane przecenie gdzie na przykład pokoje nie zarezerwowane na 1 dzień przed terminem rezerwacji 
będą poddawane przecenie "last minute". Przecena może być stała (do wyłączenia przez użytkownika) lub określona na 
pewien czas. Dla przeceny mogą być też określone warunki przy których może ona zajść. W hotelu istnieje użytkownik 
administrujący który będzie w stanie zarządzać rezerwacjami - zmieniać status rezerwacji ręcznie, pokojami - dodawać 
pokoje, przypisywać i odpinać od nich parametry, określać wyposażenie o które może ubiegać się użytkownik, 
przecenami - ustalać przeceny i przypisywać je do pokoji, ustawiać warunki pod którymi występują przeceny lub usuwa 
aktualnie nałożone przeceny. Administrator będzie w stanie zablokować pokój na określony czas, na przykład jeśli 
pokój jest przeznaczony do remontu. Klient wtedy będzie widział taki pokój jako niedostępny podobnie jak w przypadku 
gdy pokój jest zarezerwowany. Klient przy wyborze jest informowany o dostępności czasowej pokoji, może wybrać przedział
czasowy w którym interesuje go rezerwacja. Klient widzi również ceny pokoji i podsumowanie ceny wynajmu pokoju ze 
wszystkimi dodatkami na cały okres trwania rezerwacji.


## Wymagania funkcjonalne
 1. Klient może rezerwować pokoje
 2. Klient może wybierać dodatkowe wyposażenie pokoju
 3. Klient może założyć konto w aplikacji
 4. Klient może anulować rezerwacje
 5. Klient może przeglądać ofertę hotelu
 6. Administrator może dodawać/usuwać przeceny z pokoii
 7. Administrator może zmieniać status rezerwacji
 8. Administrator może dodawać/usuwać pokoje
 9. Administrator może dodawać/usuwać wyposażenie z pokoii
 10. Administrator może zarządzać dodatkowym wyposażeniem pokoii
 11. Administrator może blokować dostępność pokoii

## Wymagania niefunkcjonalne
 1. Pokoje mogą być rezerwowane na określony czas
 2. Dodatkowe wyposażenie pokoju możliwe do wyboru jest przypisywane przez właściciela
 3. Jeśli pokój jest zarezerwowany, inny klient nie może go zarezerwować
 4. Klient może zrezygnować z rezerwacji maksymalnie jeden dzień przed finalizacją
 5. Klient może przeglądać ofertę hotelu będąc niezalogowanym użytkownikiem
 6. Klient musi być zalogowany aby realizować wszystkie pozostałe funkcjonalności
 7. Klient nie widzi niedostępnych pokoi
 8. Przeceny pokoi mogą mieć ustaloną datę rozpoczęcia i wygaśnięcia
 9. Przeceny pokoi mogą mieć ustalone warunki pod którymi występują
 10. Administrator może zarządzać hotelem po zalogowaniu na konto ze specjalnymi uprawnieniami
 
## Opisy przypadków użycia

1. Wybranie dodatkowego wyposażenia pokoju
   1. Aktor: Klient
   2. Cel: Dostosowanie wyposażenia pokoju do własnych wymagań
   3. Zdarzenie inicjujące: Rezerwowanie pokoju
   4. Warunki wstępne: Pokój ma dostępne dodatkowe wyposażenie
   5. Warunki końcowe: Klient rezerwuje pokój z wybranym dodatkowym wyposażeniem
   6. Scenariusz
      1. Klient rozpoczyna rezerwacje pokoju
      2. Klient przegląda dostępne dodatkowe wyposażenia
      3. Klient zaznacza dodatkowe wyposażenie, które chciałby mieć w pokoju
      4. Cena rezerwacji zostaje zaktualizowana

2. Rezerwowanie pokoju
   1. Aktor: Klient
   2. Cel: Rezerwacja pokoju przez klienta
   3. Zdarzenie inicjujące: Wybranie pokoju z oferty pokoi
   4. Warunki wstępne: Pokój jest dostępny do rezerwacji
   5. Warunki końcowe: Pokój zostaje zarezerwowany przez klienta
   6. Scenariusz:
      1. Klient wybiera pokój do rezerwacji
      2. Klient wybiera dodatkowe wyposażenie
      3. Klient rezerwuje pokój

3. Anulowanie rezerwacji
    1. Aktor: Klient
    2. Cel: Anulowanie złożonej rezerwacji
    3. Zdarzenie inicjujące: Klient wybiera rezerwacje do anulowania
    4. Warunki wstępne: Rezerwacja musi być złożona
    5. Warunki końcowe: Rezerwacja zostaje anulowana
    6. Scenariusz:
       1. Klient przegląda złożone przez siebie rezerwacje
       2. Jeśli termin anulowania rezerwacji nie minął
          1. Rezerwacja jest możliwa do anulowania
          2. Klient anuluje rezeracje
          3. Koniec scenariusza
       3. W przeciwnym wypadku
          1. Brak możliwości anulowania rezerwacji
          2. Koniec scenariusza
          
4. Wyświetlenie niezarezerwowanych pokoi
    1. Aktor: Klient
    2. Cel: Przejrzenie oferty hotelu
    3. Zdarzenie inicjujące: Klient włącza aplikacje hotelu
    4. Warunki wstępne: Uruchomienie przez klienta aplikacji hotelu
    5. Warunki końcowe: Klient przegląda oferte hotelu
    6. Scenariusz:
       1. Klient uruchamia aplikacje hotelu
       2. Niezarezerwowanie pokoje wyświetlają się klientowi


5. Założenie konta
    1. Aktor: Klient
    2. Cel: Założenie konta w celu rozszerzenia możliwości
    3. Zdarzenie inicjujące: Klient przechodzi do panelu rejestracji
    4. Warunki wstępne: Klient przeszedł do panelu rejestracji w aplikacji hotelu
    5. Warunki końcowe: Klient zakłada konto w serwisie
    6. Scenariusz:
       1. Klient przechodzi do panelu rejestracji
       2. Klient podaje dane potrzebne do rejetracji
       3. Następuje walidacja danych
       4. Jeżeli dane są poprawne
          1. Konto w systemie zostaje utworzone
          2. Koniec scenariusza
       5. W przeciwnym wypadku
          1. Klient zostaje poinformowany o błędach
          2. Klient poprawia błędy
          3. Powrót do punktu 3


7. Zalogowanie
    1. Aktor: Klient
    2. Cel: Zalogowanie do hotelu
    3. Zdarzenie inicjujące: Klient wybiera opcje zalogowania do hotelu
    4. Warunki wstępne: Klient posiada konto w systemie
    5. Warunki końcowe: Klient zostaje zalogowany do hotelu
    6. Scenariusz:
       1. Klient przechodzi do panelu logowania
       2. Klient podaje dane logowania
       3. Następuje walidacja danych
       4. Jeżeli dane są poprawne
          1. Klient loguje się do hotelu
          2. Koniec scenariusza
       5. W przeciwnym wypadku
          1. Klient zostaje powiadomiony  błędzie
          2. Powrót do punktu 2.

8. Przeglądanie własnych rezerwacji
    1. Aktor: Klient
    2. Cel: Podjęcie akcji związanej z rezerwacją
    3. Zdarzenie inicjujące: Klient otwiera panel z rezerwacjami
    4. Warunki wstępne: Klient jest zalogowany do hotelu
    5. Warunki końcowe: Klient przegląda swoje rezerwacje
    6. Scenariusz:
       1. Klient loguje się do hotelu
       2. Klient przechodzi do panelu z własnymi rezerwacjami
       3. Rezerwacje klienta wyświetlają się


9. Zmienienie statusu rezerwacji
    1. Aktor: Admin
    2. Cel: Zmiana statusu rezerwacji na prośbe klienta
    3. Zdarzenie inicjujące: Admin wybiera rezerwacje do zarządzania
    4. Warunki wstępne: Admin jest zalogowany do hotelu
    5. Warunki końcowe: Admin zmienia status rezerwacji
    6. Scenariusz:
       1. Admin loguje się do hotelu
       2. Admin przechodzi do panelu z rezerwacjami
       3. Admin wybiera interesującą go rezerwację
       4. Admin wyświetla interesującą go rezerwację
       5. Admin zmienia status rezerwacji

10. Przeglądanie wszystkich rezerwacji
     1. Aktor: Admin
     2. Cel: Przeglądanie rezerwacji w celu podjęcia związanej z nimi akcji
     3. Zdarzenie inicjujące: Admin przechodzi do panelu z rezerwacjami
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin przegląda rezerwacje
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi do panelu z rezerwacjami
        3. Wszystkie rezerwacje wyświetlają się
        4. Admin przegląda rezerwacje

11. Dodanie przeceny
     1. Aktor: Admin
     2. Cel: Zmiana ceny pokoju na cenę promocyjną
     3. Zdarzenie inicjujące: Admin przechodzi do opcji dodania przeceny
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin dodaje przecene do pokoju
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przegląda pokoje
        3. Admin wybiera opcje nałożenia przeceny
        4. Admin określa przecenę
        5. Przecena zostaje dodana do pokoju

12. Usunięcie przeceny
     1. Aktor: Admin
     2. Cel: Powrót ceny pokoju do ceny bazowej
     3. Zdarzenie inicjujące: Admin usuwa przecene
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin usuwa przecene z pokoju
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przegląda pokoje
        3. Admin wybiera pokój, na który nałożona jest przecena
        4. Admin usuwa przecenę z pokoju

13. Dodanie pokoju
     1. Aktor: Admin
     2. Cel: Dodanie pokoju do oferty hotelu
     3. Zdarzenie inicjujące: Admin wybiera opcje dodania pokoju
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Pokój zostaje dodany do oferty hotelu
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi do panelu z pokojami
        3. Admin wybiera opcje dodania pokoju
        4. Admin uzupełnia dane związane z pokojem
        5. Admin dodaje pokój

14. Usunięcie pokoju
     1. Aktor: Admin
     2. Cel: Usunięcie pokoju z oferty hotelu
     3. Zdarzenie inicjujące: Admin wybiera opcje usunięcia pokoju
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Pokój zostaje usunięty z oferty hotelu
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi do panelu z pokojami
        3. Admin wybiera opcje usunięcia odpowiedniego pokoju
        4. Pokój zostaje usunięty

15. Wyświetlenie listy pokoi
     1. Aktor: Admin
     2. Cel: Przeglądanie pokoi przez Administratora
     3. Zdarzenie inicjujące: Przejście do panelu z pokojami
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin przegląda pokoje
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi do panelu z pokojami
        3. Admin wyświetla liste pokoi


16. Dodanie wyposażenia
     1. Aktor: Admin
     2. Cel: Dodanie wyposażenia do pokoju
     3. Zdarzenie inicjujące: Wybranie opcji dodaj wyposażenie do pokoju
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin dodaje wyposażenie pokoju
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi od panelu z pokojami
        3. Admin wyświetla liste pokoi
        4. Admin wybiera opcję dodania wyposażenia
        5. Admin wybiera wyposażenie z listy
        6. Admin dodaje wyposażenie


17. Usunięcie wyposażenia
     1. Aktor: Admin
     2. Cel: Usunięcie wyposażenia pokoju
     3. Zdarzenie inicjujące: Wybranie opcji usunięcia wyposażenia
     4. Warunki wstępne: Admin jest zalogowany do hotelu
     5. Warunki końcowe: Admin usuwa wyposażenie pokoju
     6. Scenariusz:
        1. Admin loguje się do hotelu
        2. Admin przechodzi do panelu z pokojami
        3. Admin wyświetla liste pokoi
        4. Admin wybiera opcję usunięcia wyposażenia
        5. Admin wybiera wyposażenie z listy aktualnie przypisanych wypsażeń
        6. Admin usuwa wyposażenia

18. Blokowanie pokoju
    1. Aktor: Admin
    2. Cel: Zablokowanie pokoju przed pojawianiem się w ofercie hotelu
    3. Zdarzenie inicjujące: Wybranie opcji blokady pokoju
    4. Warunki wstępne: Admin jest zalogowany do hotelu
    5. Warunki końcowe: Admin blokuje pokój
    6. Scenariusz
       1. Admin loguje się do hotelu
       2. Admin przechodzi do panelu z pokojami
       3. Admin wyświetla liste pokoi
       4. Admin wybiera opcję blokady pokoju
       5. Pokój zostaje zablokowany

19. Odblokowanie pokoju
   1. Aktor: Admin
   2. Cel: Odblokowanie pokoju
   3. Zdarzenie inicjujące: Wybranie opcji odblokowania pokoju
   4. Warunki wstępne: Admin jest zalogowany do hotelu
   5. Warunki końcowe: Admin odblokowywuje pokój
   6. Scenariusz
      1. Admin loguje się do hotelu
      2. Admin przechodzi do panelu z pokojami
      3. Admin wyświetla liste pokoi
      4. Admin wybiera opcję odblokowania pokoju
      5. Pokój zostaje odblokowany