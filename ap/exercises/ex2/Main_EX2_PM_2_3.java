package ap.exercises.ex2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_3 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String filepath = "packman.txt";
        File file = new File(filepath);

        char[][] square;
        int xDot, yDot, score, k, c;
        long startTime;

        if (file.exists() && file.isFile())
        {
            System.out.println("Found game. Loading...");
            int[] gameData = loadFile(filepath);
            if (gameData == null || gameData.length < 6)
            {
                System.out.println("Error loading game. Starting a new game.");
                k = startNewGame(scanner);
            } else
            {
                xDot = gameData[0];
                yDot = gameData[1];
                score = gameData[2];
                k = gameData[3];
                c = gameData[4];
                startTime = System.currentTimeMillis() - gameData[5];

                square = loadMatrix(filepath, k);
                if (square == null)
                {
                    System.out.println("Error loading matrix. Starting a new game.");
                    k = startNewGame(scanner);
                } else
                {
                    System.out.println("Game loaded successfully!");
                    printMatrix(square);
                    gameLoop(scanner, square, xDot, yDot, score, k, c, startTime, filepath);
                    return;
                }
            }
        } else
        {
            k = startNewGame(scanner);
        }
    }

    public static int startNewGame(Scanner scanner)
    {
        System.out.println("Starting new game.");
        System.out.println("enter k");
        int k = scanner.nextInt();
        scanner.nextLine();

        System.out.println("enter c");
        int c = scanner.nextInt();
        scanner.nextLine();

        while (c > k * k)
        {
            System.out.println("invalid number.please enter again c");
            c = scanner.nextInt();
            scanner.nextLine();
        }

        char[][] square = createMatrix(k, c);
        int xDot = k / 2, yDot = k / 2, score = 0;
        square[xDot][yDot] = 'X';
        printMatrix(square);

        long startTime = System.currentTimeMillis();
        gameLoop(scanner, square, xDot, yDot, score, k, c, startTime, "packman.txt");

        return k;
    }

    public static void gameLoop(Scanner scanner, char[][] square, int xDot, int yDot, int score, int k, int c, long startTime, String filepath)
    {
        while (true)
        {
            System.out.println("enter character:");
            String input = scanner.nextLine();

            int newX = xDot, newY = yDot;

            switch (input)
            {
                case "w":
                {
                    newX--;
                    break;
                }
                case "s":
                {
                    newX++;
                    break;
                }
                case "a":
                {
                    newY--;
                    break;
                }
                case "d":
                {
                    newY++;
                    break;
                }
                case "q":
                    long timeElapsed = System.currentTimeMillis() - startTime;
                    System.out.println("EXIT");
                    System.out.println("Score is:" + score + "\nTimer is: " + timeElapsed);
                    saveFile(filepath,square,xDot,yDot,score,k,c,timeElapsed);
                    return;
                default:
                    System.out.println("WARNING");
                    break;
            }

            if (square[newX][newY] == '*')
            {
                long timeElapsed = System.currentTimeMillis() - startTime;
                System.out.println("Hitting the game wall");
                System.out.println("Scoren is:" + score + "\nTime is:" + timeElapsed);
                saveFile(filepath, square, xDot, yDot, score, k, c, timeElapsed);
                return;
            }
            else if (square[newX][newY] == '.') {
                score++;
                c--;
            }

            square[xDot][yDot] = ' ';
            xDot = newX;
            yDot = newY;
            square[xDot][yDot] = 'X';

            saveFile(filepath, square, xDot, yDot, score, k, c, System.currentTimeMillis() - startTime);

            if (c == 0)
            {
                System.out.println("You won!");
                long timeElapsed= System.currentTimeMillis() - startTime;
                System.out.println("Score is:" + score + "\ntime is:" + timeElapsed);
                return;
            }

            printMatrix(square);
        }
    }

    public static char[][] createMatrix(int k, int c)
    {
        char[][] square = new char[k + 2][k + 2];
        Random random = new Random();

        for (int i = 0; i < k + 2; i++)
        {
            for (int j = 0; j < k + 2; j++)
            {
                if (i == 0 || i == k + 1 || j == 0 || j == k + 1) square[i][j] = '*';
                else square[i][j] = ' ';
            }
        }

        int count = 0;
        while (count < c)
        {
            int i = random.nextInt(k) + 1;
            int j = random.nextInt(k) + 1;
            if (square[i][j] == ' ') {
                square[i][j] = '.';
                count++;
            }
        }

        return square;
    }

    public static void saveFile(String filepath, char[][] matrix, int x, int y, int score, int k, int c, long time)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath)))
        {
            writer.write(x + " " + y + " " + score + " " + k + " " + c + " " + time + "\n");
            for (int i=0;i< matrix.length;i++)
            {
                for (int j = 0; j < matrix.length; j++) {
                    writer.write(matrix[i][j]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error to saving file." + e.getMessage());
        }
    }

    public static int[] loadFile(String filepath)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath)))
        {
            String[] data = reader.readLine().split(" ");

            if (data.length < 6)
            {
                System.out.println("Error");
                return null;
            }

            int[] gameData = new int[data.length];
            for (int i = 0; i < data.length; i++)
            {
                gameData[i] = Integer.parseInt(data[i]);
            }
            return gameData;
        } catch (IOException | NumberFormatException e)
        {
            System.out.println("Error to loading file." + e.getMessage());
            return null;
        }
    }

    public static char[][] loadMatrix(String filepath, int k)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath)))
        {
            reader.readLine();
            char[][] square = new char[k + 2][k + 2];

            for (int i = 0; i < square.length; i++)
            {
                square[i] = reader.readLine().toCharArray();
            }
            return square;
        } catch (IOException e)
        {
            System.out.println("Error to loading" + e.getMessage());
            return null;
        }
    }

    public static void printMatrix(char[][] matrix) {
        for (int i=0;i< matrix.length;i++)
        {
            for (int j=0;j< matrix.length;j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
