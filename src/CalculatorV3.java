import java.util.Scanner;

public class CalculatorV3 {
    public static void main(String[] args) {
        //2+3+4=9
        //X+V+III=XVIII
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку


        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1]))
            if(converter.isRoman(data[0]) == converter.isRoman(data[2])){
                int a;
                int b;
                int c;
                //Определяем, римские ли это числа
                boolean isRoman = converter.isRoman(data[0]);
                if(isRoman){
                    //если римские, то конвертируем их в арабские
                    //X+V+III
                    //X-10-5
                    //V - 5-2
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                    c = converter.romanToInt(data[2]);
                }else{
                    //если арабские, конвертируем их из строки в число
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                    c = Integer.parseInt(data[2]);

                }
                //выполняем с числами арифметическое действие
                int result = switch (actions[actionIndex]) {
                    case "+" -> (a + b + c);
                    case "-" -> (a - b - c);
                    case "*" -> (a * b * c);
                    default -> (a / b / c);
                };
                //18->XVIII
                if(isRoman){
                    //если числа были римские, возвращаем результат в римском числе
                    System.out.println(converter.intToRoman(result));
                }
                else{
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }
            }else{
                System.out.println("Числа должны быть в одном формате");
            }


    }
}
