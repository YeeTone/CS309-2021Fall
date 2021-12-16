import java.awt.*;
import java.awt.event.KeyEvent;

public class RedBall extends Ball{
    public RedBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void intersectChange(Ball whiteBall) {
        super.intersectChange(whiteBall);
        if(isIntersect(whiteBall)){
            this.setXSpeed(whiteBall.getXSpeed());
            this.setYSpeed(whiteBall.getYSpeed());
        }

    }

    @Override
    public void keyPressNotified(char ke, MainPanel.GameStatus gs) {
        switch (ke) {
            case 'a':
                setXSpeed(-random.nextInt(3) - 1);
                break;
            case 'd':
                setXSpeed(random.nextInt(3) + 1);
                break;
            case 'w':
                setYSpeed(-random.nextInt(3) - 1);
                break;
            case 's':
                setYSpeed(random.nextInt(3) + 1);
                break;
        }
    }
}
