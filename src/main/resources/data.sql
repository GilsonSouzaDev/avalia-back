-- ================================================
-- POPULAÇÃO INICIAL DO BANCO DE DADOS - AVALIA
-- (Script corrigido e alinhado com as Entidades Java)
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
(1, 1), -- Gilson -> Programação Web
(1, 2), -- Gilson -> Banco de Dados
(2, 3); -- Maria -> Engenharia de Software

-- 4️⃣ INSERINDO PERGUNTAS
-- ⚠️ CORREÇÃO: Coluna 'conteudo' renomeada para 'enunciado'
INSERT INTO perguntas (enunciado, professor_id, disciplina_id)
VALUES
('O que é o padrão MVC e qual sua importância no desenvolvimento web?', 1, 1),
('Explique o conceito de normalização em bancos de dados relacionais.', 1, 2),
('Quais são as principais etapas do processo de desenvolvimento de software?', 2, 3);

-- 5️⃣ INSERINDO ALTERNATIVAS
-- ⚠️ CORREÇÃO: Adicionada a coluna 'correta' (boolean)
-- Pergunta 1 (MVC)
INSERT INTO alternativas (texto, pergunta_id, correta) VALUES
('É um padrão que separa a aplicação em Modelo, Visão e Controle.', 1, true),
('É uma linguagem de programação orientada a objetos.', 1, false),
('É um framework específico do Java.', 1, false),
('Serve apenas para bancos de dados.', 1, false);

-- Pergunta 2 (Normalização)
INSERT INTO alternativas (texto, pergunta_id, correta) VALUES
('Normalização é o processo de organizar os dados para reduzir redundâncias.', 2, true),
('É um tipo de índice de banco de dados.', 2, false),
('Refere-se à criação de triggers e procedures.', 2, false),
('É um modelo de diagrama de casos de uso.', 2, false);

-- Pergunta 3 (Etapas SW)
INSERT INTO alternativas (texto, pergunta_id, correta) VALUES
('Levantamento de requisitos, análise, design, implementação e testes.', 3, true),
('Planejamento, execução e manutenção de hardware.', 3, false),
('Criação de wireframes e testes automatizados.', 3, false),
('Gerenciamento de usuários e segurança de rede.', 3, false);