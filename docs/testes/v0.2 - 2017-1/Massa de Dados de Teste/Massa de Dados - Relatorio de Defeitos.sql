INSERT INTO "PUBLIC"."FORMATOLOG"
( "ID", "NOME" )
VALUES (1, 'Common'),
(2, 'Combined');

INSERT INTO "PUBLIC"."SERVIDOR"
( "ID", "NOME", "FORMATOLOG_ID" )
VALUES ( 1, 'Apache HTTPD', 1),
(2, 'Apache HTTPD', 2);

INSERT INTO "PUBLIC"."SISTEMA"
( "ID", "NOME", "PASTALOGACESSO", "PASTALOGERRO", "PERIODICIDADELEITURA", "PREFIXOLOGACESSO", "PREFIXOLOGERRO", "PRIMEIRALEITURA", "SERVIDOR_ID" )
VALUES (1 , 'Sistema 1', '/home/webdep', '/home/webdep', 10800000, 'log_acesso', 'log_erro', '2016-11-25 23:50:00.0', 1);

INSERT INTO "PUBLIC"."REGISTROLOGERRO"
( "ID", "IP", "MENSAGEM", "NIVEL", "TIMESTAMP", "SISTEMA_ID" )
VALUES (1 , '200.9.149.88', 'PHP Notice: Undefined offset: 6 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144', ':error', '2009-01-01 00:00:00.0', 1),
(2 , '200.9.149.88', 'PHP Notice: Undefined offset: 7 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144', ':error', '2009-01-01 00:00:00.0', 1),
(3 , '10.0.0.0', 'PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancamento_funcionario.php on line 215', ':error', '2015-01-01 00:00:00.0', 1),
(4 , '10.0.0.1', 'PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancamento_funcionario.php on line 215', ':error', '2015-01-01 00:00:00.0', 1),
(5 , '10.0.0.2', 'PHP Warning: Division by zero in /var/www/html/avaliacao-dicap/modeloEP.php on line 57', ':error', '2010-01-01 00:00:00.0', 1),
(6 , '10.0.0.3', 'PHP Warning: Division by zero in /var/www/html/avaliacao-dicap/modeloEP.php on line 58', ':error', '2010-01-01 00:00:00.0', 1),
(7 , '10.0.0.4', 'PHP Notice: Undefined index: nota_chefia_1 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 89', ':error', '2013-01-01 00:00:00.0', 1),
(8 , '10.0.0.5', 'PHP Notice: Undefined index: nota_auto_1 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 90', ':error', '2013-01-01 00:00:00.0', 1),
(9 , '10.0.0.6', 'PHP Notice: Undefined index: ano in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 25', ':error', '2012-01-01 00:00:00.0', 1),
(10 , '10.0.0.8', 'PHP Notice: Undefined index: ano in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 25', ':error', '2012-01-01 00:00:00.0', 1),
(11 , '10.0.0.7', 'PHP Notice: Undefined index: next in /var/www/html/avaliacao-dicap/login_page.php on line 93', ':error', '2014-01-01 00:00:00.0', 1),
(12 , '10.0.0.10', 'PHP Notice: Undefined index: next in /var/www/html/avaliacao-dicap/login_page.php on line 93', ':error', '2014-01-01 00:00:00.0', 1),
(13 , '10.0.0.5', 'PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 90', ':error', '2016-01-01 00:00:00.0', 1),
(14 , '10.0.0.10', 'PHP Notice: Undefined offset: 8 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144', ':error', '2016-01-01 00:00:00.0', 1),
(15 , '10.0.0.7', 'PHP Notice: Undefined index: fv_3 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 91', ':error', '2017-01-01 00:00:00.0', 1),
(16 , '10.0.0.7', 'PHP Notice: Undefined index: fv_3 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 91', ':error', '2017-01-01 00:00:00.0', 1);
