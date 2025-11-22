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
-- 2. PROFESSORES
-- ============================================================
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (1, 'Prof. Mário Silva', 'mario.silva@fatec.sp.gov.br', '123456', 'COORDENADOR', 1001);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (2, 'Profa. Ana Clara', 'ana.clara@fatec.sp.gov.br', '123456', 'PROFESSOR', 1002);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (3, 'Prof. Carlos Eduardo', 'carlos.edu@fatec.sp.gov.br', '123456', 'PROFESSOR', 1003);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (4, 'Profa. Fernanda Lima', 'fernanda.lima@fatec.sp.gov.br', '123456', 'PROFESSOR', 1004);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (5, 'Prof. Ricardo Mendes', 'ricardo.mendes@fatec.sp.gov.br', '123456', 'PROFESSOR', 1005);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (6, 'Profa. Juliana Costa', 'juliana.costa@fatec.sp.gov.br', '123456', 'PROFESSOR', 1006);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (7, 'Prof. Paulo Souza', 'paulo.souza@fatec.sp.gov.br', '123456', 'PROFESSOR', 1007);
INSERT INTO professores (id, nome, email, senha, perfil_professor, codigo) VALUES (8, 'Prof. Roberto Almeida', 'roberto.almeida@fatec.sp.gov.br', '123456', 'PROFESSOR', 1008);

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
-- 4. PERGUNTAS
-- ============================================================
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (1, 'No contexto de Java, qual palavra reservada é utilizada para garantir que uma classe herde comportamentos de outra classe?', 1002, 1);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (2, 'Qual pilar da POO permite esconder os detalhes internos de implementação?', 1002, 1);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (3, 'O que acontece se tentarmos instanciar uma classe abstrata em Java?', 1002, 1);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (4, 'Qual constraint garante a unicidade de um registro em uma tabela?', 1003, 2);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (5, 'Qual comando SQL altera a estrutura de uma tabela existente?', 1003, 2);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (6, 'Qual forma normal exige que todos os atributos não-chave dependam diretamente da chave primária inteira?', 1003, 2);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (7, 'No Scrum, quem prioriza o Backlog do Produto?', 1001, 3);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (8, 'Qual diagrama UML é mais usado para representar a interação entre ator e sistema?', 1001, 3);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (9, 'Qual tag HTML é utilizada para criar um hiperlink?', 1004, 6);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (10, 'No CSS, qual seletor é representado por um ponto (.)?', 1004, 6);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (11, 'Qual verbo HTTP é semanticamente correto para atualizar um recurso existente?', 1008, 7);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (12, 'Em uma API REST, qual código de status indica "Não Autorizado"?', 1008, 7);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (13, 'Qual dos pilares da segurança garante que a informação não foi alterada indevidamente?', 1005, 8);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (14, 'O que é um ataque de Phishing?', 1005, 8);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (15, 'Em qual camada do modelo OSI opera o protocolo IP?', 1005, 9);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (16, 'Qual a principal diferença entre TCP e UDP?', 1005, 9);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (17, 'Qual linguagem é considerada oficial e nativa para desenvolvimento Android moderno?', 1004, 10);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (18, 'O que é o arquivo AndroidManifest.xml?', 1004, 10);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (19, 'Qual estrutura de dados segue o princípio LIFO (Last In, First Out)?', 1007, 4);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (20, 'Qual a complexidade média de busca em uma árvore binária balanceada?', 1007, 4);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (21, 'O que significa a sigla MVP em gestão de projetos ágeis?', 1006, 12);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (22, 'Qual ferramenta é comumente usada para visualizar o fluxo de trabalho em colunas (A fazer, Fazendo, Feito)?', 1006, 12);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (23, 'Qual é a função do Kernel em um sistema operacional?', 1003, 5);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (24, 'O que é um Deadlock?', 1003, 5);
INSERT INTO perguntas (id, enunciado, codigo_professor, disciplina_id) VALUES (25, 'Na terminologia técnica, o que significa "Bug"?', 1006, 15);

