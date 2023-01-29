// https://www.acmicpc.net/problem/27066
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main27066 {
    public static void main(String[] args) throws Exception {
        new Main27066().solution();
    }

    static class Bag{
        int total;
        int n = 1;
        double mean;
        
        Bag(int m){
            this.total = m;
            this.mean = m;
        }

        double getMeanIfAdd(Bag b){
            return 1.0 * (total + b.total) / (n + b.n);
        }

        double add(Bag b){
            total += b.total;
            n += b.n;
            mean = 1.0 * total / n;
            return mean;
        }
    }
    
    void solution() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // in
        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(tokenizer.nextToken());
        }
        

        // 회차마다 주머니를 합쳐 K개 주머니에서 (K+1)/2번째 항목을 값으로 사용
        // 1 ~ k/2 항목의 이동은 점수에 영향이 없음
        // 기댓값을 증가시키려면 1~k/2 항목은 최대한 작아야 함
        // 가설1: 작은 값끼리 합치는 것이 유리
        // - 5개일때 -> 6/2 ... 4개일때 -> 5/2 ... 3개일때 -> 4/2 ... -> 2개일때 -> 3/2 -> x(최악)
        // - 뒤에서 3번째 또는 전체/n 으로 수렴
        double max = 0;

        // 1. 정렬 (입력받으면서 정렬하는 것이 좋지만 가독성을 위해 여기 정리)
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        ArrayList<Bag> bags = new ArrayList<Bag>();
        for(int i=0;i<n;i++) pq.add(nums[i]);
        for(int i=0;i<n;i++) {
            bags.add(new Bag(pq.poll()));
        }

        for(int i=n-1; i>0; i--){
            // 1개 줄이는 방법
            Bag smallest = bags.remove(0);
            bags.get(0).add(smallest);
            for(int j=0;j<n;j++) {
                if (j+1 == (bags.size() + 1)/2){
                    if(bags.get(j).mean > max)
                        max = bags.get(j).mean;
                    break;
                } 
            }
        }

        // bag 1개
        if (bags.get(0).mean > max){
            max = bags.get(0).mean;
        }
        
        if ((max % 1) == 0)
            writer.write("" + (int)max);
        else
            writer.write(String.format("%.6f", max));

        writer.flush();
        reader.close();
        writer.close();
    }
}
