
// https://www.acmicpc.net/problem/27067
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.*;

public class Main27067 {
    public static void main(String args[]) throws Exception {
        new Main27067().solution();
    }

    void solution() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Integer[][] data = new Integer[3][];
        for (int i = 0; i < 3; i++) {
            data[i] = new Integer[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                data[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        // 각 데이터의 연결방향을 저장
        List<Map<Integer, Integer>> directedGraph = new ArrayList<Map<Integer, Integer>>();
        for (int i = 0; i < n + 1; i++) {
            directedGraph.add(new HashMap<Integer, Integer>());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int prev = data[i][j];
                    int next = data[i][k];
                    int weight = directedGraph.get(prev).getOrDefault(next, 0);
                    directedGraph.get(prev).put(next, weight + 1);
                }
            }
        }
        // 각 데이터의 연결방향을 기준으로 더 많이 쌓였으면 원래 방향
        for (int i = 0; i < 3; i++) {
            Arrays.sort(data[0], (a, b) -> Integer.compare(
                    directedGraph.get(b).getOrDefault(a, 0),
                    directedGraph.get(a).getOrDefault(b, 0)));
        }
        StringBuilder answer = new StringBuilder();
        answer.append(data[0][0]);
        for (int i = 1; i < n; i++) {
            answer.append(" " + data[0][i]);
        }
        writer.write(answer.toString());
        writer.flush();
        reader.close();
        writer.close();
    }
}
