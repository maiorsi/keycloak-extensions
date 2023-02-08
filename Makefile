build:
	@echo "Building..."
	mvn install --file keycloak-extensions/pom.xml

check:
	@echo "Checking..."
	mvn spotless:check checkstyle:check --file keycloak-extensions/pom.xml

fix:
	@echo "Fixing..."
	mvn rewrite:run spotless:apply --file keycloak-extensions/pom.xml

clean:
	@echo "Cleaning..."
	mvn clean --file keycloak-extensions/pom.xml

test:
	@echo "Testing..."
	mvn test --file keycloak-extensions/pom.xml