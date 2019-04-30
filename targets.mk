##################################################
# Your Project's Targets                         #
##################################################

clean:
	$(SBT) $(SBT_FLAGS) clean

print-gav:
	@{ $(SBT) $(SBT_FLAGS) "set aggregate := false" organization name | tail -n2 ; $(SBT) $(SBT_FLAGS) version | tail -n 1; } \
	|  gawk 'match($$0, /.*?\s(.*)/, ary) {print ary[1]}' | paste -sd ":" -


prepare-agent:
	@ln -s /usr/.m2 /home/build/.m2

#############################################################################
# SBT SETUP                                                                 #
#                                                                           #
# For each of the make targets that correspond to a Maven lifecycle phase:  #
# 1. Define which sbt command phase this target corresponds to              #
# 2. Set any other sbt properties needed by that command                    #
#############################################################################

build: SBT_COMMAND=+compile

install: SBT_COMMAND=+publishLocal

publish: SBT_COMMAND=+publish

#############################################################################
# MAVEN EXECUTION                                                           #
#                                                                           #
# For each of the make targets that correspond to a Maven lifecycle phase:  #
# 1. Replace the MAVEN_SKIP_TESTS value with whatever our current value is. #
# 2. If this is the last of the maven targets and we've finished building   #
#    our maven command then we want to execute maven at the last phase.     #
#############################################################################
$(MAVEN_LIFECYCLE_TARGETS):
	@if [ "x$(LAST_MAVEN_LIFECYCLE_TARGET)" = "x$@" ]; then \
	if [[ "$(REQUESTED_MAVEN_LIFECYCLE_TARGETS)" == *"test"* ]]; then \
		echo "$(SBT) $(SBT_FLAGS) test $(SBT_COMMAND)"; \
		$(SBT) $(SBT_FLAGS) test $(SBT_COMMAND); \
	else \
		echo "$(SBT) $(SBT_FLAGS) 'set test := {}' $(SBT_COMMAND)"; \
		$(SBT) $(SBT_FLAGS) 'set test := {}' $(SBT_COMMAND); \
	fi; \
	fi

#####
# Extra Targets
#####

.PHONY: sonar

# Analyze the project with Sonar & publish the results to HomeAway's Sonar database.
sonar:
	echo 'Sonar analysis is not implemented'; \
    exit 1;