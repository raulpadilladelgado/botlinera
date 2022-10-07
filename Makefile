-include .env
export

# Colours
BLUE:=			\033[0;34m
RED:=			\033[0;31m
LIGHT_RED:=		\033[1;31m
WHITE:=			\033[1;37m
LIGHT_VIOLET := \033[1;35m
NO_COLOUR := 	\033[0m

PROJECT_NAME := botlinera

MSG_SEPARATOR := "*********************************************************************"
MSG_IDENT := "    "


.SILENT:

help:
	echo "\n${MSG_SEPARATOR}\n$(BLUE)$(PROJECT_NAME)$(NO_COLOUR)\n${MSG_SEPARATOR}\n"
	echo "${MSG_IDENT}=======   ✨  BASIC   =====================================\n   "
	echo "${MSG_IDENT}  ⚠️   Requirements : Java 17 \n"
	echo "${MSG_IDENT}  clean                   -  🚮  Erase the 📁 build/"
	echo "${MSG_IDENT}  test                    -  ✅  Run Unit tests"
	echo
	echo "${MSG_IDENT}=======   🐳  DOCKER   =====================================\n"
	echo "${MSG_IDENT}  ℹ️   To work with $(PROJECT_NAME) running alone in a container"
	echo "${MSG_IDENT}  ⚠️   Requirements : docker \n"
	echo "${MSG_IDENT}  up                      -  🚀  Start container"
	echo "${MSG_IDENT}  build                   -  📦  Build package"
	echo "${MSG_IDENT}  down                    -  🛑  Stop container"
	echo "${MSG_IDENT}  restart                 -  ♻️   Rebuild the application and launch app"
	echo "${MSG_IDENT}  logs                    -  📄  Show application logs"
	echo

######################################################################
########################   BASIC    #################################
######################################################################

clean:
	echo "\n\n${MSG_SEPARATOR}\n\n CLEAN => 🚮  Erase the 📁 build/\n\n${MSG_SEPARATOR}\n\n"

	./gradlew clean

test: clean
	./gradlew test

######################################################################
########################   🐳 DOCKER    ##############################
######################################################################

up:
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 up => 🚀  Start container \n\n${MSG_SEPARATOR}\n\n"

	docker-compose -f docker/docker-compose.dev.yml up -d --build

jar:
	echo "\n\n${MSG_SEPARATOR}\n\n 📦 build => 🚀  Build package \n\n${MSG_SEPARATOR}\n\n"

	./gradlew shadowJar

down:
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 down => 🚀  Stop container \n\n${MSG_SEPARATOR}\n\n"

	docker-compose -f docker/docker-compose.dev.yml down --remove-orphans

restart: down up

logs:
	-docker logs botlinera
