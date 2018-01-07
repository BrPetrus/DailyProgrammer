
/** Note that this program was not made by me **/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ReverseScrabble {
	static List<String> dictionary = new ArrayList<>();
	
	public static void main(String[] args) {
		parseDictionary();
		System.out.println("Done parsing " + dictionary.size() + " words.");
		
		long time = System.currentTimeMillis();
		System.out.println(new ScrabbleBoard(new char[][] {
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'.', 'f', 'e', 'r', 'r', 'i', 'e', 's', '.'},
				{'.', 'l', '.', '.', '.', '.', '.', 't', '.'},
				{'.', 'o', '.', '.', 'a', 'n', '.', 'a', '.'},
				{'.', 'e', '.', '.', '.', 'e', '.', 'f', '.'},
				{'.', 's', 'h', 'o', 'r', 't', '.', 'f', '.'},
				{'.', '.', '.', '.', '.', '.', '.', 'e', '.'},
				{'.', '.', 'c', 'a', 'l', 'l', 'e', 'd', '.'}
		}).getWords());
		
		long delta = System.currentTimeMillis() - time;
		System.out.println("Reversed the board in " + delta + "ms");
	}
	
	static String randomWord() {
		return dictionary.get(ThreadLocalRandom.current().nextInt(dictionary.size()));
	}
	
	static void parseDictionary() {
		try {
			URI in = ReverseScrabble.class.getResource("enable1.txt").toURI();
			BufferedReader reader = new BufferedReader(new FileReader(new File(in)));
			String dictLine;
			
			while ((dictLine = reader.readLine()) != null) {
				dictionary.add(dictLine);
			}
			
			reader.close();
		} catch (IOException | URISyntaxException e) {}
	}
	
	static class ScrabbleBoard {
		private char[][] data;
		private final int xSize;
		private final int ySize;
		
		public ScrabbleBoard(char[][] data) {
			this.data = data;
			
			xSize = data[0].length;
			ySize = data.length;
		}
		
		private boolean inBounds(int x, int y) {
			return x >= 0 && x < xSize && y >= 0 && y < ySize;
		}
		
		public String wordAt(int x, int y, boolean horizontal) {
			String word = Character.toString(data[y][x]);
			
			int currentX = horizontal ? x - 1 : x;
			int currentY = horizontal ? y : y - 1;
			
			while (inBounds(currentX, currentY) && data[currentY][currentX] != '.') {
				word = data[currentY][currentX] + word;
				
				if (horizontal) {
					currentX--;
				} else {
					currentY--;
				}
			}
			
			currentX = horizontal ? x + 1 : x;
			currentY = horizontal ? y : y + 1;
			
			while (inBounds(currentX, currentY) && data[currentY][currentX] != '.') {
				word += data[currentY][currentX];
				
				if (horizontal) {
					currentX++;
				} else {
					currentY++;
				}
			}
			
			return word;
		}
		
		public Set<String> getWords() {
			return getWords(xSize / 2, ySize / 2, new ArrayList<>());
		}
		
		private Set<String> getWords(int x, int y, List<Position> visited) {
			Position pos = new Position(x, y);
			if (!inBounds(x, y) || visited.contains(pos) || data[y][x] == '.') {
				return new HashSet<>();
			}
			
			visited.add(pos);
			
			Set<String> words = new LinkedHashSet<>();
			String hWord = wordAt(x, y, true);
			String vWord = wordAt(x, y, false);
			
			if (hWord.length() > 1 && dictionary.contains(hWord)) {
				words.add(hWord);
			}
			
			if (vWord.length() > 1 && dictionary.contains(vWord)) {
				words.add(vWord);
			}
			
			words.addAll(getWords(x - 1, y, visited));
			words.addAll(getWords(x + 1, y, visited));
			words.addAll(getWords(x, y - 1, visited));
			words.addAll(getWords(x, y + 1, visited));
			
			return words;
		}
		
		@Override
		public String toString() {
			return Arrays.deepToString(data);
		}
	}
	
	static class Position {
		public final int x;
		public final int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			return x == ((Position) obj).x
					&& y == ((Position) obj).y;
		}
	}
} 
