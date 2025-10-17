package calculator;
import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("Input calc string:");
            String input = Console.readLine();

            String processedInput = input.replace("\\n", "\n");

            int result = add(processedInput);

            System.out.println("result: " + result);
        }catch (IllegalArgumentException e) {
            //add 메소드에서 던진 예외를 여기서 잡음
            System.err.println(e.getMessage());
        }
    }

    public static int add(String text){
        if (text == null || text.isEmpty()){
            return 0;
        }

        String delimiter = ",|:"; // 기본 구분자 설정
        String numbersText = text;

        // 커스텀 구분자가 무엇인지 확인
        Matcher m = Pattern.compile("//(.)\n(.*)", Pattern.DOTALL).matcher(text);
        if(m.find()){ // 커스텀 구분자와 숫자 부분을 분리
            String customDelimiter = Pattern.quote(m.group(1));
            numbersText = m.group(2);

            delimiter += "|" + customDelimiter;
        }

        String[] numbers = numbersText.split(delimiter);

        return getSum(numbers);
    }

    private static int getSum(String[] numbers){
        int sum = 0;
        for(String numberStr : numbers){
            String trimmedStr = numberStr.trim();
            if (trimmedStr.isEmpty()){
                continue;
            }

            int number;
            try{
                number = Integer.parseInt(trimmedStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: \"" + trimmedStr + "\"");
            }
            //음수 값에 대한 예외처리
            if (number < 0){
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }
            sum+=number;
        }
        return sum;
    }
}
