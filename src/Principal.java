import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Principal {
        public static void main(String[] args) throws Exception {

        //conexão HTTP e Extração - IMDb
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; 
        //ExtratorConteudoIMDb extrator = new ExtratorConteudoIMDb();

        //conexão HTTP e Extração - Nasa
        //String url = "https://api.nasa.gov/planetary/apod?api_key=RFsaekEz6cgPbaR9lD9n3yKFE0YmaF6yvtzdX9ea&date=2018-08-16";
        String url ="https://api.nasa.gov/planetary/apod?api_key=RFsaekEz6cgPbaR9lD9n3yKFE0YmaF6yvtzdX9ea&start_date=2018-08-16&end_date=2018-08-18";
        ExtratorConteudoNasa extrator = new ExtratorConteudoNasa();

        var conexao = new ConexaoHTTP();
        String json = conexao.buscaDados(url);
        
        //Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        File diretorioFigurinhas = new File("figurinhas/");
        diretorioFigurinhas.mkdir();

        var geradora = new GeradoraDeFigurinhas();

        for(int i= 0; i<3; i++){

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.getTitulo().replace(":", "-")  + ".png";
            
            geradora.cria(inputStream, nomeArquivo);
        
            System.out.println(conteudo.getTitulo());
            System.out.println();
        }        
    }

        //Exibir e Manipular os dados / exibir o Rating com estrelas (Decorando terminal)
        /*for (Map<String,String> conteudo: ListaDeConteudo) {
            System.out.println("\u001b[1mTitle:\u001b[m " + conteudo.get("title"));
            System.out.println("\u001b[1mPoster URL:\u001b[m " + conteudo.get("image"));
            System.out.println("\u001b[1mRating:\u001b[m " + conteudo.get("imDbRating"));
            double rating = Double.parseDouble(conteudo.get("imDbRating"));
            int NumEstrela = (int) rating;
            for(int n = 1; n <= NumEstrela; n++){
                System.out.print("🌟");
            }
            System.out.println("\n");
        }*/

    }