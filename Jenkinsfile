node {

    def mvnHome

    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://gitlab.com/Juan-Carlos-Estevez-Vargas/Check-Appointments-Spring-Boot.git'
        mvnHome = tool 'MAVEN_HOME'
    }

    stage('Validate') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" validate'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }

    stage('Compile') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean compile'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean compile/)
            }
        }
    }

    stage('Test Compile') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean test-compile'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean test-compile/)
            }
        }
    }

    stage('Test') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean test'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean test/)
            }
        }
    }

    stage('Package') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean package'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean package/)
            }
        }
    }

    stage('Integration Tests') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" integration-test'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" integration-test/)
            }
        }
    }

    stage('Install') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean install'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" clean install/)
            }
        }
    }
    
}