-- ============================================================
-- 0. LIMPEZA TOTAL (RESETA O BANCO PARA ESTADO LIMPO)
-- ============================================================
TRUNCATE TABLE alternativas, perguntas, professor_disciplina, professores, disciplinas RESTART IDENTITY CASCADE;

-- ============================================================
-- 1. DISCIPLINAS
-- ============================================================
INSERT INTO disciplinas (id, nome, cor) VALUES (1, 'Programação Orientada a Objetos', '#3498db');
INSERT INTO disciplinas (id, nome, cor) VALUES (2, 'Banco de Dados Relacional', '#e74c3c');
INSERT INTO disciplinas (id, nome, cor) VALUES (3, 'Engenharia de Software III', '#2ecc71');
INSERT INTO disciplinas (id, nome, cor) VALUES (4, 'Estrutura de Dados', '#9b59b6');
INSERT INTO disciplinas (id, nome, cor) VALUES (5, 'Sistemas Operacionais', '#34495e');
INSERT INTO disciplinas (id, nome, cor) VALUES (6, 'Desenvolvimento Web I (Frontend)', '#f1c40f');
INSERT INTO disciplinas (id, nome, cor) VALUES (7, 'Desenvolvimento Web II (Backend)', '#e67e22');
INSERT INTO disciplinas (id, nome, cor) VALUES (8, 'Segurança da Informação', '#2c3e50');
INSERT INTO disciplinas (id, nome, cor) VALUES (9, 'Redes de Computadores', '#16a085');
INSERT INTO disciplinas (id, nome, cor) VALUES (10, 'Programação para Dispositivos Móveis', '#8e44ad');
INSERT INTO disciplinas (id, nome, cor) VALUES (11, 'Interação Humano-Computador', '#ff7979');
INSERT INTO disciplinas (id, nome, cor) VALUES (12, 'Gestão de Projetos', '#95a5a6');
INSERT INTO disciplinas (id, nome, cor) VALUES (13, 'Matemática Discreta', '#badc58');
INSERT INTO disciplinas (id, nome, cor) VALUES (14, 'Sistemas Distribuídos', '#d35400');
INSERT INTO disciplinas (id, nome, cor) VALUES (15, 'Inglês Técnico', '#4834d4');

