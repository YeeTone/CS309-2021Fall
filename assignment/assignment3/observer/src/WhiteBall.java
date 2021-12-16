import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class WhiteBall extends Ball implements Subject<Ball>{
    public WhiteBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void intersectChange(Ball whiteBall) {
        super.intersectChange(whiteBall);
    }

    @Override
    public void keyPressNotified(char ke, MainPanel.GameStatus gs) {

        if (gs == MainPanel.GameStatus.START) {
            switch (ke) {
                case 'a':
                    setXSpeed(-8);
                    break;
                case 'd':
                    setXSpeed(8);
                    break;
                case 'w':
                    setYSpeed(-8);
                    break;
                case 's':
                    setYSpeed(8);
                    break;
            }
        }
        notifyObservers();
    }

    private final List<Ball> observers = new ArrayList<>();
    @Override
    public void registerObserver(Ball ball) {
        observers.add(ball);
    }

    @Override
    public void removeObserver(Ball ball) {
        observers.remove(ball);
    }

    @Override
    public void notifyObservers(char keyChar) {
        for (Ball o: observers){
            o.keyPressNotified(keyChar,MainPanel.getGameStatus());
        }
    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void move() {
        super.move();
        notifyObservers();
    }
}
