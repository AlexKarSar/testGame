package testGame;

import java.util.Random;

//В игре есть Существа. К ним относятся Игрок и Монстры.
public abstract class CCreature implements ICreature {

    //У Существа есть параметры Атака и Защита. Это целые числа от 1 до 30
    protected byte attack, defence;

    //У Существа есть параметр Урон и Здоровье
    protected int health, damageM, damageN;

    CCreature() {
    }

    CCreature(byte attack, byte defence, int health, int damageM, int damageN) {
        if (attack < 1 || attack > 30) {
            System.out.print("Параметр 'Атака' должен быть в отрезке [1; 30]");
            return;
        }
        if (defence < 1 || defence > 30) {
            System.out.print("Параметр 'Защита' должен быть в отрезке [1; 30]");
            return;
        }
        if (health < 0) {//Это натуральное число от 0 до N
            System.out.print("Параметр 'Здоровье' должен быть не меньше 0");
            return;
        }
        if (damageM < 1 || damageM > damageN) {//параметр Урон. Это диапазон натуральных чисел M - N
            System.out.print("Неправильый ввод параметра 'Урон'. Он задается диапазоном натуральных чисел от M до N, где N>=M");
            return;
        }
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.damageN = damageN;
        this.damageM = damageM;
    }

    public int attack(CCreature attacked) {
        Random r = new Random();
        int damage = r.nextInt(damageM, damageN);
        if (r.nextInt(1, 6) >= 5) {
            if (attacked.health <= damage) {
                attacked.health = 0;
            } else attacked.health -= damage;
        } else return 0;
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public byte getAttack() {
        return attack;
    }

    public byte getDefence() {
        return defence;
    }

    public int[] getDamage() {
        return new int[]{damageM, damageN};
    }
}
