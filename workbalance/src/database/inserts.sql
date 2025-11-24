-- Inserts de exemplo para o WorkBalance Hub
-- Utilizando APENAS as procedures de insert (exigência da disciplina)

DECLARE
  v_eq1 NUMBER; v_eq2 NUMBER; v_eq3 NUMBER; v_eq4 NUMBER; v_eq5 NUMBER;
  v_eq6 NUMBER; v_eq7 NUMBER; v_eq8 NUMBER; v_eq9 NUMBER; v_eq10 NUMBER;

  v_u1 NUMBER; v_u2 NUMBER; v_u3 NUMBER; v_u4 NUMBER; v_u5 NUMBER;
  v_u6 NUMBER; v_u7 NUMBER; v_u8 NUMBER; v_u9 NUMBER; v_u10 NUMBER;

  v_est1 NUMBER; v_est2 NUMBER; v_est3 NUMBER; v_est4 NUMBER; v_est5 NUMBER;
  v_est6 NUMBER; v_est7 NUMBER; v_est8 NUMBER; v_est9 NUMBER; v_est10 NUMBER;

  v_pl1 NUMBER; v_pl2 NUMBER; v_pl3 NUMBER; v_pl4 NUMBER; v_pl5 NUMBER;
  v_pl6 NUMBER; v_pl7 NUMBER; v_pl8 NUMBER; v_pl9 NUMBER; v_pl10 NUMBER;

  v_ck  NUMBER;
  v_le  NUMBER;
