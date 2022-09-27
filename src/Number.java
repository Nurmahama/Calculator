import java.util.Scanner;

public class Number {

    static Scanner st = new Scanner(System.in);
    Rim_number rim_number = new Rim_number();


    @Override
    public String toString() {
        return "";
    }

    public void st(String exp) throws Exception {
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

        int actionIndex = -1;
        {
            for (int i = 0; i < actions.length; i++) {
                if (exp.contains(actions[i])) {
                    actionIndex = i;
                    break;
                }
            }
            //Если не нашли арифметического действия, завершаем программу
            if (actionIndex == -1) {
                throw new Exception("Некорректное выражение");


            }

            //Делим строчку по найденному арифметическому знаку

            String[] data = exp.split(regexActions[actionIndex]);

            if (rim_number.isRoman(data[0]) != rim_number.isRoman(data[1])) {
                throw new Exception("Используються одновремено разные системы счисления");
            }
            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            else if (rim_number.isRoman(data[0]) == rim_number.isRoman(data[1])) {
                int a, b;

                            //Определяем, римские ли это числа
                boolean isRoman = rim_number.isRoman(data[0]);
                if (isRoman) {

                    //если римские, то конвертируем их в арабские
                    a = rim_number.romanToInt(data[0]);
                    b = rim_number.romanToInt(data[1]);

                } else {
                    //если арабские, конвертируем их из строки в число

                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }

                //выполняем с числами арифметическое действие
                int result = switch (actions[actionIndex]) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };


                if (isRoman) {
                    if (result <= 0) {
                        throw new Exception("Римской системе нет отрицательных чисел");
                    } else if (a > 10 || b > 10) {
                        throw new Exception("Калькулятор можеть принимать на выход числа от 1 до 10 включительно не более");
                    } else {
                        System.out.println(Rim_number.intToRoman(result));
                    }


                } else {
                    if (a > 10 || b > 10) {
                        throw new Exception("Калькулятор можеть принимать на выход числа от 1 до 10 включительно не более");
                    } else {
                        System.out.println(result);
                    }
                }
                } else {
                    throw new Exception("Числа должны быть в одном формате");
                }

            }


        }
    }



