pipeline {
    agent {
            docker {
                image 'maven:3.8.3' //linux machine with maven installed
                args '-v /root/.m2:/root/.m2' //save the dependencies for next run
            }
        }
    stages {
        stage('build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}
