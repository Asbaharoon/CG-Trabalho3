package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import model.Mundo;
import model.ObjetoGrafico;
import model.Point4D;

public class Main implements GLEventListener, MouseMotionListener, MouseListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		Mundo.getInstance().initMundo(gl, glu);
	}

	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		Mundo.getInstance().atualizaOrtho();

		SRU();
		gl.glLineWidth(3.0f);

		Mundo.getInstance().listarObjetosGraficos();

		gl.glFlush();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
	}

	public void SRU() {
		// eixo x
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glLineWidth(1.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(-200.0f, 0.0f);
		gl.glVertex2f(200.0f, 0.0f);
		gl.glEnd();
		// eixo y
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(0.0f, -200.0f);
		gl.glVertex2f(0.0f, 200.0f);
		gl.glEnd();
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		ObjetoGrafico objetoGrafico = Mundo.getInstance().getObjetoClicado(x, y);
		if (objetoGrafico != null) {
			Mundo.getInstance().atualizarPoligonoSelecionado(objetoGrafico);
		} else {
			// ta desenhando
			if (!Mundo.getInstance().isDesenhando()) {
				Mundo.getInstance().marcarDesenhando();
				objetoGrafico = new ObjetoGrafico();
				objetoGrafico.adicionarPonto(new Point4D(x, y));
				objetoGrafico.adicionarPonto(new Point4D(x, y));
				Mundo.getInstance().adicionarObjetoGrafico(objetoGrafico);
			} else {
				objetoGrafico = Mundo.getInstance().getPoligonoSelecionado();
				objetoGrafico.adicionarPonto(new Point4D(x, y));
			}
			Mundo.getInstance().atualizarPoligonoSelecionado(objetoGrafico);
		}

		glDrawable.display();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Mundo.getInstance().isDesenhando()) {
			Point4D last = Mundo.getInstance().getPoligonoSelecionado().getPontos().getLast();
			last.SetX(e.getX());
			last.SetY(e.getY());

			glDrawable.display();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ObjetoGrafico poligonoSelecionado = Mundo.getInstance().getPoligonoSelecionado();
		if (poligonoSelecionado != null) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				poligonoSelecionado.setPrimitiva(GL.GL_LINE_LOOP);
				Mundo.getInstance().desmarcarDesenhando();
				Mundo.getInstance().atualizarPoligonoSelecionado(null);
				break;
			case KeyEvent.VK_C:
				poligonoSelecionado.getCor().atualizarCorAleatoria();
				break;
			case KeyEvent.VK_NUMPAD1:
				poligonoSelecionado.getCor().aumentarQtdVermelho();
				break;
			case KeyEvent.VK_NUMPAD2:
				poligonoSelecionado.getCor().diminuirQtdVermelho();
				break;
			case KeyEvent.VK_NUMPAD3:
				poligonoSelecionado.getCor().aumentarQtdVerde();
				break;
			case KeyEvent.VK_NUMPAD4:
				poligonoSelecionado.getCor().diminuirQtdVerde();
				break;
			case KeyEvent.VK_NUMPAD5:
				poligonoSelecionado.getCor().aumentarQtdAzul();
				break;
			case KeyEvent.VK_NUMPAD6:
				poligonoSelecionado.getCor().diminuirQtdAzul();
				break;
				
			case KeyEvent.VK_UP:
				poligonoSelecionado.transladar(0, -5);
				break;
			case KeyEvent.VK_DOWN:
				poligonoSelecionado.transladar(0, 5);
				break;
			case KeyEvent.VK_RIGHT:
				poligonoSelecionado.transladar(5, 0);
				break;
			case KeyEvent.VK_LEFT:
				poligonoSelecionado.transladar(-5, 0);
				break;

			case KeyEvent.VK_PAGE_UP:
				poligonoSelecionado.escalar(1.5);
				break;
			case KeyEvent.VK_PAGE_DOWN:
				poligonoSelecionado.escalar(0.75);
				break;

			case KeyEvent.VK_R:
				poligonoSelecionado.rotacionar(5);
				break;
			default:
				break;
			}
			glDrawable.display();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
