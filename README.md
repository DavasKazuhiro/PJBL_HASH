# Projeto PJBL – Tabelas Hash

Este projeto tem como objetivo comparar o desempenho de diferentes funções hash na inserção e busca de dados, utilizando listas encadeadas e vetores para tratamento de colisões.
O trabalho faz parte da disciplina de Resolução de Problemas Estruturados em Computação ministrada pelo professor Andrey Cabral Meira e busca demonstrar o impacto de diferentes estratégias de hash na eficiência de uma tabela.

## Como executar 

1 - Clonar o repositório em um caminho que prefirir em sua pasta de arquivos

2 - Abrir o repositorio em um editor de código

3 - Entrar por linha de comando na pasta Comentado ou NaoComentado
```java
cd Comentado
```
4 - Compilar o arquivo Main
```java
javac Main.java
```
5 - Assim que compilar ira criar um arquivo de classe para cada arquivo .java

6 - Dpois de compilado rodar o código
```java
java Main.java
```

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
Com base nos resultados armazenados no arquivo csv presente neste repositório, foi possível fazer as anáises para os seguintes dados:

### Tempo de Inserção

<img width="1419" height="948" alt="image" src="https://github.com/user-attachments/assets/ef343963-9a67-4148-b506-a39caff654b2" />

<img width="1388" height="948" alt="image" src="https://github.com/user-attachments/assets/da5c8e7a-04ab-4f1c-bf94-90131ce44a00" />

<img width="1388" height="948" alt="image" src="https://github.com/user-attachments/assets/cc9e953c-96b1-4505-a0ad-3b2e21c80141" />

### Número de Colisões

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/96943117-8c26-405b-a5a4-23d798785dbc" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/b3b30afc-26b6-4e14-b20d-353e6d95e2f4" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/b1840965-9f6f-4bdd-bb21-aebca3e2a8e3" />

### Tempo de Busca

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/e15a19ed-df74-436a-a4ab-51861f8dfa45" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/d5dfa521-5b10-4654-a93d-635ee849a34e" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/32af4f41-d288-45ad-97f1-c702d6ab949f" />

## Média de Gaps

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/a069994a-e928-405e-b923-092f42161098" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/fe09d918-42cd-446a-92be-f3e56d7a9f08" />

<img width="1980" height="1180" alt="image" src="https://github.com/user-attachments/assets/c7c7b2f4-de89-4a3a-af3e-3d863f1a7882" />


## Quem foi melhor e por quê

Depois de rodar todos os testes com as três funções, Resto da Divisão, Multiplicação de Knuth e Hash Duplo, deu pra ver com clareza que o Hash Duplo foi o que se saiu melhor no geral.

Ele teve o menor número de colisões, principalmente nas tabelas maiores, e manteve uma boa distribuição dos valores. Isso acontece porque ele usa duas funções diferentes pra calcular as posições, o que evita aquele acúmulo de dados em poucos lugares da tabela.
Resumindo: o Hash Duplo espalha bem melhor as chaves, e isso faz diferença quando a quantidade de dados cresce muito.

A Multiplicação de Knuth também foi muito bem. Ela é simples, tem um comportamento estável e uma boa dispersão. É uma função bem equilibrada, nem a mais rápida, mas tambem não é a mais complexa, e entrega resultados bons.

Já o método do Resto da Divisão, acabou gerando mais colisões quando a tabela ficou cheia. Isso acontece porque ele depende bastante do tamanho do vetor e do tipo dos números usados, então em volumes grandes ele começa a perder eficiência.
## Conclusão

Com a realização dos testes, foi possível observar de forma clara que o desempenho das funções de hash varia bastante conforme o método utilizado e o volume de dados processados. O Hash Duplo apresentou o melhor resultado geral, mostrando alta eficiência e uma excelente distribuição das chaves, mesmo em tabelas grandes. Isso demonstra que o uso de duas funções distintas reduz significativamente as colisões e melhora a dispersão dos valores.

A função de Multiplicação de Knuth também apresentou resultados positivos, sendo uma boa alternativa por equilibrar simplicidade e desempenho. Sua implementação é prática e mantém um comportamento satizfatorio.

Por outro lado, o método do Resto da Divisão, apesar de ser o mais simples e intuitivo, mostrou algumas dificuldades em grandes volumes de dados, resultando em um número mais elevado de colisões. Isso destaca a importância de considerar bastante o tamanho e as características do conjunto de dados antes de definir a função de hash a ser utilizada.

Resumeindo, o trabalho mostrou que não há uma função de hash ideal, mas sim métodos que se adequam melhor a diferentes contextos. O Hash Duplo se destacou pela eficiência e distribuição, a Multiplicação de Knuth pela estabilidade e praticidade, e o Resto da Divisão pela simplicidade.

Então vemos a importância de compreender os fundamentos por trás das funções de hash, pois a escolha correta do método impacta diretamente na performance e na confiabilidade das estruturas de dados.

## Autores

Davi Kazuhiro Natume

Pedro Henrique Valente Favero

Lucas Antonio Pelanda

Disciplina: Resolução de Problemas Estruturados em Computação

Professor: Andrey Cabral Meira

Instituição: PUCpr

