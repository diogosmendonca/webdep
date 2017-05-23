# webdep
Web Dependability (WebDep)

O WebDep tem como objetivo proporcionar informações sobre a confiabilidade e robustez de sistemas web. Para isto ele monitora os sistemas web através dos seus arquivos de log do servidor de aplicação. O WebDep extrai informações sobre a confiabilidade, falhas e defeitos nos sistemas monitorados apresentando relatórios gráficos que permitem os administradores tomarem ações corretivas e preventivas sobre os defeitos em aplicações web.

O WebDep encontra-se em desenvolvimento, não contendo ainda todas as funcionalidades prontas para o monitoramento de um sistema web. Segundo nosso roadmap a primeira versão funcional do sistema deve estar pronta em Julho de 2017.
Nesta versão planejada o WebDep suportará somente o monitoramento de aplicações que executam no servidor de aplicação Apache HTTPD. Futuramente pretendemos incluir outros servidores de aplicação como o Apache Tomcat e o Nigx.

Instruções para instalação do sistema:

1) Faça download do sistema em uma pasta
2) Inicie o banco de dados embutido executando o arquivo src/main/resources/run_database.sh no linux ou src/main/resources/run_database.bat no windows.
3) Utilize o maven para gerar o arquivo webdep.war através da execução do seguinte comando na raiz do projeto: mvn install. O arquivo webdep.war estará na pasta target.
4) Faça deploy da aplicação no tomcat ou outro servidor de aplicação JEE. No tomcat basta mover o arquivo webdep.war para a pasta webapp dentro da instalação do tomcat.
5) Inicie o servidor de aplicação (Tomcat ou outro) e acesse o sistema pela url http://localhost:8080/webdep/
6) Faça login com o usuário admin e senha admin.

