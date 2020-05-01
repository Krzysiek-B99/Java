import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Integer> XPos = new ArrayList<>();
    static List<Integer> OPos = new ArrayList<>();
    static int counter = 0;  // to change symbol per round  //if =9 there is a tie
    static char symbol = 'X';
    public static void main(String[] args) {
        char[][] gameboard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
   /* for(char[] row : gameboard) {
        for(char c : row){
            System.out.print(c);
        }
        System.out.println();
    }*/
        System.out.println("Choose position 1-9: ");
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                System.out.print(gameboard[i][j]);
            }
            System.out.println();
        }
        while (true) {
            if (counter % 2 != 0)
                symbol = 'O';
            else
                symbol = 'X';
            System.out.println(symbol+" moves\n");
            PlaceXO(gameboard);
            //System.out.println("Choose position 1-9: ");
            for (int i = 0; i < gameboard.length; i++) {
                for (int j = 0; j < gameboard.length; j++) {
                    System.out.print(gameboard[i][j]);
                }
                System.out.println();
            }

            String result = winner();
            System.out.println(result);
            if (result != "") {
                break;
            }
            counter++;
            if(counter==9){
                System.out.println("TIE!");
                break;
            }
        }
    }

    public static String winner() {

        List horiz1 = List.of(1, 2, 3);  //wygrywające opcje
        List horiz2 = List.of(4, 5, 6);
        List horiz3 = List.of(7, 8, 9);
        List vert1 = Arrays.asList(1, 4, 7);
        List vert2 = Arrays.asList(2, 5, 8);
        List vert3 = Arrays.asList(3, 6, 9);
        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);

        List<List> win = new ArrayList<>();
        win.add(horiz1);
        win.add(horiz2);
        win.add(horiz3);
        win.add(vert1);
        win.add(vert2);
        win.add(vert3);
        win.add(diag1);
        win.add(diag2);
        String winner="";
        for (List l : win) {             //petla "przejezdza" po wszystkich "podlistach" listy 'win'
            if (OPos.containsAll(l)) {   //sprawdzenie czy lista z pozycjami O (OPos) zawiera wszystkie elementy z danej "podlisty" listy 'win'
               winner= "O Wins";
            }
        }
        for (List l : win) {
            if (XPos.containsAll(l)) {
                winner= "X Wins";
            }
        }
        return winner;
    }
    public static void PlaceXO(char[][] gameboard){
        List<Integer> PossiblePlaces = List.of(1,2,3,4,5,6,7,8,9);
        Scanner scan = new Scanner(System.in);
        int pos = scan.nextInt();
        boolean ifcontains = PossiblePlaces.contains(pos);
        while(ifcontains==false){
            System.out.println("Enter a number 1-9!");
            break;
        }
        switch (pos) {
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
        }
            if(symbol=='X')
                XPos.add(pos);
            if(symbol=='O')
                OPos.add(pos);

        }
    }


