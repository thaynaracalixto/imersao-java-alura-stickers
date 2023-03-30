import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDb {
    public List<Conteudo> extrairConteudos (String json){

        //Extrair dados que nos interessam (Título, poster do Filme e Notas)
        var parser = new JsonParser();
        List<Map<String, String>> listaJson = parser.parse(json);
    
        List<Conteudo> conteudosList = new ArrayList<>();
    
        //Popular a lista
        for (Map<String,String> atributos: listaJson) {
            String titulo = atributos.get("title");
            String UrlImage = atributos.get("image");
            var conteudo = new Conteudo(titulo, UrlImage);  
            
            conteudosList.add(conteudo);
        }
        return conteudosList;
        }
    
}
