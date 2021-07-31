import java.sql.SQLOutput;

public class NewKnights {
    int a[][];
    int n;
//    int[][] pos ={{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};
    int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    NewKnights(){
    n=8;
    a=new int[n][n];
    a[0][0]=1;
    disp();
    solver(0,0,2);
    disp();

    }
    public void disp(){
//        System.out.println();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
                System.out.print(a[i][j]+"\t");
            System.out.println();
        }
        System.out.println();
    }
    boolean is_valid(int row,int col)
    {
        if(row>=0&&row<n&&col>=0&&col<n&&a[row][col]==0)
            return true;
        return false;
    }
    boolean solver(int x,int y,int cnt)
    {
        int next_x,next_y;
        if(cnt==65)
            return true;
        for(int i=0;i<8;i++)
        {
            next_x=x+xMove[i];
            next_y=y+yMove[i];
            if(is_valid(next_x,next_y))
            {
                a[next_x][next_y]=cnt;
                if(solver(next_x,next_y,cnt+1))
                    return true;
                else
                    a[next_x][next_y]=0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new NewKnights();
    }

}
