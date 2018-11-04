package model;

import javax.media.opengl.GL;

public final class BoundingBox {
	private double menorX;
	private double menorY;
	private double menorZ;
	private double maiorX;
	private double maiorY;
	private double maiorZ;
	private Point4D centro = new Point4D();
    private Cor color = Cor.VERMELHO;


	public BoundingBox() {
		this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}
	
	public BoundingBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY, double greaterZ) {
		this.menorX = smallerX;
		this.menorY = smallerY;
		this.menorZ = smallerZ;
		this.maiorX = greaterX;
		this.maiorY = greaterY;
		this.maiorZ = greaterZ;
	}
	
	/**
	 * Atribuir a posição da BBox.
	 * @param smallerX menor posição de x.
	 * @param smallerY menor posição de y.
	 * @param smallerZ menor posição de z.
	 * @param greaterX maior posição de x.
	 * @param greaterY maior posição de y.
	 * @param greaterZ maior posição de z.
	 */
	public void atribuirBoundingBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY, double greaterZ) {
		this.menorX = smallerX;
		this.menorY = smallerY;
		this.menorZ = smallerZ;
		this.maiorX = greaterX;
		this.maiorY = greaterY;
		this.maiorZ = greaterZ;
		processarCentroBBox();
	}
		
	/**
	 * Atualiza a posição da BBox.
	 * @param point nova posição da BBox.
	 */
	public void atualizarBBox(Point4D point) {
	    atualizarBBox(point.GetX(), point.GetY(), point.GetZ());
	}

	/**
	 * Atualiza a posição da BBox.
	 * @param x posição x da BBox.
	 * @param y posição y da BBox.
	 * @param z posição z da BBox.
	 */
	public void atualizarBBox(double x, double y, double z) {
	    if (x < menorX)
	        menorX = x;
	    else {
	        if (x > maiorX) maiorX = x;
	    }
	    if (y < menorY)
	        menorY = y;
	    else {
	        if (y > maiorY) maiorY = y;
	    }
	    if (z < menorZ)
	        menorZ = z;
	    else {
	        if (z > maiorZ) maiorZ = z;
	    }
	}
	
	/**
	 * Calcula o ponto do centro da BBox.
	 */
	public void processarCentroBBox() {
	    centro.SetX((maiorX + menorX)/2);
	    centro.SetY((maiorY + menorY)/2);
	    centro.SetZ((maiorZ + menorZ)/2);
	}

	/**
	 * Desenha a BBox.
	 * @param gl objeto do tipo GL.
	 */
	public void desenharOpenGLBBox(GL gl) {
		gl.glColor3f(color.getRed(), color.getBlue(), color.getGreen());

		gl.glBegin (GL.GL_LINE_LOOP);
			gl.glVertex3d (menorX, maiorY, menorZ);
			gl.glVertex3d (maiorX, maiorY, menorZ);
			gl.glVertex3d (maiorX, menorY, menorZ);
			gl.glVertex3d (menorX, menorY, menorZ);
	    gl.glEnd();
	    gl.glBegin(GL.GL_LINE_LOOP);
	    	gl.glVertex3d (menorX, menorY, menorZ);
	    	gl.glVertex3d (menorX, menorY, maiorZ);
	    	gl.glVertex3d (menorX, maiorY, maiorZ);
	    	gl.glVertex3d (menorX, maiorY, menorZ);
	    gl.glEnd();
	    gl.glBegin(GL.GL_LINE_LOOP);
	    	gl.glVertex3d (maiorX, maiorY, maiorZ);
	    	gl.glVertex3d (menorX, maiorY, maiorZ);
	    	gl.glVertex3d (menorX, menorY, maiorZ);
	    	gl.glVertex3d (maiorX, menorY, maiorZ);
	    gl.glEnd();
	    gl.glBegin(GL.GL_LINE_LOOP);
	    	gl.glVertex3d (maiorX, menorY, menorZ);
	    	gl.glVertex3d (maiorX, maiorY, menorZ);
	    	gl.glVertex3d (maiorX, maiorY, maiorZ);
	    	gl.glVertex3d (maiorX, menorY, maiorZ);
    	gl.glEnd();
	}

	/**
	 * Obter menor valor X da BBox.
	 * @return menor valor X da BBox.
	 */
	public double obterMenorX() {
		return menorX;
	}

	/**
	 * Obter menor valor Y da BBox.
	 * @return menor valor Y da BBox.
	 */
	public double obterMenorY() {
		return menorY;
	}

	/**
	 * Obter menor valor Z da BBox.
	 * @return menor valor Z da BBox.
	 */
	public double obterMenorZ() {
		return menorZ;
	}

	/**
	 * Obter maior valor X da BBox.
	 * @return maior valor X da BBox.
	 */
	public double obterMaiorX() {
		return maiorX;
	}

	/**
	 * Obter maior valor Y da BBox.
	 * @return maior valor Y da BBox.
	 */
	public double obterMaiorY() {
		return maiorY;
	}

	/**
	 * Obter maior valor Z da BBox.
	 * @return maior valor Z da BBox.
	 */
	public double obterMaiorZ() {
		return maiorZ;
	}
	
	/**
	 * Obter ponto do centro da BBox.
	 * @return ponto do centro da BBox.
	 */
	public Point4D obterCentro() {
		return centro;
	}
	
	/**
	 * Verifica se vértice está dentro da BBox.
	 * @param ponto posição do vértice.
	 * @return se vértice está dentro da BBox.
	 */
	public boolean isPonto2DDentro(Point4D ponto) {
		return obterMenorX() <= ponto.GetX() && //
			   obterMaiorX() >= ponto.GetX() && //
			   obterMenorY() <= ponto.GetY() && //
			   obterMaiorY() >= ponto.GetY();
	}

	/**
	 * Verifica se vértice está dentro da BBox.
	 * @param x posição x do vértice.
	 * @param y posição y do vértice.
	 * @return se vértice está dentro da BBox.
	 */
	public boolean isPonto2DDentro(double x, double y) {
		return isPonto2DDentro(new Point4D(x, y, 0.0, 1.0));
	}

}

