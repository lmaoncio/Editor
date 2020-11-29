package com.jtorres.editor.services;

import com.jtorres.editor.model.Filtro;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class ImageConverter {

    public BufferedImage applyBlue(BufferedImage bufferedImage, double porcentaje) {

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp = porcentaje;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                    int blue = colorOriginal.getBlue();
                    int green = colorOriginal.getGreen();
                    int red = colorOriginal.getRed();


                    if (porcentaje > 0) {
                        porcentaje = porcentaje / 10;
                        blue = (int) (blue + (blue * porcentaje));
                        porcentaje = temp;
                    }

                    if (porcentaje < 0) {
                        porcentaje = porcentaje * (-1) / 10;
                        blue = (int) (blue - (blue * porcentaje));
                        porcentaje = temp;
                    }

                    blue = blue > 255 ? 255 : blue;
                    blue = blue < 0 ? 0 : blue;

                    Color colorCopia = new Color(red, green, blue);

                    nuevaImagen.setRGB(x, y, colorCopia.getRGB());
            }
        }
        return nuevaImagen;
    }

    public BufferedImage applyBlur(BufferedImage bufferedImage, Filtro filtro, int repeticiones) {

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        byte[] resultado = ((DataBufferByte) nuevaImagen.getRaster().getDataBuffer()).getData();

        for (int vuelta = 0; vuelta < repeticiones; vuelta++) {
            for (int fila = 0; fila < bufferedImage.getHeight(); fila++) {
                if (fila == 0 || fila == bufferedImage.getHeight() - 1) {
                    continue;
                }
                for (int columna = 0; columna < bufferedImage.getWidth(); columna++) {
                    if (columna == 0 || columna == bufferedImage.getWidth() - 1) {
                        continue;
                    }
                    int posicionAProcesar = 0;
                    for (int profundidad = 0; profundidad < 3; profundidad++) {
                            posicionAProcesar = ((fila) * (bufferedImage.getWidth() * 3) + ((columna) * 3)) + (profundidad);
                            int valor = 0;
                            for (int i = 0; i < filtro.getFiltro().length; i++) {
                                for (int j = 0; j < filtro.getFiltro()[0].length; j++) {
                                    int filaConvolucion = fila - 1 + i;
                                    int columnaConvolucion = columna - 1 + j;
                                    int posicionPixel = ((filaConvolucion) * (bufferedImage.getWidth() * 3) + ((columnaConvolucion) * 3)) + (profundidad);
                                    valor += Byte.toUnsignedInt(pixels[posicionPixel]) * filtro.getFiltro()[i][j];
                                }
                            }
                            valor = valor / filtro.getDivisorK();
                            valor = valor > 255 ? 255 : valor;
                            valor = valor < 0 ? 0 : valor;
                            resultado[posicionAProcesar] = (byte) valor;
                    }
                }
            }
            pixels = resultado;
        }
        return nuevaImagen;
    }

    public BufferedImage applyBrigthness(BufferedImage bufferedImage, double porcentaje) {
        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        porcentaje = porcentaje / 10;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                    int blue = colorOriginal.getBlue();
                    int green = colorOriginal.getGreen();
                    int red = colorOriginal.getRed();

                    if (porcentaje < 0) {
                        porcentaje = porcentaje * (-1);
                        blue = (int) (blue - (blue * porcentaje));
                        green = (int) (green - (green * porcentaje));
                        red = (int) (red - (red * porcentaje));
                        porcentaje = porcentaje * (-1);
                    }

                    if (porcentaje > 0) {
                        blue = (int) (blue + (blue * porcentaje));
                        green = (int) (green + (green * porcentaje));
                        red = (int) (red + (red * porcentaje));
                    }

                    red = red > 255 ? 255 : red;
                    red = red < 0 ? 0 : red;
                    green = green > 255 ? 255 : green;
                    green = green < 0 ? 1 : green;
                    blue = blue > 255 ? 255 : blue;
                    blue = blue < 0 ? 0 : blue;

                    Color colorCopia = new Color(red, green, blue);

                    nuevaImagen.setRGB(x, y, colorCopia.getRGB());
            }
        }
        return nuevaImagen;
    }

    public BufferedImage applyGreen(BufferedImage bufferedImage, double porcentaje) {

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp = porcentaje;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                    int blue = colorOriginal.getBlue();
                    int green = colorOriginal.getGreen();
                    int red = colorOriginal.getRed();


                    if (porcentaje > 0) {
                        porcentaje = porcentaje / 10;
                        green = (int) (green + (green * porcentaje));
                        porcentaje = temp;
                    }

                    if (porcentaje < 0) {
                        porcentaje = porcentaje * (-1) / 10;
                        green = (int) (green - (green * porcentaje));
                        porcentaje = temp;
                    }

                    green = green > 255 ? 255 : green;
                    green = green < 0 ? 0 : green;

                    Color colorCopia = new Color(red, green, blue);

                    nuevaImagen.setRGB(x, y, colorCopia.getRGB());
            }
        }
        return nuevaImagen;
    }

    public BufferedImage applyGrey(BufferedImage bufferedImage) {

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        for (int x = 0; x < nuevaImagen.getWidth(); x++) {
            for (int y = 0; y < nuevaImagen.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int media = (red + green + blue) / 3;
                red = media;
                green = media;
                blue = media;
                Color resultado = new Color(red, green, blue);
                nuevaImagen.setRGB(x, y, resultado.getRGB());
            }
        }

        return nuevaImagen;
    }

    public BufferedImage applyRed(BufferedImage bufferedImage, double porcentaje) {

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp = porcentaje;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                    int blue = colorOriginal.getBlue();
                    int green = colorOriginal.getGreen();
                    int red = colorOriginal.getRed();


                    if (porcentaje > 0) {
                        porcentaje = porcentaje / 10;
                        red = (int) (red + (red * porcentaje));
                        porcentaje = temp;
                    }

                    if (porcentaje < 0) {
                        porcentaje = porcentaje * (-1) / 10;
                        red = (int) (red - (red * porcentaje));
                        porcentaje = temp;
                    }

                    red = red > 255 ? 255 : red;
                    red = red < 0 ? 0 : red;

                    Color colorCopia = new Color(red, green, blue);

                    nuevaImagen.setRGB(x, y, colorCopia.getRGB());
            }
        }
        return nuevaImagen;
    }

    public BufferedImage applySharp(BufferedImage bufferedImage, Filtro filtro, int repeticiones) {
        repeticiones = repeticiones * (-1);

        BufferedImage nuevaImagen = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        byte[] resultado = ((DataBufferByte) nuevaImagen.getRaster().getDataBuffer()).getData();

        for (int vuelta = 0; vuelta < repeticiones; vuelta++) {
            for (int fila = 0; fila < bufferedImage.getHeight(); fila++) {
                if (fila == 0 || fila == bufferedImage.getHeight() - 1) {
                    continue;
                }
                for (int columna = 0; columna < bufferedImage.getWidth(); columna++) {
                    if (columna == 0 || columna == bufferedImage.getWidth() - 1) {
                        continue;
                    }
                    int posicionAProcesar = 0;
                    for (int profundidad = 0; profundidad < 3; profundidad++) {
                            posicionAProcesar = ((fila) * (bufferedImage.getWidth() * 3) + ((columna) * 3)) + (profundidad);
                            int backup1 = pixels[posicionAProcesar];
                            int valor = 0;
                            for (int i = 0; i < filtro.getFiltro().length; i++) {
                                for (int j = 0; j < filtro.getFiltro()[0].length; j++) {
                                    int filaConvolucion = fila - 1 + i;
                                    int columnaConvolucion = columna - 1 + j;
                                    int posicionPixel = ((filaConvolucion) * (bufferedImage.getWidth() * 3) + ((columnaConvolucion) * 3)) + (profundidad);
                                    valor += Byte.toUnsignedInt(pixels[posicionPixel]) * filtro.getFiltro()[i][j];
                                }
                            }
                            if (valor > 255) {
                                valor = backup1;
                                resultado[posicionAProcesar] = (byte) valor;
                                continue;
                            }
                            valor = valor / filtro.getDivisorK();
                            valor = valor < 0 ? 0 : valor;
                            resultado[posicionAProcesar] = (byte) valor;
                    }
                }
            }

            pixels = resultado;
        }

        return nuevaImagen;
    }

    public BufferedImage copyImage(BufferedImage origen) {

        ColorModel cm = origen.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = origen.copyData(origen.getRaster().createCompatibleWritableRaster());

        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
