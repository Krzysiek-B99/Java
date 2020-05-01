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
        List<Integer> PossiblePlaces = new ArrayList<>();
        PossiblePlaces.add(1);
        PossiblePlaces.add(2);
        PossiblePlaces.add(3);
        PossiblePlaces.add(4);
        PossiblePlaces.add(5);
        PossiblePlaces.add(6);
        PossiblePlaces.add(7);
        PossiblePlaces.add(8);
        PossiblePlaces.add(9);
        //List <Integer> PossiblePlaces = List.of(1, 2, 3,4,5,6,7,8,9); //nie dziala bo elementow utworzonych przez list.of nie mozna zmieniac (np remove w funkcji CheckPlace)
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
            PlaceXO(gameboard,PossiblePlaces);
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

        List <Integer> horiz1 = List.of(1, 2, 3);  //wygrywające opcje
        List <Integer> horiz2 = List.of(4, 5, 6);
        List <Integer> horiz3 = List.of(7, 8, 9);
        List <Integer> vert1 = Arrays.asList(1, 4, 7);
        List <Integer> vert2 = Arrays.asList(2, 5, 8);
        List <Integer> vert3 = Arrays.asList(3, 6, 9);
        List <Integer> diag1 = Arrays.asList(1, 5, 9);
        List <Integer> diag2 = Arrays.asList(3, 5, 7);

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
    public static void PlaceXO(char[][] gameboard,List<Integer> PossiblePlaces){
        Scanner scan = new Scanner(System.in);
        int pos;
        while(true){
            pos= scan.nextInt();
            boolean check=CheckPlace(pos,PossiblePlaces);
            if(!check)
            System.out.println("Wrong field!");
            else
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
    public static boolean CheckPlace(int pos,List<Integer> PossiblePlaces){
       boolean checkedplace=false;
       if (PossiblePlaces.contains(pos))
           checkedplace=true;
        PossiblePlaces.remove(Integer.valueOf(pos));
        //System.out.println(PossiblePlaces);
        return checkedplace;
    }
    }


