package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */

        
           if (isValidSquare(row, col)){
               if (!isSquareMarked(row, col)){
                   if (xTurn){
                        board[row][col] = Mark.X;
                   }
                   else {
                       board[row][col] = Mark.O;
                   }
                   xTurn = !xTurn;
                   return true;

               }
               else {
                   return false;
               }
           }
           else{
               return false;
           }
        
        
    }
        
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
    if ((row >= width || col >= width) || (row < 0 || col < 0)){
        return false;
            }
        else{
            return true;
        }
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
       if (board[row][col] != Mark.EMPTY){
            return true;
        }
        else{
            return false;
        }
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col];

            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if(isMarkWin(Mark.X) == true){
            return Result.X;
        }
        
        else if(isMarkWin(Mark.O) == true){
            return Result.O;
        }
        
        else if(isTie()){
            return Result.TIE;
        }
        
        else
            return Result.NONE;
        

        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
           boolean result = false;
           for (int i=0; i<width; i++){ //Row
               result = true;
               for (int j=0; j<width; j++){
                   if (board[i][j] != mark)
                   result = false;
               }
               if (result){
                   break;
               }
           }

           if (!result){
            for (int j=0; j<width; j++){ //Columns
                result = true;
                for (int i=0; i<width; i++){
                    if (board[i][j] != mark)
                        result = false;
                }                 
                if (result){
                    break;
                }
            }
           }
        //Diagonal
           if (!result){
                result = true;
                for (int i = 0; i < width; ++i){
                    if (board[i][i] != mark){
                        result = false;
                    }
                }
 
            }
            if (!result){
                result = true;
                for (int j = 0;j < width; ++j){
                    if (board[(width - 1) - j][j] != mark){
                        result = false;
                    }
                }
            }


            return result;


    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        boolean boardEmpty = false;
        for (int i = 0; i < width; ++i){
            for (int j = 0; j < width; ++j){
                if (board[i][j] == Mark.EMPTY){
                    boardEmpty = true;
                }
            }
        }
        if (boardEmpty) {
            return false;
        }
            else{
                return true;
            }

        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        
        int size = 0;
        for (int i = 0; i < width; ++i){
            output.append(i);
        }
        output.append("\n");
        for (int j = 0; j < width; ++j){
            output.append(size + " ");
            for (int n = 0; n < width; ++n){
                output.append(board[j][n]);
                }
                output.append("\n");
                size++;
            }
        
        
        return output.toString();
        
    }
    
}
