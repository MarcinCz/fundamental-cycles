Instrukcja obs�ugi

Program dzia�a w 2 trybach: standardowym oraz testowym. R�ni� si� one zasad� dzia�ania oraz rodzajem parametr�w wej�ciowych programu. 

1. Tryb standardowy
Umo�liwia znalezienie fundamentalnego zbioru cykli w zadanym przez u�ytkownika grafie.
wywo�anie programu: >program.jar -f nazwa_pliku.graph
-f - flaga sygnalizuj�ca czytanie grafu z pliku. Plik musi mie� rozszerzenie ".graph", a jego struktura ma zawiera� kolejno w wierszach id w�z�a oraz po tabulatorze list� jego s�siad�w oddzielonych przecinkami.

Wyniki zwracane przez program s� wypisywane w konsoli oraz w pliku o nazwie "nazwa_pliku.graph.result". Wewn�trz s� wypisywane wszystkie znalezione cykle, najpierw za pomoc� algorytmu przeszukiwania wszerz, a potem w g��b.

2. Tryb testowy
Umo�liwia przetestowanie dzia�ania programu oraz zaimplementowanych algorytm�w z zadanymi odpowiednimi parametrami.
wywo�anie programu: >program.jar x y z
x - liczba wierzcho�k�w w grafie
y - liczba iteracji testu
z - liczba kraw�dzi w grafie

S�siedztwo wierzcho�k�w jest generowane losowo. Jedyny warunek na liczb� kraw�dzi, jaki musi by� spe�niony to  y < x(x-1)/2. Drzewo jest generowane automatycznie, wi�c graf musi mie� x-1 kraw�dzi. Wynika z tego to, ze liczba y jest tak na prawd� liczb� cyklometryczn� grafu.
Wyniki zwracane przez program s� wypisywane w konsoli oraz w plikach o nazwie "czas_wykonania.graph.result" w folderze "results". Wewn�trz, dla ka�dej iteracji testu, jest wypisany graf w postaci struktury adekwatnej do struktury pliku wej�ciowego oraz w dalszej kolejno�ci wszystkie znalezione cykle, najpierw za pomoc� algorytmu przeszukiwania wszerz, a potem w g��b. Dodatkowo jest wypisana tak�e informacja o numerze iteracji oraz jej wyniku (positive/negative).


przyk�ad struktury pliku wej�ciowego *.graph:
1	2,3
2	1,3
3	1,2