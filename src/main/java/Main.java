import java.util.Random;

public class Main {
    private static int length = 8, height = 4;
    private static int neighbours;

    private static String[] cells = new String[length * height];
    private static String[] cellsOfNextGen = new String[length * height];

    public static void main(String[] args) {
        defineStartCells();

        drawGameField();

        checkNeighbourCells();

        syndicateCells();

        drawGameField();

        checkNeighbourCells();

        syndicateCells();

        drawGameField();
    }

    private static void defineStartCells(){
        for (int i=0; i < cells.length; i++) {
            if (i < 9 || i > 22 || i == 15 || i == 16) {
                cells[i] = "-";
            } else {
                Random rand = new Random();
                int randomNumberOneOrZero = rand.nextInt(2);

                if (randomNumberOneOrZero == 0) {
                    cells[i] = "-";
                } else {
                    cells[i] = "*";
                }
            }
        }
        System.arraycopy(cells, 0, cellsOfNextGen, 0, cellsOfNextGen.length);
    }

    private static void checkNeighbourCells() {
        for (int i = 0; i < cells.length; i++) {
            neighbours = countNeighbours(i);
            setCellLifeState(i);

            /* Gibt Anzahl der Nachbarn aus
            if (i % 8 == 0) {
                System.out.print("\n");
            }

            System.out.print(neighbours);
            */
        }
    }
    private static void setCellLifeState(int cell) {
        if (neighbours == 3 || (neighbours == 2 && cells[cell].equals("*"))) {
            cellsOfNextGen[cell] = cellLives();
        } else {
            cellsOfNextGen[cell] = cellDies();
        }
    }

    private static int countNeighbours(int cell) {
        int countedNeighbours;

        countedNeighbours = countUpperNeighbours(cell) + countSideNeighbours(cell) + countLowerNeighbours(cell);

        return countedNeighbours;
    }

    private static int countUpperNeighbours(int cell) {
        int upperLeftNeighbour = cell - length - 1;
        int upperMidNeighbour = cell - length;
        int upperRightNeighbour = cell - length + 1;
        int upperNeighbours = 0;

        if (upperLeftNeighbour >= 0 && cells[upperLeftNeighbour].equals("*")) {
            upperNeighbours++;
        } if (upperMidNeighbour >= 0 && cells[upperMidNeighbour].equals("*")) {
            upperNeighbours++;
        } if (upperRightNeighbour >= 0 && cells[upperRightNeighbour].equals("*") && (upperRightNeighbour % length != 0)) {
            upperNeighbours++;
        }

        return upperNeighbours;
    }

    private static int countSideNeighbours(int cell) {
        int leftNeighbour = cell - 1;
        int rightNeighbour = cell + 1;
        int sideNeighbours = 0;

        if (leftNeighbour >= 0 && cells[leftNeighbour].equals("*") && (leftNeighbour + 1 % length != 0)) {
            sideNeighbours++;
        } if (rightNeighbour < cells.length && cells[rightNeighbour].equals("*") && (rightNeighbour % length != 0)) {
            sideNeighbours++;
        }

        return sideNeighbours;
    }

    private static int countLowerNeighbours(int cell) {
        int downerLeftNeighbour = cell + length - 1;
        int downerNeighbour = cell + length;
        int downerRightNeighbour = cell + length + 1;
        int lowerNeighbours = 0;

        if (downerLeftNeighbour < cells.length && cells[downerLeftNeighbour].equals("*") && (downerLeftNeighbour + 1 % length != 0)) {
            lowerNeighbours++;
        } if (downerNeighbour < cells.length && cells[downerNeighbour].equals("*")) {
            lowerNeighbours++;
        } if (downerRightNeighbour < cells.length && cells[downerRightNeighbour].equals("*")) {
            lowerNeighbours++;
        }

        return lowerNeighbours;
    }

    private static String cellLives() {
        return "*";
    }

    private static String cellDies() {
        return "-";
    }

    private static void drawGameField() {
        for (int i = 0; i < cells.length; i++) {
            if (i % length == 0 && i != 0) {
                System.out.print("\n");
            }
            System.out.print(cells[i]);
        }
        System.out.println("\n");
    }

    private static void syndicateCells() {
        System.arraycopy(cellsOfNextGen, 0, cells, 0, cells.length);
        //cells = cellsOfNextGen haelt beide Arrays permanent gleich -> sehr schlecht hierfuer
    }
}