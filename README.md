# ChatApp

Chat application in JavaFX

Aplikacja jest podzielona na 2 foldery - serwer oraz klient (stworzylem jar do obslugi wspolnego kodu, jak rozmawialismy podczas konsultacji, jezeli jendak kod z folderu server sie nie skompiluje to pozostawilem w folderze chat opcje zmiany w klasie Main wartosci isServer na true i tak tez serwer powinien sie wlaczyc ). Aby aplikacja dzialala poprawnie nalezy najpierw wlaczyc serwer i nastepnie 2 okna klienta. Po podaniu nazwy uzytkownika, okno zmieni si� na okno czatu.

Aby skompilowac i wlaczyc program nalezy wpisac ponizsze komendy po wejsciu do glownego folderu projektu:

mvn clean install

mvn exec:java -Dexec.mainClass=Chat.Main