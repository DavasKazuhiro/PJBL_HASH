# Projeto PJBL – Tabelas Hash

Este projeto tem como objetivo comparar o desempenho de diferentes funções hash na inserção e busca de dados, utilizando listas encadeadas e vetores para tratamento de colisões.
O trabalho faz parte da disciplina de Resolução de Problemas Estruturados em Computação ministrada pelo professor Andrey Cabral Meira e busca demonstrar o impacto de diferentes estratégias de hash na eficiência de uma tabela.

## Estrutura do Projeto

Main.java – Classe principal que executa os testes com diferentes tamanhos de tabelas e funções hash.
Divisao.java – Implementa a função hash por resto da divisão.
MultiplicacaoKnuth.java – Implementa a função hash pelo método da multiplicação de Knuth.
Rehash.java – Implementa a estratégia de rehashing para tratamento de colisões.
TabelaHash.java – Classe base com estrutura e métodos comuns das tabelas hash.
ListaEncadeada.java – Estrutura usada para resolver colisões por encadeamento.
No.java – Representa cada nó da lista encadeada.
Resultado.java – Registra e organiza os dados de tempo, colisões e métricas de desempenho.
Teste.java – Classe auxiliar usada para validação e depuração das funções hash.

## Como executar 

##Métodos de Hash Implementados 

- Método de resto da divisão com primo grande
- Método da Multiplicação de Knuth
- Método

##Métricas e Análise de Desempenho

O programa mede automaticamente: Número total de colisões, Tempo total de inserção, Tempo total de busca, Tamanho das três maiores listas encadeadas, Distância média, mínima e máxima entre posições ocupadas (gaps).
Esses dados são salvos em arquivos dentro da pasta “resultados”.

##Conclusão

##Autores

Davi Kazuhiro Natume
Pedro Henrique Valente Favero
Lucas Antonio Pelanda

Disciplina: Estrutura de Dados
Professor: Resolução de Problemas Estruturados em Computação
Instituição: PUCpr
