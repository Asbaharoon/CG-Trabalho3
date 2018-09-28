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
		ObjetoGrafico objetoGrafico = Mundo.getInstance().getPoligonoSelecionado();
		if (objetoGrafico == null) {
			objetoGrafico = new ObjetoGrafico();
			objetoGrafico.adicionarPonto(new Point4D(x, y));
			objetoGrafico.adicionarPonto(new Point4D(x, y));
			Mundo.getInstance().adicionarObjetoGrafico(objetoGrafico);
		} else {
			objetoGrafico.adicionarPonto(new Point4D(x, y));
		}
		
		Mundo.getInstance().atualizarPoligonoSelecionado(objetoGrafico);
		
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
		if (Mundo.getInstance().getPoligonoSelecionado() != null) {
			Point4D last = Mundo.getInstance().getPoligonoSelecionado().getPontos().getLast();
			last.SetX(e.getX());
			last.SetY(e.getY());
			
			glDrawable.display();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			ObjetoGrafico poligonoSelecionado = Mundo.getInstance().getPoligonoSelecionado();
			poligonoSelecionado.setPrimitiva(GL.GL_LINE_LOOP);
			Mundo.getInstance().atualizarPoligonoSelecionado(null);
			glDrawable.display();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
