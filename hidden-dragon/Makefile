all: clean

clean:
	gradle clean

compile:
	gradle build

test:
	gradle test

jar: 
	gradle build -x test
	gradle bootJar

run: jar
	echo Starting Spring at:  http://localhost:8080
	java -jar build/libs/hidden-dragon-1.0.jar

# Docker

docker-build: jar
	docker build -t hidden-dragon .
	docker images

docker-run: docker-build
	docker run --network dragon -e "MYSQL_HOST=mysql" --name hidden-dragon -td -p 8080:8080 hidden-dragon	
	docker ps

docker-clean:
	docker stop hidden-dragon
	docker rm hidden-dragon
	docker rmi hidden-dragon

docker-shell:
	docker exec -it hidden-dragon bash 

docker-push:
	docker login
	docker build -t $(account)/hidden-dragon:v2 -t $(account)/hidden-dragon:v2 .
	docker push $(account)/hidden-dragon:v2 

# Compose

network-ls:
	docker network ls 

network-create:
	docker network create --driver bridge hidden-dragon-v2_network

network-prune:
	docker network prune

compose-up:
	docker-compose up --scale gumball=2 -d

compose-down:
	docker-compose down 	

lb-stats:
	echo "user = admin | password = admin"
	open http://localhost:1936

lb-test:
	open http://localhost


# Pod

pod-run:
	kubectl apply -f pod.yaml

pod-list:
	kubectl get pods

pod-desc:
	kubectl describe pods hidden-dragon

pod-delete:
	kubectl delete -f pod.yaml

pod-shell:
	kubectl exec -it hidden-dragon -- /bin/bash

pod-logs:
	kubectl logs -f hidden-dragon

# Deployment

deployment-create:
	kubectl create -f deployment.yaml --save-config 

deployment-get:
	kubectl get deployments

deployment-get-pods:
	kubectl get pods -l name=hidden-dragon

deployment-pod-shell:
	kubectl exec -it $(pod) -- /bin/bash

deployment-upgrade:
	kubectl apply  -f deployment.yaml

deployment-delete:
	kubectl delete deployment hidden-dragon-deployment

# Service

service-create:
	kubectl create -f service.yaml

service-get:
	kubectl get services

service-get-ip:
	kubectl get service hidden-dragon -o wide

service-delete:
	kubectl delete service hidden-dragon

# Ingress

ingress-apply:
	kubectl apply -f ingress.yaml

ingress-ip:
	kubectl get ingress hidden-dragon-ingress
