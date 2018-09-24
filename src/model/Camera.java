package model;

import javax.media.opengl.glu.GLU;

public class Camera {

	private float xMin = 0.0f;
	private float xMax = 400.0f;
	private float yMin = 0.0f;
	private float yMax = 400.0f;
	
//	private float quantidadeAumentar = 10;
	
	private GLU glu;
	
	public Camera(GLU glu) {
		this.glu = glu;
		atualizaOrtho();
	}

	public void zoom() {
	
	}
	
	public void pan() {
		
	}
	public void atualizaOrtho() {
		glu.gluOrtho2D(xMin, xMax, yMin, yMax);
	}
	
//	private boolean isLimitesExtrapolados(float xMinT, float xMaxT, float yMinT, float yMaxT) {
//		return xMinT == xMaxT || yMinT == yMaxT;
//	}
//	
}
