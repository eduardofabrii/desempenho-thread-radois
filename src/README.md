# Comparação de Desempenho entre Modelos de Threads N:M e 1:1

Desenvolver dois programas em Java que simulem os modelos de execução de threads N:M e 1:1, e comparar o desempenho entre eles com base no tempo total de execução. O objetivo é compreender como o modelo de mapeamento de threads influencia a eficiência da execução concorrente.

São dois programas distintos que devem realizar a mesma tarefa computacional (vou fazer simulando com o thread sleep mesmo:
* Simulação do modelo N:M -> múltiplas threads do usuário são mapeadas para um número menor de thredas do SO, permitindo que múltiplas threads de usuário sejam gerenciadas ao mesmo tempo, mas com controle parcial do gerenciamento de tarefas para processadores e outros recursos (escalonamento). 
* Simulação do modelo 1:1 -> múltiplas threads de usuário mapeadas diretamente para threads do SO, com execução concorrente real.

Para fazer o N:M dá para agrupar as threads de usuário que são gerenciadas e executadas por um conjunto fixo de threads do sistema

Métrica de Avaliação
* Tempo total de execução (em milissegundos), medido do início ao fim da execução de todas as threads.
* A comparação deve ser feita com diferentes quantidades de threads (ex: 10, 100, 500, 1000) para observar o impacto da escalabilidade.