-- ============================================================
-- 2. PROFESSORES (Removido campo codigo, focado apenas no ID)
-- ============================================================
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (1, 'Prof. Mário Silva', 'mario.silva@fatec.sp.gov.br', '123456', 'COORDENADOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (2, 'Profa. Ana Clara', 'ana.clara@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (3, 'Prof. Carlos Eduardo', 'carlos.edu@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (4, 'Profa. Fernanda Lima', 'fernanda.lima@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (5, 'Prof. Ricardo Mendes', 'ricardo.mendes@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (6, 'Profa. Juliana Costa', 'juliana.costa@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (7, 'Prof. Paulo Souza', 'paulo.souza@fatec.sp.gov.br', '123456', 'PROFESSOR');
INSERT INTO professores (id, nome, email, senha, perfil_professor) VALUES (8, 'Prof. Roberto Almeida', 'roberto.almeida@fatec.sp.gov.br', '123456', 'PROFESSOR');

-- ============================================================
-- 3. VINCULOS PROFESSOR_DISCIPLINA
-- ============================================================
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (1, 3);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (2, 1);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (2, 4);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (3, 2);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (3, 5);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (4, 6);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (4, 10);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (4, 11);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (5, 8);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (5, 9);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (5, 14);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (6, 12);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (6, 15);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (7, 13);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (7, 4);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (8, 7);
INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (8, 1);

-- ============================================================
-- 4. PERGUNTAS (Adicionado coluna imagem e usando professor_id)
-- ============================================================
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (1, 'No contexto de Java, qual palavra reservada é utilizada para garantir que uma classe herde comportamentos de outra classe?', NULL, 2, 1);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (2, 'Qual pilar da POO permite esconder os detalhes internos de implementação?', NULL, 2, 1);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (3, 'O que acontece se tentarmos instanciar uma classe abstrata em Java?', NULL, 2, 1);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (4, 'Qual constraint garante a unicidade de um registro em uma tabela?', NULL, 3, 2);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (5, 'Qual comando SQL altera a estrutura de uma tabela existente?', NULL, 3, 2);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (6, 'Qual forma normal exige que todos os atributos não-chave dependam diretamente da chave primária inteira?', NULL, 3, 2);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (7, 'No Scrum, quem prioriza o Backlog do Produto?', NULL, 1, 3);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (8, 'Qual diagrama UML é mais usado para representar a interação entre ator e sistema?', NULL, 1, 3);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (9, 'Qual tag HTML é utilizada para criar um hiperlink?', NULL, 4, 6);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (10, 'No CSS, qual seletor é representado por um ponto (.)?', NULL, 4, 6);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (11, 'Qual verbo HTTP é semanticamente correto para atualizar um recurso existente?', NULL, 8, 7);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (12, 'Em uma API REST, qual código de status indica "Não Autorizado"?', NULL, 8, 7);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (13, 'Qual dos pilares da segurança garante que a informação não foi alterada indevidamente?', NULL, 5, 8);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (14, 'O que é um ataque de Phishing?', NULL, 5, 8);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (15, 'Em qual camada do modelo OSI opera o protocolo IP?', NULL, 5, 9);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (16, 'Qual a principal diferença entre TCP e UDP?', NULL, 5, 9);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (17, 'Qual linguagem é considerada oficial e nativa para desenvolvimento Android moderno?', NULL, 4, 10);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (18, 'O que é o arquivo AndroidManifest.xml?', NULL, 4, 10);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (19, 'Qual estrutura de dados segue o princípio LIFO (Last In, First Out)?', NULL, 7, 4);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (20, 'Qual a complexidade média de busca em uma árvore binária balanceada?', NULL, 7, 4);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (21, 'O que significa a sigla MVP em gestão de projetos ágeis?', NULL, 6, 12);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (22, 'Qual ferramenta é comumente usada para visualizar o fluxo de trabalho em colunas (A fazer, Fazendo, Feito)?', NULL, 6, 12);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (23, 'Qual é a função do Kernel em um sistema operacional?', NULL, 3, 5);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (24, 'O que é um Deadlock?', NULL, 3, 5);
INSERT INTO perguntas (id, enunciado, imagem, professor_id, disciplina_id) VALUES (25, 'Na terminologia técnica, o que significa "Bug"?', NULL, 6, 15);

-- ============================================================
-- 5. ALTERNATIVAS (ATUALIZADO COM COLUMN correta)
-- ============================================================
-- P1 (Herança em Java)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('implements', false, 1);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('extends', true, 1);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('inherits', false, 1);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('instanceof', false, 1);

-- P2 (Esconder detalhes)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Polimorfismo', false, 2);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Herança', false, 2);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Encapsulamento', true, 2);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Abstração', false, 2);

-- P3 (Instanciar classe abstrata)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Gera um erro de compilação', true, 3);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Cria um objeto vazio', false, 3);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Funciona normalmente', false, 3);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('O Java converte em Interface', false, 3);

-- P4 (Unicidade SQL)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('FOREIGN KEY', false, 4);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('CHECK', false, 4);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('PRIMARY KEY', true, 4);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('INDEX', false, 4);

-- P5 (Alterar estrutura tabela)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('UPDATE TABLE', false, 5);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('CHANGE TABLE', false, 5);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('MODIFY TABLE', false, 5);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('ALTER TABLE', true, 5);

-- P6 (Forma normal dependência chave inteira)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('1FN', false, 6);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('2FN', true, 6);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('3FN', false, 6);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('4FN', false, 6);

