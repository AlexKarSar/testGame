package testGame;

public class CMonster extends CCreature {

    private String name;

    public CMonster(byte attack, byte defence, int health, int damageN, int damageM) {//Рандомизация имени
        super(attack, defence, health, damageN, damageM);
        randomName();
    }

    public CMonster(byte attack, byte defence, int health, int damageN, int damageM, String name) {//Ввод пользователем имени
        super(attack, defence, health, damageN, damageM);
        this.name = name;
    }

    private void randomName() {
        String[] tmp = new String[2];
        if (health < 10) {
            tmp[0] = ("Tiny ");
        } else if (health < 50) {
            tmp[0] = ("Sweet ");
        } else if (health < 100) {
            tmp[0] = ("Scary ");
        } else tmp[0] = ("The chosen one ");

        if (damageM < 0) {
            tmp[1] = ("Healing Druid");
        } else if (damageM < 10) {
            tmp[1] = ("Bunny");
        } else if (damageM < 50) {
            tmp[1] = ("Hedgehog");
        } else if (damageM < 100) {
            tmp[1] = ("Bear");
        } else tmp[1] = ("Diablo");
        name = tmp[0] + tmp[1];
    }

    public String getName() {
        return name;
    }

    public void getInfo(){
        System.out.printf("Показатели %s :\tЗдоровье -> %d; Урон -> [%d;%d]; Атака -> %d; Защита -> %d\n", name, getHealth(), getDamage()[0], getDamage()[1], getAttack(), getDefence());
    }

}
