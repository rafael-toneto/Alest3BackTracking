import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SubsetSumSolver {
    public Optional<List<Integer>> findOneZeroSumSubset(int[] values) {
        List<Integer> chosen = new ArrayList<>();

        boolean found = findOneBacktracking(values, 0, 0, chosen);
        if (!found) {
            return Optional.empty();
        }

        return Optional.of(new ArrayList<>(chosen));
    }

    private boolean findOneBacktracking(
            int[] values,
            int index,
            int currentSum,
            List<Integer> chosen
    ) {
        if (!chosen.isEmpty() && currentSum == 0) {
            return true;
        }

        if (index == values.length) {
            return false;
        }

        chosen.add(values[index]);
        if (findOneBacktracking(values, index + 1, currentSum + values[index], chosen)) {
            return true;
        }
        chosen.remove(chosen.size() - 1);

        return findOneBacktracking(values, index + 1, currentSum, chosen);
    }

    public List<List<Integer>> findAllZeroSumSubsets(int[] values) {
        List<List<Integer>> results = new ArrayList<>();
        findAllBacktracking(values, 0, 0, new ArrayList<>(), results);
        return results;
    }

    private void findAllBacktracking(
            int[] values,
            int index,
            int currentSum,
            List<Integer> chosen,
            List<List<Integer>> results
    ) {
        if (index == values.length) {
            if (!chosen.isEmpty() && currentSum == 0) {
                results.add(new ArrayList<>(chosen));
            }
            return;
        }

        chosen.add(values[index]);
        findAllBacktracking(values, index + 1, currentSum + values[index], chosen, results);
        chosen.remove(chosen.size() - 1);

        findAllBacktracking(values, index + 1, currentSum, chosen, results);
    }

    public int[] generateLargeSet(int size, int maximumAbsoluteValue, long seed) {
        if (size < 50 || size > 1000) {
            throw new IllegalArgumentException("O tamanho deve ficar entre 50 e 1000 elementos.");
        }

        if (maximumAbsoluteValue < 1) {
            throw new IllegalArgumentException("O valor absoluto maximo deve ser pelo menos 1.");
        }

        Random random = new Random(seed);
        int[] values = new int[size];

        int guaranteedValue = random.nextInt(maximumAbsoluteValue) + 1;
        values[0] = guaranteedValue;
        values[1] = -guaranteedValue;

        for (int i = 2; i < size; i++) {
            int value = random.nextInt(maximumAbsoluteValue) + 1;
            values[i] = random.nextBoolean() ? value : -value;
        }

        return values;
    }

    public String complexityForOneSolution() {
        return "Tempo: O(2^n) no pior caso; espaco: O(n) pela pilha de recursao e subconjunto atual.";
    }

    public String complexityForAllSolutions() {
        return "Tempo: O(2^n) para visitar os subconjuntos, mais o custo de copiar as respostas; "
                + "espaco: O(n) durante a busca, mais o tamanho da lista de resultados.";
    }
}
