## Poc KKGM

Este projeto contém as tecnologias:

- Kotlin
- Micronaut
- Gradle
- Kubernetes
- Helm
- Jaeger
- Splunk + Connector Kubernetes
- Prometheus Stack
- Kafka
- PostgreSQL

## Aplicação

Foi utilizado Kotlin + Micronaut + Gradle para aprendizado do orange stack da ZUP Innovation.

O Padrão de projeto adotado seguiu os principios Solid, DDD e Hexagonal.

O fluxo da aplicação é um CRUD que persiste dados no banco postgreSQL e busca de forma reativa.

Os logs e métricas são extraídos à partir do side car incluso pelo istioctl agregado às dependencias de observability.

## Infraestrutura

Utilizando os principios de deploy automatizado fazemos o deploy utilizando:

- Docker / Kubernetes
- Helm
- Scripts

É configurado o serviço da api Kubernetes em cima de docker e após instalamos as aplicações/operatores helm.

Também é configurado a utilização do Kustomize para atribuir os graficos do grafana.  

## Automatização

Utilizamos a lib Jib para gerar a imagem containerizada para a aplicação e enviar a mesma em seguida para o contexto
docker onde será aplicado a uma instancia do kubernetes.

É utilizado a exposição de um range de ips do docker simulando os ingresses de um ambiente em cloud na internet.
