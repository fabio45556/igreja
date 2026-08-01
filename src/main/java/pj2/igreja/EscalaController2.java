package pj2.igreja;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; 
import java.util.stream.Collectors; 

@Controller
public class EscalaController2 {

    // Método auxiliar para ler o CSV de forma robusta
    private List<Escala> carregarEscalaDoCSV() {
        List<Escala> lista = new ArrayList<>();

        try {
            List<Path> caminhosPossiveis = List.of(
                Path.of("escala.csv"),
                Path.of("src/main/resources/escala.csv")
            );

            for (Path caminho : caminhosPossiveis) {
                if (Files.exists(caminho)) {
                    try (BufferedReader br = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
                        preencherListaComCSV(lista, br);
                        return lista;
                    }
                }
            }

            Resource resource = new ClassPathResource("escala.csv");
            if (resource.exists()) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                    preencherListaComCSV(lista, br);
                    return lista;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private void preencherListaComCSV(List<Escala> lista, BufferedReader br) throws Exception {
        String linha;
        boolean primeiraLinha = true;

        while ((linha = br.readLine()) != null) {
            if (primeiraLinha) {
                primeiraLinha = false;
                continue;
            }

            String[] dados = linha.split(",");
            if (dados.length >= 4) {
                String culto = dados.length >= 5 ? dados[4].trim() : "";
                lista.add(new Escala(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim(), culto));
            }
        }
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


    
    public List<String> obterIrmaosDisponiveis(List<String> listaTodos, List<String> listaOcupados) {
        List<String> resultado = listaTodos.stream()
                .filter(nome -> !listaOcupados.contains(nome)) 
                .collect(Collectors.toList());

        return resultado; 
    }

    
    @GetMapping("/api/escala/gerar") 
    public List<String> gerarSorteioExemplo() {
        List<String> todosOsIrmaos = List.of("Pr Otacílio", "Co-pastor Davi", "Antônio", "Matheus", "Fernando");
        List<String> irmãosBloqueados = List.of("Antônio");

        List<String> disponiveis = obterIrmaosDisponiveis(todosOsIrmaos, irmãosBloqueados);

        Collections.shuffle(disponiveis);
        
        return disponiveis; 
    }

} 