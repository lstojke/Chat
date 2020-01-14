# ChatApp

Chat application in JavaFX

Aplikacja sluzy do komunikacji 2 uzytkownikow za posrednictwem serwera. Mozna przesylac wiadomosci (tekst lub obrazek) jako dymki czatu oraz je usuwac w razie potrzeby.

Aplikacja przy uruchomieniu daje wybor czy ma pelnic role serwera czy klienta. Nastepnie nalezy wybrac port, przez ktory ma sie polaczyc. Po wybraniu wersji klienta i wpisaniu portu pojawi sie okno, gdzie mozna wpisac swoja nazwe uzytkownika. Nastepnie, po zatwierdzeniu nicku, otwarte zostaje okno, gdzie bedzie przebiegac komunikacja. Mozna otwierac aplikacje serwer i 2 klientow w dowolnej kolejnosci, ale przed podaniem nazwy uzytkownika nalezy juz miec wlaczony serwer.

Aby skompilowac i wlaczyc program nalezy wpisac ponizsze komendy po wejsciu do folderu chat :

mvn clean install

mvn exec:java -Dexec.mainClass=Chat.Main

Aby wykonac testy nalezy wykonac komende :

mvn test