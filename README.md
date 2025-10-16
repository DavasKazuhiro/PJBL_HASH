# Projeto PJBL – Tabelas Hash

Este projeto tem como objetivo comparar o desempenho de diferentes funções hash na inserção e busca de dados, utilizando listas encadeadas e vetores para tratamento de colisões.
O trabalho faz parte da disciplina de Resolução de Problemas Estruturados em Computação ministrada pelo professor Andrey Cabral Meira e busca demonstrar o impacto de diferentes estratégias de hash na eficiência de uma tabela.

## Funções Hash Implementadas 

### 1. Resto da Divisão com multiplicação por Primo Grande
Este método utiliza a fórmula:

h(k) = (k * m) / t

m é um número primo grande escolhido e t é o tamanho da tabela.

É o método mais simples e amplamente utilizado, servindo como referência base para comparação.

Espera-se que o uso de um primo grande como multiplicador reduza a ocorrência de padrões repetitivos e melhora a distribuição uniforme das chaves.

Permite observar claramente o impacto da relação entre tamanho da tabela e quantidade de elementos (load factor α = n/m) no desempenho, especialmente em termos de colisões.

É eficiente em tempo de execução, pois envolve apenas uma operação aritmética simples.

### 2. Multiplicação de Knuth

Proposto por Donald Knuth, usa a expressão:

h(k) = m * ((k * A) mod 1)

onde A é aproximadamente 0.6180339887

Esse método independe da escolha de m ser primo, o que o torna mais flexível.

A constante irracional garante uma boa dispersão dos bits significativos, evitando agrupamentos de chaves próximas.

É ideal para comparar com o método do resto, pois usa uma estratégia de hashing diferente, baseada em multiplicação e fração.

Permite avaliar o impacto do custo computacional ligeiramente maior (multiplicações e operações de ponto flutuante) frente à melhor distribuição esperada das chaves.
   
### 3. Hash Duplo

No hash duplo a posição de inserção é determinada por duas funções:

h(k, i) = (h1(k) + i * h2(k)) mod t

onde h1(k) é a primeira função de hash, geralmente simples, como o resto da divisão por um primo grande;

h2 (k) é uma segunda função de hash que gera um deslocamento (step size), normalmente garantindo que h2(k) é diferente de 0;

i representa o número de tentativas (iterações) em caso de colisões;

t é o tamanho da tabela.

O método de hash duplo foi escolhido por combinar duas funções de dispersão distintas, reduzindo significativamente a formação de colisões e agrupamentos comuns em métodos simples. Ele garante que cada chave siga uma sequência de sondagem única, melhorando a distribuição mesmo sob alta ocupação da tabela. Essa abordagem é ideal para avaliar o desempenho em grandes volumes de dados, especialmente nos vetores de 1000, 10000 e 100000 posições com conjuntos de até 10 milhões de elementos. Além disso, permite comparar o custo computacional adicional das duas funções com o ganho em eficiência nas operações de busca e inserção.


## Estrutura do Projeto

Main.java – Classe principal que executa os testes com diferentes tamanhos de tabelas, conjuntos e funções hash.

RestoDivisao.java – Implementa a função hash por resto da divisão.

MultiplicacaoKnuth.java – Implementa a função hash pelo método da multiplicação de Knuth.

Rehash.java – Implementa a estratégia de rehashing para tratamento de colisões.

TabelaHash.java – Classe base com estrutura e métodos comuns das tabelas hash.

ListaEncadeada.java – Estrutura usada para resolver colisões por encadeamento.

No.java – Representa cada nó da lista encadeada.

Resultado.java – Registra e organiza os dados de tempo, colisões e métricas de desempenho.

Teste.java – Classe auxiliar usada para validação e depuração das funções hash.

## Tamanhos usados 
Tabela (vetor da hash)

- 1000, 10000, 100000 (crescimento ×10).

Conjuntos de dados

- 100.000, 1.000.000, 10.000.000 registros.

Seed e formato dos dados

Gerador aleatorio com seed fixa para reprodutibilidade.

Cada elemento é um código inteiro de 9 dígitos (ex.: 123456789).
(No código usamos int; o gerador garante unicidade por conjunto.)

## Métricas e Análise de Desempenho

O programa mede automaticamente: Número total de colisões, Tempo total de inserção, Tempo total de busca, Tamanho das três maiores listas encadeadas, Distância média, mínima e máxima entre posições ocupadas (gaps).
Esses dados são salvos em arquivos dentro da pasta “resultados”.

## Conclusão

## Autores

Davi Kazuhiro Natume
Pedro Henrique Valente Favero
Lucas Antonio Pelanda

Disciplina: Estrutura de Dados
Professor: Resolução de Problemas Estruturados em Computação
Instituição: PUCpr
