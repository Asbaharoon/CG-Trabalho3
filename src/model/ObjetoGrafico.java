package model;

import java.util.LinkedList;

import javax.media.opengl.GL;

public class ObjetoGrafico {

	private LinkedList<Point4D> pontos = new LinkedList<>();
	private int primitiva = GL.GL_LINE_STRIP;
	private Cor cor = new Cor();
	private BoundingBox boundingBox;

	public LinkedList<Point4D> getPontos() {
		return pontos;
	}
	
	public void adicionarPonto(Point4D point4d) {
		if (this.pontos.isEmpty()) {
			boundingBox = new BoundingBox(point4d.GetX(), point4d.GetY(), 0, 
					point4d.GetX(), point4d.GetY(), 0);
		}
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
		
		if (this.primitiva == GL.GL_LINE_LOOP) {
			desenharBoundingBox(gl);
		}
		
	}
	
	public void desenharBoundingBox(GL gl) {
		for (Point4D point4d : pontos) {
			boundingBox.atualizarBBox(point4d);
		}
		boundingBox.desenharOpenGLBBox(gl);
	}
	
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}


	public boolean isSelecionado(int xClique, int yClique) {
		if (boundingBox.isPonto2DDentro(xClique ,yClique)) {

			int paridade = 0;
			for (int i = 0; i < pontos.size(); i++) {
				if (i + 1 < pontos.size()) {
					Point4D ponto1 = pontos.get(i);
					Point4D ponto2 = pontos.get(i+1);
					double ti = (yClique - ponto1.GetY()) / (ponto2.GetY() - ponto1.GetY());
					if (ti >=0 && ti <= 1) {
						double x = ponto1.GetX() + (ponto2.GetX() - ponto1.GetX()) * ti;  
						if (x > xClique) {
							paridade++;
						}
					}
				}
			}
			
			return paridade % 2 == 1;
			
		}
		return false;
	}

}
