package model;

import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Mundo {

	private List<ObjetoGrafico> objetosGraficos;
        private Point4D pontoMaisProximo;

    
    
	private Camera camera;
	private ObjetoGrafico poligonoSelecionado;
	
	private static Mundo instance;
	
	private GL gl;
	private GLU glu;
	
	private boolean isDesenhando = false;
        private boolean isPontoSelecionado = false;

	public static Mundo getInstance() {
		if (instance == null) {
			instance = new Mundo();
		}
		
		return instance;
	}
	
	public void initMundo(GL gl, GLU glu) {
		this.gl = gl;
		this.glu = glu;
		objetosGraficos = new LinkedList<>();
		camera = new Camera(glu);
	}
	
	public void listarObjetosGraficos() {
		for (ObjetoGrafico objetoGrafico : objetosGraficos) {
			objetoGrafico.desenhar(gl, poligonoSelecionado);
		}
	}

	public void atualizaOrtho() {
		camera.atualizaOrtho();
	}

	public void atualizarPoligonoSelecionado(ObjetoGrafico objetoGrafico) {
		this.poligonoSelecionado = objetoGrafico;
	}

	public ObjetoGrafico getPoligonoSelecionado() {
		return poligonoSelecionado;
	}

	public void adicionarObjetoGrafico(ObjetoGrafico objetoGrafico) {
		this.objetosGraficos.add(objetoGrafico);
	}

	public ObjetoGrafico getObjetoClicado(int x, int y) {
		for (ObjetoGrafico objetoGrafico : objetosGraficos) {
			
			ObjetoGrafico selecionado = objetoGrafico.isSelecionado(x, y);
			if (selecionado != null) {
				return selecionado;
			}
			
		}
		return null;
	}

	public void marcarDesenhando() {
		this.isDesenhando = true;
	}

	public void desmarcarDesenhando() {
		this.isDesenhando = false;
	}
	
	public boolean isDesenhando() {
		return isDesenhando;
	}
        
        public void marcarPontoSelecionado(Point4D ponto) {
                pontoMaisProximo = ponto;
		this.isPontoSelecionado = true;
	}

	public void desmarcarPontoSelecionado() {
		this.isPontoSelecionado = false;
	}
	
	public boolean isPontoSelecionado() {
		return isPontoSelecionado;
	}
	
        public List<ObjetoGrafico> getObjetosGraficos() {
                return objetosGraficos;
        }

        public void setObjetosGraficos(List<ObjetoGrafico> objetosGraficos) {
                this.objetosGraficos = objetosGraficos;
        } 
        
        public Point4D getPontoMaisProximo() {
            return pontoMaisProximo;
        }
        
        public double calcularDistanciaEntrePontos(double x1, double x2, double y1, double y2) {
            return Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2);
        }
        
        public Point4D pontoDeMenorDistancia(){
            return pontoMaisProximo;
        }
        
}
