DELETE FROM "PUBLIC"."PADRAOURL";
INSERT INTO "PUBLIC"."PADRAOURL"
( "ID", "EXPRESSAOREGULAR", "NOME", "USUARIO_ID" )
VALUES (1, '.*', 'Todas as URLs', 1);
INSERT INTO "PUBLIC"."PADRAOURL"
( "ID", "EXPRESSAOREGULAR", "NOME", "USUARIO_ID" )
VALUES (2, '.*?[.]jsp', 'Somente Jsps', 1);

DELETE FROM "PUBLIC"."VERSAO";
INSERT INTO "PUBLIC"."VERSAO"
( "ID", "NOME", "TIMESTAMPLIBERACAO", "SISTEMA_ID" )
VALUES (1, 'V0.0.1', '2017-04-21 12:00:00.0', 2);

INSERT INTO "PUBLIC"."VERSAO"
( "ID", "NOME", "TIMESTAMPLIBERACAO", "SISTEMA_ID" )
VALUES (2, 'V0.0.2', '2017-05-10 12:00:00.0', 2);

INSERT INTO "PUBLIC"."VERSAO"
( "ID", "NOME", "TIMESTAMPLIBERACAO", "SISTEMA_ID" )
VALUES (13, 'V0.1', '2017-05-23 12:00:00.0', 2);


DELETE FROM "PUBLIC"."REGISTROLOGACESSO";
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (1, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-23 12:00:00.0', 'http://localhost:8080/webdep/index.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (2, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-25 12:00:00.0', 'http://localhost:8080/webdep/index.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (3, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-20 12:00:00.0', 'http://localhost:8080/webdep/index.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (4, 'teste',10 ,500 , '192.168.0.1', 'origem', '2017-04-21 12:00:00.0', 'http://localhost:8080/webdep/index.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (5, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-06-21 12:00:00.0', 'http://localhost:8080/webdep/index.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (6, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-14 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (7, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-15 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (8, 'teste',10 ,500 , '192.168.0.1', 'origem', '2017-05-16 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (9, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-09 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (10, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-10 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (11, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-11 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (12, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-19 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (13, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-20 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (14, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-21 12:00:00.0', 'http://localhost:8080/webdep/config.jsp', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (15, 'teste',10 ,404 , '192.168.0.1', 'origem', '2017-05-21 12:00:00.0', 'http://localhost:8080/webdep/config.html', 'joao', 2);
INSERT INTO "PUBLIC"."REGISTROLOGACESSO"
( "ID", "AGENTE", "BYTES", "CODIGO", "IP", "ORIGEM", "TIMESTAMP", "URL", "USUARIO", "SISTEMA_ID" )
VALUES (16, 'teste',10 ,200 , '192.168.0.1', 'origem', '2017-05-23 12:00:00.0', 'http://localhost:8080/webdep/config.php', 'joao', 2);
