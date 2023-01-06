public class BoardCreate{
  private int [][] board = new int[9][9];

  //creates board by filling in diagonally then filling in the rest
  public BoardCreate(){
    fillDiagonal(); 
    fillRemaining(0, 3);
  }

  //fills numbers diagonally 
  public void fillDiagonal() { 
    for (int i = 0; i < 9; i += 3){
      fillBox(i, i); 
    } 
  }

  //checks if the number is unused in the 3x3 
  public boolean unUsedInBox(int rowStart, int colStart, int num){ 
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){ 
        if (board[rowStart + i][colStart + j] == num){
          return false; 
        }
      }
    }
    return true; 
  } 
  
  //fills the box (3x3) 
  public void fillBox(int row,int col){
    int num = 0;
    for (int i = 0; i < 3; i++){ 
      for (int j = 0; j < 3; j++){ 
        do{ 
          num = (int) (Math.random() * 9 + 1); 
        }while (!unUsedInBox(row, col, num)); 
        board[row + i][col + j] = num;
      } 
    } 
  } 

  //checks if the number is allowed to be there and isn't used in row, column, or box (3x3)
  public boolean CheckIfSafe(int i,int j,int num){ 
    return (unUsedInRow(i, num) && unUsedInCol(j, num) && unUsedInBox(i - i % 3, j - j % 3, num)); 
  } 
  
  //checks if unused in row
  public boolean unUsedInRow(int i,int num){ 
    for (int j = 0; j < 9; j++){ 
      if (board[i][j] == num){
        return false;
      }
    }
    return true; 
  } 
  
  //checks if unsused in column
  public boolean unUsedInCol(int j,int num){ 
    for (int i = 0; i < 9; i++){ 
      if (board[i][j] == num){
        return false; 
      }
    }
    return true; 
  } 
  
  //fills remaining 0 with numbers
  public boolean fillRemaining(int i, int j){ 
    if (j >= 9 && i < 8){ 
      i = i + 1; 
      j = 0; 
    } 
    if (i >= 9 && j >= 9){
      return true; 
    }
    if (i < 3) { 
      if (j < 3) 
          j = 3; 
      } 
      else if (i < 6){ 
        if (j == (int)(i / 3)*3){
          j =  j + 3; 
        } 
      }
      else{ 
        if (j == 6){ 
          i = i + 1; 
          j = 0; 
          if (i >= 9){ 
            return true;
          } 
        } 
      } 
  
      for (int num = 1; num <= 9; num++){ 
        if (CheckIfSafe(i, j, num)){ 
          board[i][j] = num; 
          if (fillRemaining(i, j+1)) {
            return true; 
          }
          board[i][j] = 0; 
        } 
      } 
      return false; 
  } 

  //gets created board
  public int[][] getBoardStore(){
    return board;
  }

}