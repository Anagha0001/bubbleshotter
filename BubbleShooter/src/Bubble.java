public class Bubble
{
    private int row, col;
    private char color;
    private boolean checked;
    private double x,y,changeX,changeY;

    public Bubble(int row, int col, char color)
    {
        this.row = row;
        this.col = col;
        this.color = color;
        this.checked = false;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public char getColor()
    {
        return this.color;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX(double x)
    {
        this.col = (int)((this.x+Shared.RADIUS) / Shared.DIAMETER);
        this.x = x;
    }

    public void setY(double y)
    {
        this.row = (int)((this.y+Shared.DIAMETER) / Shared.DIAMETER);
        this.y = y;
    }

    public double getChangeX()
    {
        return changeX;
    }

    public double getChangeY()
    {
        return changeY;
    }

    public void setChangeX(double changeX)
    {
        this.changeX = changeX;
    }

    public void setChangeY(double changeY)
    {
        this.changeY = changeY;
    }
}
