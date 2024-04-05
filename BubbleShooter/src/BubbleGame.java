import java.util.Random;
import java.util.jar.JarOutputStream;

public class BubbleGame
{
    private int rows, cols, initialRows, numColors;
    private Bubble[][] board;
    private Bubble nextBubble;
    private Random rand;
    private boolean win;
    private boolean lose;

    public BubbleGame(int rows, int cols, int initalRows, int numColors)
    {
        this.rows = rows;
        this.cols = cols;
        this.initialRows = initalRows;
        this.numColors = numColors;
        this.win = false;
        this.lose = false;

        init();
    }

    private void init()
    {
        rand = new Random();
        this.board = new Bubble[rows][cols];

        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.cols; j++)
            {
                this.board[i][j] = new Bubble(i,j,i < this.initialRows ? Shared.COLORS[rand.nextInt(this.numColors)] : Shared.EMPTY);
            }
        }
        generateNextBubble();
    }

    public void generateNextBubble()
    {
        int width = Shared.COLS*Shared.DIAMETER + Shared.DIAMETER;
        int height = Shared.ROWS*Shared.DIAMETER + Shared.DIAMETER;

        int x = width/2-Shared.RADIUS;
        int y = height;
        this.nextBubble = new Bubble(-1,-1, Shared.COLORS[rand.nextInt(this.numColors)]);
        this.nextBubble.setX(x);
        this.nextBubble.setY(y);
    }

    public Bubble getNextBubble()
    {
        return this.nextBubble;
    }

    public boolean checkCollision()
    {
        if(this.nextBubble.getRow() == 0) return true;
        for(int[] pair : this.nextBubble.getRow() % 2 == 0 ? Shared.EVEN_NEIGHBORS : Shared.ODD_NEIGHBORS)
        {
            int neighborRow = this.nextBubble.getRow() + pair[0];
            int neighborCol = this.nextBubble.getCol() + pair[1];
            if(neighborRow >= 0 && neighborRow < this.rows && neighborCol >=0 && neighborCol < this.cols &&
                    this.board[neighborRow][neighborCol].getColor() != Shared.EMPTY)
            {
                return true;
            }
        }
        return false;
    }

    public boolean addBubble(Bubble b)
    {
        try
        {
            boolean bottom = false;
            if(b.getCol() == this.cols && this.board[b.getRow()][b.getCol()-1].getColor() == Shared.EMPTY)
                b.setCol(b.getCol()-1);
            if(b.getRow() == this.rows)
            {
                bottom = true;
            }
            if(!bottom) this.board[b.getRow()][b.getCol()] = b;
            int count = countPop(b.getRow(), b.getCol(),b.getColor());
            if (count >= 3)
            {
                pop();
                checkFloating();
            }
            else if (bottom)
            {
                return false;
            }
            else
            {
                resetCheck();
            }
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    private void checkFloating(){
        for(int i = 1; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                if(board[i][j].getColor() != Shared.EMPTY && checkFloatingHelper(i,j))
                {
                    board[i][j] = new Bubble(i,j,Shared.EMPTY);
                }
                resetCheck();
            }
        }
    }

    private boolean checkFloatingHelper(int row, int col)
    {
        if(row == 0) return false;
        this.board[row][col].setChecked(true);
        for(int[] pair : row % 2 == 0 ? Shared.EVEN_NEIGHBORS : Shared.ODD_NEIGHBORS)
        {
            int neighborRow = row + pair[0];
            int neighborCol = col + pair[1];
            if(neighborRow >= 0 && neighborRow < this.rows && neighborCol >=0 && neighborCol < this.cols &&
                    this.board[neighborRow][neighborCol].getColor() != Shared.EMPTY &&
                    !this.board[neighborRow][neighborCol].isChecked())
            {
                if(!checkFloatingHelper(neighborRow,neighborCol)) return false;
            }
        }
        return true;
    }

    private void resetCheck()
    {
        for(Bubble[] row : board)
        {
            for(Bubble b : row)
            {
                b.setChecked(false);
            }
        }
    }

    private void pop()
    {
        for(int i = 0; i < this.rows; i++)
        {
            for(int j = 0; j < this.cols; j++)
            {
                if(board[i][j].isChecked()) board[i][j] = new Bubble(i,j,Shared.EMPTY);
            }
        }
    }

    private int countPop(int row, int col, char color)
    {
        int count = 1;
        if(row < this.rows) this.board[row][col].setChecked(true);
        for(int[] pair : row % 2 == 0 ? Shared.EVEN_NEIGHBORS : Shared.ODD_NEIGHBORS)
        {
            int neighborRow = row + pair[0];
            int neighborCol = col + pair[1];
            if(neighborRow >= 0 && neighborRow < this.rows && neighborCol >=0 && neighborCol < this.cols &&
                this.board[neighborRow][neighborCol].getColor() != Shared.EMPTY &&
                !this.board[neighborRow][neighborCol].isChecked() &&
                this.board[neighborRow][neighborCol].getColor() == color)
            {
                count += countPop(neighborRow,neighborCol,color);
            }
        }
        return count;
    }

    public Bubble getBubble(int row, int col)
    {
        return this.board[row][col];
    }

    public int getRows()
    {
        return this.rows;
    }

    public int getCols()
    {
        return this.cols;
    }

    public void printBoard()
    {
        for(int i = 0; i < this.rows; i++)
        {
            for(Bubble b: this.board[i])
            {
                System.out.print(i % 2 == 0 ? b.getColor() + " " : " " + b.getColor());
            }
            System.out.println();
        }
    }

    public boolean checkWin()
    {
        for(int i = 0; i < this.cols; i++)
        {
            if(this.board[0][i].getColor() != Shared.EMPTY) return false;
        }
        return true;
    }

    public boolean isLose()
    {
        return lose;
    }

    public boolean isWin()
    {
        return win;
    }

    public void setLose()
    {
        this.lose = true;
    }

    public void setWin()
    {
        this.win = true;
    }

    public static void main(String[] args)
    {
        BubbleGame game = new BubbleGame(10, 6, 4, 3);
        game.printBoard();
        game.addBubble(new Bubble(4,0,'G'));
        game.printBoard();
    }
}
