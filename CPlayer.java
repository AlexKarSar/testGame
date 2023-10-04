package testGame;

public class CPlayer extends CCreature implements ICreature.IPlayer {
    // 4 восстановления здоровья для всех игроков в целом
    private static byte numOfHeal = 4;
    private int healthMax;

    CPlayer(){}

    public CPlayer(byte attack, byte defence, int health, int damageN, int damageM) {
        super(attack, defence, health, damageN, damageM);
        healthMax = health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    @Override
    public void heal() {
        if(health == 0 || numOfHeal == 0) return;
        if(healthMax - health > healthMax * 0.3)health += healthMax * 0.3;
        else health = healthMax;
        System.out.printf("Игрок восстановил здоровье на %.0f, теперь оно равно %d\n", healthMax * 0.3 - 1, health);
        numOfHeal -= 1;
    }

    public void getInfo(){
        System.out.printf("Показатели Игрока :\tЗдоровье -> %d; Урон -> [%d;%d]; Атака -> %d; Защита -> %d; Осталось исцелений -> %d\n", getHealth(), getDamage()[0], getDamage()[1], getAttack(), getDefence(), numOfHeal);
    }

    public byte getNumOfHeal(){
        return numOfHeal;
    }

    public void setNumOfHeal(byte num){
        numOfHeal = num;
    }
}
