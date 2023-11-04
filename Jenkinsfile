pipeline {
    agent any
    environment {
        // Définissez ici le mot de passe de votre serveur SonarQube comme variable d'environnement sécurisée
        SONARQUBE_PASSWORD = credentials('password1P@')
    }
    stages {
        stage('Dev') {
            steps {
                script {
                    def date = new Date()
                    def datePart = date.format("dd/MM/yyyy")
                    def timePart = date.format("HH:mm:ss")
                    echo "datePart : ${datePart}\ttimePart : ${timePart}"
                   
                }
            }
        }
        stage('GIT'){
            steps {
                echo "Getting Project from Git";
            }
        }
        stage('MVN CLEAN'){
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN COMPILE'){
            steps {
                sh 'mvn compile'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool name: 'SonarQube', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    withSonarQubeEnv('SonarQube') {
                        // Utilisez la variable d'environnement sécurisée pour le mot de passe
                        sh "mvn sonar:sonar -Dsonar.login=${env.SONARQUBE_PASSWORD}"
                    }
                }
            }
        }
    }
    post {
        success {
            echo 'The pipeline has succeeded!'
        }
        failure {
            echo 'The pipeline has failed!'
        }
    }
}