-- P7 (Scrum Prioriza Backlog)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Scrum Master', false, 7);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Product Owner', true, 7);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Dev Team', false, 7);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Stakeholder', false, 7);

-- P8 (UML Ator e Sistema)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Diagrama de Classes', false, 8);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Diagrama de Caso de Uso', true, 8);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Diagrama de Sequência', false, 8);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Diagrama de Atividades', false, 8);

-- P9 (HTML Hiperlink)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('<link>', false, 9);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('<a>', true, 9);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('<href>', false, 9);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('<url>', false, 9);

-- P10 (CSS Ponto)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('ID', false, 10);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Tag', false, 10);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Classe', true, 10);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Universal', false, 10);

-- P11 (HTTP Atualizar)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('GET', false, 11);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('POST', false, 11);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('PUT', true, 11);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('DELETE', false, 11);

-- P12 (REST Não Autorizado)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('404', false, 12);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('500', false, 12);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('200', false, 12);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('401', true, 12);

-- P13 (Segurança não alterada)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Confidencialidade', false, 13);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Integridade', true, 13);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Disponibilidade', false, 13);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Autenticidade', false, 13);

-- P14 (Phishing)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Derrubar o servidor', false, 14);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Enganar o usuário para roubar dados', true, 14);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Interceptar Wi-Fi', false, 14);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Vírus que apaga arquivos', false, 14);

-- P15 (OSI IP)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Camada 1 - Física', false, 15);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Camada 2 - Enlace', false, 15);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Camada 3 - Rede', true, 15);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Camada 4 - Transporte', false, 15);

-- P16 (TCP vs UDP)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('TCP é não confiável', false, 16);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('UDP garante a entrega', false, 16);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('TCP é orientado a conexão, UDP não', true, 16);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Não há diferença', false, 16);

-- P17 (Android Nativo Moderno)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Swift', false, 17);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Kotlin', true, 17);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Python', false, 17);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('C#', false, 17);

-- P18 (AndroidManifest)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Define o layout da tela', false, 18);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Contém o código Java', false, 18);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Arquivo de configuração essencial do app', true, 18);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Banco de dados local', false, 18);

-- P19 (LIFO)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Fila (Queue)', false, 19);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Pilha (Stack)', true, 19);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Lista Encadeada', false, 19);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Árvore', false, 19);

-- P20 (Arvore Binaria)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('O(1)', false, 20);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('O(n)', false, 20);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('O(log n)', true, 20);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('O(n^2)', false, 20);

-- P21 (MVP)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Most Valuable Player', false, 21);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Minimum Viable Product', true, 21);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Maximum Value Project', false, 21);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Minimum Verified Process', false, 21);

-- P22 (Colunas fluxo)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Scrum', false, 22);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('XP', false, 22);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Kanban', true, 22);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Waterfall', false, 22);

-- P23 (Kernel)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Interface Gráfica', false, 23);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Núcleo que gerencia hardware e recursos', true, 23);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Navegador de Internet', false, 23);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Editor de Texto', false, 23);

-- P24 (Deadlock)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Tela azul da morte', false, 24);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Impasse onde dois processos esperam um pelo outro', true, 24);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Memória cheia', false, 24);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Disco corrompido', false, 24);

-- P25 (Bug)
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Um recurso novo', false, 25);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Uma falha ou defeito no código', true, 25);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Um vírus', false, 25);
INSERT INTO alternativas (texto, correta, pergunta_id) VALUES ('Um componente de hardware', false, 25);

-- ============================================================
-- 6. CORREÇÃO DAS SEQUENCES
-- ============================================================
SELECT setval('disciplinas_id_seq', (SELECT MAX(id) FROM disciplinas));
SELECT setval('professores_id_seq', (SELECT MAX(id) FROM professores));
SELECT setval('perguntas_id_seq', (SELECT MAX(id) FROM perguntas));
SELECT setval('alternativas_id_seq', (SELECT MAX(id) FROM alternativas));

-- final script