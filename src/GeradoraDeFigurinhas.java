import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {
        
        //Leitura da Imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Criar nova Imagem com novo tamanho e transparência
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        
        //Copiar a imagem original por cima da imagem nova que criamos
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        Font fonte = new Font("impact", Font.BOLD, 100);
        graphics.setColor(Color.BLACK);
        graphics.setFont(fonte);   
        
        //Obter largura do texto e calcular posição X para centralizá-lo
        FontMetrics medidas = graphics.getFontMetrics(fonte);
        int larguraTexto = medidas.stringWidth("FILME TOP 10");
        int posicaoX = (largura - larguraTexto) / 2;

        //Escrever uma frase na Nova Imagem 
        graphics.drawString("FILME TOP 10", posicaoX, novaAltura - 100);
        
        //Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }    
}
