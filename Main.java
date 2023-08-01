package DEV120_2_1_Tekiev;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String nameOfFile = "";

            while (nameOfFile.isEmpty()) {

                System.out.println("Введите путь к текстовому файлу для чтения:");

                nameOfFile = scanner.nextLine();

            }

            File file = new File(nameOfFile);

            ReadFile readFile = new ReadFile();

            try {

                readFile.read(file);

            } catch (IOException e) {
                System.out.println(e);
            }

        }

}