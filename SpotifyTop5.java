import java.util.*;

public class SpotifyTop5 {
    public static void main(String[] args) {

        int[] listeners = {45, 12, 78, 23, 56, 89, 34, 67, 18, 91, 50, 39};

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int k = 5;

        for (int count : listeners) {

            if (heap.size() < k) {
                heap.add(count);
            }
            else if (count > heap.peek()) {
                heap.poll();
                heap.add(count);
            }
        }

        ArrayList<Integer> top5 = new ArrayList<>(heap);

        Collections.sort(top5, Collections.reverseOrder());

        System.out.println("Top 5 Monthly Listener Counts:");

        for (int x : top5) {
            System.out.println(x + " Million");
        }
    }
}