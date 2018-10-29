package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.media.opengl.GL;

public class ObjetoGrafico {

	private LinkedList<Point4D> pontos = new LinkedList<>();
	private int primitiva = GL.GL_LINE_STRIP;
	private Cor cor = new Cor();
	private BoundingBox boundingBox;

	private Transformacao4D transformacao4D = new Transformacao4D();
	private Transformacao4D matrizTmpTranslacao = new Transformacao4D();
	private Transformacao4D matrizTmpEscala = new Transformacao4D();
	private Transformacao4D matrizTmpTranslacaoInversa = new Transformacao4D();
	private Transformacao4D matrizGlobal = new Transformacao4D();
	
	private List<ObjetoGrafico> objetosFilhos = new ArrayList<>();

	public LinkedList<Point4D> getPontos() {
		return pontos;
	}

	public void adicionarPonto(Point4D point4d) {
		if (this.pontos.isEmpty()) {
			boundingBox = new BoundingBox(point4d.GetX(), point4d.GetY(), 0, point4d.GetX(), point4d.GetY(), 0);
		} else {
			boundingBox.atualizarBBox(point4d);
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

	public void desenhar(GL gl, ObjetoGrafico selecionado) {
		gl.glColor3f(getCor().getRed(), getCor().getGreen(), getCor().getBlue());

		gl.glPushMatrix();
			gl.glMultMatrixd(transformacao4D.GetDate(), 0);

			gl.glBegin(primitiva);
			for (Point4D point4d : pontos) {
				gl.glVertex2d(point4d.GetX(), point4d.GetY());
			}
	
			gl.glEnd();

			if (selecionado == this) {
				this.boundingBox.desenharOpenGLBBox(gl);
			}
		
			objetosFilhos.forEach(f -> f.desenhar(gl, selecionado));
		gl.glPopMatrix();
		
	}

	public void desenharBoundingBox(GL gl) {
		boundingBox.desenharOpenGLBBox(gl);
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	
	public List<ObjetoGrafico> getObjetosFilhos() {
		return objetosFilhos;
	}
	
	public ObjetoGrafico isSelecionado(int xClique, int yClique) {
		if (boundingBox.isPonto2DDentro(xClique, yClique)) {

			int paridade = 0;
			for (int i = 0; i < pontos.size(); i++) {
				if (i + 1 < pontos.size()) {
					if (isPontoIntersecciona(xClique, yClique, pontos.get(i), pontos.get(i + 1))) {
						paridade++;
					}
				}
			}

			if (isPontoIntersecciona(xClique, yClique, pontos.getLast(), pontos.getFirst())) {
				paridade++;
			}

			if (paridade % 2 == 1) return this;

		}
		
		Optional<ObjetoGrafico> encontrado = objetosFilhos.stream().filter(f -> f.isSelecionado(xClique, yClique) != null).findFirst();
		return encontrado.isPresent() ? encontrado.get() : null;
	}

	private boolean isPontoIntersecciona(int xClique, int yClique, Point4D ponto1, Point4D ponto2) {
		double ti = (yClique - ponto1.GetY()) / (ponto2.GetY() - ponto1.GetY());
		if (ti >= 0 && ti <= 1) {
			double x = ponto1.GetX() + (ponto2.GetX() - ponto1.GetX()) * ti;
			return x > xClique;
		}
		return false;
	}

	public void transladar(double x, double y) {
		Transformacao4D matrizTranslate = new Transformacao4D();
		matrizTranslate.atribuirTranslacao(x, y, 0.0d);
		transformacao4D = matrizTranslate.transformMatrix(transformacao4D);
	}

	public void escalar(double escala) {
		Point4D ptoFixo = getCentro();
		matrizGlobal.atribuirIdentidade();

		matrizTmpTranslacao.atribuirTranslacao(ptoFixo.GetX(), ptoFixo.GetY(), ptoFixo.GetZ());
		matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

		matrizTmpEscala.atribuirEscala(escala, escala, 1.0);
		matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

		ptoFixo.inverterSinal(ptoFixo);
		matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.GetX(), ptoFixo.GetY(), ptoFixo.GetZ());
		matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

		transformacao4D = transformacao4D.transformMatrix(matrizGlobal);
	}

	public void rotacionar(double angulo) {
		Point4D ptoFixo = getCentro();
		matrizGlobal.atribuirIdentidade();

		matrizTmpTranslacao.atribuirTranslacao(ptoFixo.GetX(), ptoFixo.GetY(), ptoFixo.GetZ());
		matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

		matrizTmpEscala.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
		matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

		ptoFixo.inverterSinal(ptoFixo);
		matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.GetX(), ptoFixo.GetY(), ptoFixo.GetZ());
		matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

		transformacao4D = transformacao4D.transformMatrix(matrizGlobal);
	}

	public Point4D getCentro() {
		double maiorX = pontos.stream().mapToDouble(Point4D::GetX).max().getAsDouble();
		double menorX = pontos.stream().mapToDouble(Point4D::GetX).min().getAsDouble();
		double maiorY = pontos.stream().mapToDouble(Point4D::GetY).max().getAsDouble();
		double menorY = pontos.stream().mapToDouble(Point4D::GetY).min().getAsDouble();
		return new Point4D(((maiorX + menorX) / 2) * -1, ((maiorY + menorY) / 2) * -1);
	}
	
}
