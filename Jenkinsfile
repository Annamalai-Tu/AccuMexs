pipeline {
    agent any
       parameters{
            separator(name : 'CTS_WEB_TESTING' , sectionHeader : 'CTS')
            booleanParam(name : 'Skip test' , defaultValue : false ,
            description : 'Click above to Skip test'
            }
    stages {
        stage('Start Selenium Grid') {
            steps {
                echo "Starting Selenium Grid with Docker Compose..."
                bat 'docker-compose down || true'
                bat 'docker-compose up -d'
                echo "Waiting for Selenium Grid to be ready..."
                retry(1) {
                    sleep(time: 10, unit: 'SECONDS')
                }
            }
        }

        stage('Checkout Code') {
            steps {
                echo 'Cloning the Git repository...'
                git branch: 'master', url: 'https://github.com/Annamalai-Tu/AccuMexs.git'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Building and Running Tests..."
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    bat "mvn clean install"
                }
            }
        }

         stage('Report') {
                    steps {
                        echo 'Archiving test reports'
                        archiveArtifacts artifacts: 'ExtentReports/**/*.zip', allowEmptyArchive: true
                    }
                }

    }
    post {
        always {
            echo "Cleaning up Selenium Grid..."
            bat 'docker-compose down'
        }
    }
}
