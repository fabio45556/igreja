package pj2.igreja;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // <-- IMPORTANTE: Adicione para usar o shuffle!
import java.util.stream.Collectors; // <-- IMPORTANTE: Adicione para o ToList!

@Controller
public class EscalaController2 {

    // Método auxiliar para ler o CSV
    private List<Escala> carregarEscalaDoCSV() {
        List<Escala> lista = new ArrayList<>();
        String caminhoArquivo = "escala.csv"; 

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length >= 4) {
                    lista.add(new Escala(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista; 
    }

    @GetMapping("/")
    public String exibirEscala(Model model) {
        List<Escala> escala = carregarEscalaDoCSV();
        model.addAttribute("escala", escala);
        return "escala";
    }

    @GetMapping("/api/escala")
    public List<Escala> listarEscalaAPI() {
        return carregarEscalaDoCSV();
    }


    // Método para obter irmãos disponíveis
    public List<String> obterIrmaosDisponiveis(List<String> listaTodos, List<String> listaOcupados) {
        List<String> resultado = listaTodos.stream()
                .filter(nome -> !listaOcupados.contains(nome)) 
                .collect(Collectors.toList());

        return resultado; 
    }

    // Método para gerar sorteio exemplo
    @GetMapping("/api/escala/gerar") 
    public List<String> gerarSorteioExemplo() {
        List<String> todosOsIrmaos = List.of("Pr Otacílio", "Co-pastor Davi", "Antônio", "Matheus", "Fernando");
        List<String> irmãosBloqueados = List.of("Antônio");

        List<String> disponiveis = obterIrmaosDisponiveis(todosOsIrmaos, irmãosBloqueados);

        Collections.shuffle(disponiveis);
        
        return disponiveis; 
    }

} // <-- AQUI FECHA A CLASSE EscalaController2 (Última linha do arquivo)