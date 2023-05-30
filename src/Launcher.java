import java.io.*;
import java.util.*;

public class Launcher {

	static int MAX_EDGE = 128; // Diese Zahl gibt an, wie viele Kanten sich in der gegebenen Datei befinden
								// dürfen. Wird für automatisches Erzeugen von Arrays und Objekten genutzt

	public static void main(String[] args) {

		// Variablen
		
		String filename;
		//Gibt Dateiname an
		
		int[][] data = new int[MAX_EDGE][3];
		//Diese Variabel speichert den Datensatz aus der eingelesenen Datei nach folgendem Prinzip
		//data[[u,v,w][u,v,w][u,v,w]...] u=Start, v=Ziel, w=Gewichtung von u nach v
		
		int iEdge = 0;
		//Zähler um Platzhalter in data[][] zu ignorieren
		
		boolean negZyk = false;
		//Tritt ein negativer Zyklus auf?

		
		
		
		
		
		// Abfrage der Datei, falls keine angegeben wurde (über args[0] kann eine angegeben werden)
		if (args.length != 1) { 
			Scanner in = new Scanner(System.in);
			System.out.print("Bitte Dateiname eingeben: ");
			filename = in.nextLine();
			in.close();
		} else {
			filename = args[0];
		}

		// Befüllen von data[][] mit Platzhaltern (-1)
		for (int y = 0; y < MAX_EDGE; y++) {
			for (int x = 0; x < 3; x++) {
				data[y][x] = -1;
			}
		}

		// Auslesen angegebener Datei und schreiben der Infos in data[][] mit
		// Zeilenzähler iEdge
		try {
			Scanner sc = new Scanner(new File(filename));
			while (sc.hasNext()) {
				for (int x = 0; x < 3; x++) {
					data[iEdge][x] = sc.nextInt();
				}
				iEdge++;
			}
		// catch, fals Datei nicht existiert
		} catch (FileNotFoundException e) {
			System.err.println("Datei [" + filename + "] nicht gefunden. Endung mit angegeben?");
			System.out.println("Prozess beendet.");
			System.exit(0);
		//catch, falls die Datei nicht das erforderte Format einhält
		} catch (InputMismatchException e) {
			System.err.println("Formatierung der Datei fehlerhaft.\nBitte die Knoten beginnend mit 0 nummerieren und keine Buchstaben nutzen!");
			System.out.println("\nFolgendes Format nutzen:");
			System.out.println("[Startknoten] [Zielknoten] [Gewichtung vom Start- zum Zielknoten]");
			System.out.println("[Startknoten] [Zielknoten] [Gewichtung vom Start- zum Zielknoten]");
			System.out.println("[Startknoten] [Zielknoten] [Gewichtung vom Start- zum Zielknoten]");
			System.out.println("\nBeispiel");
			System.out.println("0 1 3");
			System.out.println("Vom Knoten 0 zum Knoten 1 fällt das Gewicht 3 an.");
			System.out.println("Prozess beendet.");
			System.exit(0);
		}
		
		
		// BF-Algorythmus - Die funktionen werden in BellmanFord.java ausgeführt
		BellmanFord b = new BellmanFord();
		b.initialise();
		for (int x = 0; x < iEdge - 1; x++) {
			b.relax(iEdge, data);
		}
		negZyk = b.checkForNegative(iEdge, data);
		b.printSolution(iEdge, negZyk);
		System.out.println("Prozess beendet.");

	}
}
