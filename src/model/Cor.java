package model;

import java.util.Random;

public class Cor {

	private float red = 0.0f;
	private float green = 0.0f;
	private float blue = 0.0f;

	public Cor() {
		super();
	}
	
	public static final Cor BRANCO = new Cor(1.0f, 1.0f, 1.0f);
	public static final Cor VERMELHO = new Cor(1.0f, 0.0f, 0.0f);

	public Cor(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Retorna a tonalidade de vermelho na Cor.
	 * @return a tonalidade de vermelho.
	 */
	public float getRed() {
		return red;
	}

	/**
	 * Seta a tonalidade de vermelho na Cor.
	 * @param red tonalidade de vermelho.
	 */
	public void setRed(float red) {
		this.red = red;
	}

	/**
	 * Retorna a tonalidade de verde na Cor.
	 * @return a tonalidade de verde.
	 */
	public float getGreen() {
		return green;
	}

	/**
	 * Seta a tonalidade de verde na Cor.
	 * @param green tonalidade de verde.
	 */
	public void setGreen(float green) {
		this.green = green;
	}

	/**
	 * Retorna a tonalidade de azul na Cor.
	 * @return a tonalidade de azul.
	 */
	public float getBlue() {
		return blue;
	}

	/**
	 * Seta a tonalidade de azul na Cor.
	 * @param blue tonalidade de azul.
	 */
	public void setBlue(float blue) {
		this.blue = blue;
	}

	/**
	 * Seta tonalidades aleatórias de vermelho, verde e azul na Cor.
	 */
	public void atualizarCorAleatoria() {
		do {
			red = (new Random().nextInt() * 10) * 0.1f;
			green = (new Random().nextInt() * 10) * 0.1f;
			blue = (new Random().nextInt() * 10) * 0.1f;
		} while (red == 1.0f && green == 1.0f && blue == 1.0f);
	}

	/**
	 * Aumenta a tonalidade de vermelho na Cor.
	 */
	public void aumentarQtdVermelho() {
		if (red < 1) {
			red += 0.1f;
		}
	}

	/**
	 * Diminui a tonalidade de vermelho na Cor.
	 */
	public void diminuirQtdVermelho() {
		if (red > 0) {
			red -= 0.1f;
		}
	}

	/**
	 * Aumenta a tonalidade de verde na Cor.
	 */
	public void aumentarQtdVerde() {
		if (green < 1) {
			green += 0.1f;
		}
	}

	/**
	 * Diminui a tonalidade de vermelho na Cor.
	 */
	public void diminuirQtdVerde() {
		if (green > 0) {
			green -= 0.1f;
		}
	}

	/**
	 * Aumenta a tonalidade de azul na Cor.
	 */
	public void aumentarQtdAzul() {
		if (blue < 1) {
			blue += 0.1f;
		}
	}

	/**
	 * Diminui a tonalidade de azul na Cor.
	 */
	public void diminuirQtdAzul() {
		if (blue > 0) {
			blue -= 0.1f;
		}
	}

}
