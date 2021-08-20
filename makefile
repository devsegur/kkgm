
kubernetes-install:
	sudo chown evertonfs /var/run/docker.sock
	sudo curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.11.1/kind-linux-amd64
	sudo chmod +x ./kind
	sudo mv ./kind /usr/local/bin/kind
	kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/master/manifests/namespace.yaml
	kubectl create secret generic -n metallb-system memberlist --from-literal=secretkey="$(openssl rand -base64 128)"
	kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/master/manifests/metallb.yaml
	kubectl apply -f https://kind.sigs.k8s.io/examples/loadbalancer/metallb-configmap.yaml
	make kubernetes-expose-services
	zsh

kubernetes-expose-services:
	sudo ip route add 172.19.0.0/16 via 172.18.0.1

helm-install:
	curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash
	zsh

postgres-helm-add:
	helm repo add bitnami https://charts.bitnami.com/bitnami

postgres-helm-install:
	helm install postgresql -f helm/postgresql/values.yaml bitnami/postgresql

postgres-rm:
	helm uninstall postgresql

postgres-restart:
	make postgres-rm;
	make postgres-helm-install;

kafka-helm-install:
	helm install kafka -f helm/kafka/values.yaml bitnami/kafka;

kafka-rm:
	helm uninstall kafka;

kafka-restart:
	make kafka-rm;
	make kafka-helm-install;

build-application:
	./gradlew clean build --continue --parallel

create-image:
	./gradlew jibDockerBuild
	kind load docker-image kotlin-project:1.0-SNAPSHOT

deploy-image:
	kubectl delete -f k8s.yml
	kubectl apply -f k8s.yml

build-create-deploy:
	make build-application;
	make create-image;
	make deploy-image;
