import testGame.CMonster;
import testGame.CPlayer;
import testGame.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice = -1;
        while (choice != 3) {
            System.out.println("""
                    Выберите режим игры:
                    \t1)Рандомизированнные исходные данные (рандомные показатели, рандомное количество монстров, автоматическое исцеление героя в случае потери им половины или более здоровья)
                    \t2)Ввод данных пользователем
                    \t3)Прекратить работу программы""");
            System.out.print("Ваш выбор: ");
            Scanner in = new Scanner(System.in);
            System.out.println();
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введенное значение не является целочисленным (int)");
            }
            switch (choice) {
                case 1 -> {
                    Game g = new Game();
                }
                case 2 -> {
                    System.out.println("Введите параметры объекта класса 'Игрок'");
                    int[] tmp = inputDamage(in);
                    CPlayer pl = new CPlayer(inputAttack(in), inputDefence(in), inputHealth(in), tmp[0], tmp[1]);
                    pl.getInfo();
                    System.out.println("Введите кол-во монстров : ");
                    CMonster[] arrOfMonst = new CMonster[0];
                    while (arrOfMonst.length == 0) {
                        try {
                            arrOfMonst = new CMonster[Integer.parseInt(in.nextLine())];
                            if (arrOfMonst.length == 0)
                                System.out.println("Герой заскучает без монстров. Сделай ему хотя бы 1 монстра");
                        } catch (NumberFormatException e) {
                            System.out.println("Введенное значение не является целочисленным (int)");
                        }
                    }
                    System.out.printf("Создадим %d объектов класса Монстр\n", arrOfMonst.length);
                    byte b = -1;
                    System.out.println("Вы хотите сами давать монстрам имена? (1 - да, 0 - нет) :");
                    try {
                        b = Byte.parseByte(in.nextLine());
                        if (b != 0 && b != 1) {
                            System.out.println("Введенное значение не является 0 или 1; значит я сам придумаю им имена");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Введенное значение не является 0 или 1; значит я сам придумаю им имена)");
                    }
                    if (b == 0) {
                        for (int i = 0; i < arrOfMonst.length; i++) {
                            System.out.printf("Введите параметры %d-го объекта класса 'Монстр' :\n", i + 1);
                            tmp = inputDamage(in);
                            arrOfMonst[i] = new CMonster(inputAttack(in), inputDefence(in), inputHealth(in), tmp[0], tmp[1]);
                            arrOfMonst[i].getInfo();
                        }
                    } else {
                        for (int i = 0; i < arrOfMonst.length; i++) {
                            System.out.printf("Введите параметры %d-го объекта класса 'Монстр' :\n", i + 1);
                            System.out.println("Дайте имя монстру: ");
                            String str = in.nextLine();
                            tmp = inputDamage(in);
                            arrOfMonst[i] = new CMonster(inputAttack(in), inputDefence(in), inputHealth(in), tmp[0], tmp[1], str);
                            arrOfMonst[i].getInfo();
                        }
                    }
                    Game game = new Game(arrOfMonst, pl);
                }
            }
        }
    }

    public static byte inputAttack(Scanner in) {
        System.out.println("Введите параметр 'Атака' (натуральное число в промежутке [1,30]) : ");
        byte attack = -1;
        while (attack == -1) {
            try {
                attack = Byte.parseByte(in.nextLine());
                if (attack < 1 || attack > 30) {
                    System.out.println("Ошибка. Параметр 'Атака' ограничен промежутком [1;30] - введите заново");
                    attack = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. 'Атака' это целочисленный параметр(byte) - введите заново");
            }
        }
        return attack;
    }

    public static byte inputDefence(Scanner in) {
        byte defence = -1;
        System.out.println("Введите параметр 'Защита' (натуральное число в промежутке [1,30]) : ");
        while (defence == -1) {
            try {
                defence = Byte.parseByte(in.nextLine());
                if (defence < 1 || defence > 30) {
                    System.out.println("Ошибка. Параметр 'Защита' ограничен промежутком [1;30] - введите заново");
                    defence = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. 'Защита' это целочисленный параметр (byte)- введите заново");
            }
        }
        return defence;
    }

    public static int inputHealth(Scanner in) {
        int health = -1;
        System.out.println("Введите параметр 'Здоровье' (натуральное число) : ");
        while (health == -1) {
            try {
                health = Integer.parseInt(in.nextLine());
                if (health < 1) {
                    System.out.println("Ошибка. Параметр 'Здоровье' должен быть больше 0 - введите заново ");
                    health = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. 'Здоровье' это целочисленный параметр - введите заново");
            }
        }
        return health;
    }

    public static int[] inputDamage(Scanner in) {
        int m = -1, n = -1;
        System.out.println("Введите параметр 'Урон'. Это диапазон натуральных чисел M - N.");
        while (n == -1) {
            try {
                System.out.println("Введите нижнюю границу: ");
                m = Integer.parseInt(in.nextLine());
                if (m < 1) {
                    System.out.println("Ошибка. Параметр 'Урон' это диапазон натуральных чисел (M>0)");
                    m = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. 'Урон' задается двумя целочисленными числами");
            }
            if (m != -1) {
                while (n == -1) {
                    System.out.println("Введите верхнюю границу:");
                    try {
                        n = Integer.parseInt(in.nextLine());
                        if (n < m) {
                            System.out.printf("Ошибка. Вверхняя граница должна быть больше нижней %d\n", m);
                            n = -1;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка. 'Урон' задается двумя целочисленными числами");
                    }
                }
            }
        }
        return new int[]{m, n};
    }
}
