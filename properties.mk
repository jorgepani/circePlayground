##################################################
# Your Project's Build Agent Properties          #
##################################################
BUILD_AGENT=dr.homeawaycorp.com/mts/sbt-build-agent:0
DOCKER_FLAGS=-v /etc/localtime:/etc/localtime:ro \
	-v "$(M2_REPO)/":/usr/.m2 \
	-v "$(HOME)/.ssh":/home/build/.ssh \
	-v "$(HOME)/.ivy2":/home/build/.ivy2 \
	-e CI=$(CI) \
	-e SONAR_USER=$(SONAR_USER) \
	-e SONAR_PASS=$(SONAR_PASS)

AGENT_COMMAND_PREFIX=build-agent

ifndef HOME
HOME=$(shell echo $$HOME)
endif

##################################################
# M2_REPO setup
#   M2_REPO is reqired by the sbt build as the
#   nexus credentials are stored in the maven
#   settings.xml
##################################################

ifndef M2_REPO
M2_REPO=$(shell echo $$M2_REPO)
endif

ifeq ("$(M2_REPO)", "")
M2_REPO=$(HOME)/.m2
endif

SBT=sbt

SBT_FLAGS=-Dsbt.log.format=false -Dsbt.override.build.repos=true -Dsbt.repository.config=/home/build/.sbt/repositories

##################################################
# Maven Lifecycle Setup
#  The make commands this make file implements
#  are based on the maven lifecycle stages
#  hence the references to maven.
#  This is part of the ha containerized build
#  pattern, for details see
#  https://github.wvrgroup.internal/DevTools/Makefile
#  The maven commands are translated to sbt in
#  targets.mk
##################################################
MAVEN_LIFECYCLE_TARGETS=build test install publish
REQUESTED_MAVEN_LIFECYCLE_TARGETS=$(filter $(MAVEN_LIFECYCLE_TARGETS), $@ $(MAKECMDGOALS))
LAST_MAVEN_LIFECYCLE_TARGET=$(lastword $(REQUESTED_MAVEN_LIFECYCLE_TARGETS))
