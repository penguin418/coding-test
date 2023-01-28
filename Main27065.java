import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.lang.Math;

public class Main27065 {
    public static void main(String[] args) throws Exception{
        new Main27065().solution();
    }

    public void solution() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // in
        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];
        for(int i=0; i< n; i++){
            nums[i] = Integer.parseInt(reader.readLine());
        }

        // memo
        int[] isAbdunt = new int[5001];
        isAbdunt[1] = 0;
        isAbdunt[2] = 0;

        // solution
        for(int i=3; i<5001; i++){
            int sqrt = (int) Math.sqrt(i);
            int sumOfDivisor = 1;
            for(int j=2; j<=sqrt; j++){
                int divisor2 = i / j;
                if (i == j * divisor2){
                    if (j != divisor2)
                        sumOfDivisor += j + divisor2;
                    else
                        sumOfDivisor += j;
                }
            }
            if (sumOfDivisor > i){
                isAbdunt[i] = 1;
            }
        }

        // out
        for(int i=0; i<n; i++){
            int sqrt = (int) Math.sqrt(nums[i]);
            boolean abdunt = true;
            if (isAbdunt[nums[i]] == 0){
                writer.write("BOJ 2022\n");
                continue;
            }
            for(int j=2; j<=sqrt; j++){
                int divisor2 = nums[i] / j;
                if (nums[i] == j * divisor2){
                    if (isAbdunt[j]>0||isAbdunt[divisor2]>0){
                        abdunt = false;
                        break;
                    }
                }
            }
            writer.write( abdunt ? "Good Bye" : "BOJ 2022");
            if (i < n-1){
                writer.write("\n");
            }
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}
