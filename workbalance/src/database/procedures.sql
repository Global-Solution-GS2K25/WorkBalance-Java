------------------------------------------------------------
-- EQUIPE
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_equipe_ins(
  p_id   OUT NUMBER,
  p_nome IN  VARCHAR2,
  p_setor IN VARCHAR2
) AS
BEGIN
  INSERT INTO TB_EQUIPE(NOME, SETOR)
  VALUES (p_nome, p_setor)
  RETURNING ID INTO p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_equipe_upd(
  p_id    IN NUMBER,
  p_nome  IN VARCHAR2,
  p_setor IN VARCHAR2
) AS
  v_cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_cnt FROM TB_EQUIPE WHERE ID = p_id;
  IF v_cnt = 0 THEN
    RAISE_APPLICATION_ERROR(-21101,'Equipe não encontrada');
  END IF;

  UPDATE TB_EQUIPE
     SET NOME  = NVL(p_nome,NOME),
         SETOR = NVL(p_setor,SETOR)
   WHERE ID = p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_equipe_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_PLANO_ACAO WHERE EQUIPE_ID = p_id;
  UPDATE TB_USUARIO SET EQUIPE_ID = NULL WHERE EQUIPE_ID = p_id;
  DELETE FROM TB_EQUIPE WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21102,'Equipe não encontrada');
  END IF;
END;
/

------------------------------------------------------------
-- USUARIO
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_usuario_ins(
  p_id         OUT NUMBER,
  p_nome       IN  VARCHAR2,
  p_email      IN  VARCHAR2,
  p_senha_hash IN  VARCHAR2,
  p_role       IN  VARCHAR2,
  p_equipe_id  IN  NUMBER
) AS
BEGIN
  INSERT INTO TB_USUARIO(NOME,EMAIL,SENHA_HASH,ROLE,EQUIPE_ID,CRIADO_EM)
  VALUES (p_nome,p_email,p_senha_hash,p_role,p_equipe_id,SYSTIMESTAMP)
  RETURNING ID INTO p_id;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    RAISE_APPLICATION_ERROR(-21201, 'Email já cadastrado');
END;
/
CREATE OR REPLACE PROCEDURE pr_usuario_upd(
  p_id         IN NUMBER,
  p_nome       IN VARCHAR2,
  p_email      IN VARCHAR2,
  p_senha_hash IN VARCHAR2,
  p_role       IN VARCHAR2,
  p_equipe_id  IN NUMBER
) AS
  v_cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_cnt FROM TB_USUARIO WHERE ID = p_id;
  IF v_cnt = 0 THEN
    RAISE_APPLICATION_ERROR(-21202,'Usuário não encontrado');
  END IF;

  UPDATE TB_USUARIO
     SET NOME        = NVL(p_nome,NOME),
         EMAIL       = NVL(p_email,EMAIL),
         SENHA_HASH  = NVL(p_senha_hash,SENHA_HASH),
         ROLE        = NVL(p_role,ROLE),
         EQUIPE_ID   = NVL(p_equipe_id,EQUIPE_ID),
         ATUALIZADO_EM = SYSTIMESTAMP
   WHERE ID = p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_usuario_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_CHECKIN WHERE USUARIO_ID = p_id;
  DELETE FROM TB_USUARIO WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21203,'Usuário não encontrado');
  END IF;
END;
/

------------------------------------------------------------
-- CHECKIN
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_checkin_ins(
  p_id             OUT NUMBER,
  p_usuario_id     IN  NUMBER,
  p_humor          IN  NUMBER,
  p_nivel_estresse IN  NUMBER,
  p_qualidade_sono IN  NUMBER,
  p_sintomas       IN  VARCHAR2,
  p_observacoes    IN  VARCHAR2
) AS
  v_pode VARCHAR2(3);
BEGIN
  v_pode := fn_pode_fazer_checkin(p_usuario_id);
  IF v_pode = 'NAO' THEN
    RAISE_APPLICATION_ERROR(-21301,'Usuário já realizou check-in hoje');
  END IF;

  INSERT INTO TB_CHECKIN(
    USUARIO_ID, HUMOR, NIVEL_ESTRESSE, QUALIDADE_SONO,
    SINTOMAS_FISICOS, OBSERVACOES, DATA_HORA
  ) VALUES (
    p_usuario_id, p_humor, p_nivel_estresse, p_qualidade_sono,
    p_sintomas, p_observacoes, SYSTIMESTAMP
  )
  RETURNING ID INTO p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_checkin_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_CHECKIN WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21302,'Check-in não encontrado');
  END IF;
