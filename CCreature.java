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
