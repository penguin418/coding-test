import java.io.*;
import java.util.*;

public class Main27069 {
    public static void main(String[] args) throws Exception {
        new Main27069().solution();
    }

    void solution() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            var tokenizer = new StringTokenizer(reader.readLine());
            long a, b, c, k, n;
            a = Long.parseLong(tokenizer.nextToken());
            b = Long.parseLong(tokenizer.nextToken());
            c = Long.parseLong(tokenizer.nextToken());
            n = Long.parseLong(tokenizer.nextToken());
            k = Long.parseLong(tokenizer.nextToken());

            long[] points = new long[9];

            if (a < c) {
                long tmp = a;
                a = c;
                c = tmp;
            }
            
            // 전체 무승부
            points[0] = b * (n-1);
            // 우승이 무승부보다 유리하고 패배가 무승부보다 유리할때 k등의 최대점수
            points[1] = a * (n - k) + (k - 1) / 2 * (a + c) + ((k - 1) % 2) * Math.max(b, c);
            // 우승이 무승부보다 유리하고 무승부가 패배보다 유리할때 k등의 최대점수
            points[2] = a * (n - k) + (k - 1) * b;
            // 무승부가 우승보다 유리하고 패배가 우승보다 유리할때 k등의 최대점수
            points[3] = b * (n - k) + (k - 1) / 2 * (a + c) + ((k - 1) % 2) * b;
            // 무승부가 우승보다 유리하고 무승부가 패배보다 유리할때 k등의 최대점수
            points[4] = b * (n - k) + (k - 1) * b;
            // 패배가 무승부보다 유리하고 패배가 승리보다 유리할때 k등에서 가능한 최소점수
            points[5] = b * (k - 1) + (n - k) / 2 * (a + c) + ((n - k) % 2) * b;
            points[6] = b * (k - 1) + (n - k) * b;
            points[7] = c * (k - 1) + (n - k) / 2 * (a + c) + ((n - k) % 2) * Math.min(a, b);
            points[8] = c * (k - 1) + (n - k) * b;
            long max = Arrays.stream(points).max().getAsLong();
            long min = Arrays.stream(points).min().getAsLong();

            writer.write(max + " " + min + "\n");
        }
        writer.flush();
        writer.close();
        reader.close();
    }
}
