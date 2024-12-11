pipeline {
    agent any
    environment {
        SELENIUM_GRID_URL = 'http://localhost:4444/wd/hub'
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
                    bat "mvn clean install
                }
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
