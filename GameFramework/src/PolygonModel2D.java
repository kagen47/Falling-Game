import java.awt.Graphics;
import java.awt.Graphics;

public abstract class PolygonModel2D {

	int x;
	int y;
	double xDouble;
	double yDouble;

	int angle;
	
	int[][] xCoords = getXCoords();
	int[][] yCoords = getYCoords();
	
	public PolygonModel2D(int x, int y, int angle){
		this.x = x;
		this.y = y;
		this.xDouble = x;
		this.yDouble = y;
		
		this.angle = angle;
	}
	
	public abstract int[][] getXCoords();
	public abstract int[][] getYCoords();
	
	public void draw(Graphics g){
		int[] xRotated = new int[4];
		int[] yRotated = new int[4];
		
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		for (int poly = 0; poly < xCoords.length; poly++){

			for (int i = 0; i < xRotated.length; i++){
				xRotated[i] = (int)(xCoords[poly][i] * cosA - yCoords[poly][i] * sinA);
				yRotated[i] = (int)(xCoords[poly][i] * sinA + yCoords[poly][i] * cosA);
			}
			
			// current x and y points
			int[] xCurrent = new int[4];
			int[] yCurrent = new int[4];
			
			for (int i = 0; i < xRotated.length; i++){
				xCurrent[i] = xRotated[i] + x;
				yCurrent[i] = yRotated[i] + y;
			}
			g.drawPolygon(xCurrent, yCurrent, 4);
		}
	}

	public void moveForwardBy(int dist, int angle){
		xDouble += (dist * Lookup.cos[angle]);
		yDouble += (dist * Lookup.sin[angle]);
		x = (int)xDouble;
		y = (int)yDouble;
	}
	
	public void moveForwardBy(int dist){
		moveForwardBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist, int angle){
		xDouble -= (dist * Lookup.cos[angle]);
		yDouble -= (dist * Lookup.sin[angle]);
		x = (int)xDouble;
		y = (int)yDouble;
	}
	
	public void moveBackwardBy(int dist){
		moveBackwardBy(dist, angle);
	}
	
	public void rotateRightBy(int degrees){
		this.angle += degrees;
		if (angle >= 360){
			angle -= 360;
		}
	}
	
	public void rotateLeftBy(int degrees){
		this.angle -= degrees;
		if (angle < 0){
			angle += 360;
		}
	}
}
