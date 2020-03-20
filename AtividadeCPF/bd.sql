CREATE DATABASE corona1
GO
USE corona1

CREATE TABLE pessoa(
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(200) NOT NULL
PRIMARY KEY (cpf)
)

CREATE PROCEDURE sp_validaCPF(@cpf VARCHAR(11), @saida VARCHAR(MAX) OUTPUT)
AS
	IF (LEN(@cpf) != 11)
	BEGIN
		RAISERROR('CPF inválido',16,1)
	END
	ELSE
	BEGIN 
		DECLARE @cont INT,
				@digito VARCHAR(1),
				@resp INT,
				@soma INT,
				@digito1 INT,
				@digito2 INT
		  
		SET @digito = SUBSTRING(@cpf,1,1)

		SET @cont = 1
		SET @resp = 1

		WHILE (@cont <= 11)
		BEGIN
			IF (SUBSTRING(@cpf,@cont,1) <> @digito)
			BEGIN
				SET @resp = 0
			END
			SET @cont = @cont + 1
		END

		IF (@resp = 0)
		BEGIN
			SET @cont = 1
			SET @soma = 0

			WHILE (@cont <= 9)
			BEGIN
				SET @soma = @soma +( convert(INT,substring(@cpf, @cont,1)) * (11 - @cont))
				SET @cont = @cont + 1
			END
	
			SET @digito1 = 11 - (@soma % 11)

			IF (@digito1 > 9)
			BEGIN
				SET @digito1 = 0
			END

			SET @cont = 1
			SET @soma = 0

			WHILE (@cont <= 10)
			BEGIN
				SET @soma = @soma + (convert(INT,substring(@cpf, @cont,1)) * (12 - @cont))
				SET @cont = @cont + 1
			END
		
			SET @digito2 = 11 - (@soma % 11)

			IF (@digito2 > 9)
			BEGIN
				SET @digito2 = 0
			END

			IF(@digito1 = SUBSTRING(@cpf,LEN(@cpf)-1,1)) AND (@digito2 = SUBSTRING(@cpf,LEN(@cpf),1))
			BEGIN
				SET @saida = 'CPF Válido'
			END
			ELSE
			BEGIN
				SET @saida = 'CPF Inválido'
			END
		END
		ELSE
		BEGIN
			RAISERROR('Todos os dígitos são idênticos',16,1)
		END
	END


CREATE PROCEDURE sp_Cadastrar(@cpf VARCHAR(11),@nome VARCHAR(200), @saida VARCHAR(MAX) OUTPUT)
AS
	DECLARE  @out VARCHAR(MAX)

	EXEC sp_validaCPF @cpf, @out OUTPUT

	IF(@out = 'CPF Válido')
	BEGIN
		INSERT INTO pessoa VALUES (@cpf,@nome)
		SET @saida = 'Cadastrado com sucesso!'
	END
	ELSE
	BEGIN
		SET @saida = 'Erro'
	END
