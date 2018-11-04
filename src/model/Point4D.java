package model;

public final class Point4D {
	private double x;
	private double y;
	private double z;
	private double w;

	public Point4D() {
		this(0, 0, 0, 1);
	}
	
	public Point4D(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Point4D(double x, double y) {
		this(x, y, 0, 1);
	}

	/**
	 * Inverte o sinal (positivo/negativo) dos pontos X, Y e Z.
	 * @param pto objeto que armazena os pontos.
	 * @return o objeto com os pontos X, Y e Z invertidos.
	 */
	public Point4D inverterSinal(Point4D pto) {
		pto.SetX(pto.GetX()*-1);
		pto.SetY(pto.GetY()*-1);
		pto.SetZ(pto.GetZ()*-1);
		return pto;
	}
	
	/**
	 * Retorna o valor de x.
	 * @return o valor de x.
	 */
	public double GetX() {
		return x;
	}
	
	/**
	 * Retorna o valor de y.
	 * @return o valor de y.
	 */
	public double GetY() {
		return y;
	}
	
	/**
	 * Retorna o valor de z.
	 * @return o valor de z.
	 */
	public double GetZ() {
		return z;
	}
	
	/**
	 * Retorna o valor de w.
	 * @return o valor de w.
	 */
	public double GetW() {
		return w;
	}

	/**
	 * Seta o valor de x.
	 * @param x valor de x.
	 */
	public void SetX(double x) {
		this.x = x;
	}
	
	/**
	 * Seta o valor de y.
	 * @param y valor de y.
	 */
	public void SetY(double y) {
		this.y = y;
	}
	
	/**
	 * Seta o valor de z.
	 * @param z valor de z.
	 */
	public void SetZ(double z) {
		this.z = z;
	}

	/**
	 * Seta o valor de w.
	 * @param w valor de w.
	 */
	public void SetW(double w) {
		this.w = w;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
}
