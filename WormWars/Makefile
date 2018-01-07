CFLAGS=-Wall -std=c++11 -g

wormWars: wormWars.o
	g++ wormWars.o $(CFLAGS) -o wormWars
	
wormWars.o: wormWars.c
	g++ -c wormWars.c $(CFLAGS)
	
graph:
	gnuplot5 -persistance plot.gb
	
clean:
	rm -f *.o
