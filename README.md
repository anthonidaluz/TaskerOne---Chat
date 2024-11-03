# TaskerOne

ChatMulticast é um aplicativo de chat em grupo usando sockets multicast em Java. Ele permite que os usuários enviem e recebam mensagens em uma rede multicast, atualizando-as em uma interface gráfica (Swing).

## Funcionalidades

- Envio e recebimento de mensagens multicast.
- Interface gráfica (GUI) para exibir mensagens em tempo real.
- Utilização de JSON para formatação das mensagens enviadas e recebidas.

## Estrutura do Projeto

```plaintext
ChatMulticast/
├── ChatMulticast.java         # Classe principal, inicializa a GUI
├── ChatMulticastForm.java     # Interface gráfica do chat
├── Connection.java            # Classe responsável por receber e exibir mensagens
├── README.md                  # Documentação do projeto
