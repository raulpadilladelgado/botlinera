-include .env
export

# Get the absolute path to the running Makefile
ROOT_DIR := $(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))

# Colours
BLUE:=			\033[0;34m
RED:=			\033[0;31m
LIGHT_RED:=		\033[1;31m
WHITE:=			\033[1;37m
LIGHT_VIOLET := \033[1;35m
NO_COLOUR := 	\033[0m

# Environment : { dev, staging, prod }
ENV := dev

PROJECT_NAME := botlinera

MSG_SEPARATOR := "*********************************************************************"
MSG_IDENT := "    "


.SILENT:

help:
	echo "\n${MSG_SEPARATOR}\n$(LIGHT_VIOLET)$(PROJECT_NAME)$(NO_COLOUR)\n${MSG_SEPARATOR}\n"
	echo "${MSG_IDENT}=======   ✨  BASIC   =====================================\n   "
	echo "${MSG_IDENT}  ⚠️   Requirements : Java 17 \n"
	echo "${MSG_IDENT}  clean                   -  🚮  Erase the 📁 build/"
	echo "${MSG_IDENT}  test                    -  ✅  Run Unit tests"
	echo "${MSG_IDENT}  run                     -  🚀  Run the app with profile '${ENV}'"
	echo
	echo "${MSG_IDENT}=======   🐳  DOCKER   =====================================\n"
	echo "${MSG_IDENT}  ℹ️   To work with $(PROJECT_NAME) running alone in a container"
	echo "${MSG_IDENT}  ⚠️   Requirements : docker \n"
	echo "${MSG_IDENT}  dk-build                -  📦  Build a docker image with the .jar"
	echo "${MSG_IDENT}  up                      -  🚀  Start container"
	echo "${MSG_IDENT}  down                    -  🛑  Stop container"
	echo "${MSG_IDENT}  restart                 -  ♻️  Rebuild the application and launch app"
	echo

######################################################################
########################   BASIC    #################################
######################################################################

clean:
	echo "\n\n${MSG_SEPARATOR}\n\n CLEAN => 🚮  Erase the 📁 build/\n\n${MSG_SEPARATOR}\n\n"

	./gradlew clean

test: clean
	./gradlew test

run: clean
	./gradlew run


######################################################################
########################   🐳 DOCKER    ##############################
######################################################################

dk-build: test
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 dk-build => Building the docker dev environment ...\n\n${MSG_SEPARATOR}\n\n"

	docker-compose -f docker/docker-compose.dev.yml build  --force-rm

dk-build-SkipTest:
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 dk-build - ${RED}skip tests${NO_COLOUR} => Building the docker image with name ${DOCKER_IMAGE_NAME} ...\n\n${MSG_SEPARATOR}\n\n"

	docker-compose -f docker/docker-compose.dev.yml build  --force-rm

up:
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 up => 🚀  Start container \n\n${MSG_SEPARATOR}\n\n"

	docker-compose -f docker/docker-compose.dev.yml up -d

down:
	echo "\n\n${MSG_SEPARATOR}\n\n 🐳 down => 🚀  Stop container \n\n${MSG_SEPARATOR}\n\n"

	-docker-compose -f docker/docker-compose.dev.yml down --remove-orphans

restart: down dk-build-SkipTest up
