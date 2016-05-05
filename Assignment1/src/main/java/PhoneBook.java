import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PhoneBook {

    // Keep list of all existing (i.e. not deleted yet) contacts.
    private Map<Integer, String> contacts = new HashMap<>();

    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        PhoneBook phoneBook = new PhoneBook();
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            phoneBook.processQuery(readQuery(in));
    }

    private static Query readQuery(FastScanner in) {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            contacts.put(query.number, query.name);
        } else if (query.type.equals("del")) {
            contacts.remove(query.number);
        } else {
            String response = contacts.get(query.number);
            if (response == null) {
                response = "not found";
            }
            System.out.println(response);
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
