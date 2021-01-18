# Votação Doc

### Config rabbitmq

É preciso que tenha o servidor do rabbitmq on para que as mensagens possam ser postadas ou lidas eventualmente! 

### Caso não tenha e tenha o Docker instalado na máquina, basta rodar o comando: 

`docker run -d -p 5672:5672 -p 15672:15672 --name=rabbitmq rabbitmq:3.8.3-management`

### Project Lombok

- Caso não tenha o plugin do Lombok na ferramenta, basta baixa [aqui](https://projectlombok.org/download)
- Em seguida, caso ao executar o jar do lombok, basta seguir os passos informados para a instalação do plugin 
- Passo de instalação de cada IDE, está em `https://projectlombok.org/setup/eclipse -> Menu Install -> IDEs`

### A documentação da api encontra-se em: 

`http://localhost:8080/api/swagger-ui.html`