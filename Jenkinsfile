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
                //only create the package don't install on docker img
            }
        }
        stage('Test') {
                    steps {
                        sh 'mvn test'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
    }
}