BEGIN
  ----------------------------------------------------------
  -- 1) EQUIPES (10 registros)
  ----------------------------------------------------------
  pr_equipe_ins(v_eq1, 'Equipe Alfa',     'Tecnologia');
  pr_equipe_ins(v_eq2, 'Equipe Beta',     'Recursos Humanos');
  pr_equipe_ins(v_eq3, 'Equipe Gamma',    'Operações');
  pr_equipe_ins(v_eq4, 'Equipe Delta',    'Financeiro');
  pr_equipe_ins(v_eq5, 'Equipe Épsilon',  'Marketing');
  pr_equipe_ins(v_eq6, 'Equipe Zeta',     'Comercial');
  pr_equipe_ins(v_eq7, 'Equipe Theta',    'Atendimento');
  pr_equipe_ins(v_eq8, 'Equipe Lambda',   'Suporte');
  pr_equipe_ins(v_eq9, 'Equipe Sigma',    'Projetos');
  pr_equipe_ins(v_eq10,'Equipe Ômega',    'Diretoria');

  ----------------------------------------------------------
  -- 2) USUÁRIOS (10 registros, vinculados às equipes)
  ----------------------------------------------------------
  pr_usuario_ins(v_u1,  'Ana Silva',      'ana.alfa@empresa.com',   'hash1', 'COLABORADOR', v_eq1);
  pr_usuario_ins(v_u2,  'Bruno Costa',    'bruno.alfa@empresa.com', 'hash2', 'COLABORADOR', v_eq1);
  pr_usuario_ins(v_u3,  'Carla Souza',    'carla.beta@empresa.com', 'hash3', 'RH',          v_eq2);
  pr_usuario_ins(v_u4,  'Daniel Lima',    'daniel.gamma@empresa.com','hash4','COLABORADOR', v_eq3);
  pr_usuario_ins(v_u5,  'Eduarda Rocha',  'duda.delta@empresa.com', 'hash5', 'GESTOR',      v_eq4);
  pr_usuario_ins(v_u6,  'Felipe Santos',  'felipe.epsilon@empresa.com','hash6','COLABORADOR',v_eq5);
  pr_usuario_ins(v_u7,  'Gabriela Melo',  'gabi.zeta@empresa.com',  'hash7', 'COLABORADOR', v_eq6);
  pr_usuario_ins(v_u8,  'Henrique Alves', 'henrique.theta@empresa.com','hash8','COLABORADOR',v_eq7);
  pr_usuario_ins(v_u9,  'Isabela Nunes',  'isa.lambda@empresa.com', 'hash9', 'COLABORADOR', v_eq8);
  pr_usuario_ins(v_u10, 'João Pedro',     'joao.sigma@empresa.com', 'hash10','GESTOR',      v_eq9);

  ----------------------------------------------------------
  -- 3) ESTAÇÕES DE TRABALHO (10 registros)
  ----------------------------------------------------------
  pr_estacao_ins(v_est1,  'Estação 01', 'Open space - 3º andar');
  pr_estacao_ins(v_est2,  'Estação 02', 'Open space - 3º andar');
  pr_estacao_ins(v_est3,  'Estação 03', 'Sala de Reunião A');
  pr_estacao_ins(v_est4,  'Estação 04', 'Sala de Reunião B');
  pr_estacao_ins(v_est5,  'Estação 05', 'Home Office - Ana');
  pr_estacao_ins(v_est6,  'Estação 06', 'Home Office - Bruno');
  pr_estacao_ins(v_est7,  'Estação 07', 'Operações - Galpão');
  pr_estacao_ins(v_est8,  'Estação 08', 'Financeiro - 4º andar');
  pr_estacao_ins(v_est9,  'Estação 09', 'Suporte - 2º andar');
  pr_estacao_ins(v_est10, 'Estação 10', 'Diretoria - 5º andar');

  ----------------------------------------------------------
  -- 4) PLANOS DE AÇÃO (10 registros, ligados às equipes)
  ----------------------------------------------------------
  pr_plano_ins(v_pl1,  v_eq1, 'Campanha de pausas ativas',
               DATE '2025-10-01', DATE '2025-10-15', 'PLANEJADO');
  pr_plano_ins(v_pl2,  v_eq2, 'Roda de conversa sobre saúde mental',
               DATE '2025-10-05', DATE '2025-10-20', 'PLANEJADO');
  pr_plano_ins(v_pl3,  v_eq3, 'Reorganização de turnos de operação',
               DATE '2025-09-15', DATE '2025-10-15', 'EM_ANDAMENTO');
  pr_plano_ins(v_pl4,  v_eq4, 'Avaliação ergonômica das estações',
               DATE '2025-09-01', DATE '2025-09-30', 'CONCLUIDO');
  pr_plano_ins(v_pl5,  v_eq5, 'Programa de alongamento guiado',
               DATE '2025-11-01', DATE '2025-11-30', 'PLANEJADO');
  pr_plano_ins(v_pl6,  v_eq6, 'Comunicação interna sobre o uso do app',
               DATE '2025-09-10', DATE '2025-09-25', 'CONCLUIDO');
  pr_plano_ins(v_pl7,  v_eq7, 'Redução de ruído no atendimento',
               DATE '2025-10-10', DATE '2025-11-10', 'EM_ANDAMENTO');
  pr_plano_ins(v_pl8,  v_eq8, 'Revisão da carga de chamados',
               DATE '2025-08-01', DATE '2025-09-01', 'CONCLUIDO');
  pr_plano_ins(v_pl9,  v_eq9, 'Treinamento de líderes em saúde mental',
               DATE '2025-11-05', DATE '2025-11-25', 'PLANEJADO');
  pr_plano_ins(v_pl10, v_eq10,'Semana de bem-estar na diretoria',
               DATE '2025-12-01', DATE '2025-12-07', 'PLANEJADO');

  ----------------------------------------------------------
  -- 5) CHECK-INS (10 registros distribuídos entre usuários)
  ----------------------------------------------------------
  pr_checkin_ins(v_ck, v_u1, 4, 5, 8, 'leve dor de cabeça', 'Dia intenso, mas ok');
  pr_checkin_ins(v_ck, v_u2, 3, 6, 7, 'cansaço',            'Muitas reuniões seguidas');
  pr_checkin_ins(v_ck, v_u3, 2, 8, 6, 'tensão muscular',    'Situação difícil no time');
  pr_checkin_ins(v_ck, v_u4, 5, 3, 9, NULL,                 'Me sentindo muito bem hoje');
  pr_checkin_ins(v_ck, v_u5, 1, 9, 4, 'insônia',            'Semana muito puxada');
  pr_checkin_ins(v_ck, v_u6, 3, 5, 7, 'cansaço',            'Volume médio de trabalho');
  pr_checkin_ins(v_ck, v_u7, 4, 4, 8, NULL,                 'Tudo sob controle');
  pr_checkin_ins(v_ck, v_u8, 2, 7, 5, 'dores nas costas',   'Preciso de ajuste de cadeira');
  pr_checkin_ins(v_ck, v_u9, 5, 2, 9, NULL,                 'Boa noite de sono, dia tranquilo');
  pr_checkin_ins(v_ck, v_u10,3, 6, 6, 'ansiedade leve',     'Preocupado com prazos');

  ----------------------------------------------------------
  -- 6) LEITURAS DE AMBIENTE (10 registros em estações variadas)
  ----------------------------------------------------------
  pr_leitura_ins(v_le, v_est1,  23.5, 45.0, 320.0);
  pr_leitura_ins(v_le, v_est1,  24.0, 50.0, 310.0);
  pr_leitura_ins(v_le, v_est2,  25.5, 60.0, 400.0);
  pr_leitura_ins(v_le, v_est3,  22.0, 40.0, 350.0);
  pr_leitura_ins(v_le, v_est4,  26.5, 70.0, 280.0);
  pr_leitura_ins(v_le, v_est5,  24.5, 35.0, 300.0);
  pr_leitura_ins(v_le, v_est6,  25.0, 55.0, 290.0);
  pr_leitura_ins(v_le, v_est7,  27.0, 75.0, 260.0);
  pr_leitura_ins(v_le, v_est8,  21.5, 38.0, 330.0);
  pr_leitura_ins(v_le, v_est9,  23.0, 48.0, 310.0);

  COMMIT;
END;
/
