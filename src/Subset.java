
public class Subset {
    public static void main(String[] args) {
        if (args.length < 1)
            return;
        int num = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        String input = StdIn.readLine();
        String[] s = input.split(" ");

        int j = 0;
        
        while (j < s.length) {
            queue.enqueue(s[j]);
            j++;
            
        }

        for (int i = 0; i < num; i++) {
            StdOut.println(queue.dequeue());
        }
    }

}
