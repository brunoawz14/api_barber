-- Reconciles columns that may have been created by Hibernate while
-- spring.jpa.hibernate.ddl-auto was configured as update.

SET @has_status_column = (
    SELECT COUNT(*)
    FROM information_schema.columns
    WHERE table_schema = DATABASE()
      AND table_name = 'servicos'
      AND column_name = 'status'
);

SET @copy_status_sql = IF(
    @has_status_column > 0,
    'UPDATE servicos SET ativo = COALESCE(status, ativo) WHERE status IS NOT NULL',
    'SELECT 1'
);
PREPARE migration_statement FROM @copy_status_sql;
EXECUTE migration_statement;
DEALLOCATE PREPARE migration_statement;

SET @drop_status_sql = IF(
    @has_status_column > 0,
    'ALTER TABLE servicos DROP COLUMN status',
    'SELECT 1'
);
PREPARE migration_statement FROM @drop_status_sql;
EXECUTE migration_statement;
DEALLOCATE PREPARE migration_statement;

SET @has_legacy_duration_column = (
    SELECT COUNT(*)
    FROM information_schema.columns
    WHERE table_schema = DATABASE()
      AND table_name = 'servicos'
      AND column_name = 'duracao_em_minutos'
);

SET @copy_duration_sql = IF(
    @has_legacy_duration_column > 0,
    'UPDATE servicos SET duracao_minutos = duracao_em_minutos WHERE duracao_em_minutos IS NOT NULL',
    'SELECT 1'
);
PREPARE migration_statement FROM @copy_duration_sql;
EXECUTE migration_statement;
DEALLOCATE PREPARE migration_statement;

SET @drop_duration_sql = IF(
    @has_legacy_duration_column > 0,
    'ALTER TABLE servicos DROP COLUMN duracao_em_minutos',
    'SELECT 1'
);
PREPARE migration_statement FROM @drop_duration_sql;
EXECUTE migration_statement;
DEALLOCATE PREPARE migration_statement;

ALTER TABLE clientes
    MODIFY nome VARCHAR(100) NOT NULL,
    MODIFY telefone VARCHAR(20) NOT NULL,
    MODIFY email VARCHAR(150) NULL;

ALTER TABLE servicos
    MODIFY nome VARCHAR(100) NOT NULL,
    MODIFY descricao VARCHAR(255) NULL,
    MODIFY preco DECIMAL(10, 2) NOT NULL,
    MODIFY duracao_minutos INT NOT NULL,
    MODIFY ativo BOOLEAN NOT NULL DEFAULT TRUE;
