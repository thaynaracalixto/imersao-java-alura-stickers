import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
        public static void main(String[] args) throws Exception {

        //Pegando dados do IMDb - conexão HTTP 
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //Endereço Alternativo, pois API esta inconsistente
        URI endereco = URI.create(url);
        HttpClient Client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = Client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        //Extrair dados que nos interessam (Título, poster do Filme e Notas)
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        //Exibir e Manipular os dados / exibir o Rating com estrelas.
        for (Map<String,String> filme: ListaDeFilmes) {
            System.out.println("\u001b[1mTitle:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1mPoster URL:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[1mRating:\u001b[m " + filme.get("imDbRating"));
            double rating = Double.parseDouble(filme.get("imDbRating"));
            int NumEstrela = (int) rating;
            for(int n = 1; n <= NumEstrela; n++){
                System.out.print("🌟");
            }
            System.out.println("\n");
        }

        File diretorioFigurinhas = new File("figurinhas/");
        diretorioFigurinhas.mkdir();


        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme: ListaDeFilmes) {

        String UrlImage = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(UrlImage).openStream();
        String nomeArquivo = "figurinhas/" + titulo.replace(":", "-")  + ".png";

        geradora.cria(inputStream, nomeArquivo);

        System.out.println(titulo);
        System.out.println();        
    }

    }
}
