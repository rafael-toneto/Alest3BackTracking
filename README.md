# Alest3BackTracking

Implementacoes em Java usando backtracking para:

1. Problema das n-rainhas.
2. Problema da soma dos subconjuntos.

## Como compilar

```bash
javac *.java
```

## Como executar

```bash
java Main
```

## Arquivos

- `NQueensSolver.java`: encontra uma solucao valida para o problema das n-rainhas.
- `SubsetSumSolver.java`: encontra uma solucao ou todas as solucoes de soma zero.
- `Main.java`: executa exemplos, imprime as respostas e mostra as complexidades.

## Complexidades

### N-rainhas

- Tempo: `O(n!)` no pior caso, porque o algoritmo tenta posicionar uma rainha por linha e evita repetir colunas.
- Espaco: `O(n)`, considerando os vetores de controle e a pilha de recursao.

### Soma dos subconjuntos

- Uma solucao: `O(2^n)` no pior caso, pois cada elemento pode entrar ou nao no subconjunto.
- Todas as solucoes: `O(2^n)` para visitar os subconjuntos, mais o custo de copiar e armazenar as respostas.
- Espaco: `O(n)` durante a recursao, mais o espaco ocupado pelas solucoes retornadas.
