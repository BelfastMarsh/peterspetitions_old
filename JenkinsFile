pipeline {
    agent any
    options { disableConcurrentBuilds(abortPrevious: true) }
    stages {
        stage ('GetProject') {
            steps {
                git 'https://github.com/BelfastMarsh/ct5717_SpringBoot2.git'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean:clean'
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
            }
        }
        stage ('Exec') {
            steps {
                sh 'mvn exec:java'
            }
        }
    }
}