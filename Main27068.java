//https://www.acmicpc.net/problem/27068
import java.util.*;
import java.io.*;

public class Main27068 {
    int N, M, K;
    long X;
    long[][] A;
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { 1, 0, -1, 0 };

    void solution() throws Exception {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        X = Long.parseLong(tokenizer.nextToken());
        A = new long[N][M];
        for (int i = 0; i < N; ++i) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; ++j) {
                A[i][j] = Long.parseLong(tokenizer.nextToken());
            }
        }

        // 이분탐색 -> 최소 얼마 차이?
        long lo = -1, hi = X;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (check(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        writer.write("" + hi);
        writer.flush();
        writer.close();
        reader.close();
    }

    boolean check(long mid) { // mid가 최대 차이일때 최적화해야하는 스팟이 K개 이하인가?
        long[][] tmp = new long[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(A[i], 0, tmp[i], 0, A[i].length);
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean bigDiff = false;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (0 <= nx && nx < N && 0 <= ny && ny < M
                            && tmp[nx][ny] - tmp[i][j] > mid) {
                        bigDiff = true;
                        break;
                    }
                }
                if (bigDiff) {
                    tmp[i][j] = X;
                    q.add(new int[] { i, j });
                }
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < M
                        && tmp[p[0]][p[1]] - tmp[nx][ny] > mid) {
                    tmp[nx][ny] = X;
                    q.add(new int[] { nx, ny });
                }
            }
            cnt++;
        }
        return cnt <= K;
    }

    public static void main(String[] args) throws Exception {
        new Main27068().solution();
    }
}
