import java.lang.Math;
//import java.lang.*; 
public class Board{
  private int [] [] board = new int [9][9];
  private int [] [] boardStore = new int[9][9];

  //Creates board using BoardCreate. Shuffles board, then stores it for later on when needed for hint or when coparing to the user completed board (this is what boardStore is). 
  public Board(){
    BoardCreate a1 = new BoardCreate();
    boardStore = a1.getBoardStore();
    for(int a = 0; a < 9; a++){
      for(int b = 0; b < 9; b++){
        board[a][b] = boardStore[a][b];
      }
    }
    shuffle100();
    for(int a = 0; a < 9; a++){
      for(int b = 0; b < 9; b++){
        boardStore[a][b] = board[a][b];
      }
    }
    makeGame();
  } 
  
  /*Shuffles two of the three box of columns (box 1 is columns 1-3, box 2 is columns 4-6, and box 3 is columns 7-9) **box may not be the correct term so ill give an example below:
  1 1 1   2 2 2   3 3 3  */
   public void shuffleBoard3Col(){
    for(int c = 0; c < 3; c++){
      int changeCol1 = ((int)(Math.random() * 3)) * 3;
      int changeCol2 = ((int)(Math.random() * 3)) * 3;
      int temp = 0;
      for(int n = 0; n < 3; n++){
        for(int i = 0; i < 9; i++){
          temp = board[i][changeCol1];
          board[i][changeCol1] = board[i][changeCol2];
          board[i][changeCol2] = temp;
        }
        changeCol1++;
        changeCol2++;
      }
    }
  }

  /*Shuffles two of the three box of rows (box 1 is rows 1-3, box 2 is rows 4-6, and box 3 rows is 7-9) **box may not be the correct term so ill give an example below:
  1
  1
  1

  2
  2
  2

  3
  3
  3 */
  public void shuffleBoard3Row(){
    for(int z = 0; z < 3; z++){
      int changeRow1 = ((int)(Math.random() * 3)) * 3;
      int changeRow2 = ((int)(Math.random() * 3)) * 3;

      int temp = 0;
      for(int a = 0; a < 3; a++){
        for(int c = 0; c < 9; c++){
          temp = board[changeRow1][c];
          board[changeRow1][c] = board[changeRow2][c];
          board[changeRow2][c] = temp;
        }
        changeRow1++;
        changeRow2++;
      }
    }
  }

  /*Shuffles two columns within a box. Example:
  1 2 3 turns into 3 1 2  */ 
  public void shuffleBoard2Col(){
    for(int x = 0; x <= 6; x += 3){
      int changeCol1 = (int)((Math.random() * 3) + x);
      int changeCol2 = (int)((Math.random() * 3) + x);
      while(changeCol1 == changeCol2){
        changeCol2 = (int)((Math.random() * 3) + x);
      }
      int temp = 0;
      for(int j = 0; j < 9; j++){
        temp = board[j][changeCol1];
        board[j][changeCol1] = board[j][changeCol2];
        board[j][changeCol2] = temp;
      }
    }
  }

  /*Shuffles two rows within a box. Example:
  1
  2
  3
  turns into
  3
  1
  2 */
  public void shuffleBoard2Row(){
    for(int x = 0; x <= 6; x += 3){
      int changeRow1 = (int)((Math.random() * 3) + x);
      int changeRow2 = (int)((Math.random() * 3) + x);
      while(changeRow1 == changeRow2){
        changeRow2 = (int)((Math.random() * 3) + x);
      }
      int temp = 0;
      for(int j = 0; j < 9; j++){
        temp = board[changeRow1][j];
        board[changeRow1][j] = board[changeRow2][j];
        board[changeRow2][j] = temp;
      }
    }
  }

  //shuffles 100 times by calling the 4 methods above
  public void shuffle100(){
    for(int x = 0; x < 100; x++){
      shuffleBoard2Row();
      shuffleBoard2Col();
      shuffleBoard3Row();
      shuffleBoard3Col();  
    }
  }

  //removes numbers to create the puzzle 
  public void makeGame(){
    for(int x = 0; x < 41; x++){
      int row = (int)(Math.random() * 9);
      int col = (int)(Math.random() * 9);
      while(board[row][col] == 0){
        row = (int)(Math.random() * 9);
        col = (int)(Math.random() * 9);
      }
      board[row][col] = 0;
    }
  }

  //prints out the board
  public String toString(){
    String hold = "";
    for(int row = 0; row < 9; row++){
      for(int col = 0; col < 9; col++){
        if(col == 3 || col == 6){
          hold += "\t";
        }
        if(col < 3 || (col > 2 && col < 6) || (col > 5 && col < 8)){
          hold += board[row][col] + " ";
        }
        else{
          hold += board[row][col] + "\n"; 
        }
      }
      if(row == 2 || row == 5){
          hold += "\n";
      }
      else{
        hold += "";
      }
    }
    return hold;
  }

  //Allows user to input numbers into the board. If number isn't valid, asks for a valid number. If 0 is entered for row and column, it will give a hint
  public void inputNum(int row, int col, int num){
    if(row == 0 && col == 0){
      int check = 0;
      for(int a [] : board){
        for(int b : a){
          if(b == 0){
            check++;
          }
        }
      }
      if(check > 0){
        getHint();
      }
      else{
        System.out.println("No more hints :(");
      }
    }
    else if(row < 1 || row > 9 || col < 1 || col > 9 || num > 9 || num < 1){
      System.out.println("Please enter a number 1-9");
    }
    else{
      board[Math.abs(row - 9)][col - 1] = num;
    }
  }  

  //checks if user finished board is equal to the correct solve
  public int boardCheck(){
    for(int a = 0; a < 9; a++){
      for(int b = 0; b < 9; b++){
        if(boardStore[a][b] != board[a][b]){
          return -1;
        }
      }
    }
    return 1;
  }

  //gives a random hint in the location of a 0 on the board
  public void getHint(){
    int randomRow = (int) (Math.random() * 9);
    int randomCol = (int) (Math.random() * 9);
    while(board[randomRow][randomCol] != 0){
      randomCol = (int) (Math.random() * 9);
      randomRow = (int) (Math.random() * 9);
    }
    board[randomRow][randomCol] = boardStore[randomRow][randomCol];
  }
}

 


