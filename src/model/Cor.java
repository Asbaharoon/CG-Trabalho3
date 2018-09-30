package model;

import java.util.Random;

public class Cor {

	private float red = 0.0f;
	private float green = 0.0f;
	private float blue = 0.0f;

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public void atualizarCorAleatoria() {
		red = (new Random().nextInt() * 10) * 0.1f;
		green = (new Random().nextInt() * 10) * 0.1f;
		blue = (new Random().nextInt() * 10) * 0.1f;
	}

	public void aumentarQtdVermelho() {
		if (red < 1) {
			red += 0.1f;
		}
	}

	public void diminuirQtdVermelho() {
		if (red > 0) {
			red -= 0.1f;
		}
	}

	public void aumentarQtdVerde() {
		if (green < 1) {
			green += 0.1f;
		}
	}

	public void diminuirQtdVerde() {
		if (green > 0) {
			green -= 0.1f;
		}
	}

	public void aumentarQtdAzul() {
		if (blue < 1) {
			blue += 0.1f;
		}
	}

	public void diminuirQtdAzul() {
		if (blue > 0) {
			blue -= 0.1f;
		}
	}

}
