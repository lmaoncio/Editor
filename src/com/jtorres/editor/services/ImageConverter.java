package com.jtorres.editor.services;

import com.jtorres.editor.model.Filter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class ImageConverter {

    public BufferedImage applyBlue(BufferedImage bufferedImage, double percent) {

        BufferedImage newImage;
        newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp;
        temp = percent;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                int blue = colorOriginal.getBlue();
                int green = colorOriginal.getGreen();
                int red = colorOriginal.getRed();

                if (percent > 0) {
                    percent = percent / 10;
                    blue = (int) (blue + (blue * percent));
                    percent = temp;
                }

                if (percent < 0) {
                    percent = percent * (-1) / 10;
                    blue = (int) (blue - (blue * percent));
                    percent = temp;
                }

                blue = Math.min(blue, 255);
                blue = Math.max(blue, 0);

                Color copyColor = new Color(red, green, blue);

                newImage.setRGB(x, y, copyColor.getRGB());
            }
        }
        return newImage;
    }

    public BufferedImage applyBlur(BufferedImage bufferedImage, Filter filter, int repeats) {

        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        byte[] result = ((DataBufferByte) newImage.getRaster().getDataBuffer()).getData();

        for (int lap = 0; lap < repeats; lap++) {
            for (int row = 0; row < bufferedImage.getHeight(); row++) {
                if (row == 0 || row == bufferedImage.getHeight() - 1) {
                    continue;
                }
                for (int column = 0; column < bufferedImage.getWidth(); column++) {
                    if (column == 0 || column == bufferedImage.getWidth() - 1) {
                        continue;
                    }
                    int positionToProcess;
                    for (int deep = 0; deep < 3; deep++) {
                        positionToProcess = (((row) * (bufferedImage.getWidth() * 3)) + ((column) * 3)) + deep;
                        int valor = 0;
                        for (int i = 0; i < filter.getFilter().length; i++) {
                            for (int j = 0; j < filter.getFilter()[0].length; j++) {
                                int convolutionRow = row - 1 + i;
                                int convolutionColumn = column - 1 + j;
                                int pixelPosition = ((convolutionRow) * (bufferedImage.getWidth() * 3) + ((convolutionColumn) * 3)) + (deep);
                                valor += Byte.toUnsignedInt(pixels[pixelPosition]) * filter.getFilter()[i][j];
                            }
                        }
                        valor = valor / filter.getK();
                        valor = Math.min(valor, 255);
                        valor = Math.max(valor, 0);
                        result[positionToProcess] = (byte) valor;
                    }
                }
            }
            pixels = result;
        }
        return newImage;
    }

    public BufferedImage applyBrightness(BufferedImage bufferedImage, double percent) {
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        percent = percent / 10;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color originalColor = new Color(bufferedImage.getRGB(x, y));

                int blue = originalColor.getBlue();
                int green = originalColor.getGreen();
                int red = originalColor.getRed();

                if (percent < 0) {
                    percent = percent * (-1);
                    blue = (int) (blue - (blue * percent));
                    green = (int) (green - (green * percent));
                    red = (int) (red - (red * percent));
                    percent = percent * (-1);
                }

                if (percent > 0) {
                    blue = (int) (blue + (blue * percent));
                    green = (int) (green + (green * percent));
                    red = (int) (red + (red * percent));
                }

                red = Math.min(red, 255);
                red = Math.max(red, 0);
                green = Math.min(green, 255);
                green = green < 0 ? 1 : green;
                blue = Math.min(blue, 255);
                blue = Math.max(blue, 0);

                Color copyColor = new Color(red, green, blue);

                newImage.setRGB(x, y, copyColor.getRGB());
            }
        }
        return newImage;
    }

    public BufferedImage applyGreen(BufferedImage bufferedImage, double percent) {

        BufferedImage newImage;
        newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp;
        temp = percent;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color colorOriginal = new Color(bufferedImage.getRGB(x, y));

                int blue = colorOriginal.getBlue();
                int green = colorOriginal.getGreen();
                int red = colorOriginal.getRed();

                if (percent > 0) {
                    percent = percent / 10;
                    green = (int) (green + (green * percent));
                    percent = temp;
                }

                if (percent < 0) {
                    percent = percent * (-1) / 10;
                    green = (int) (green - (green * percent));
                    percent = temp;
                }

                green = Math.min(green, 255);
                green = Math.max(green, 0);

                Color copyColor = new Color(red, green, blue);

                newImage.setRGB(x, y, copyColor.getRGB());
            }
        }
        return newImage;
    }

    public BufferedImage applyGrey(BufferedImage bufferedImage) {

        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        for (int x = 0; x < newImage.getWidth(); x++) {
            for (int y = 0; y < newImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int average = (red + green + blue) / 3;
                red = average;
                green = average;
                blue = average;
                Color result = new Color(red, green, blue);
                newImage.setRGB(x, y, result.getRGB());
            }
        }

        return newImage;
    }

    public BufferedImage applyRed(BufferedImage bufferedImage, double percent) {

        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());

        double temp = percent;

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color originalColor = new Color(bufferedImage.getRGB(x, y));

                int blue = originalColor.getBlue();
                int green = originalColor.getGreen();
                int red = originalColor.getRed();

                if (percent > 0) {
                    percent = percent / 10;
                    red = (int) (red + (red * percent));
                    percent = temp;
                }

                if (percent < 0) {
                    percent = percent * (-1) / 10;
                    red = (int) (red - (red * percent));
                    percent = temp;
                }

                red = Math.min(red, 255);
                red = Math.max(red, 0);

                Color copyColor = new Color(red, green, blue);

                newImage.setRGB(x, y, copyColor.getRGB());
            }
        }
        return newImage;
    }

    public BufferedImage applySharp(BufferedImage bufferedImage, Filter filter, int repeats) {
        repeats = repeats * (-1);

        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        byte[] result;
        result = ((DataBufferByte) newImage.getRaster().getDataBuffer()).getData();

        for (int lap = 0; lap < repeats; lap++) {
            for (int row = 0; row < bufferedImage.getHeight(); row++) {
                if (row == 0 || row == bufferedImage.getHeight() - 1) {
                    continue;
                }
                for (int column = 0; column < bufferedImage.getWidth(); column++) {
                    if (column == 0 || column == bufferedImage.getWidth() - 1) {
                        continue;
                    }
                    int positionToProcess;
                    for (int deep = 0; deep < 3; deep++) {
                        positionToProcess = ((row) * (bufferedImage.getWidth() * 3) + ((column) * 3)) + (deep);
                        int backup = pixels[positionToProcess];
                        int valor = 0;
                        for (int i = 0; i < filter.getFilter().length; i++) {
                            for (int j = 0; j < filter.getFilter()[0].length; j++) {
                                int convolutionRow = row - 1 + i;
                                int convolutionColumn = column - 1 + j;
                                int pixelPosition = ((convolutionRow) * (bufferedImage.getWidth() * 3) + ((convolutionColumn) * 3)) + (deep);
                                valor += Byte.toUnsignedInt(pixels[pixelPosition]) * filter.getFilter()[i][j];
                            }
                        }
                        if (valor > 255) {
                            valor = backup;
                            result[positionToProcess] = (byte) valor;
                            continue;
                        }
                        valor = valor / filter.getK();
                        valor = Math.max(valor, 0);
                        result[positionToProcess] = (byte) valor;
                    }
                }
            }

            pixels = result;
        }

        return newImage;
    }

    public BufferedImage copyImage(BufferedImage original) {
        ColorModel cm = original.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = original.copyData(original.getRaster().createCompatibleWritableRaster());

        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
