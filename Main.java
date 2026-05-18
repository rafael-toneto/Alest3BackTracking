import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        runNQueensExample();
        runSubsetSumExamples();
        runLargeSetPerformanceExample();
    }

    private static void runNQueensExample() {
        NQueensSolver solver = new NQueensSolver();
        int n = 7;

        System.out.println("=== Problema das " + n + "-rainhas ===");
        Optional<int[]> solution = solver.solve(n);

        if (solution.isPresent()) {
            System.out.println("Coluna de cada rainha por linha: " + Arrays.toString(solution.get()));
            System.out.println(solver.formatBoard(solution.get()));
        } else {
            System.out.println("Nao existe solucao para n = " + n + ".");
        }

        System.out.println("Complexidade: " + solver.complexity());
        System.out.println();
    }

    private static void runSubsetSumExamples() {
        SubsetSumSolver solver = new SubsetSumSolver();

        int[] exampleFromStatement = {-7, -3, -2, 5, 8};
        int[] positiveOnlyExample = {1, 2, 3, 4, 5, 10};
        int[] mixedExample = {-5, 2, 3, -1, 1};

        System.out.println("=== Soma dos subconjuntos ===");
        testSubsetSet("Exemplo do enunciado", exampleFromStatement, solver, true);
        testSubsetSet("Apenas positivos", positiveOnlyExample, solver, true);
        testSubsetSet("Misto", mixedExample, solver, true);

        System.out.println("Complexidade para uma solucao: " + solver.complexityForOneSolution());
        System.out.println("Complexidade para todas as solucoes: " + solver.complexityForAllSolutions());
        System.out.println();
    }

    private static void testSubsetSet(
            String name,
            int[] values,
            SubsetSumSolver solver,
            boolean printAllSolutions
    ) {
        System.out.println(name + ": " + Arrays.toString(values));

        Optional<List<Integer>> oneSolution = solver.findOneZeroSumSubset(values);
        if (oneSolution.isPresent()) {
            System.out.println("Uma solucao: " + oneSolution.get());
        } else {
            System.out.println("Uma solucao: nenhuma encontrada.");
        }

        if (printAllSolutions) {
            List<List<Integer>> allSolutions = solver.findAllZeroSumSubsets(values);
            System.out.println("Todas as solucoes (" + allSolutions.size() + "): " + allSolutions);
        }

        System.out.println();
    }

    private static void runLargeSetPerformanceExample() {
        SubsetSumSolver solver = new SubsetSumSolver();
        int[] largeSet = solver.generateLargeSet(1000, 10_000, 42L);

        long start = System.nanoTime();
        Optional<List<Integer>> solution = solver.findOneZeroSumSubset(largeSet);
        long end = System.nanoTime();

        double elapsedMilliseconds = (end - start) / 1_000_000.0;

        System.out.println("=== Teste com conjunto grande ===");
        System.out.println("Tamanho do conjunto: " + largeSet.length);
        System.out.println("Encontrou subconjunto? " + solution.isPresent());
        solution.ifPresent(subset -> System.out.println("Subconjunto encontrado: " + subset));
        System.out.printf("Tempo gasto: %.3f ms%n", elapsedMilliseconds);
    }
}
