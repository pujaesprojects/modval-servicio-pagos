
#!/bin/bash

docker run --name "eureka-server" -p 8761:8761 -d -t docker.pkg.github.com/pujaesprojects/modval-eureka-server/registry
docker run --name "modval-gateway" -p 8080:8090 --network modval -e "EUREKA_URI=http://eureka-server:8761/eureka" -d -t docker.pkg.github.com/pujaesprojects/modval-servicio-pagos/modval-gateway
docker run --name "modval-water" --network modval -e "WATER_URI=http://water-service:8080/servicios/pagos/v1" -e "EUREKA_URI=http://eureka-server:8761/eureka" -d -t docker.pkg.github.com/pujaesprojects/modval-servicio-pagos/modval-water
docker run --name "modval-gas" --network modval -e "GAS_URI=http://gas-service-soap_web-services_1:8080/gas-service/PagosService" -e "EUREKA_URI=http://eureka-server:8761/eureka" -d -t docker.pkg.github.com/pujaesprojects/modval-servicio-pagos/modval-gas