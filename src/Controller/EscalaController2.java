package pj2.igreja;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // <-- IMPORTANTE: Adicione para usar o shuffle!
import java.util.stream.Collectors; // <-- IMPORTANTE: Adicione para o ToList!

@RestController
public class EscalaController2 {

    @GetMapping("/api/escala")
    public List<Escala> listarEscala() {
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

                String[] dados = line.split(",");
                if (dados.length >= 3) {
                    lista.add(new Escala(dados[0], dados[1], dados[2]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista; 
    } // <-- AQUI FECHA O MÉTODO listarEscala()

    // GAVETA 2: Seu método de filtrar que agora está no lugar certo!
    public List<String> obterIrmaosDisponiveis(List<String> listaTodos, List<String> listaOcupados) {
        List<String> resultado = listaTodos.stream()
                .filter(nome -> !listaOcupados.contains(nome)) 
                .collect(Collectors.toList());

        return resultado; 
    } // <-- AQUI FECHA O MÉTODO obterIrmaosDisponiveis()


    // GAVETA 3: O terceiro método que você pediu para rodar o sorteio!
   @GetMapping("/api/escala/gerar") 
public List<String> gerarSorteioExemplo() {
    List<String> todosOsIrmaos = List.of("Pr Otacílio", "Co-pastor Davi", "Antônio", "Matheus", "Fernando");
    List<String> irmãosBloqueados = List.of("Antônio");

    // PASSO 1: A máquina trabalha e guarda a lista limpa na variável 'disponiveis'
    List<String> disponiveis = obterIrmaosDisponiveis(todosOsIrmaos, irmãosBloqueados);

    // PASSO 2: Você embaralha a variável 'disponiveis' (note o 's' minúsculo)
    Collections.shuffle(disponiveis);

    // PASSO 3: Retorna a lista embaralhada para o navegador
    return disponiveis; 
}
} // <-- AQUI FECHA A CLASSE EscalaController2 (Última linha do arquivo)