import java.util.List; 
import java.util.Arrays;
import java.util.stream.Collectors;

List<String> tabelaDirigentes = Arrays.asList("Pr Otacílio", "Co-pastor Novo", "Co-pastor Davi", "Antônio");
List<String> tabelaBloqueados = Arrays.asList("Antônio"); // Exemplo de quem não pode ser escalado

List<String> resultado = tabelaDirigentes.stream()
    .filter(nome -> !tabelaBloqueados.contains(nome))
    .collect(Collectors.toList());

