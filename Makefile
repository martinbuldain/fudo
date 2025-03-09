APP_NAME=fudo-app
DOCKER_IMAGE=$(APP_NAME):latest

d-build:
	docker build -t $(DOCKER_IMAGE) .

d-stop:
	@echo "Deteniendo el contenedor si es que está en ejecución..."
	@if docker ps -q --filter "name=$(APP_NAME)" | grep -q .; then docker stop $(APP_NAME); fi
	@echo "Eliminando el contenedor si es que existe..."
	@if docker ps -a -q --filter "name=$(APP_NAME)" | grep -q .; then docker rm $(APP_NAME); fi

d-run: d-stop
	docker run -d -p 8080:8080 --name $(APP_NAME) $(DOCKER_IMAGE)

run: d-build d-run

d-clean:
	docker rmi -f $(APP_NAME)
