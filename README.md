# Comparação de Desempenho entre Modelos de Threads N:M e 1:1

Desenvolver dois programas em Java que simulem os modelos de execução de threads N:M e 1:1, e comparar o desempenho entre eles com base no tempo total de execução. O objetivo é compreender como o modelo de mapeamento de threads influencia a eficiência da execução concorrente.

São dois programas distintos que devem realizar a mesma tarefa computacional (vou fazer simulando com o thread sleep mesmo:

* **Modelo 1:1 (`ThreadOneToOneModel.java`):** Simula o mapeamento 1:1 criando uma nova Thread de Plataforma (`java.lang.Thread`) para cada tarefa solicitada. Cada thread do programa é mapeada diretamente para uma thread do Sistema Operacional.
* **Modelo N:M (`MNThreadModel.java`):** Simula o mapeamento N:M usando as **Threads Virtuais** do Java (`Thread.startVirtualThread()`). Neste modelo, N threads "leves" (virtuais) criadas pela aplicação são gerenciadas pela JVM e executadas em um número menor (M) de threads de plataforma.

Ambos os programas executam a mesma tarefa: uma simulação de 50ms de trabalho (`Thread.sleep(50)`).

## Resultados da Execução

Os testes foram executados medindo o tempo total para concluir N tarefas em cada modelo.

**Tabela Comparativa de Tempos de Execução (em milissegundos)**

| **Qtd. de Tarefas (N)** | **Tempo Modelo 1:1 (ms)** | **Tempo Modelo N:M (ms)** |
| ----------------------------- | ------------------------------- | ------------------------------- |
| 10                            | média 61ms                    | média de 76ms                  |
| 100                           | média de 67ms                  | média de 67ms                 |
| 500                           | méida de 85ms                  | média de 73ms                  |
| 1000                          | média de 108ms                 | média de 86ms                  |

### 1. Observação Geral: Escalabilidade

A diferença mais clara está na  **escalabilidade** .

* **Modelo N:M (Virtual Threads):** O tempo de execução permaneceu quase constante, aumentando muito pouco (de 76ms para 86ms) mesmo quando a carga de trabalho aumentou 100 vezes (de 10 para 1000 tarefas).
* **Modelo 1:1 (Platform Threads):** O tempo de execução aumentou visivelmente a cada etapa. O custo de 61ms para 10 tarefas subiu para 108ms para 1000 tarefas.

Isso mostra que o  **Modelo 1:1 tem um custo de *overhead* (sobrecarga) que cresce junto com o número de threads** , enquanto o Modelo N:M tem um custo de overhead quase fixo e muito baixo.


### 2. Análise por Carga

* **Carga Baixa (10 Tarefas):** O Modelo 1:1 (61ms) foi ligeiramente *mais rápido* que o N:M (76ms). O modelo N:M (Virtual Threads) tem um pequeno custo inicial de gerenciamento que, para pouquíssimas tarefas, o torna um pouco mais lento.
* **Ponto de Equilíbrio (100 Tarefas):** Com 100 tarefas, os modelos empataram (67ms). Pelo menos em meus testes, onde calculei para cada tarefa três execuções.
* **Carga Alta (500 e 1000 Tarefas):** O modelo N:M se sobressai.
  * Para 500 tarefas, o N:M (73ms) já era mais rápido que o 1:1 (85ms).
  * Para 1000 tarefas, a diferença é clara: 86ms (N:M) contra 108ms (1:1).
  * **Por quê?** No modelo 1:1, ao criar 1000 threads pedimos ao Sistema Operacional para criar e agendar 1000 threads. O S.O. gasta tempo e memória para *gerenciar* essas threads. No modelo N:M, a JVM cria 1000 objetos "leves" (Virtual Threads) e os executa usando um pool de threads de sistema, de forma muito mais eficiente.

### Conclusão Final

A atividade demonstra que o **Modelo 1:1 (Threads de Plataforma) não é escalável** para um grande número de tarefas concorrentes. O custo de criação e gerenciamento de threads do sistema operacional se torna o gargalo.

O **Modelo N:M (simulado com Threads Virtuais) é a solução moderna** para alta concorrência. Ele desacopla a "tarefa" da "thread do sistema", permitindo que a aplicação gerencie milhares de tarefas com desempenho estável e baixo uso de recursos.
