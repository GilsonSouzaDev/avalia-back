INSERT INTO professores (nome, email, senha, coordenador)
VALUES
('Gilson Souza', 'gilson@fatec.edu.br', '1234', TRUE),
('Maria Oliveira', 'maria@fatec.edu.br', 'abcd', FALSE);

INSERT INTO disciplinas (nome)
VALUES
('Programação Web'),
('Banco de Dados'),
('Engenharia de Software');

INSERT INTO professor_disciplina (professor_id, disciplina_id)
VALUES (1, 1), (1, 2);

INSERT INTO professor_disciplina (professor_id, disciplina_id)
VALUES (2, 3);

INSERT INTO perguntas (conteudo, professor_id, disciplina_id)
VALUES
('O que é o padrão MVC e qual sua importância no desenvolvimento web?', 1, 1),
('Explique o conceito de normalização em bancos de dados relacionais.', 1, 2),
('Quais são as principais etapas do processo de desenvolvimento de software?', 2, 3);

INSERT INTO alternativas (texto, pergunta_id) VALUES
('É um padrão que separa a aplicação em Modelo, Visão e Controle.', 1),
('É uma linguagem de programação orientada a objetos.', 1),
('É um framework específico do Java.', 1),
('Serve apenas para bancos de dados.', 1);

-- Alternativas da Pergunta 2
INSERT INTO alternativas (texto, pergunta_id) VALUES
('Normalização é o processo de organizar os dados para reduzir redundâncias.', 2),
('É um tipo de índice de banco de dados.', 2),
('Refere-se à criação de triggers e procedures.', 2),
('É um modelo de diagrama de casos de uso.', 2);

-- Alternativas da Pergunta 3
INSERT INTO alternativas (texto, pergunta_id) VALUES
('Levantamento de requisitos, análise, design, implementação e testes.', 3),
('Planejamento, execução e manutenção de hardware.', 3),
('Criação de wireframes e testes automatizados.', 3),
('Gerenciamento de usuários e segurança de rede.', 3);