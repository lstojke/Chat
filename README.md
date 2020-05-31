# ChatApp

## Wstęp

Aplikacja sluzy do komunikacji 2 uzytkownikow za posrednictwem serwera. Mozna przesylac wiadomosci (tekst lub obrazek) jako dymki czatu oraz je usuwac w razie potrzeby.

Aplikacja przy uruchomieniu daje wybor czy ma pelnic role serwera czy klienta. Nastepnie nalezy wybrac port, przez ktory ma sie polaczyc. Po wybraniu wersji klienta i wpisaniu portu pojawi sie okno, gdzie mozna wpisac swoja nazwe uzytkownika. Nastepnie, po zatwierdzeniu nicku, otwarte zostaje okno, gdzie bedzie przebiegac komunikacja. Mozna otwierac aplikacje serwer i 2 klientow w dowolnej kolejnosci, ale przed podaniem nazwy uzytkownika nalezy juz miec wlaczony serwer.

Aby skompilowac i wlaczyc program nalezy wpisac ponizsze komendy po wejsciu do folderu chat :

`mvn clean install`

`mvn exec:java -Dexec.mainClass=Chat.Main`

Aby wykonac testy nalezy wykonac komendę :

`mvn test`

## Struktura aplikacji

Program został stworzony zgodnie z szablonem MVC (Model-View-Controller). Dzięki temu poszczególne funkcjonalności zostały wydzielone dla konkretnego członu. 

### Serwer

Serwer nie posiada widoku, a tak naprawdę tylko wyświetla na konsoli komunikaty o połączeniach. Stale pobiera on wiadomości od klientów i rozsyła je do pozostałych, jak również wykonuje polecenia usunięcia ostatniej wiadomości.

### Klient

**Model** - obsługuje połączenie z serwerem oraz wykonuje żądania użytkownika, przesyłając je do serwera. Konwertuje również obrazy do przesłania na tablice bajtów w celu serializacji danych. <br>
**Kontroler** - odpowiada za komunikację modelu z widokiem/użytkownikiem. W nim zapadają decyzje o przełożeniu wciśnięcia klawisza przez użytkownika na konkretne działanie modelu w tej sytuacji. Dzięki JavaFX można w prosty sposób zaimplementować taką komunikację poprzez wstrzykiwanie przycisków do odpowiednich funkcji.
