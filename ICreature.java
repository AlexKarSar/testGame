package testGame;

public interface ICreature {

    //Одно Существо может ударить другое
    int attack(CCreature attacked);

    //В игре есть Существа. К ним относятся Игрок и Монстры
    interface IPlayer extends ICreature {

        //Игрок может себя исцелить
        void heal();
    }
}
