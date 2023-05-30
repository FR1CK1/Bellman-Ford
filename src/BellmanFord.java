
public class BellmanFord {

	static int INFTY = 9999;
	// Diese Zahl soll Unendlich simulieren und kann bei Bedarf ge�ndert werden
	// (sollte die Distans 9999 �berschreiten).

	int[] distance = new int[Launcher.MAX_EDGE];
	// Diese Variabel speichert die Distanzen der einzelnen Knoten. Die Nummer des
	// Knoten gilt dabei als der Zeiger im Array

	int[] pi = new int[Launcher.MAX_EDGE];
	// pi stellt den Vorg�nger von den einzelnen Knoten dar. Der Knoten gilt dabei
	// als Zeiger im Array
	
	

	// Initialisierung des BF-Algorythmus
	public void initialise() {
		for (int x = 0; x < Launcher.MAX_EDGE; x++) {
			distance[x] = INFTY; // Distanz aller Knoten ist Unendlich
			distance[0] = 0; // 0 ist Start und hat daher die Distanz 0
			pi[x] = -1; // pi=-1 soll Kein Vorg�nger bedeuten
		}
	}

	// Relaxion wird durchgef�hrt
	public void relax(int iEdge, int[][] data) {
		for (int x = 0; x < iEdge; x++) {
			// Die drei Variablen werden f�r die Vereinfachung im Code erstellt
			int u = data[x][0];
			int v = data[x][1];
			int w = data[x][2];
			// Die If Konditionen pr�ft, ob ein Weg k�rzer ist und akutallisiert ggf. die
			// Distanz und den Vorg�nger
			if (distance[v] > distance[u] + w) {
				distance[v] = distance[u] + w;
				pi[v] = u;
			}
		}
	}

	// Negative Zyklenpr�fung
	public boolean checkForNegative(int iEdge, int[][] data) {
		boolean bool = false;
		for (int x = 0; x < iEdge; x++) {
			// Die drei Variablen werden f�r die Vereinfachung im Code erstellt
			int u = data[x][0];
			int v = data[x][1];
			int w = data[x][2];
			// P�rfung auf Negativen Zyklus
			if (distance[v] > distance[u] + w) {
				bool = true;
				break;
			} else {
				bool = false;
			}
		}
		return bool;
	}

	// Diese Funktion erzeugt die Ausgabe auf der Konsole
	public void printSolution(int iEdge, boolean negZyk) {
		if (negZyk) {
			System.out.println("Negativer Zyklus im Graphen");
		} else {
			System.out.println("Knoten 0\nStartknoten\n"); // Der Startknoten hat keinen Vorg�nger. Damit er dennoch in
															// der Ausgabe erw�hnt wird, dient diese zus�tzliche Zeile
			for (int x = 0; x < iEdge; x++) {
				// iEdge, da die Anzahl der Knoten nicht (einfach) ermittelt werden kann und
				// nicht alle Zeilen bis MAX_EDGE gepr�ft werden m�ssen. iEdge > Anzahl der Knoten
				if (pi[x] >= 0) { // wenn kein Vorg�nger definiert ist (pi=-1), handelt es sich in der Zeile um
									// keinen Knoten und muss nicht ausgegeben werden
					System.out.println("Knoten " + x);
					System.out.println("Distanz " + distance[x]);
					System.out.println("Vorg�nger " + pi[x]);
					System.out.println("");
				}
			}
		}
	}
}
