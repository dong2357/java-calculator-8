package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("Input calc string:");
        String input = Console.readLine();

        int result = add(input);

        System.out.println("result: " + result);
    }

    public static int add(String text){
        if (text == null || text.isEmpty()){
            return 0;
        }

        String[] numbers = text.split(",|:");

        int sum = 0;
        for(String numberStr : numbers){
            sum+= Integer.parseInt(numberStr);
        }

        return sum;
    }
}
