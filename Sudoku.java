import java.util.Scanner;
class Sudoku{
  public static void main(String[] args) {
    //Runs game, introduction and how to play. Then it prompts user what row, column, and number to place the number in. When the board is solved it will say congrats.
    Scanner scan = new Scanner(System.in);
    Board a1 = new Board();
    System.out.println("Hello and welcome to Sudoku.\nUse numbers 1-9 to fill in the board.\nType 0 for row and column for a hint where there is a 0.\nGood luck!");
    System.out.println(a1);
    while(a1.boardCheck() != 1){
      System.out.println("Input row, then hit enter, rows start at the bottom");
      int row = scan.nextInt();
      System.out.println("Input column, then hit enter, columns start from the left");
      int col = scan.nextInt();
      System.out.println("Input number to put in, then hit enter");
      int num = scan.nextInt();
      a1.inputNum(row, col, num);
      System.out.println("\n" + a1);
    }
    int check = a1.boardCheck();
    if(check == 1){
      System.out.println("Congrats you have won!");
    }
  }
}




