kubernetes-install:
	sudo chown ${shell whoami} /var/run/docker.sock
	sudo curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.11.1/kind-linux-amd64
	sudo chmod +x ./kind
	sudo mv ./kind /usr/local/bin/kind
	kind create cluster
	kubectl apply -f ./dashboard.yaml
	kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/master/manifests/namespace.yaml
	kubectl create secret generic -n metallb-system memberlist --from-literal=secretkey="$(openssl rand -base64 128)"
	kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/master/manifests/metallb.yaml
	kubectl apply -f https://kind.sigs.k8s.io/examples/loadbalancer/metallb-configmap.yaml
	make kubernetes-expose-services
	zsh

kubernetes-expose-services:
	sudo ip route add 172.19.0.0/16 via ${shell ip a s docker0 | egrep -o 'inet [0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' | cut -d' ' -f2}

kubernetes-dashboard:
	kubectl get secret -n kubernetes-dashboard $(kubectl get serviceaccount admin-user -n kubernetes-dashboard -o jsonpath="{.secrets[0].name}") -o jsonpath="{.data.token}" | base64 --decode

helm-install:
	curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash
	helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
	helm repo add bitnami https://charts.bitnami.com/bitnami
	zsh

istio-install:
	curl -L https://istio.io/downloadIstio | sh -
	sudo mv istio*/bin/istioctl /usr/local/bin/
	rm -rf istio*
	istioctl install -y

istio-helm-install:
	kubectl create namespace istio-system
	helm install istio-base manifests/charts/base -n istio-system
	helm install istiod manifests/charts/istio-control/istio-discovery -n istio-system
	helm install istio-ingress manifests/charts/gateways/istio-ingress -n istio-system
	helm install istio-egress manifests/charts/gateways/istio-egress -n istio-system
	kubectl get pods -n istio-system

istio-helm-rm:
	helm delete istio-egress -n istio-system
	helm delete istio-ingress -n istio-system
	helm delete istiod -n istio-system
	helm delete istio-base -n istio-system
	kubectl delete namespace istio-system

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

prometheus-helm-install:
	kubectl kustomize | kubectl apply -f -
	helm install prometheus-stack -f helm/prometheus-operator/values.yaml prometheus-community/kube-prometheus-stack

prometheus-helm-upgrade:
	kubectl kustomize | kubectl apply -f -
	helm upgrade prometheus-stack -f helm/prometheus-operator/values.yaml prometheus-community/kube-prometheus-stack

prometheus-helm-rm:
	helm uninstall prometheus-stack
	kubectl delete crd alertmanagerconfigs.monitoring.coreos.com
	kubectl delete crd alertmanagers.monitoring.coreos.com
	kubectl delete crd podmonitors.monitoring.coreos.com
	kubectl delete crd probes.monitoring.coreos.com
	kubectl delete crd prometheuses.monitoring.coreos.com
	kubectl delete crd prometheusrules.monitoring.coreos.com
	kubectl delete crd servicemonitors.monitoring.coreos.com
	kubectl delete crd thanosrulers.monitoring.coreos.com

splunk-helm-install:
	helm install splunk-connector -f helm/splunk-connector/values.yaml splunk/splunk-connect-for-kubernetes
	helm package helm/splunk/splunk-operator -d helm/splunk/splunk-operator/
	kubectl apply -f helm/splunk/custom-resources.yaml
	helm install splunkoperator helm/splunk/splunk-operator/splunk-operator-0.1.0.tgz -f helm/splunk/splunk-operator/values.yaml
	kubectl get secret splunk-s1-standalone-secret-v1 -o jsonpath='{.data.password}' | base64 -d

splunk-helm-uninstall:
	helm uninstall splunk-connector
	helm uninstall splunkoperator
	kubectl delete -f helm/splunk/custom-resources.yaml

splunk-helm-reinstall:
	make splunk-helm-uninstall
	make splunk-helm-install

helm-instalations:
	make postgres-helm-install
	make kafka-helm-install
	make prometheus-helm-install

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

get-secrets:
	 kubectl get secret prometheus-stack-grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo " - Grafana admin passwd"

create-table:
	curl kubernets:8080/save --header "Content-Type:application/json" -X POST --data-raw '{"id": "", "name": "created from makefile", "reference": []}' -vvv

create-tables:
	curl kubernets:8080/save/all --header "Content-Type:application/json" -X POST -d '[{"id": "", "name": "created from makefile0", "reference": []},{"id": "", "name": "created from makefile1", "reference": []},{"id": "", "name": "created from makefile2", "reference": []}]' -vvv
