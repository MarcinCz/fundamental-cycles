Instrukcja obs³ugi

Program dzia³a w 2 trybach: standardowym oraz testowym. Ró¿ni¹ siê one zasad¹ dzia³ania oraz rodzajem parametrów wejœciowych programu. 

1. Tryb standardowy
Umo¿liwia znalezienie fundamentalnego zbioru cykli w zadanym przez u¿ytkownika grafie.
wywo³anie programu: >program.jar -f nazwa_pliku.graph
-f - flaga sygnalizuj¹ca czytanie grafu z pliku. Plik musi mieæ rozszerzenie ".graph", a jego struktura ma zawieraæ kolejno w wierszach id wêz³a oraz po tabulatorze listê jego s¹siadów oddzielonych przecinkami.

Wyniki zwracane przez program s¹ wypisywane w konsoli oraz w pliku o nazwie "nazwa_pliku.graph.result". Wewn¹trz s¹ wypisywane wszystkie znalezione cykle, najpierw za pomoc¹ algorytmu przeszukiwania wszerz, a potem w g³¹b.

2. Tryb testowy
Umo¿liwia przetestowanie dzia³ania programu oraz zaimplementowanych algorytmów z zadanymi odpowiednimi parametrami.
wywo³anie programu: >program.jar x y z
x - liczba wierzcho³ków w grafie
y - liczba iteracji testu
z - liczba krawêdzi w grafie

S¹siedztwo wierzcho³ków jest generowane losowo. Jedyny warunek na liczbê krawêdzi, jaki musi byæ spe³niony to  y < x(x-1)/2. Drzewo jest generowane automatycznie, wiêc graf musi mieæ x-1 krawêdzi. Wynika z tego to, ze liczba y jest tak na prawdê liczb¹ cyklometryczn¹ grafu.
Wyniki zwracane przez program s¹ wypisywane w konsoli oraz w plikach o nazwie "czas_wykonania.graph.result" w folderze "results". Wewn¹trz, dla ka¿dej iteracji testu, jest wypisany graf w postaci struktury adekwatnej do struktury pliku wejœciowego oraz w dalszej kolejnoœci wszystkie znalezione cykle, najpierw za pomoc¹ algorytmu przeszukiwania wszerz, a potem w g³¹b. Dodatkowo jest wypisana tak¿e informacja o numerze iteracji oraz jej wyniku (positive/negative).


przyk³ad struktury pliku wejœciowego *.graph:
1	2,3
2	1,3
3	1,2