-- ================================================
-- POPULAÇÃO INICIAL DO BANCO DE DADOS - AVALIA
-- ================================================

-- 1️⃣ INSERINDO PROFESSORES
INSERT INTO professores (nome, email, senha, perfilProfessor)
VALUES
('Gilson Souza', 'gilson@fatec.edu.br', '1234', 'COORDENADOR'),
('Maria Oliveira', 'maria@fatec.edu.br', 'abcd', 'PROFESSOR');

-- 2️⃣ INSERINDO DISCIPLINAS
INSERT INTO disciplinas (nome)
VALUES
('Programação Web'),
('Banco de Dados'),
('Engenharia de Software');

-- 3️⃣ RELACIONAMENTO ENTRE PROFESSORES E DISCIPLINAS (TABELA MANY-TO-MANY)
INSERT INTO professor_disciplina (professor_id, disciplina_id)
VALUES 
(1, 1),
(1, 2),
(2, 3);

-- 4️⃣ INSERINDO PERGUNTAS
-- ⚠️ As colunas devem corresponder à sua entidade Pergunta (conteudo, professor_id, disciplina_id)
INSERT INTO perguntas (conteudo, professor_id, disciplina_id)
VALUES
('O que é o padrão MVC e qual sua importância no desenvolvimento web?', 1, 1),
('Explique o conceito de normalização em bancos de dados relacionais.', 1, 2),
('Quais são as principais etapas do processo de desenvolvimento de software?', 2, 3);

-- 5️⃣ INSERINDO ALTERNATIVAS
-- ⚠️ As colunas devem corresponder à sua entidade Alternativa (texto, pergunta_id)
-- Pergunta 1
INSERT INTO alternativas (texto, pergunta_id) VALUES
('É um padrão que separa a aplicação em Modelo, Visão e Controle.', 1),
('É uma linguagem de programação orientada a objetos.', 1),
('É um framework específico do Java.', 1),
('Serve apenas para bancos de dados.', 1);

-- Pergunta 2
INSERT INTO alternativas (texto, pergunta_id) VALUES
('Normalização é o processo de organizar os dados para reduzir redundâncias.', 2),
('É um tipo de índice de banco de dados.', 2),
('Refere-se à criação de triggers e procedures.', 2),
('É um modelo de diagrama de casos de uso.', 2);

-- Pergunta 3
INSERT INTO alternativas (texto, pergunta_id) VALUES
('Levantamento de requisitos, análise, design, implementação e testes.', 3),
('Planejamento, execução e manutenção de hardware.', 3),
('Criação de wireframes e testes automatizados.', 3),
('Gerenciamento de usuários e segurança de rede.', 3);
