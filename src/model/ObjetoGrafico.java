package model;

import java.util.LinkedList;

import javax.media.opengl.GL;

import aplicativo_grafico.Point4D;

public class ObjetoGrafico {

	private LinkedList<Point4D> pontos = new LinkedList<>();
	private int primitiva = GL.GL_LINE_LOOP;
	private Cor cor;

	public LinkedList<Point4D> getPontos() {
		return pontos;
	}
	
	public void adicionarPonto(Point4D point4d) {
		pontos.add(point4d);
	}

	public void removerPonto(Point4D point4d) {
		pontos.remove(point4d);
	}

	public void setPontos(LinkedList<Point4D> pontos) {
		this.pontos = pontos;
	}

	public int getPrimitiva() {
		return primitiva;
	}

	public void setPrimitiva(int primitiva) {
		this.primitiva = primitiva;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public void desenhar(GL gl) {
		gl.glColor3f(getCor().getRed(), getCor().getGreen(), getCor().getBlue());
		gl.glBegin(primitiva);
		for (Point4D point4d : pontos) {
			gl.glVertex2d(point4d.GetX(), point4d.GetY());
		}
		gl.glEnd();
	}

}
