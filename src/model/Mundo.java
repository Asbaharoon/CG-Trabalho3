package model;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Mundo {

	private List<ObjetoGrafico> objetosGraficos;
	private Camera camera;
	private ObjetoGrafico poligonoSelecionado;
	
	private static Mundo instance;
	
	private GL gl;
	private GLU glu;
	
	private Mundo(GL gl, GLU glu) {
		this.gl = gl;
		this.glu = glu;
	}

	public static Mundo getInstance() {
		return instance;
	}
	
	public void initMundo(GL gl, GLU glu) {
		instance = new Mundo(gl, glu);
		objetosGraficos = new LinkedList<>();
		camera = new Camera(glu);
	}
	
	public void listarObjetosGraficos() {
		for (ObjetoGrafico objetoGrafico : objetosGraficos) {
			objetoGrafico.desenhar(gl);
		}
	}

	public void atualizaOrtho() {
		camera.atualizaOrtho();
	}

}
