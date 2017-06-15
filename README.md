# webdep
Web Dependability (WebDep)

O WebDep tem como objetivo proporcionar informações sobre a confiabilidade e robustez de sistemas web. Para isto ele monitora os sistemas web através dos seus arquivos de log do servidor de aplicação. O WebDep extrai informações sobre a confiabilidade, falhas e defeitos nos sistemas monitorados apresentando relatórios gráficos que permitem os administradores tomarem ações corretivas e preventivas sobre os defeitos em aplicações web.

O WebDep encontra-se em desenvolvimento, não contendo ainda todas as funcionalidades prontas para o monitoramento de um sistema web. Segundo nosso roadmap a primeira versão funcional do sistema deve estar pronta em Julho de 2017.
Nesta versão planejada o WebDep suportará somente o monitoramento de aplicações que executam no servidor de aplicação Apache HTTPD. Futuramente pretendemos incluir outros servidores de aplicação como o Apache Tomcat e o Nigx.

Pré-requisitos para instalação:
1) Ter instalado o git 
2) Ter instalado o Apache Tomcat 9
3) Ter instalado o maven
4) Ter instalado a JRE (Java) versão 8 ou superior
5) Estar conectado com a Internet (para baixar as dependências do maven)
6) Ter a linguagem R instalada, com o pacote Rserve e o Rserve estar rodando (para gerar os gráficos)

Instruções para instalação do sistema:

1) Faça download do sistema em uma pasta (preferencialmente utilizando o comando do git no terminal: git clone https://github.com/diogosmendonca/webdep.git. Se já tiver baixado anteriormente e quiser somente atualizar utilize o comando git pull. 
2) Utilize o seguinte comando do maven para transformar o charset dos arquivos de mensages de UTF-8 para Ascii. mvn -B org.codehaus.mojo:native2ascii-maven-plugin:native2ascii
3) Utilize o maven para gerar o arquivo webdep.war através da execução do seguinte comando na raiz do projeto: mvn install. O arquivo webdep.war estará na pasta target.
4) Inicie o banco de dados embutido executando o arquivo src/main/resources/run_database.sh no linux ou src/main/resources/run_database.bat no windows.
5) Faça deploy da aplicação no tomcat ou outro servidor de aplicação JEE. No tomcat basta mover o arquivo webdep.war para a pasta webapp dentro da instalação do tomcat.
6) Inicie o servidor de aplicação (dentro da pasta do Tomcat execute o arquivo bin/startup.bat ou bin/startup.sh) e acesse o sistema pela url http://localhost:8080/webdep/
7) Faça login com o usuário admin e senha admin.

O sistema vem configurado para executar em um banco de dados HSQLDB local. É possível acessar diretamente este banco de dados seguindo as instruções abaixo:

Executando o aplicação de administração de banco de dados:
1) Inicie o banco de dados embutido executando o arquivo src/main/resources/run_database.sh no linux ou src/main/resources/run_database.bat no windows.
2) Execute o arquivo src/main/resources/run_gerenciador.sh no linux ou src/main/resources/run_gerenciador.bat no windows.
3) Na tela do gerenciador selecione no campo tipo o valor HSQL Database Server Engine e preencha a URL com o seguinte valor jdbc:hsqldb:hsql://localhost/WebDep, clique em OK. Deve abrir o gerenciador com as tabelas do sistema carregadas.

Resolvendo o problema de charset.

A aplicação webdep está configurada para ser desenvolvida e executar utilizando o charset UTF-8. Contudo, o Java espera que os arquivos de mensagens do i18n sejam escritos em ISO8859-1, e, no webdep eles estão escritos em UTF-8. Para resolver este problema foi adicionado um plugin do maven no pom.xml que faz a conversão dos arquivos .properties de UTF-8 para Ascii (que não apresenta problemas de charset) e move eles da pasta src/main/resources para src/target/classes. Para a conversão funcionar em desenvolvimento a cada modificação nos arquivos .properties deverá ser executado o comando do maven (mvn -B org.codehaus.mojo:native2ascii-maven-plugin:native2ascii) para os arquivos serem convertidos. É possível configurar o eclipse para rodar este comando clicando sobre o projeto, clicando na opção Run As -> Maven Build ... e lá dentro configurando o campo goal com o valor org.codehaus.mojo:native2ascii-maven-plugin:native2ascii .
