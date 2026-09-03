install:
	./gradlew installDist

build:
	./gradlew build

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport
	# отчёт: build/reports/jacoco/test/html/index.html

test-coverage:
	./gradlew test jacocoTestCoverageVerification

lint:
	./gradlew spotlessCheck

lint-fix:
	./gradlew spotlessApply

.PHONY: build
