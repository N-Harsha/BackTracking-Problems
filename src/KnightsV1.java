import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class KnightsV1{

    //    BufferedWriter bw;
    int a[][];
    int cnt1;
    boolean flag;
    //    int[][] pos ={{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};
    int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    int cnt;
    KnightsV1()throws IOException{
        flag=false;
        a= new int[8][8];
        new BufferedWriter(new FileWriter("entry.txt"));
        cnt=0;
        cnt1=0;
        disp();
        writer(0,0);
        disp();
//        bw.close();
    }
    public void disp1() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("entry.txt",true));
        if(cnt1==0)
            bw.write("");
        cnt1++;
        bw.append(String.valueOf(cnt1)).append(" )\n");
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
                bw.append(String.valueOf(a[i][j])).append("\t");
            bw.append("\n");
        }
        bw.append("\n");
        bw.close();
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
    public boolean writer(int row,int col)throws IOException{
        cnt++;

        for(int i=0;i<8;i++)
        {
            int r;
            a[row][col]=cnt;
            if(cnt==64)
                return true;
            row += xMove[i];
            col += yMove[i];
            if(is_valid(row,col))
            {

                if(writer(row,col))
                    return true;
                else{
                    a[row][col] = 0;
                    cnt--;

                }
            }
            row -= xMove[i];
            col -= yMove[i];
        }


        return false;

    }

    private boolean is_valid(int row, int col) {

        if(row>=0&&row<8&&col>=0&&col<8)
        {
            if(a[row][col]==0)
                return true;
        }
        return false;
    }

    public static void main(String[] args)throws IOException {
        new KnightsV1();
    }
}