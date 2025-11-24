------------------------------------------------------------
-- FUNÇÃO 1: Média de humor por equipe em um intervalo de datas
------------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_media_humor_equipe(
  p_equipe_id IN NUMBER,
  p_data_ini  IN DATE,
  p_data_fim  IN DATE
) RETURN NUMBER AS
  v_media NUMBER;
BEGIN
  SELECT AVG(c.HUMOR)
    INTO v_media
    FROM TB_CHECKIN c
    JOIN TB_USUARIO u ON u.ID = c.USUARIO_ID
   WHERE u.EQUIPE_ID = p_equipe_id
     AND c.DATA_HORA BETWEEN p_data_ini AND p_data_fim;

  RETURN v_media;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN NULL;
END;
/
-- Exemplo:
-- SELECT fn_media_humor_equipe(1, DATE '2025-01-01', DATE '2025-12-31') FROM DUAL;
------------------------------------------------------------

------------------------------------------------------------
-- FUNÇÃO 2: Índice de risco da equipe (BAIXO / MEDIO / ALTO / SEM_DADOS)
------------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_indice_risco_equipe(
  p_equipe_id IN NUMBER
) RETURN VARCHAR2 AS
  v_humor    NUMBER;
  v_estresse NUMBER;
BEGIN
  SELECT AVG(c.HUMOR), AVG(c.NIVEL_ESTRESSE)
    INTO v_humor, v_estresse
    FROM TB_CHECKIN c
    JOIN TB_USUARIO u ON u.ID = c.USUARIO_ID
   WHERE u.EQUIPE_ID = p_equipe_id;

  IF v_humor IS NULL OR v_estresse IS NULL THEN
    RETURN 'SEM_DADOS';
  ELSIF v_humor < 2 OR v_estresse > 7 THEN
    RETURN 'ALTO';
  ELSIF v_humor < 3 OR v_estresse > 5 THEN
    RETURN 'MEDIO';
  ELSE
    RETURN 'BAIXO';
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 'SEM_DADOS';
END;
/
-- Exemplo:
-- SELECT fn_indice_risco_equipe(1) FROM DUAL;
------------------------------------------------------------

------------------------------------------------------------
-- FUNÇÃO 3: Verifica se o usuário já fez check-in hoje
-- Retorna 'SIM' se pode fazer (ainda não fez), 'NAO' se já existe check-in no dia
------------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_pode_fazer_checkin(
  p_usuario_id IN NUMBER
) RETURN VARCHAR2 AS
  v_count NUMBER;
BEGIN
  SELECT COUNT(*)
    INTO v_count
    FROM TB_CHECKIN
   WHERE USUARIO_ID = p_usuario_id
     AND TRUNC(DATA_HORA) = TRUNC(SYSDATE);

  IF v_count > 0 THEN
    RETURN 'NAO';
  ELSE
    RETURN 'SIM';
  END IF;
END;
/
-- Exemplo:
-- SELECT fn_pode_fazer_checkin(1) FROM DUAL;
------------------------------------------------------------