-- ============================================================
-- 5. ALTERNATIVAS
-- ============================================================
-- P1
INSERT INTO alternativas (texto, pergunta_id) VALUES ('implements', 1);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('extends', 1);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('inherits', 1);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('instanceof', 1);
-- P2
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Polimorfismo', 2);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Herança', 2);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Encapsulamento', 2);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Abstração', 2);
-- P3
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Gera um erro de compilação', 3);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Cria um objeto vazio', 3);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Funciona normalmente', 3);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('O Java converte em Interface', 3);
-- P4
INSERT INTO alternativas (texto, pergunta_id) VALUES ('FOREIGN KEY', 4);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('CHECK', 4);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('PRIMARY KEY', 4);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('INDEX', 4);
-- P5
INSERT INTO alternativas (texto, pergunta_id) VALUES ('UPDATE TABLE', 5);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('CHANGE TABLE', 5);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('MODIFY TABLE', 5);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('ALTER TABLE', 5);
-- P6
INSERT INTO alternativas (texto, pergunta_id) VALUES ('1FN', 6);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('2FN', 6);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('3FN', 6);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('4FN', 6);
-- P7
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Scrum Master', 7);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Product Owner', 7);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Dev Team', 7);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Stakeholder', 7);
-- P8
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Diagrama de Classes', 8);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Diagrama de Caso de Uso', 8);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Diagrama de Sequência', 8);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Diagrama de Atividades', 8);
-- P9
INSERT INTO alternativas (texto, pergunta_id) VALUES ('<link>', 9);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('<a>', 9);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('<href>', 9);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('<url>', 9);
-- P10
INSERT INTO alternativas (texto, pergunta_id) VALUES ('ID', 10);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Tag', 10);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Classe', 10);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Universal', 10);
-- P11
INSERT INTO alternativas (texto, pergunta_id) VALUES ('GET', 11);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('POST', 11);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('PUT', 11);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('DELETE', 11);
-- P12
INSERT INTO alternativas (texto, pergunta_id) VALUES ('404', 12);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('500', 12);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('200', 12);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('401', 12);
-- P13
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Confidencialidade', 13);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Integridade', 13);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Disponibilidade', 13);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Autenticidade', 13);
-- P14
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Derrubar o servidor', 14);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Enganar o usuário para roubar dados', 14);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Interceptar Wi-Fi', 14);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Vírus que apaga arquivos', 14);
-- P15
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Camada 1 - Física', 15);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Camada 2 - Enlace', 15);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Camada 3 - Rede', 15);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Camada 4 - Transporte', 15);
-- P16
INSERT INTO alternativas (texto, pergunta_id) VALUES ('TCP é não confiável', 16);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('UDP garante a entrega', 16);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('TCP é orientado a conexão, UDP não', 16);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Não há diferença', 16);
-- P17
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Swift', 17);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Kotlin', 17);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Python', 17);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('C#', 17);
-- P18
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Define o layout da tela', 18);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Contém o código Java', 18);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Arquivo de configuração essencial do app', 18);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Banco de dados local', 18);
-- P19
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Fila (Queue)', 19);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Pilha (Stack)', 19);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Lista Encadeada', 19);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Árvore', 19);
-- P20
INSERT INTO alternativas (texto, pergunta_id) VALUES ('O(1)', 20);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('O(n)', 20);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('O(log n)', 20);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('O(n^2)', 20);
-- P21
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Most Valuable Player', 21);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Minimum Viable Product', 21);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Maximum Value Project', 21);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Minimum Verified Process', 21);
-- P22
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Scrum', 22);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('XP', 22);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Kanban', 22);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Waterfall', 22);
-- P23
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Interface Gráfica', 23);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Núcleo que gerencia hardware e recursos', 23);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Navegador de Internet', 23);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Editor de Texto', 23);
-- P24
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Tela azul da morte', 24);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Impasse onde dois processos esperam um pelo outro', 24);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Memória cheia', 24);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Disco corrompido', 24);
-- P25
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Um recurso novo', 25);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Uma falha ou defeito no código', 25);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Um vírus', 25);
INSERT INTO alternativas (texto, pergunta_id) VALUES ('Um componente de hardware', 25);

-- ============================================================
-- 6. CORREÇÃO DAS SEQUENCES (O PASSO VITAL)
-- ============================================================
-- Isso evita o erro: "Key (id)=(X) already exists"
-- Ajusta o contador de cada tabela para o maior ID existente

SELECT setval('disciplinas_id_seq', (SELECT MAX(id) FROM disciplinas));
SELECT setval('professores_id_seq', (SELECT MAX(id) FROM professores));
SELECT setval('perguntas_id_seq', (SELECT MAX(id) FROM perguntas));
SELECT setval('alternativas_id_seq', (SELECT MAX(id) FROM alternativas));

-- Fim do Script