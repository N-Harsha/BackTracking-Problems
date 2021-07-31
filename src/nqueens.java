import java.util.Scanner;

public class nqueens {
    int a[][];
    public nqueens(int n){
        a=new int[n][n];
        disp(a);
        solver(a,0);
        System.out.println();
        disp(a);
    }
    boolean solver(int[][] a,int row){
        if(row>=a.length)
            return true;
        for(int i=0;i<a.length;i++){
            if(checker(a,row,i))
                a[row][i]=1;
            else{
                continue;
            }
            if(solver(a,row+1)){
                return true;
            }
            a[row][i]=0;
        }
        return false;
    }

boolean checker(int board[][], int row, int col)
{
    int i, j;
    int N=board.length;

    for(i=0;i<N;i++)
        if(board[i][col]==1)
            return false;

    for (i = 0; i < col; i++)
        if (board[row][i] == 1)
            return false;

    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j] == 1)
            return false;

    for (i = row, j = col; j >= 0 && i < N; i++, j--)
        if (board[i][j] == 1)
            return false;

    for(i=row,j=col;j<N&&i<N;i++,j++)
        if(board[i][j]==1)
            return false;

    for(i = row , j = col ; i >= 0 && j < N ; i--, j++)
        if(board[i][j]==1)
            return false;

    return true;
}

    void disp(int[][] a){
        for(int[] i : a)
        {
            for(int j: i){
                System.out.print(j+"\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the value of n : ");
    new nqueens(sc.nextInt());
    }
}
