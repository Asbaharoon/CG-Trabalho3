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

	/**
	 * Retorna a instância do Mundo.
	 * @return a instância do Mundo.
	 */
	public static Mundo getInstance() {
		if (instance == null) {
			instance = new Mundo();
		}
		
		return instance;
	}
	
	/**
	 * Inicia o Mundo.
	 * @param gl objeto do tipo GL.
	 * @param glu objeto do tipo GLU.
	 */
	public void initMundo(GL gl, GLU glu) {
		this.gl = gl;
		this.glu = glu;
		objetosGraficos = new LinkedList<>();
		camera = new Camera(glu);
	}
	
	/**
	 * Lista os objetos gráficos.
	 */
	public void listarObjetosGraficos() {
		for (ObjetoGrafico objetoGrafico : objetosGraficos) {
			objetoGrafico.desenhar(gl, poligonoSelecionado);
		}
	}

	/**
	 * Reposiciona a câmera.
	 */
	public void atualizaOrtho() {
		camera.atualizaOrtho();
	}

	/**
	 * Altera o poligono selecionado no Mundo.
	 * @param objetoGrafico poligono selecionado.
	 */
	public void atualizarPoligonoSelecionado(ObjetoGrafico objetoGrafico) {
		this.poligonoSelecionado = objetoGrafico;
	}

	/**
	 * Retorna o poligono selecionado.
	 * @return o poligono selecionado.
	 */
	public ObjetoGrafico getPoligonoSelecionado() {
		return poligonoSelecionado;
	}

	/**
	 * Adiciona novo poligono no Mundo.
	 * @param objetoGrafico novo poligono.
	 */
	public void adicionarObjetoGrafico(ObjetoGrafico objetoGrafico) {
		this.objetosGraficos.add(objetoGrafico);
	}

	/**
	 * Retorna o poligono clicado.<br>
	 * Se o clique não atingir nenhum poligono, retorna null.
	 * @param x posição x do mouse no Mundo.
	 * @param y posição y do mouse no Mundo.
	 * @return o poligono clicado.
	 */
	public ObjetoGrafico getObjetoClicado(int x, int y) {
		for (ObjetoGrafico objetoGrafico : objetosGraficos) {
			
			ObjetoGrafico selecionado = objetoGrafico.isSelecionado(x, y);
			if (selecionado != null) {
				return selecionado;
			}
			
		}
		return null;
	}

	/**
	 * Informa que o programa entrou em modo desenho.
	 */
	public void marcarDesenhando() {
		this.isDesenhando = true;
	}

	/**
	 * Informa que o programa saiu do modo desenho.
	 */
	public void desmarcarDesenhando() {
		this.isDesenhando = false;
	}
	
	/**
	 * Verifica se o usuário está desenhando.
	 * @return se o programa está em modo desenho.
	 */
	public boolean isDesenhando() {
		return isDesenhando;
	}
        
    /**
     * Informa o vértice que foi selecionado.
     * @param ponto vértice selecionado.
     */
    public void marcarPontoSelecionado(Point4D ponto) {
        pontoMaisProximo = ponto;
		this.isPontoSelecionado = true;
	}

	/**
	 * Informa que não há nenhum vértice selecionado.
	 */
	public void desmarcarPontoSelecionado() {
		pontoMaisProximo = null;
		this.isPontoSelecionado = false;
	}
	
	/**
	 * Verifica se há algum vértice selecionado no Mundo.
	 * @return se existe algum vértice selecionado.
	 */
	public boolean isPontoSelecionado() {
		return isPontoSelecionado;
	}
	
    /**
     * Retorna todos os poligonos do Mundo.
     * @return todos os poligonos do Mundo.
     */
    public List<ObjetoGrafico> getObjetosGraficos() {
        return objetosGraficos;
    }

    /**
     * Seta os novos poligonos do Mundo.
     * @param objetosGraficos novos poligonos do Mundo.
     */
    public void setObjetosGraficos(List<ObjetoGrafico> objetosGraficos) {
        this.objetosGraficos = objetosGraficos;
    } 
    
    /**
     * Retorna o vértice mais próximo.
     * @return o vértice mais próximo.
     */
    public Point4D getPontoMaisProximo() {
        return pontoMaisProximo;
    }
    
    /**
     * Calcula e retorna a distância entre dois vértices.
     * @param x1 valor x do primeiro vértice.
     * @param x2 valor x do segundo vértice.
     * @param y1 valor y do primeiro vértice.
     * @param y2 valor y do segundo vértice.
     * @return a distância entre dois vértices.
     */
    public double calcularDistanciaEntrePontos(double x1, double x2, double y1, double y2) {
        return Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2);
    }
    
}
