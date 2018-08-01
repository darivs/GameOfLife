import java.util.Random;

public class Main {
    private static String[] cells = new String[32];

    public static void main(String[] args) {
        defineCells();
        drawField();
        checkNeighbours();
        drawField();
        checkNeighbours();
        drawField();
    }

    private static void defineCells(){
        Random rand = new Random();
        int r;

        for (int i=0; i < cells.length; i++) {
            if (i < 9 || i > 22 || i == 15 || i == 16) {
                cells[i] = "-";
            } else {
                r = rand.nextInt(2);

                if (r == 0) {
                    cells[i] = "-";
                } else {
                    cells[i] = "*";
                }
            }
        }
    }

    private static void checkNeighbours() {
        for(int i = 9; i <= 22; i++) {
            if (i != 15 && i != 16) {
                if (cells[i].equals("*")) {
                    // Any live cell with more than three live neighbours dies, as if by overcrowding.
                    if (cells[i - 1].equals("*") && cells[i + 1].equals("*") && cells[i - 8].equals("*") && cells[i + 8].equals("*")) {
                        cells[i] = "-";
                    // Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
                    } else if (cells[i - 1].equals("*") && cells[i + 1].equals("-") && cells[i - 8].equals("-") && cells[i + 8].equals("-")
                            || cells[i - 1].equals("-") && cells[i + 1].equals("*") && cells[i - 8].equals("-") && cells[i + 8].equals("-")
                            || cells[i - 1].equals("-") && cells[i + 1].equals("-") && cells[i - 8].equals("*") && cells[i + 8].equals("-")
                            || cells[i - 1].equals("-") && cells[i + 1].equals("-") && cells[i - 8].equals("-") && cells[i + 8].equals("*")) {
                        cells[i] = "-";
                    } else {
                    // Any live cell with two or three live neighbours lives on to the next generation.
                    }
                } else {
                    // Any dead cell with exactly three live neighbours becomes a live cell.
                    if (cells[i - 1].equals("*") && cells[i + 1].equals("*") && cells[i - 8].equals("*") && cells[i + 8].equals("-")
                     || cells[i - 1].equals("*") && cells[i + 1].equals("*") && cells[i - 8].equals("-") && cells[i + 8].equals("*")
                     || cells[i - 1].equals("*") && cells[i + 1].equals("-") && cells[i - 8].equals("*") && cells[i + 8].equals("*")
                     || cells[i - 1].equals("-") && cells[i + 1].equals("*") && cells[i - 8].equals("*") && cells[i + 8].equals("*")) {
                        cells[i] = "*";
                    }
                }
            }
        }
    }

    private static void drawField() {
        System.out.println(cells[0] + cells[1] + cells[2] + cells[3] + cells[4] + cells[5] + cells[6] + cells[7]);
        System.out.println(cells[8] + cells[9] + cells[10] + cells[11] + cells[12] + cells[13] + cells[14] + cells[15]);
        System.out.println(cells[16] + cells[17] + cells[18] + cells[19] + cells[20] + cells[21] + cells[22] + cells[23]);
        System.out.println(cells[24] + cells[25] + cells[26] + cells[27] + cells[28] + cells[29] + cells[30] + cells[31]);
    }
}