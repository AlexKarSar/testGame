package testGame;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private CMonster[] arrOfMonst;
    private CPlayer player;

    public Game(CMonster[] arrOfMonst, CPlayer player){
        this.arrOfMonst = arrOfMonst;
        this.player = player;
        fight();
        this.arrOfMonst = new CMonster[0];
        this.player = new CPlayer();
        player.setNumOfHeal((byte)4);
    }

    public Game() {
        Random r = new Random();
        arrOfMonst = new CMonster[r.nextInt(1, 7)];
        for (int i = 0; i < arrOfMonst.length; i++) {
            arrOfMonst[i] = new CMonster((byte) r.nextInt(1, 30), (byte) r.nextInt(1, 30), r.nextInt(110), r.nextInt(2, 20), r.nextInt(21, 150));
        }
        player = new CPlayer((byte) r.nextInt(1, 30), (byte) r.nextInt(1, 30), r.nextInt(110), r.nextInt(50, 100), r.nextInt(101, 500));
        autoFight();
        arrOfMonst = new CMonster[0];
        player = new CPlayer();
        player.setNumOfHeal((byte)4);
    }

    private void autoFight() {
        player.getInfo();
        System.out.printf("\nНа поле битвы 1 Игрок и %d монстр(а/ов). Да начнется бой!\n", arrOfMonst.length);
        System.out.println("__________________________________________________________________");
        for (int i = 0; i < arrOfMonst.length; i++) {
            startAutoFight(i);
            if (player.getHealth() == 0) return;
        }
        System.out.println("\nИгрок победил монстров\n\n\n");
    }

    private void fight(){
        player.getInfo();
        System.out.printf("\nНа поле битвы 1 Игрок и %d монстр(а/ов). Да начнется бой!\n", arrOfMonst.length);
        System.out.println("__________________________________________________________________");
        for (int i = 0; i < arrOfMonst.length; i++) {
            startFight(i);
            if (player.getHealth() == 0) return;
        }
        System.out.println("\nИгрок победил монстров\n\n\n");
    }

    private void startAutoFight(int i) {
        byte round = 1;
        System.out.printf("Битва Игрока и %s :\n", arrOfMonst[i].getName());
        info(i);
        int damage;
        while (arrOfMonst[i].getHealth() != 0 && player.getHealth() != 0) {
            System.out.printf("| %d-й раунд битвы | \n", round);
            for (int j = 0; j < player.getAttack() - arrOfMonst[i].getDefence() + 1 && arrOfMonst[i].getHealth() != 0 || j < 1; j++) {
                damage = player.attack(arrOfMonst[i]);
                System.out.printf(damage == 0 ? "\tИгрок не нанес урон %s \n" : "\tИгрок успешно атаковал %s и нанес %d урона\n", arrOfMonst[i].getName(), damage);
            }
            if (arrOfMonst[i].getHealth() != 0) {
                for (int j = 0; j < arrOfMonst[i].getAttack() - player.getDefence() + 1 && player.getHealth() != 0 || j < 1; j++) {
                    damage = arrOfMonst[i].attack(player);
                    System.out.printf(damage == 0 ? "\t%s не нанес урон Игроку \n" : "\t%s успешно атаковал Игрока и нанес урон %d\n", arrOfMonst[i].getName(), damage);
                }
            }
            System.out.printf("Здоровье игрока -> %d, Здоровье монстра -> %d\n", player.getHealth(), arrOfMonst[i].getHealth());
            if (player.getHealthMax() - player.getHealth() >= player.getHealthMax() / 2) {
                player.heal();
            }
            round++;
        }
        System.out.println("__________________________________________________________________");
        System.out.printf(arrOfMonst[i].getHealth() == 0 ? "Игрок сразил %s\n\n" : "Игрок пал в бою с %s\n\n\n", arrOfMonst[i].getName());
        if (player.getHealthMax() - player.getHealth() >= player.getHealth() / 2) {
            player.heal();
        }
    }

    private void startFight(int i){
        byte round = 1;
        System.out.printf("Битва Игрока и %s :\n", arrOfMonst[i].getName());
        info(i);
        int damage;
        while (arrOfMonst[i].getHealth() != 0 && player.getHealth() != 0) {
            System.out.printf("| %d-й раунд битвы | \n", round);
            for (int j = 0; j < player.getAttack() - arrOfMonst[i].getDefence() + 1 && arrOfMonst[i].getHealth() != 0 || j < 1; j++) {
                damage = player.attack(arrOfMonst[i]);
                System.out.printf(damage == 0 ? "\tИгрок не нанес урон %s \n" : "\tИгрок успешно атаковал %s и нанес %d урона\n", arrOfMonst[i].getName(), damage);
            }
            if (arrOfMonst[i].getHealth() != 0) {
                for (int j = 0; j < arrOfMonst[i].getAttack() - player.getDefence() + 1 && player.getHealth() != 0 || j < 1; j++) {
                    damage = arrOfMonst[i].attack(player);
                    System.out.printf(damage == 0 ? "\t%s не нанес урон Игроку \n" : "\t%s успешно атаковал Игрока и нанес урон %d\n", arrOfMonst[i].getName(), damage);
                }
            }
            System.out.printf("Здоровье игрока -> %d, Здоровье монстра -> %d\n", player.getHealth(), arrOfMonst[i].getHealth());
            if(player.getHealth() != 0 && player.getNumOfHeal() != 0) {
                System.out.println("Выберете дальнйшее действие : 1 - продолжить бой, 0 - исцелить героя");
                Scanner in = new Scanner(System.in);
                byte choice = -1;
                while (choice == -1) {
                    try {
                        choice = Byte.parseByte(in.nextLine());
                        if (choice != 1 && choice != 0)
                            System.out.println("Введенное число не равно 0 или 1 - введите значение еще раз");
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка. Введите целочисленное значение: ");
                    }
                }
                if (choice == 0) player.heal();
            }
            round++;
        }
        System.out.println("__________________________________________________________________");
        System.out.printf(arrOfMonst[i].getHealth() == 0 ? "Игрок сразил %s\n\n" : "Игрок пал в бою с %s\n\n\n", arrOfMonst[i].getName());

    }


    public CPlayer getPlayer(){
        return player;
    }

    public void info(int i) {
        System.out.printf("Показатели Игрока :\tЗдоровье -> %d; Урон -> [%d;%d]; Атака -> %d; Защита -> %d; Осталось исцелений -> %d\n", player.getHealth(), player.getDamage()[0], player.getDamage()[1], player.getAttack(), player.getDefence(), player.getNumOfHeal());
        System.out.printf("Показатели %s :\tЗдоровье -> %d; Урон -> [%d;%d]; Атака -> %d; Защита -> %d\n", arrOfMonst[i].getName(), arrOfMonst[i].getHealth(), arrOfMonst[i].getDamage()[0], arrOfMonst[i].getDamage()[1], arrOfMonst[i].getAttack(), arrOfMonst[i].getDefence());
        System.out.printf(player.getAttack() - arrOfMonst[i].getDefence() + 1 > 0 ? "Модификатор атаки Игрока -> " + (player.getAttack() - arrOfMonst[i].getDefence() + 1) + ";\t" : "Модификатор атаки Игрока -> 1\t");
        System.out.printf(arrOfMonst[i].getAttack() - player.getDefence() + 1 > 0 ? "модификатор атаки Монстра -> " + (arrOfMonst[i].getAttack() - player.getDefence() + 1) + '\n' : "модификатор атаки Монстра -> 1\n");
    }
}
