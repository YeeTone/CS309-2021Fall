import java.awt.*;
import java.awt.event.KeyEvent;

public class BlueBall extends Ball{
    public BlueBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void intersectChange(Ball whiteBall) {
        super.intersectChange(whiteBall);
        if(isIntersect(whiteBall)){
            setXSpeed(getXSpeed()*-1);
            setYSpeed(getYSpeed()*-1);
        }

    }

    @Override
    public void keyPressNotified(char ke, MainPanel.GameStatus gs) {
        setXSpeed(-1 * getXSpeed());
        setYSpeed(-1 * getYSpeed());
    }
}