END;
/

------------------------------------------------------------
-- ESTACAO DE TRABALHO
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_estacao_ins(
  p_id          OUT NUMBER,
  p_nome        IN  VARCHAR2,
  p_localizacao IN  VARCHAR2
) AS
BEGIN
  INSERT INTO TB_ESTACAO_TRABALHO(NOME,LOCALIZACAO)
  VALUES (p_nome,p_localizacao)
  RETURNING ID INTO p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_estacao_upd(
  p_id          IN NUMBER,
  p_nome        IN VARCHAR2,
  p_localizacao IN VARCHAR2
) AS
  v_cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_cnt FROM TB_ESTACAO_TRABALHO WHERE ID = p_id;
  IF v_cnt = 0 THEN
    RAISE_APPLICATION_ERROR(-21401,'Estação não encontrada');
  END IF;

  UPDATE TB_ESTACAO_TRABALHO
     SET NOME        = NVL(p_nome,NOME),
         LOCALIZACAO = NVL(p_localizacao,LOCALIZACAO)
   WHERE ID = p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_estacao_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_LEITURA_AMBIENTE WHERE ESTACAO_ID = p_id;
  DELETE FROM TB_ESTACAO_TRABALHO WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21402,'Estação não encontrada');
  END IF;
END;
/

------------------------------------------------------------
-- LEITURA DE AMBIENTE
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_leitura_ins(
  p_id          OUT NUMBER,
  p_estacao_id  IN  NUMBER,
  p_temp        IN  NUMBER,
  p_ruido       IN  NUMBER,
  p_lux         IN  NUMBER
) AS
BEGIN
  INSERT INTO TB_LEITURA_AMBIENTE(
    ESTACAO_ID, TEMPERATURA, RUIDO, LUMINOSIDADE, DATA_LEITURA
  ) VALUES (
    p_estacao_id, p_temp, p_ruido, p_lux, SYSTIMESTAMP
  )
  RETURNING ID INTO p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_leitura_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_LEITURA_AMBIENTE WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21501,'Leitura não encontrada');
  END IF;
END;
/

------------------------------------------------------------
-- PLANO DE AÇÃO
------------------------------------------------------------
CREATE OR REPLACE PROCEDURE pr_plano_ins(
  p_id         OUT NUMBER,
  p_equipe_id  IN  NUMBER,
  p_descricao  IN  VARCHAR2,
  p_inicio     IN  DATE,
  p_fim        IN  DATE,
  p_status     IN  VARCHAR2
) AS
BEGIN
  INSERT INTO TB_PLANO_ACAO(
    EQUIPE_ID, DESCRICAO, DATA_INICIO, DATA_FIM, STATUS
  ) VALUES (
    p_equipe_id, p_descricao, p_inicio, p_fim, p_status
  )
  RETURNING ID INTO p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_plano_upd(
  p_id         IN NUMBER,
  p_descricao  IN VARCHAR2,
  p_inicio     IN DATE,
  p_fim        IN DATE,
  p_status     IN VARCHAR2
) AS
  v_cnt NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_cnt FROM TB_PLANO_ACAO WHERE ID = p_id;
  IF v_cnt = 0 THEN
    RAISE_APPLICATION_ERROR(-21601,'Plano de ação não encontrado');
  END IF;

  UPDATE TB_PLANO_ACAO
     SET DESCRICAO  = NVL(p_descricao,DESCRICAO),
         DATA_INICIO = NVL(p_inicio,DATA_INICIO),
         DATA_FIM    = NVL(p_fim,DATA_FIM),
         STATUS      = NVL(p_status,STATUS)
   WHERE ID = p_id;
END;
/
CREATE OR REPLACE PROCEDURE pr_plano_del(
  p_id IN NUMBER
) AS
BEGIN
  DELETE FROM TB_PLANO_ACAO WHERE ID = p_id;
  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-21602,'Plano de ação não encontrado');
  END IF;
END;
/
