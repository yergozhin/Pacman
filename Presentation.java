public class Presentation {
    public static void main(String[] args) {
        //===When running the code, first you will see the window with three buttons: "New game",
        //"High scores" and "Exit"

        //(1)After Selecting the "New game" button you will first see the window with two fields
        //for columns and rows, in which you need to type numbers from 10 until 100,
        // to select the size of the board

        //---After selecting the size of the board and clicking the "ok" button, you will be moved
        //to the game which is described below:

        //---Basically this game has two type of characters: ghost and pacman.
        //(There can be only four ghosts and one pacman)

        //---The plot of this game is that our pacman character is trying to collect as many
        //pellets as it can, while running from ghosts that are trying to protect those pellets

        //---In the game you also have 3 lives of a pacman character and a score provided in the
        //downside of the window, so that you can see how much points you got from collecting or
        //eating those pellets (each pellet gives you 10 points)

        //---In the begging of the game or after losing one of your additional lives, you are
        //going to start your journey back in the first empty space chosen from up-left to
        //down-right corner, considering that if you lose one of your lives you are going to
        //continue with the same board as you had before,and you will be going back to the same
        //first empty cell, taking into account also that you will continue with the same
        //amount of pellets left in their own place as in the time when you lost one of your lives

        //---As you understood you will have some free cells and not free cells which are going to
        //be walls, which will be generated randomly each time you will start a new game, that
        //makes the game more interesting and exciting

        //---As an important noticement, movement of your pacman character can be possible only
        //by clicking arrows on your keyboard

        //---Furthermore after eating all the pellets on the board, you will continue playing in
        //the same board with a full amount of pellets and ghosts, which will return to their
        //place

        //---You will also be able to stop the game at any time when you click the "shift",
        //"control", "Q" buttons at the same time

        //---After losing the game or stopping the game you will be asked to write your
        //nickname, so that you can be put to a high scores board, after which you will be
        //returned to main menu where you can play a new game, see high scores board, or
        //quit from the app

        //(2)If you click the "High Scores" button, then you will see a list of nicknames that
        //the players selected and their scores sorted in descending order

        //(3)Finally if you click the "Exit" button, as you might understand it will just
        //close the app and the window itself
    }
}
