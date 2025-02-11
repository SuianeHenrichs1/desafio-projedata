package src;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // Adicionando funcionários na lista
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletrecista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // Remover o João
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // Ordenar a lista de funcionários por nome com Comparator.comparing
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        // Aplicando o aumento de 10% nos salários
        for (Funcionario f : funcionarios) {
            BigDecimal salarioAtual = f.getSalario();
            BigDecimal aumento = salarioAtual.multiply(new BigDecimal("0.10"));
            f.setSalario(salarioAtual.add(aumento)); // Atualiza o salário com o aumento
        }

        // Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = new TreeMap<>();
        for (Funcionario f : funcionarios) {
            // Se não houver uma lista para a função, cria uma nova lista
            funcionariosPorFuncao.putIfAbsent(f.getFuncao(), new ArrayList<>());
            // Adiciona o funcionário na lista correspondente à função
            funcionariosPorFuncao.get(f.getFuncao()).add(f);
        }

        // Definir formatadores
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setGroupingUsed(true);

        // Exibir os funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> listaDeFuncionarios = entry.getValue();

            System.out.println("Função: " + funcao);
            for (Funcionario f : listaDeFuncionarios) {
                System.out.println("Nome: " + f.getNome());
                System.out.println("Data de Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Salário: R$ " + decimalFormat.format(f.getSalario()));
                System.out.println("-----------------------------------");
            }
        }

        // Imprimir funcionários que fazem aniversário em outubro (10) e dezembro (12)
        System.out.println("Funcionários com aniversário em outubro ou dezembro:");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println("Nome: " + f.getNome());
                System.out.println("Data de Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("-----------------------------------");
            }
        }

        // Encontrar o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = null;
        int maiorIdade = -1;

        for (Funcionario f : funcionarios) {
            // Calculando a idade do funcionário
            Period periodo = Period.between(f.getDataNascimento(), LocalDate.now());
            int idade = periodo.getYears();

            // Verificando se a idade é a maior
            if (idade > maiorIdade) {
                maiorIdade = idade;
                funcionarioMaisVelho = f;
            }
        }

        // Exibindo o funcionário com a maior idade
        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário com a maior idade:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + maiorIdade);
        }

        // Calcular o total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Exibir o total dos salários
        System.out.println("Total dos salários dos funcionários: R$ " + decimalFormat.format(totalSalarios));

        // Definindo o valor do salário mínimo
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        // Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("Quantos salários mínimos ganha cada funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal salario = f.getSalario();
            BigDecimal salariosMinimos = salario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP); // Dividindo o salário pelo salário mínimo
            System.out.println("Nome: " + f.getNome());
            System.out.println("Salários Mínimos: " + salariosMinimos);
            System.out.println("-----------------------------------");
        }
    }
}










