import java.util.*;

public class Arguments {
    private Map<String, List<String>> params;

    public Arguments(String... args){
        params = new HashMap<>();
        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
            String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }
                options = new ArrayList<>();
                params.put(a.substring(1), options);
            }
            else if (options != null) {
                options.add(a);
            }
            else {
                System.err.println("Flag doesn't exist.");
                return;
            }
        }
    }

    public List<String> getArgumentsOf(String arg){
        return params.get(arg);
    }

    public void printOut(){
        for(Map.Entry<String,List<String>> entry : params.entrySet()){
            System.out.println("Key: " + entry.getKey());
            System.out.print("Values: ");
            for(String s: entry.getValue()){
                System.out.println(s + ", ");
            }
        }
    }
}
